package com.example.myapplication.activities

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.adapters.MovieAdapter
import com.example.myapplication.network.NetworkUtils
import com.example.myapplication.pojos.Movie
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
//
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var movieList:ArrayList<Movie> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initSearchButton()

    }
    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)

        movieAdapter= MovieAdapter(movieList,{ movieItem:Movie->movieItemClicked(movieItem)})

        movie_list_rv.apply { setHasFixedSize(true)
        layoutManager=viewManager
        adapter=movieAdapter
        }
    }
    private fun initSearchButton() = add_movie_btn.setOnClickListener {
        if(!movie_name_et.text.toString().isEmpty()){
            FetchMovie().execute(movie_name_et.text.toString())
        }
    }
    private fun movieItemClicked(item:Movie){
    }
    private inner class FetchMovie : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            if(params.isNullOrEmpty()) return ""
            val movieName = params[0]

            val movieUrl = NetworkUtils().buildSearchUrl(movieName!!)//EROR AQUI VO

            return try{
                NetworkUtils().getResponseFromHttpUrl(movieUrl)
            }catch (e: IOException){

                ""
            }
        }

        override fun onPostExecute(movieInfo: String?) {
            super.onPostExecute(movieInfo)
            if (movieInfo != null) {
                if(!movieInfo.isEmpty()){
                    val movieJSon = JSONObject(movieInfo)
                    if(movieJSon.getString("Response")=="True"){
                        val movie = Gson().fromJson<Movie>(movieInfo,Movie::class.java)
                        addMovieToList(movie)
                    }else{
                        Snackbar.make(main_ll,"No existe la pelicula en la base",Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    fun addMovieToList(movie:Movie){
        movieList.add(movie)
        movieAdapter.changeList(movieList)
    }
}
