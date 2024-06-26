package com.hlopezg.presentation_movie.list

import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.usecase.GetMoviesUseCase
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.models.CommonContentDetailModel
import com.hlopezg.presentation_common.navigation.MovieInput
import com.hlopezg.presentation_common.navigation.NavRoutes
import com.hlopezg.presentation_common.state.LoadingType
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiState
import com.hlopezg.presentation_movie.single.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val converter: MovieListConverter,
) : MviViewModel<MovieListModel, UiState<MovieListModel>, MovieListUiAction, MovieListUiSingleEvent>() {
    override fun initState(): UiState<MovieListModel> = UiState.Loading(LoadingType.RowList)

    override fun handleAction(action: MovieListUiAction) {
        when (action) {
            MovieListUiAction.Load -> loadMovies()
            is MovieListUiAction.SingleMovieClick -> {
                submitSingleEvent(
                    MovieListUiSingleEvent.OpenMovieScreen(
                        action.movie
                    )
                )
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            getMoviesUseCase.execute(GetMoviesUseCase.Request)
                .map {
                    converter.convert(it)
                }.collect {
                    submitState(it)
                }
        }
    }
}