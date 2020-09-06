package com.dsm.bookapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.bookapplication.BooksViewModel
import com.dsm.bookapplication.R
import com.dsm.bookapplication.model.Book
import com.dsm.bookapplication.model.Resource
import com.dsm.bookapplication.view.recycler.BookAdapter
import com.dsm.bookapplication.view.recycler.viewholders.BookViewHolderInteraction
import com.dsm.bookapplication.view.recycler.ItemDecorator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(),
    BookViewHolderInteraction {

    private val viewModel by lazy { ViewModelProvider(activity!!).get(BooksViewModel::class.java) }

    private var bookAdapter = BookAdapter(
        style = BookAdapter.Style.Book,
        bookViewHolderInteraction = this
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeToRefresh.setOnRefreshListener {
            viewModel.getBooks()
        }

        recyclerview.adapter = bookAdapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.addItemDecoration(
            ItemDecorator(
                20,
                20,
                20,
                20
            )
        )


        viewModel.booksRequest.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.STARTED -> {

                }
                Resource.Status.LOADING -> {
                    progressBar.visibility = VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    swipeToRefresh.isRefreshing = false
                    progressBar.visibility = GONE
                    bookAdapter.updateData(it.data as List<Book>)
                }
                Resource.Status.ERROR -> {
                    showErrorDialog()
                    bookAdapter.updateData(emptyList())
                    swipeToRefresh.isRefreshing = false
                    progressBar.visibility = GONE
                }
            }
        })

        viewModel.getBooks()

    }


    private fun showErrorDialog(){
        AlertDialog.Builder(context!!)
            .setTitle(R.string.error_title)
            .setMessage(R.string.error_message)
            .setPositiveButton(R.string.retry) { _, _ ->
                viewModel.getBooks()
            }
            .show()
    }


    override fun bookPressed(book: Book?) {
        var currentFragments = activity?.supportFragmentManager?.fragments

        viewModel.selectBook(book)

        if(currentFragments?.none{it is TickerDetailsFragment } != false){
             activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                ?.replace(
                    R.id.container,
                    TickerDetailsFragment()
                )
                ?.addToBackStack("BookDetail")
                ?.commit()
        }
    }


}