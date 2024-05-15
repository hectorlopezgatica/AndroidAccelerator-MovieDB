package com.hlopezg.presentation_tv.single

import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.usecase.GetTvUseCase
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

class TvViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val useCase = mock<GetTvUseCase>()
    private val converter = mock<TvConverter>()
    private lateinit var viewModel: TvViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = TvViewModel(useCase, converter)
    }

    @Test
    fun testHandleActionLoad() = runTest {
        assertEquals(UiState.Loading(LoadingType.ContentDetail), viewModel.uiStateFlow.value)
        val postId = 1L
        val uiState = mock<UiState<TvModel>>()
        val result = mock<Result<GetTvUseCase.Response>>()
        whenever(useCase.execute(GetTvUseCase.Request(postId))).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.handleAction(TvUiAction.Load(postId))
        assertEquals(uiState, viewModel.uiStateFlow.value)
    }
}