package com.android.dailycat.data.repositories

import com.android.dailycat.network.ImgApiService
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ImgRepositoryTest {

    private lateinit var repository: ImgRepository
    private val catApiService = mock(ImgApiService::class.java)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ImgRepository(catApiService)
    }

    @Test
    fun fetchImage_success_returnsImageUrl() = runBlocking {
        // Given
        val expectedImage = "image_url"
        `when`(catApiService.getCatImage()).thenReturn(Response.success(expectedImage))

        // When
        val actualImage = repository.getCatImage()

        // Then
        assertEquals(expectedImage, actualImage)
    }

    @Test(expected = Exception::class)
    fun fetchImage_failure_throwsException() {
        runBlocking {
            // Given
            `when`(catApiService.getCatImage()).thenReturn(
                Response.error(
                    404,
                    mock(ResponseBody::class.java)
                )
            )

            // When
            repository.getCatImage()
        }
    }
}