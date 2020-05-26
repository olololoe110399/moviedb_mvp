package com.sun_asterisk.moviedb_50.data.source.remote.response

import com.sun_asterisk.moviedb_50.data.model.Genres
import com.sun_asterisk.moviedb_50.data.source.remote.fetchjson.ParseJsonHandler
import org.json.JSONObject

data class GenresResponse(var list: List<Genres>) {
    constructor(jsonObject: JSONObject) : this(
        list = ParseJsonHandler.parseJsonToData(
            jsonObject,
            Genres.GenresEntry.GENRES
        ) as List<Genres>
    )
}
