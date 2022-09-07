package co.euphony.euphony_hub_collector.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.euphony.euphony_hub_collector.ui.theme.EuphonyHubCollectorTheme
import co.euphony.euphony_hub_collector.ui.theme.Typography

@Composable
fun CustomTextLabel(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .height(20.dp)
            .padding(end = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = text,
                style = Typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextLabel() {
    EuphonyHubCollectorTheme {
        CustomTextLabel(text = "Base16", color = Color.LightGray)
    }
}