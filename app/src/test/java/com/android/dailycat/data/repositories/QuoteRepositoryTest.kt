package com.android.dailycat.data.repositories

import com.android.dailycat.network.QuoteApiService
import com.android.dailycat.network.QuoteResponse
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response


class QuoteRepositoryTest {

    private lateinit var repository: QuoteRepository
    private val quoteApiService = mock(QuoteApiService::class.java)

    @Before
    fun setUp() {
        repository = QuoteRepository(quoteApiService)
    }

    @Test
    fun fetchQuote_success_returnsQuote() = runBlocking {
        // Given
        val expectedQuote = "To be or not to be."
        val fakeResponse = QuoteResponse(listOf(expectedQuote))

        // Assuming the getQuote() method returns a Response<QuoteResponse>
        `when`(quoteApiService.getQuote()).thenReturn(Response.success(fakeResponse))

        // When
        val actualQuote = repository.getQuote()

        // Then
        assertEquals(expectedQuote, actualQuote)
    }


    @Test(expected = Exception::class)
    fun fetchQuote_failure_throwsException() {
        runBlocking {

            // Given
            `when`(quoteApiService.getQuote()).thenReturn(
                Response.error(400, mock(ResponseBody::class.java))
            )

            // When
            repository.getQuote() // This should throw error.
        }
    }
}

