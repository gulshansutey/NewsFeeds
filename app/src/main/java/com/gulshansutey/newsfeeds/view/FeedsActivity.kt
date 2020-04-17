package com.gulshansutey.newsfeeds.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gulshansutey.newsfeeds.R
import com.gulshansutey.newsfeeds.view.adapter.FeedRecyclerAdapter
import com.gulshansutey.newsfeeds.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class FeedsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.getFeeds()

        val feedAdapter = FeedRecyclerAdapter()

        feeds_recycler_view.layoutManager = LinearLayoutManager(this)
        feeds_recycler_view.adapter = feedAdapter
        feedAdapter.submitList(viewModel.response.value?.rows)

    }

}
