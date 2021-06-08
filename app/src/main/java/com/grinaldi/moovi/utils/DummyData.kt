package com.grinaldi.moovi.utils

import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.data.sources.remote.response.*

object DummyData {
    fun getDummyListData(): List<MovieEntity> {
        val result = ArrayList<MovieEntity>()
        result.add(
            MovieEntity(
                overview = "overview1",
                title = "name1",
                posterPath = "poster1",
                voteAverage = 5.0,
                id = 1,
            )
        )
        result.add(
            MovieEntity(
                overview = "overview2",
                title = "name2",
                posterPath = "poster2",
                voteAverage = 5.0,
                id = 2,
            ),
        )
        return result
    }

    fun getDummyDetailData(): DetailEntity {
        return DetailEntity(
            id = 1,
            title = "title",
            overview = "overview",
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            releaseDate = "releaseDate",
            voteCount = 5,
            voteAverage = 5.0,
            tagLine = "tagLine",
            homepage = "homepage",
            status = "status"
        )
    }

    fun getDummyMovieResponse(): ListMovies {
        return ListMovies(
            movies = mutableListOf(
                Movies("overview1", "title1", "poster1", 5.6, 1),
                Movies("overview2", "title2", "poster2", 5.6, 2),
                Movies("overview3", "title3", "poster3", 5.6, 3),
            )
        )

    }

    fun getDummyMovieDetailResponse(): MovieDetail {
        return MovieDetail(
            "overview",
            "title",
            "poster",
            "backdrop",
            "release",
            5.6,
            "tagLine",
            1,
            10,
            "homepage",
            "status",
        )
    }

    fun getDummyTvShowResponse(): ListTvShows {
        return ListTvShows(
            tvShows = mutableListOf(
                TvShow("overview1", "poster1", 5.0, "name1", 1),
                TvShow("overview2", "poster2", 4.0, "name2", 2),
                TvShow("overview3", "poster3", 5.0, "name3", 3),
            )
        )

    }

    fun getDummyTvShowDetailResponse(): TvShowDetail {
        return TvShowDetail(
            overview = "overview",
            title = "title",
            posterPath = "poster",
            id = 1,
            backdropPath = "backdrop",
            voteAverage = 5.0,
            voteCount = 10,
            releaseDate = "releaseDate",
            tagLine = "tagLine",
            homepage = "homepage",
            status = "status"
        )
    }
}

