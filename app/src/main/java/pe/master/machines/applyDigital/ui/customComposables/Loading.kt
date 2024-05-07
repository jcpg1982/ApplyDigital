package pe.master.machines.applyDigital.ui.customComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pe.master.machines.applyDigital.ui.theme.ColorWhite
import pe.master.machines.applyDigital.ui.theme.ContentInset
import pe.master.machines.applyDigital.ui.theme.ContentInsetOneHundred
import pe.master.machines.applyDigital.ui.theme.ContentInsetOneHundredFifty
import pe.master.machines.applyDigital.ui.theme.ContentInsetQuarter
import pe.master.machines.applyDigital.ui.theme.DynamicContentTextSize
import pe.master.machines.applyDigital.ui.theme.Orange500
import pe.master.machines.applyDigital.ui.theme.Turquoise500

@Preview
@Composable
fun PreviewLoading() {
    LoadingData(message = "Cargando")
}

@Composable
fun LoadingData(message: String) {
    Dialog(
        onDismissRequest = { }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator(
                    color = Turquoise500,
                    strokeWidth = ContentInsetQuarter,
                    modifier = Modifier
                        .size(ContentInsetOneHundredFifty)
                        .align(Alignment.Center)
                )
                Image(
                    imageVector = Icons.Filled.Check, contentDescription = "",
                    modifier = Modifier
                        .size(ContentInsetOneHundred)
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(Orange500)
                )
                /*Image(
                    painter = painterResource(id = R.drawable.ic_action_loading),
                    contentDescription = "Image Loading",
                    modifier = Modifier
                        .size(ContentInsetOneHundred)
                        .align(Alignment.Center),
                    alignment = Alignment.Center
                )*/
            }

            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ContentInset, end = ContentInset, top = ContentInset)
                    .align(Alignment.CenterHorizontally),
                color = ColorWhite,
                fontSize = DynamicContentTextSize,
                textAlign = TextAlign.Center
            )
        }
    }
}