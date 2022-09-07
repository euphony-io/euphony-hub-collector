package co.euphony.euphony_hub_collector.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.euphony.euphony_hub_collector.ui.theme.LightSkyBlue
import co.euphony.euphony_hub_collector.ui.theme.Typography

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    enabled: Boolean = true,
    trailingIcon: @Composable()(() -> Unit),
) {
    TextField(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightSkyBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        placeholder = {
            Text(
                text = "Input Text",
                style = Typography.body1
            )
        },
        enabled = enabled,
        value = text,
        onValueChange = { onTextChanged(it) },
        singleLine = true
    )
}