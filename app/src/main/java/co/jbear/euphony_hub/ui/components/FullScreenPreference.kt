package co.jbear.euphony_hub.ui.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.jbear.euphony_hub.ui.theme.EuphonyHubTheme

@Composable
fun FullscreenPreference(
    title: String,
    items: List<Pair<String, Pair<String, String>>>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            Surface(elevation = 4.dp) {
                TopAppBar(
                    title = { Text(text = title) },
                    contentColor = MaterialTheme.colors.onSurface,
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    navigationIcon = {
                        IconButton(
                            onClick = onNavigateBack,
                            content = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                )
                            },
                        )
                    },
                )
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(items) { item ->
                RadioPreferenceItem(
                    title = item.second.first,
                    subtitle = item.second.second,
                    selected = selectedItem == item.first,
                    onClick = {
                        onItemSelected(item.first)
                    },
                )
            }
        }
    }
}

@Composable
fun RadioPreferenceItem(
    title: String,
    subtitle: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = LocalIndication.current,
            )
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp,
            ),
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            interactionSource = interactionSource,
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(weight = 1f, fill = true),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview
@Composable
private fun FullScreenPreferencePreview() {
    var selectedItem by remember { mutableStateOf("lorem") }

    EuphonyHubTheme {
        Surface(color = MaterialTheme.colors.background) {
            FullscreenPreference(
                title = "Lorem ipsum setting",
                items = listOf(
                    "lorem" to ("Lorem ipsum" to "lorem"),
                    "dolor" to ("Dolor sit amet" to "dolor"),
                    "foo" to ("Foo bar" to "foobar"),
                ),
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it },
                onNavigateBack = { },
            )
        }
    }
}