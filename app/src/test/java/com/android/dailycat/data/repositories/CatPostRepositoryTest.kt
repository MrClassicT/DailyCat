package com.android.dailycat.data.repositories

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class CatPostRepositoryTest {

    private lateinit var imgRepository: ImgRepository
    private lateinit var quoteRepository: QuoteRepository
    private lateinit var catPostRepository: CatPostRepository

    @Before
    fun setUp() {
        // Initialize the mocks
        imgRepository = mock(ImgRepository::class.java)
        quoteRepository = mock(QuoteRepository::class.java)

        // Create an instance of the repository with the mocks
        catPostRepository = CatPostRepository(imgRepository, quoteRepository)
    }

    /**
     * Test where we check if getCatPost actually returns a post.
     * **/
    @Test
    fun getCatPost_returnsCatPost() = runTest {
        // Given
        val fakeImage = ByteArray(10) // Just a placeholder for the image
        val fakeQuote = "Mocked Quote"
        `when`(imgRepository.getCatImage()).thenReturn(fakeImage)
        `when`(quoteRepository.getQuote()).thenReturn(fakeQuote)

        // When
        val catPost = catPostRepository.getCatPost()

        // Then
        assertNotNull(catPost)
        assertArrayEquals(fakeImage, catPost.image)
        assertEquals(fakeQuote, catPost.quote)

        // Verify that the methods were called
        verify(imgRepository).getCatImage()
        verify(quoteRepository).getQuote()
    }


    /**
     * Test where we check if getCatPost throws an error if something were to go wrong.
     * **/
    @Test
    fun getCatPost_throwsException() = runTest {
        // Given
        `when`(imgRepository.getCatImage()).thenThrow(RuntimeException("Failed to load image"))
        `when`(quoteRepository.getQuote()).thenReturn("Some quote")

        // Then
        assertThrows(RuntimeException::class.java) {
            // When
            runBlocking {
                catPostRepository.getCatPost()
            }
        }
    }
}
