package com.sun_asterisk.moviedb_50.data.source.remote.fetchjson

import com.sun_asterisk.moviedb_50.data.model.*
import org.json.JSONException
import org.json.JSONObject

object ParseJsonHandler {
    fun parseJsonToData(jsonObject: JSONObject, strKey: String): List<Any> {
        val movieList = mutableListOf<Any>()
        try {

            val jsonArray = when (strKey) {
                Cast.CastEntry.CREDITS -> jsonObject.getJSONObject(Cast.CastEntry.CREDITS)
                    .getJSONArray(Cast.CastEntry.CAST)
                MovieTrailer.MovieTrailerEntry.MOVIE_TRAILER_VIDEO -> jsonObject.getJSONObject(
                    MovieTrailer.MovieTrailerEntry.MOVIE_TRAILER_VIDEO
                ).getJSONArray(MovieTrailer.MovieTrailerEntry.MOVIE_TRAILER_RESULTS)
                else -> jsonObject.getJSONArray(strKey)
            }


            for (i in 0 until jsonArray.length()) {
                val movieJson = jsonArray.getJSONObject(i)
                parseJsonToObject(
                    movieJson, strKey
                )?.let { movieList.add(it) }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movieList
    }

    private fun parseJsonToObject(jsonObject: JSONObject, strKey: String): Any? {
        return when (strKey) {
             Genres.GenresEntry.GENRES -> Genres(jsonObject)
            Cast.CastEntry.CREDITS -> Cast(jsonObject)
            Produce.ProduceEntry.PRODUCES -> Produce(jsonObject)
            MovieTrailer.MovieTrailerEntry.MOVIE_TRAILER_VIDEO -> MovieTrailer(jsonObject)
            else -> Movie(jsonObject)
        }
    }
}
