package pe.master.machines.applyDigital.ui.customComposables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import pe.master.machines.applyDigital.ui.theme.ContentInset
import pe.master.machines.applyDigital.ui.theme.DynamicTitleTextSize
import pe.master.machines.applyDigital.ui.theme.regular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbarCompose(
    title: String,
    iconNavigation: ImageVector?,
    iconNavigationColor: Color,
    onClickNavigation: () -> Unit,
    backgroundColor: Color
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier,
                color = iconNavigationColor,
                fontSize = DynamicTitleTextSize,
                maxLines = 1,
                fontFamily = FontFamily(regular)
            )
        },
        navigationIcon = {
            iconNavigation?.let {
                CustomIconButton(
                    modifier = Modifier.padding(start = ContentInset),
                    iconNavigation = it,
                    iconNavigationColor = iconNavigationColor
                ) { onClickNavigation() }
            }
        },
        actions = {},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
    )
}