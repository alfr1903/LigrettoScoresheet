package no.fredheim.ligrettoScoresheet

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

infix fun NavController.shouldHaveCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
