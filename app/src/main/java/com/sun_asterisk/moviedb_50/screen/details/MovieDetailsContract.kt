package com.sun_asterisk.moviedb_50.screen.details

import com.sun_asterisk.moviedb_50.data.model.*
import com.sun_asterisk.moviedb_50.screen.base.BasePresenter
import com.sun_asterisk.moviedb_50.utils.FavoriteEnum

class MovieDetailsContract {
    /**
     * View
     */
    interface View {
        fun onGetMovieSuccess(movie: Movie)
        fun onGetGenresSuccess(genres: List<Genres>)
        fun onGetCastsSuccess(casts: List<Cast>)
        fun onGetProducesSuccess(produces: List<Produce>)
        fun onGetMovieTrailerSuccess(movieTrailers: List<MovieTrailer>)
        fun showFavoriteImage(type: FavoriteEnum)
        fun notifyFavorite(type: FavoriteEnum)
        fun onLoading(isLoad: Boolean)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View?> {
        fun getMovieDetails(movieID: Int)
        fun handleFavorites(favorite: Favorite)
    }
}
