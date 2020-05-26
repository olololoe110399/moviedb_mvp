package com.sun_asterisk.moviedb_50.data.source.remote.fetchjson

import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MovieDetailsResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import org.json.JSONObject

object ResponseHandler {
    class GenresResponseHandler : ParseDataWithJson<GenresResponse> {
        override fun parseToObject(jsonData: String): GenresResponse =
            GenresResponse(JSONObject(jsonData))
    }
    class MovieDetailsResponseHandler : ParseDataWithJson<MovieDetailsResponse> {
        override fun parseToObject(jsonData: String): MovieDetailsResponse =
            MovieDetailsResponse(JSONObject(jsonData))
    }
    class MoviesResponseHandler : ParseDataWithJson<MoviesResponse> {
        override fun parseToObject(jsonData: String): MoviesResponse =
            MoviesResponse(JSONObject(jsonData))
    }
}
