package co.jbear.euphony_hub_controller.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.jbear.euphony_hub_controller.data.repository.FakeDataRepository
import co.jbear.euphony_hub_controller.domain.entity.CollectorLog
import co.jbear.euphony_hub_controller.presentation.result.ResultViewModel
import co.jbear.euphony_hub_controller.ui.components.CustomTextLabel
import co.jbear.euphony_hub_controller.ui.components.SettingCategory
import co.jbear.euphony_hub_controller.ui.theme.EuphonyHubTheme
import co.jbear.euphony_hub_controller.ui.theme.LightGreen
import co.jbear.euphony_hub_controller.ui.theme.LightPink
import co.jbear.euphony_hub_controller.ui.theme.LightSkyBlue
import com.ramcosta.composedestinations.annotation.Destination
import java.text.SimpleDateFormat
import java.util.*

@Composable
@Destination(route = "home/result_view")
fun ResultView(
    viewModel: ResultViewModel = hiltViewModel()
) {
    val logs by viewModel.collectorLogs.observeAsState(listOf())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        item {
            SettingCategory(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Euphony Hub Result"
            )
        }
        items(logs) { log ->
            CollectorLogView(collectorLog = log)
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollectorLogView(
    collectorLog: CollectorLog,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            modifier = modifier.padding(start = 8.dp, top = 4.dp),
            text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(collectorLog.timestamp*1000)),
            style = MaterialTheme.typography.caption
        )
        Divider(modifier = modifier.padding(start = 8.dp, end = 8.dp))
        ListItem(
            icon = {
                Icon(Icons.Rounded.PlayArrow, null)
            },
            text = {
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = collectorLog.data,
                        style = MaterialTheme.typography.body1,
                        softWrap = true,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    CustomTextLabel(text = collectorLog.modulationType.name, color = LightSkyBlue)
                    CustomTextLabel(text = collectorLog.baseType.name, color = LightGreen)
                    CustomTextLabel(text = collectorLog.charsetType.name, color = LightPink)
                    CustomTextLabel(text = collectorLog.fileType.name, color = Color.LightGray)
                }
            },
            trailing = {
            },
            singleLineSecondaryText = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CollectorLogPreview() {
    EuphonyHubTheme {
        CollectorLogView(collectorLog = CollectorLog.getMockCollectorLog())
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