package com.gulshansutey.newsfeeds.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gulshansutey.newsfeeds.R
import com.gulshansutey.newsfeeds.utils.DialogUtils
import com.gulshansutey.newsfeeds.view.adapter.FactsRecyclerAdapter
import com.gulshansutey.newsfeeds.viewmodel.FactsActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class FactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(FactsActivityViewModel::class.java)
        val feedAdapter = FactsRecyclerAdapter()
        val layoutManager = LinearLayoutManager(this)

        facts_recycler_view.layoutManager = layoutManager
        facts_recycler_view.adapter = feedAdapter
        facts_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )

        /*
        * As we are using [androidx.lifecycle.ViewModel] that is designed to store and manage
        * UI-related data in a lifecycle conscious way,
        * and allows data to survive configuration changes such as screen rotations.
        * So instead of check @savedInstanceState here we are checking if @FeedsActivityViewModel contains the data.
        * */
        if (viewModel.response.value == null) {
            viewModel.getFacts()
        }

        viewModel.response.observe(this, Observer {
            tv_message.visibility = View.GONE
            supportActionBar?.title = it.title.toString()
            feedAdapter.submitList(it.rows)

        })

        viewModel.isInProgress.observe(this, Observer {
            refresh_layout.isRefreshing = it
            if (it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
        })

        viewModel.networkErrors.observe(this, Observer {

            DialogUtils.showAlertDialog(this, it)
            if (viewModel.response.value == null) {
                tv_message.visibility = View.VISIBLE
            }
        })

        refresh_layout.setOnRefreshListener {
            viewModel.getFacts()
        }
    }

}
