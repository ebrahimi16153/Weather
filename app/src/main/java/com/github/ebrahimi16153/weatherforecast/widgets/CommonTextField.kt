package com.github.ebrahimi16153.weatherforecast.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.ebrahimi16153.weatherforecast.color.MyColors

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MyColors().text.value,
            cursorColor = MyColors().text.value,
            backgroundColor = MyColors().sheetBackground.value,
            disabledBorderColor = MyColors().text.value,
            focusedBorderColor = MyColors().background.value,
            unfocusedBorderColor = MyColors().background.value,
            disabledLabelColor = MyColors().text.value,
            focusedLabelColor = MyColors().text.value
        ),
        shape = RoundedCornerShape(15.dp),


        )


}