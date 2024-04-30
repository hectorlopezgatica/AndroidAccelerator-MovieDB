package com.hlopezg.presentation_movie.list

import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.usecase.GetMoviesUseCase
import com.hlopezg.presentation_common.state.LoadingType
import com.hlopezg.presentation_common.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MovieListViewModelTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val getMoviesUseCase =
        mock<GetMoviesUseCase>()
    private val converter = mock<MovieListConverter>()

    //private val updateInteractionUseCase = mock<GetMoviesUseCase>()
    private lateinit var viewModel: MovieListViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieListViewModel(
            getMoviesUseCase,
            converter,
        )
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testHandleActionLoad() = runTest {
        assertEquals(UiState.Loading(LoadingType.RowList), viewModel.uiStateFlow.value)
        val uiState = mock<UiState<MovieListModel>>()
        val result = mock<Result<GetMoviesUseCase.Response>>()
        whenever(
            getMoviesUseCase.execute(
                GetMoviesUseCase.Request
            )
        ).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.handleAction(MovieListUiAction.Load)
        assertEquals(uiState, viewModel.uiStateFlow.value)
    }
}