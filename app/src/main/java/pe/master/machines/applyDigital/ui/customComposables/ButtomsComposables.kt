package pe.master.machines.applyDigital.ui.customComposables

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomIconButton(
    modifier: Modifier,
    iconNavigation: ImageVector?,
    iconNavigationColor: Color?,
    onClickNavigation: () -> Unit
) {
    iconNavigation?.let { iv ->
        iconNavigationColor?.let { inc ->
            Icon(
                imageVector = iv,
                contentDescription = "Action bar",
                modifier = modifier.clickable { onClickNavigation() },
                tint = inc
            )
        }
    }
}