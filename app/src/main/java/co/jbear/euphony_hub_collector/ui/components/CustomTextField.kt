package co.jbear.euphony_hub_collector.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.jbear.euphony_hub_collector.ui.theme.LightSkyBlue
import co.jbear.euphony_hub_collector.ui.theme.Typography

@Composable
fun CustomTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    trailingIcon: @Composable()(() -> Unit),
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightSkyBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        placeholder = {
            Text(
                text = "Input Text",
                style = Typography.body1
            )
        },
        value = text,
        onValueChange = { onTextChanged(it) },
        singleLine = true
    )
}