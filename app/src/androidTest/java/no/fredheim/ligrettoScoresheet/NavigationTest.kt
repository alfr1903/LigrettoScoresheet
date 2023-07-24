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
    fun afterStartGamePressed_currentScreenShouldBePlayersScreen() {
        composeTestRule.clickNode(R.string.start_ligretto_calculator)
        navController shouldHaveCurrentRouteName Screen.Players.name
    }

    @Test
    fun afterPlayersAdded_and_StartGamePressed_currentScreenShouldBeFirstPlayerRoundScreen() {
        composeTestRule.navigateToPlayersScreen()

        composeTestRule.insertIntoField(R.string.add_new_player, "Alex")
        composeTestRule.clickNode(R.string.add_player)
        composeTestRule.insertIntoField(R.string.add_new_player, "Thao")
        composeTestRule.clickNode(R.string.add_player)
        composeTestRule.insertIntoField(R.string.add_new_player, "Rikke")
        composeTestRule.clickNode(R.string.add_player)
        composeTestRule.clickNode(R.string.start_ligretto_calculator)

        navController shouldHaveCurrentRouteName Screen.PlayerRound.name
    }

    @Test
    fun afterNextPlayerPressed_currentScreenShouldBeSecondPlayerRoundScreen() {
        composeTestRule.navigateToFirstPlayerRoundScreen()
        composeTestRule.clickNode(R.string.next_player)

        navController shouldHaveCurrentRouteName Screen.PlayerRound.name
    }

    @Test
    fun afterNextPlayerPressed_currentScreenShouldBeThirdPlayerRoundScreen() {
        composeTestRule.navigateToSecondPlayerRoundScreen()
        composeTestRule.clickNode(R.string.next_player)

        navController shouldHaveCurrentRouteName Screen.PlayerRound.name
    }

    @Test
    fun afterResultsPressed_currentScreenShouldBeResultsScreen() {
        composeTestRule.navigateToThirdPlayerRoundScreen()
        composeTestRule.clickNode(R.string.results)

        navController shouldHaveCurrentRouteName Screen.Results.name
    }



}