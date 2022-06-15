package co.jbear.euphony_collector.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.jbear.euphony_collector.data.repository.PreferenceRepository
import co.jbear.euphony_collector.presentation.collector.CollectorViewModel
import co.jbear.euphony_collector.ui.components.CustomTextField
import co.jbear.euphony_collector.ui.theme.EuphonyCollectorTheme
import co.jbear.euphony_collector.ui.theme.SkyBlue
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
        Speaker(viewModel)
    }
    Listener(viewModel)
}

@Composable
private fun Header() {
    Text(
        modifier = Modifier.padding(top = 80.dp),
        text = "Euphony Data Collector",
        style = MaterialTheme.typography.h3
    )

    Text(
        modifier = Modifier.padding(
            top = 16.dp,
            start = 30.dp,
            end = 30.dp,
        ),
        text = "Collector provides both speaker and listener mode, \n" +
               "so you can send data using euphony and get the result. \n" +
               "To send clearly, please set device volume to max.",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1
    )
}

@Composable
private fun Speaker(viewModel: CollectorViewModel) {
    var textToSend by rememberSaveable { mutableStateOf("") }

    CustomTextField(
        text = textToSend,
        onTextChanged = { textToSend = it },
        trailingIcon = {
            if (textToSend.isNotEmpty()) {
                IconButton(onClick = {
                    viewModel.listen()
                    viewModel.speak(textToSend)
                }) {
                    Icon(Icons.Outlined.SpeakerPhone, "", tint = Color.Black)
                }
            }
        },
        modifier = Modifier
            .padding(top = 40.dp)
            .height(48.dp)
    )
}

@Composable
private fun Listener(viewModel: CollectorViewModel) {
    val isProcessing by viewModel.isProcessing.observeAsState(false)
    val listenResult by viewModel.listenResult.observeAsState("")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isProcessing) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp),
                color = SkyBlue,
                strokeWidth = 10.dp
            )
        } else if (listenResult.isNotEmpty()) {
            val color = if (viewModel.getResult().equals("SUCCESS")) {
                SkyBlue
            } else {
                Color.Red
            }

            Text(
                text = viewModel.getResult(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                color = color
            )
            Text(
                text = "\"$listenResult\"",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectorPreview() {
    EuphonyCollectorTheme {
        CollectorView(
            viewModel = CollectorViewModel(PreferenceRepository(LocalContext.current))
        )
    }
}