package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.entity.UseCaseException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class UseCaseTest{
    @ExperimentalCoroutinesApi
    private val configuration = UseCase.Configuration(UnconfinedTestDispatcher())
    private val request = mock<UseCase.Request>()
    private val response = mock<UseCase.Response>()

    @ExperimentalCoroutinesApi
    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                Assert.assertEquals(this@UseCaseTest.request, request)
                return flowOf(response)
            }

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteMovieException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                Assert.assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw UseCaseException.MovieException(Throwable())
                }
            }

        }
        runTest {
            val result = useCase.execute(request).first()
            Assert.assertTrue((result as Result.Error).exception is UseCaseException.MovieException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteTvException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                Assert.assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw UseCaseException.TvException(Throwable())
                }
            }

        }
        runTest {
            val result = useCase.execute(request).first()
            Assert.assertTrue((result as Result.Error).exception is UseCaseException.TvException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteUnknownException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                Assert.assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw Throwable()
                }
            }

        }
        runTest {
            val result = useCase.execute(request).first()
            Assert.assertTrue((result as Result.Error).exception is UseCaseException.UnknownException)
        }
    }
}