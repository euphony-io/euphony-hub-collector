package co.jbear.euphony_hub.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.jbear.euphony_hub.R
import co.jbear.euphony_hub.ui.theme.EuphonyHubTheme
import co.jbear.euphony_hub.ui.theme.LightGray

enum class BottomMenuContent(
    @StringRes val title: Int,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val route: String
) {
    COLLECTOR_VIEW(R.string.collector_view, Icons.Outlined.Mic, Icons.Filled.Mic, "home/collector_view"),
    RESULT_VIEW(R.string.result_view, Icons.Outlined.CloudUpload, Icons.Filled.CloudUpload,"home/result_view"),
    SETTING_VIEW(R.string.setting_view, Icons.Outlined.Settings, Icons.Filled.Settings, "home/setting_view"),
}

@Composable
fun BottomNavigator(
    items: List<BottomMenuContent>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(LightGray)
            .padding(1.dp)
    ) {
        items.forEach { item ->
            BottomMenuItem(
                item = item,
                isSelected = currentRoute == item.route,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .weight(1f)
            ) {
                navigateToRoute(item.route)
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    val color =
        if (isSelected) LocalContentColor.current else LocalContentColor.current.copy(alpha = 0.5F)

    Box(
        modifier = modifier
            .selectable(selected = isSelected, onClick = onItemClick)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                if(isSelected) item.iconSelected else item.icon,
                item.name,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Text(
                stringResource(id = item.title),
                color = color
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigatorPreview() {
    EuphonyHubTheme {
        Scaffold(
            bottomBar = {
                BottomNavigator(
                    items = BottomMenuContent.values().toList(),
                    currentRoute = BottomMenuContent.COLLECTOR_VIEW.route,
                    navigateToRoute = {},
                )
            }
        ) {}
    }
}
