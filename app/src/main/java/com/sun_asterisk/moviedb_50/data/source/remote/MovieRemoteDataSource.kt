package com.sun_asterisk.moviedb_50.data.source.remote

import android.util.Log
import com.sun_asterisk.moviedb_50.data.source.MovieDataSource
import com.sun_asterisk.moviedb_50.data.source.remote.fetchjson.*
import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MovieDetailsResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import com.sun_asterisk.moviedb_50.utils.Constant

class MovieRemoteDataSource : MovieDataSource.Remote {

    override fun getGenres(listener: OnDataLoadedCallback<GenresResponse>) {
        val url =
            Constant.BASE_URL +
                    Constant.BASE_GENRES_LIST +
                    Constant.BASE_API_KEY +
                    Constant.BASE_LANGUAGE
        GetDataFromUrlAsync(ResponseHandler.GenresResponseHandler(), listener).execute(url)
    }

    override fun getMovies(
        type: String,
        query: String,
        page: Int,
        listener: OnDataLoadedCallback<MoviesResponse>
    ) {

        val url = Constant.BASE_URL +
                when (type) {
                    Constant.BASE_GENRES_ID -> Constant.BASE_DISCOVER_MOVIE
                    Constant.BASE_CAST_ID -> Constant.BASE_DISCOVER_MOVIE
                    Constant.BASE_PRODUCE_ID -> Constant.BASE_DISCOVER_MOVIE
                    else -> type
                } +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE +
                Constant.BASE_PAGE +
                page +
                when (type) {
                    Constant.BASE_GENRES_ID -> type + query
                    Constant.BASE_CAST_ID -> type + query
                    Constant.BASE_PRODUCE_ID -> type + query
                    Constant.BASE_SEARCH -> Constant.BASE_QUERY + query
                    else -> ""
                }
        GetDataFromUrlAsync(ResponseHandler.MoviesResponseHandler(), listener).execute(url)
    }

    override fun getMovieDetails(
        movieID: Int,
        listener: OnDataLoadedCallback<MovieDetailsResponse>
    ) {
        val url = Constant.BASE_URL +
                Constant.BASE_MOVIE +
                movieID +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE +
                Constant.BASE_APPEND +
                Constant.BASE_CREDITS +
                Constant.BASE_VIDEO
        GetDataFromUrlAsync(ResponseHandler.MovieDetailsResponseHandler(), listener).execute(url)
    }

    companion object {
        private var instance: MovieRemoteDataSource? = null
        fun getInstance() =
            instance ?: MovieRemoteDataSource().also { instance = it }
    }
}
