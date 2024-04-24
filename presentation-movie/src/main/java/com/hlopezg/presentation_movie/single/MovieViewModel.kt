package com.hlopezg.presentation_movie.single

import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.usecase.GetMovieUseCase
import com.hlopezg.presentation_common.state.LoadingType
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiSingleEvent
import com.hlopezg.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: GetMovieUseCase,
    private val movieConverter: MovieConverter,
): MviViewModel<MovieModel, UiState<MovieModel>, MovieUiAction, UiSingleEvent>(){
    override fun initState(): UiState<MovieModel> = UiState.Loading(LoadingType.ContentDetail)

    override fun handleAction(action: MovieUiAction) {
        when (action){
            is MovieUiAction.Load -> {
                loadMovie(action.movieId)
            }
        }
    }

    private fun loadMovie(movieId: Long) {
        viewModelScope.launch {
            movieUseCase.execute(GetMovieUseCase.Request(movieId))
                .map {
                    movieConverter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }
}