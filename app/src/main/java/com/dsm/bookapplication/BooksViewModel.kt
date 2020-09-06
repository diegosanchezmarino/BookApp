package com.dsm.bookapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.bookapplication.model.Book
import com.dsm.bookapplication.model.Resource
import com.dsm.bookapplication.model.Ticker
import com.dsm.bookapplication.network.Repository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel: ViewModel(){

    var repository = Repository()
    
    var booksRequest: MutableLiveData<Resource<List<Book>>> = MutableLiveData()


    var selectedBook: MutableLiveData<Book> = MutableLiveData()



    fun selectBook(book: Book?){
        selectedBook.postValue(book)
    }
    
    
    fun getBooks(){
        booksRequest.postValue(Resource.Loading())
        repository.getBooks(object : Callback<List<Book>> {
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                booksRequest.postValue(Resource.Error(""))
            }

            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {

                var bookList = response.body() as List<Book>

                var tickerRequests: ArrayList<Deferred<Response<Ticker>>> = ArrayList()

                viewModelScope.launch {
                    bookList.forEach { book ->
                        tickerRequests.add(async { repository.getTicker(bookName = book.name) })
                    }

                    tickerRequests.forEachIndexed { index, tickerRequest ->
                        Log.v("BooksViewModel", "Ticker: " + tickerRequest.await().body()?.bookName)
                        bookList[index].ticker = tickerRequest.await().body()
                    }
                    booksRequest.postValue(Resource.Success(bookList))
                }
            }

        })
        
    }

    fun updateSelectedTicker(){
        booksRequest.postValue(Resource.Loading(booksRequest.value!!.data))

        viewModelScope.launch {
            var updatedTicker= async { repository.getTicker(selectedBook.value!!.name) }.await().body()
            updatedTicker?.let { ticker ->

                var booklist = booksRequest.value!!.data!!
                booklist.find { it.name == selectedBook.value!!.name }?.ticker = ticker
                Log.v("TIcker Update Value: ", ticker.ask.toString())
                Log.v("TIcker Update Value: ", ticker.bid.toString())
                Log.v("TIcker Update Value: ", ticker.bookName.toString())
                Log.v("TIcker Update Value: ", ticker.dayHigh.toString())
                Log.v("TIcker Update Value: ", ticker.dayLow.toString())
                Log.v("TIcker Update Value: ", ticker.volume.toString())
                Log.v("TIcker Update Value: ", ticker.spread.toString())

                booksRequest.postValue(Resource.Success(booklist))

            } ?: run{
                var booklist = booksRequest.value!!.data!!
                booklist.find { it.name == selectedBook.value!!.name }?.ticker = null
                booksRequest.postValue(Resource.Error(""))
            }
        }
    }
}