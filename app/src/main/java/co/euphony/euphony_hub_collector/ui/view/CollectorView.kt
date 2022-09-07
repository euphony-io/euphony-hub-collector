package co.euphony.euphony_hub_collector.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PauseCircleFilled
import androidx.compose.material.icons.outlined.SpeakerPhone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.euphony.euphony_hub_collector.R
import co.euphony.euphony_hub_collector.data.repository.PreferenceRepository
import co.euphony.euphony_hub_collector.presentation.collector.CollectorViewModel
import co.euphony.euphony_hub_collector.ui.components.CustomTextField
import co.euphony.euphony_hub_collector.ui.theme.EuphonyHubCollectorTheme
import co.euphony.euphony_hub_collector.ui.theme.SkyBlue
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(route = "home/collector_view", start = true)
fun CollectorView(
    viewModel: CollectorViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Spacer(modifier = Modifier.height(40.dp))
        Speaker(viewModel)
        Spacer(modifier = Modifier.height(100.dp))
        Listener(viewModel)
    }
}

@Composable
private fun Header() {
    Text(
        modifier = Modifier.padding(top = 80.dp),
        text = stringResource(R.string.header_title),
        style = MaterialTheme.typography.h3
    )

    Text(
        modifier = Modifier.padding(
            top = 16.dp,
            start = 30.dp,
            end = 30.dp,
        ),
        text = stringResource(R.string.header_description),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1
    )
}

@Composable
private fun Speaker(viewModel: CollectorViewModel) {
    val isProcessing by viewModel.isProcessing.observeAsState(false)
    var textToSend by rememberSaveable { mutableStateOf("") }

    CustomTextField(
        text = textToSend,
        onTextChanged = { textToSend = it },
        trailingIcon = {
            if (textToSend.isNotEmpty()) {
                IconButton(onClick = {
                    when(isProcessing) {
                        false -> viewModel.process(textToSend)
                        true -> viewModel.stop()
                    }
                }) {
                    when(isProcessing) {
                        true -> Icon(Icons.Outlined.PauseCircleFilled, "", tint = Color.Black, modifier = Modifier.testTag("pauseButton"))
                        false -> Icon(Icons.Outlined.SpeakerPhone, "", tint = Color.Black, modifier = Modifier.testTag("speakerButton"))
                    }
                }
            }
        },
        modifier = Modifier
            .testTag("textToSend")
            .width(300.dp)
            .height(48.dp)
    )
}

@Composable
private fun Listener(viewModel: CollectorViewModel) {
    val isProcessing by viewModel.isProcessing.observeAsState(false)
    val listenResult by viewModel.listenResult.observeAsState("")

    if (isProcessing) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp),
            color = SkyBlue,
            strokeWidth = 10.dp
        )
    } else if (listenResult.isNotEmpty()) {
        Text(
            text = if (viewModel.isSuccess()) stringResource(R.string.result_success) else stringResource(R.string.result_fail),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            color = if (viewModel.isSuccess()) SkyBlue else Color.Red
        )
        Text(
            text = "\"$listenResult\"",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CollectorPreview() {
    EuphonyHubCollectorTheme {
        CollectorView(
            viewModel = CollectorViewModel(PreferenceRepository(LocalContext.current))
        )
    }
}