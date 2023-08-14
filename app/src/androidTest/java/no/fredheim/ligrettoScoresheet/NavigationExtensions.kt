package no.fredheim.ligrettoScoresheet

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToPlayersScreen() = this.clickTextNode(R.string.start_ligretto_calculator)

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToFirstPlayerRoundScreen() {
    this.navigateToPlayersScreen()
    this.insertIntoField(R.string.type_name, "Alex")
    this.clickTextNode(R.string.add_player)
    this.insertIntoField(R.string.type_name, "Thao")
    this.clickTextNode(R.string.add_player)
    this.insertIntoField(R.string.type_name, "Rikke")
    this.clickTextNode(R.string.add_player)
    this.clickTextNode(R.string.start_game)
}

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToSecondPlayerRoundScreen() {
    this.navigateToFirstPlayerRoundScreen()
    this.clickTextNode(R.string.next_player)
        }

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToResultsScreen() {
    this.navigateToFirstPlayerRoundScreen()
    this.clickTextNode(R.string.results)
}



