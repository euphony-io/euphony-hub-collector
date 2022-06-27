package co.jbear.euphony_hub.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import co.jbear.euphony_hub.data.repository.FakeDataRepository
import co.jbear.euphony_hub.presentation.result.ResultViewModel
import co.jbear.euphony_hub.ui.theme.EuphonyHubTheme
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(route = "home/result_view")
fun ResultView(
    viewModel: ResultViewModel = hiltViewModel()
) {
    val items by viewModel.collectorLogs.observeAsState(listOf())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("result")
        if (items.isNotEmpty()) {
            Log.i("ResultView", viewModel.collectorLogs.value.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    EuphonyHubTheme {
        ResultView(
            viewModel = ResultViewModel(FakeDataRepository())
        )
    }
}