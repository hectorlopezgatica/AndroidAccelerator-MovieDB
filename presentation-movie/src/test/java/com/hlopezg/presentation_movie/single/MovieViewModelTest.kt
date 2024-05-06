package com.hlopezg.presentation_movie.single

import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.usecase.GetMovieUseCase
import com.hlopezg.presentation_common.state.LoadingType
import com.hlopezg.presentation_common.state.UiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MovieViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val useCase = mock<GetMovieUseCase>()
    private val converter = mock<MovieConverter>()
    private lateinit var viewModel: MovieViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieViewModel(useCase, converter)
    }

    @Test
    fun testHandleActionLoad() = runTest {
        assertEquals(UiState.Loading(LoadingType.ContentDetail), viewModel.uiStateFlow.value)
        val postId = 1L
        val uiState = mock<UiState<MovieModel>>()
        val result = mock<Result<GetMovieUseCase.Response>>()
        whenever(useCase.execute(GetMovieUseCase.Request(postId))).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.handleAction(MovieUiAction.Load(postId))
        assertEquals(uiState, viewModel.uiStateFlow.value)
    }
}