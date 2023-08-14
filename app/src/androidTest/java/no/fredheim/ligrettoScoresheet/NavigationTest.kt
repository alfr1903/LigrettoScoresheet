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
        composeTestRule.clickTextNode(R.string.start_ligretto_calculator)
        navController shouldHaveCurrentRouteName Screen.Players.name
    }

    @Test
    fun afterBackIconPressed_currentScreenShouldBeWelcome() {
        composeTestRule.navigateToPlayersScreen()
        composeTestRule.clickDescriptionNode(R.string.arrow_back)
        navController shouldHaveCurrentRouteName Screen.Welcome.name
    }

    @Test
    fun afterPlayersAdded_and_StartGamePressed_currentScreenShouldBeFirstPlayerRoundScreen() {
        composeTestRule.navigateToPlayersScreen()

        composeTestRule.insertIntoField(R.string.type_name, "Alex")
        composeTestRule.clickTextNode(R.string.add_player)
        composeTestRule.insertIntoField(R.string.type_name, "Thao")
        composeTestRule.clickTextNode(R.string.add_player)
        composeTestRule.insertIntoField(R.string.type_name, "Rikke")
        composeTestRule.clickTextNode(R.string.add_player)
        composeTestRule.clickTextNode(R.string.start_game)

        navController shouldHaveCurrentRouteName Screen.PlayerRound.name
    }

    @Test
    fun afterNextPlayerPressed_currentScreenShouldBeSecondPlayerRoundScreen() {
        composeTestRule.navigateToFirstPlayerRoundScreen()
        composeTestRule.clickTextNode(R.string.next_player)

        navController shouldHaveCurrentRouteName Screen.PlayerRound.name
    }

    @Test
    fun afterPrevPlayerPressed_currentScreenShouldBeFirstPlayerRoundScreen() {
        composeTestRule.navigateToSecondPlayerRoundScreen()
        composeTestRule.clickTextNode(R.string.prev_player)

        navController shouldHaveCurrentRouteName Screen.PlayerRound.name
    }

    @Test
    fun afterHomePressed_currentScreenShouldBePlayersScreen() {
        composeTestRule.navigateToSecondPlayerRoundScreen()
        composeTestRule.clickDescriptionNode(R.string.home)

        navController shouldHaveCurrentRouteName Screen.Players.name
    }


    @Test
    fun afterResultsPressed_currentScreenShouldBeResultsScreen() {
        composeTestRule.navigateToFirstPlayerRoundScreen()
        composeTestRule.clickTextNode(R.string.results)

        navController shouldHaveCurrentRouteName Screen.Results.name
    }

    @Test
    fun afterNewRoundPressed_currentScreenShouldBePlayerRoundScreen() {
        composeTestRule.navigateToResultsScreen()
        composeTestRule.clickTextNode(R.string.new_round)

        navController shouldHaveCurrentRouteName Screen.PlayerRound.name
    }



}