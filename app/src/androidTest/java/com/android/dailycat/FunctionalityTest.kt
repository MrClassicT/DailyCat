package com.android.dailycat

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FunctionalityTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            DailyCatApp()
        }
    }

    @Test
    fun canFavoritePost() {
        Thread.sleep(5000) // Make sure posts are loaded.
        composeTestRule
            .onNodeWithTag("Favorite")
            .performClick()
        composeTestRule
            .onNodeWithText("FAVORITES")
            .performClick()
        composeTestRule
            .onNodeWithTag("Favorite")
            .assertIsDisplayed()

    }

    @Test
    fun canUnFavoritePost() {
        Thread.sleep(5000) // Make sure posts are loaded.
        composeTestRule
            .onNodeWithTag("Favorite")
            .performClick()
        composeTestRule
            .onNodeWithText("FAVORITES")
            .performClick()
        composeTestRule
            .onNodeWithTag("Favorite")
            .performClick()
        composeTestRule
            .onNodeWithText("You don't have any favorites yet!")
            .assertIsDisplayed()

    }
}