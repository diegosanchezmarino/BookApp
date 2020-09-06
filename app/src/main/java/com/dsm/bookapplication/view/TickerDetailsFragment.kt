package com.dsm.bookapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.bookapplication.BooksViewModel
import com.dsm.bookapplication.R
import com.dsm.bookapplication.model.Book
import com.dsm.bookapplication.model.Resource
import com.dsm.bookapplication.view.recycler.BookAdapter
import com.dsm.bookapplication.view.recycler.ItemDecorator
import kotlinx.android.synthetic.main.fragment_book_detail.*

class TickerDetailsFragment: Fragment() {

    private val viewModel by lazy { ViewModelProvider(activity!!).get(BooksViewModel::class.java) }

    private var bookAdapter =
        BookAdapter(style = BookAdapter.Style.Ticker)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        swipeToRefresh.setOnRefreshListener {
            viewModel.updateSelectedTicker()
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
                    progressBar.visibility = View.GONE
                    bookAdapter.updateData((it.data as ArrayList<Book>).filter { it.name == viewModel.selectedBook.value!!.name })
                }
                Resource.Status.ERROR -> {
                    bookAdapter.updateData(emptyList())
                    swipeToRefresh.isRefreshing = false
                    progressBar.visibility = View.GONE
                }
            }
        })
    }



}