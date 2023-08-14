package no.fredheim.ligrettoScoresheet

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToPlayersScreen() = this.clickNode(R.string.start_ligretto_calculator)

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToFirstPlayerRoundScreen() {
    this.navigateToPlayersScreen()
    this.insertIntoField(R.string.type_name, "Alex")
    this.clickNode(R.string.add_player)
    this.insertIntoField(R.string.type_name, "Thao")
    this.clickNode(R.string.add_player)
    this.insertIntoField(R.string.type_name, "Rikke")
    this.clickNode(R.string.add_player)
    this.clickNode(R.string.start_game)
}

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToSecondPlayerRoundScreen() {
    this.navigateToFirstPlayerRoundScreen()
    this.clickNode(R.string.next_player)
        }

fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .navigateToThirdPlayerRoundScreen() {
    this.navigateToSecondPlayerRoundScreen()
    this.clickNode(R.string.next_player)
}

