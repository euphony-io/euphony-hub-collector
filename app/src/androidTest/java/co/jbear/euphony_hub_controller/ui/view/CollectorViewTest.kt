package co.jbear.euphony_hub_controller.ui.view

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import co.jbear.euphony_hub_controller.data.repository.PreferenceRepository
import co.jbear.euphony_hub_controller.presentation.collector.CollectorViewModel
import co.jbear.euphony_hub_controller.ui.theme.EuphonyHubTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
class CollectorViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            EuphonyHubTheme() {
                CollectorView(viewModel = CollectorViewModel(PreferenceRepository(InstrumentationRegistry.getInstrumentation().targetContext)))
            }
        }
    }

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
}