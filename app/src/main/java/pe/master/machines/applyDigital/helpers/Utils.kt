package pe.master.machines.applyDigital.helpers

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import pe.master.machines.applyDigital.ui.theme.ContentInset
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import kotlin.math.floor

object Utils {

    val getEnterTransition: EnterTransition
        get() {
            val fastOutSlowIn = FastOutSlowInEasing
            return slideInHorizontally(
                initialOffsetX = { 1000 }, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + fadeIn(
                initialAlpha = 0.3f, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + scaleIn(
                initialScale = 0.9f, animationSpec = tween(700, easing = fastOutSlowIn)
            )
        }

    val getExitTransition: ExitTransition
        get() {
            val fastOutSlowIn = FastOutSlowInEasing
            return slideOutHorizontally(
                targetOffsetX = { -1000 }, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + fadeOut(
                targetAlpha = 0.3f, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + scaleOut(
                targetScale = 0.9f, animationSpec = tween(700, easing = fastOutSlowIn)
            )
        }

    val shimmerColors
        get() = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f)
        )

    val roundedImage
        get() = RoundedCornerShape(
            topStart = ContentInset,
            topEnd = ContentInset,
            bottomEnd = ContentInset,
            bottomStart = ContentInset
        )

    val <T> T?.objectToString: String get() = if (this != null) Gson().toJson(this) else ""

    fun <T> String?.stringToObject(clase: Class<T>): T? {
        if (isNullOrEmpty()) return null
        return try {
            Gson().fromJson(this, clase)
        } catch (e: Exception) {
            null
        }
    }

    fun timeElapsed(date: String): String {
        var dateNow = System.currentTimeMillis()
        var dateOld = parseDate(date)
        var difference = dateNow - dateOld

        val minutes = TimeUnit.MILLISECONDS.toMinutes(difference).toDouble()
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = days / 30
        val years = days / 365

        return when {
            minutes < 60 -> "%.1f minutos".format(minutes)
            hours < 24 -> "%.1f horas".format(hours)
            days < 2 -> "ayer"
            days < 7 -> "%.1f días".format(days)
            weeks <= 4 -> "%.1f semanas".format(weeks)
            months <= 12 -> "%.1f meses".format(months)
            else -> "%.1f años".format(years)
        }
    }

    fun parseDate(dateTime: String): Long {
        val newDate = dateTime.replace("T", " ").replace("Z", "")
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val date = formatter.parse(newDate)
        return date.time
    }

}