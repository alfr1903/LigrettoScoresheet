package no.fredheim.ligrettoScoresheet

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LigrettoApp(navController = navController)
        }
    }


    @Test
    fun startScreenShouldBeWelcomeScreen() {
        navController shouldHaveCurrentRouteName Screen.Welcome.name
    }

    @Test
    fun afterStartGameButtonPressed_currentScreenShouldBePlayersScreen() {
        composeTestRule.clickNodeWithText(R.string.start_game)
        navController shouldHaveCurrentRouteName Screen.Players.name
    }

}