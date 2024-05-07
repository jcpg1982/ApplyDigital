package pe.master.machines.applyDigital.ui.customComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pe.master.machines.applyDigital.ui.theme.BlueGray100
import pe.master.machines.applyDigital.ui.theme.BlueGray300
import pe.master.machines.applyDigital.ui.theme.BlueGray500
import pe.master.machines.applyDigital.ui.theme.ColorWhite
import pe.master.machines.applyDigital.ui.theme.ContentInset
import pe.master.machines.applyDigital.ui.theme.ContentInsetEight
import pe.master.machines.applyDigital.ui.theme.ContentInsetTwelve
import pe.master.machines.applyDigital.ui.theme.ContentInsetTwo
import pe.master.machines.applyDigital.ui.theme.DynamicMiniTextSize
import pe.master.machines.applyDigital.ui.theme.DynamicTitleTextSize
import pe.master.machines.applyDigital.ui.theme.Red500

@Preview
@Composable
fun GetPreview() {
    RowHomeSwipe(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorWhite),
        title = "Juanity",
        autor = "juanito los palotes",
        createdAt = "24/05/1982"
    ) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowHomeSwipe(
    modifier: Modifier,
    title: String, autor: String, createdAt: String, onDeleted: () -> Unit
) {
    var isItemRemoved by remember { mutableStateOf(false) }
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                isItemRemoved = true
                false
            } else true
        }
    )

    LaunchedEffect(isItemRemoved) {
        if (isItemRemoved) {
            onDeleted()
            isItemRemoved = false
        }
    }

    SwipeToDismissBox(
        state = state,
        backgroundContent = { DeleteBackground(swipeDismissState = state) },
        enableDismissFromStartToEnd = false,
        content = {
            RowHome(
                modifier = modifier,
                title = title,
                autor = autor,
                createdAt = createdAt
            )
        }
    )
}

@Composable
fun RowHome(
    modifier: Modifier,
    title: String, autor: String, createdAt: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = ContentInset,
                    end = ContentInset,
                    top = ContentInsetEight
                ),
            color = BlueGray500,
            fontSize = DynamicTitleTextSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Text(
            text = "$autor - $createdAt",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = ContentInset,
                    end = ContentInset,
                    top = ContentInsetEight,
                    bottom = ContentInsetEight
                ),
            color = BlueGray300,
            fontSize = DynamicMiniTextSize,
            textAlign = TextAlign.Start
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(), thickness = ContentInsetTwo, color = BlueGray100
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(swipeDismissState: SwipeToDismissBoxState) {
    val color = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        Red500
    } else Color.Transparent
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color, shape = RoundedCornerShape(ContentInsetTwelve))
            .padding(ContentInset),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = "Delete",
            color = ColorWhite,
            fontSize = DynamicTitleTextSize,
            fontWeight = FontWeight.Bold
        )
    }
}