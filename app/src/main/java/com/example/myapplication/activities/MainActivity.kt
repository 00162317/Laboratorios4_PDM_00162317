package com.example.myapplication.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.MovieAdapter
import com.example.myapplication.pojos.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var movieList:ArrayList<Movie> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)

        movieAdapter= MovieAdapter(movieList,{ movieItem:Movie->movieIte
            mClicked(movieItem)})

        movie_list_rv.apply { setHasFixedSize(true)
        layoutManager=viewManager
        adapter=movieAdapter
        }
    }
}
