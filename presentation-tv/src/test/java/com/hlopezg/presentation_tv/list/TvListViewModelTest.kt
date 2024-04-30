package com.hlopezg.presentation_tv.list

import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.usecase.GetTvsUseCase
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
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TvListViewModelTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val getTvsUseCase =
        mock<GetTvsUseCase>()
    private val converter = mock<TvListConverter>()

    //private val updateInteractionUseCase = mock<GetMoviesUseCase>()
    private lateinit var viewModel: TvListViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = TvListViewModel(
            getTvsUseCase,
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
        Assert.assertEquals(UiState.Loading(LoadingType.RowList), viewModel.uiStateFlow.value)
        val uiState = mock<UiState<TvListModel>>()
        val result = mock<Result<GetTvsUseCase.Response>>()
        whenever(
            getTvsUseCase.execute(
                GetTvsUseCase.Request
            )
        ).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.handleAction(TvListUiAction.Load)
        Assert.assertEquals(uiState, viewModel.uiStateFlow.value)
    }
}