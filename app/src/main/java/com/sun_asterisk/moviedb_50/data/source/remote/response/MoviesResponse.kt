package com.sun_asterisk.moviedb_50.data.source.remote.response

import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.model.MovieResultPage
import com.sun_asterisk.moviedb_50.data.source.remote.fetchjson.ParseJsonHandler
import org.json.JSONObject

data class MoviesResponse(var movieResultPage: MovieResultPage, var list: List<Any>) {
    constructor(jsonObject: JSONObject) : this(
        movieResultPage = MovieResultPage(jsonObject),
        list = ParseJsonHandler.parseJsonToData(jsonObject,Movie.MovieEntry.MOVIE)
    )
}
