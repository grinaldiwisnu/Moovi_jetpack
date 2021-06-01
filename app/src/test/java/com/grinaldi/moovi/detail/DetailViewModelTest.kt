package com.grinaldi.moovi.detail

import com.grinaldi.moovi.R
import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.views.detail.DetailViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private var mViewModel: DetailViewModel? = null
    private var mMoviesData: Movie? = null
    private var mTVShowData: Movie? = null

    @Before
    fun setUp() {
        mViewModel = DetailViewModel()
        mMoviesData = Movie(
                2,
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                R.drawable.poster_aquaman,
                "2018",
                "95"
        )

        mTVShowData = Movie(
                2,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                R.drawable.poster_doom_patrol,
                "2018",
                "56"
        )
    }

    @Test
    fun detailMovie() {
        Assert.assertEquals(mMoviesData?.id, mViewModel?.detailMovie(2)?.id)
        Assert.assertEquals(mMoviesData?.title, mViewModel?.detailMovie(2)?.title)
        Assert.assertEquals(mMoviesData?.description, mViewModel?.detailMovie(2)?.description)
        Assert.assertEquals(mMoviesData?.image, mViewModel?.detailMovie(2)?.image)
        Assert.assertEquals(mMoviesData?.date, mViewModel?.detailMovie(2)?.date)
    }

    @Test
    fun detailTvShow() {
        Assert.assertEquals(mTVShowData?.id, mViewModel?.detailTvShow(2)?.id)
        Assert.assertEquals(mTVShowData?.title, mViewModel?.detailTvShow(2)?.title)
        Assert.assertEquals(mTVShowData?.description, mViewModel?.detailTvShow(2)?.description)
        Assert.assertEquals(mTVShowData?.image, mViewModel?.detailTvShow(2)?.image)
        Assert.assertEquals(mTVShowData?.date, mViewModel?.detailTvShow(2)?.date)
    }
}