package com.android.dailycat.data.repositories

import com.android.dailycat.network.ImgApiService
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


class ImgRepositoryTest {

    private lateinit var repository: ImgRepository
    private val catApiService = mock(ImgApiService::class.java)
    private lateinit var imgApiService: ImgApiService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ImgRepository(catApiService)
        imgApiService = mock(ImgApiService::class.java)
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