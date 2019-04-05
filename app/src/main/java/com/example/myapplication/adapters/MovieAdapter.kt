package com.example.myapplication.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.myapplication.pojos.Movie

class MovieAdapter(var movies: List<Movie>,val clickListener:(Movie)->Unit): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder {

    }

}