package no.fredheim.ligrettoScoresheet

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule

internal fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .clickNode(@StringRes textId: Int) =
    this.onNodeWithText(this.activity.getString(textId)).performClick()

internal fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
        .insertIntoField(@StringRes textId: Int, input: String) =
    this.onNodeWithText(this.activity.getString(textId)).performTextInput(input)
