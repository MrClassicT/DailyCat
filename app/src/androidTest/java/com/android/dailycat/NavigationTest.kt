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

class NavigationTest {
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
    fun verifyHomePage() {
        composeTestRule
            .onNodeWithText("DISCOVER")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToFavorites() {
        composeTestRule
            .onNodeWithText("FAVORITES")
            .performClick()

        composeTestRule
            .onNodeWithText("You don't have any favorites yet!")
            .assertIsDisplayed()

    }

    @Test
    fun navigateToAbout() {
        composeTestRule
            .onNodeWithTag("About")
            .performClick()
        composeTestRule
            .onNodeWithText("Tristan Cuvelier")
            .assertIsDisplayed()
    }

    @Test
    fun navigateFromAbout() {
        composeTestRule
            .onNodeWithTag("About")
            .performClick()
        composeTestRule
            .onNodeWithText("Tristan Cuvelier")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("About")
            .performClick()
        composeTestRule
            .onNodeWithText("DISCOVER")
            .assertIsDisplayed()
    }

}