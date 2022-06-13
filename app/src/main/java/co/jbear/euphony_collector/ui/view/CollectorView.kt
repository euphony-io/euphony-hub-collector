package co.jbear.euphony_collector.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.jbear.euphony_collector.ui.components.CustomTextField
import co.jbear.euphony_collector.ui.theme.EuphonyCollectorTheme
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(route = "home/collector_view", start = true)
fun CollectorView() {

    var textToSend by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        CustomTextField(
            text = textToSend,
            onTextChanged = { textToSend = it },
            modifier = Modifier
                .padding(top = 40.dp)
                .height(48.dp)
        )
    }
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
                "so you can send data using euphony and get the result.",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1
    )
}

@Preview(showBackground = true)
@Composable
fun CollectorPreview() {
    EuphonyCollectorTheme {
        CollectorView()
    }
}