package co.jbear.euphony_hub.ui.view

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import co.jbear.euphony_hub.MainActivity
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
class CollectorViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun assertThatCollectorViewComponentsIsDisplayed() {
        composeTestRule.onNode(hasTestTag("textToSend"), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun assertThatSpeakerButtonIsDisplayed() {
        composeTestRule.onNode(hasTestTag("textToSend"), useUnmergedTree = true)
            .performTextInput("Hello")
        composeTestRule.onNode(hasTestTag("speakerButton"), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun assertThatPauseButtonIsDisplayed() {
        composeTestRule.onNode(hasTestTag("textToSend"), useUnmergedTree = true)
            .performTextInput("Hello")
        composeTestRule.onNode(hasTestTag("speakerButton"), useUnmergedTree = true)
            .performClick()
        composeTestRule.onNode(hasTestTag("pauseButton"), useUnmergedTree = true)
            .assertIsDisplayed()
    }
}