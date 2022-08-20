package co.jbear.euphony_hub_collector.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.euphony.rx.EuRxManager
import co.euphony.tx.EuTxManager
import co.jbear.euphony_hub_collector.presentation.collector.TxRxCheckerViewModel
import co.jbear.euphony_hub_collector.ui.theme.EuphonyHubCollectorTheme
import co.jbear.euphony_hub_collector.util.Constants.TAG_BUTTON
import co.jbear.euphony_hub_collector.util.Constants.TAG_INPUT
import co.jbear.euphony_hub_collector.util.Constants.TAG_RESULT_FAIL
import co.jbear.euphony_hub_collector.util.Constants.TAG_RESULT_SUCCESS

@Composable
fun TxRxChecker(
    viewModel: TxRxCheckerViewModel,
    randomLength: Int = 5
) {
    val isProcessing by viewModel.isProcessing.observeAsState(false)
    var textToSend by rememberSaveable { mutableStateOf("") }
    val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTextField(
            text = textToSend,
            onTextChanged = { textToSend = it },
            enabled = (randomLength == 0),
            trailingIcon = {
                IconButton(
                    modifier = Modifier.testTag(TAG_BUTTON),
                    onClick = {
                    if (randomLength > 0) {
                        textToSend = (1..randomLength).map { charset.random() }.joinToString("")
                    }
                    viewModel.start(textToSend, 1)
                }) {
                    Icon(Icons.Outlined.PlayCircle, "", tint = Color.Black)
                }
            },
            modifier = Modifier
                .testTag(TAG_INPUT)
                .width(300.dp)
                .height(48.dp)
                .padding(end = 20.dp)
        )

        if (!isProcessing && viewModel.txCode.value!!.isNotEmpty()) {
            if (viewModel.isSuccess()) {
                Icon(Icons.Outlined.CheckCircle, "", tint = Color.Green, modifier = Modifier.testTag(TAG_RESULT_SUCCESS))
            } else {
                Icon(Icons.Outlined.Cancel, "", tint = Color.Red, modifier = Modifier.testTag(TAG_RESULT_FAIL))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TxRxCheckerPreview() {
    val viewModel = TxRxCheckerViewModel(EuTxManager(), EuRxManager())
    EuphonyHubCollectorTheme {
        TxRxChecker(viewModel = viewModel)
    }
}
