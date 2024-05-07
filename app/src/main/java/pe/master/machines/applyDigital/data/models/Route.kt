package pe.master.machines.applyDigital.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import pe.master.machines.applyDigital.helpers.Constants
import java.net.URLEncoder

sealed class Route(val id: String, open var route: String, val title: String) {

    var icon: ImageVector? = when (this) {
        is Home -> null
        is HitDetail -> Icons.AutoMirrored.Filled.ArrowBack
    }

    data object Home : Route(
        id = Constants.Routes.HOME,
        route = Constants.Routes.HOME,
        title = "Inicio"
    )

    data class HitDetail(
        override var route: String = "${Constants.Routes.HIT_DETAIL}/{webUrl}"
    ) : Route(
        id = Constants.Routes.HIT_DETAIL,
        route = route,
        title = "Detalle"
    ){
        fun create(highlightResult: String) = this.apply {
            val encode = URLEncoder.encode(highlightResult, "UTF-8")
            route = "${Constants.Routes.HIT_DETAIL}/$encode"
        }
    }
}
