package com.sun_asterisk.moviedb_50.data.source.remote.response

import com.sun_asterisk.moviedb_50.data.model.*
import com.sun_asterisk.moviedb_50.data.source.remote.fetchjson.ParseJsonHandler
import org.json.JSONObject

data class MovieDetailsResponse(
    var movies: Movie,
    var genres: List<Genres>,
    var produce: List<Produce>,
    var casts: List<Cast>,
    var trailers: List<MovieTrailer>
) {
    constructor(jsonObject: JSONObject) : this(
        movies = Movie(jsonObject),
        genres = ParseJsonHandler.parseJsonToData(
            jsonObject,
            Genres.GenresEntry.GENRES
        ) as MutableList<Genres>,
        produce = ParseJsonHandler.parseJsonToData(
            jsonObject,
            Produce.ProduceEntry.PRODUCES
        ) as MutableList<Produce>,
        casts = ParseJsonHandler.parseJsonToData(
            jsonObject,
            Cast.CastEntry.CREDITS
        ) as MutableList<Cast>,
        trailers = ParseJsonHandler.parseJsonToData(
            jsonObject,
            MovieTrailer.MovieTrailerEntry.MOVIE_TRAILER_VIDEO
        ) as MutableList<MovieTrailer>
    )
}
