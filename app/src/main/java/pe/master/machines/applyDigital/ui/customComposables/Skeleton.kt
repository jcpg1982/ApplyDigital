package pe.master.machines.applyDigital.ui.customComposables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.master.machines.applyDigital.helpers.Utils.shimmerColors
import pe.master.machines.applyDigital.ui.theme.BlueGray300
import pe.master.machines.applyDigital.ui.theme.ContentInset
import pe.master.machines.applyDigital.ui.theme.ContentInsetEight
import pe.master.machines.applyDigital.ui.theme.ContentInsetFive
import pe.master.machines.applyDigital.ui.theme.ContentInsetOne
import pe.master.machines.applyDigital.ui.theme.ContentInsetQuarter
import pe.master.machines.applyDigital.ui.theme.ContentInsetThirty
import pe.master.machines.applyDigital.ui.theme.ContentInsetTwo

@Preview
@Composable
fun LoadingSkeleton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        repeat(30) { animatedShimmer() }
    }
}

@Composable
fun animatedShimmer() {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, easing = FastOutSlowInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )
    shimmerGridItem(brush = brush)
}

@Composable
fun shimmerGridItem(brush: Brush) {
    val rounded = RoundedCornerShape(ContentInsetFive)
    OutlinedCard(
        border = BorderStroke(ContentInsetOne, BlueGray300),
        modifier = Modifier
            .fillMaxWidth()
            .padding(ContentInsetQuarter)
            .background(color = Color.Transparent)
            .clip(rounded)
    ) {
        Spacer(
            modifier = Modifier
                .padding(
                    start = ContentInsetEight,
                    end = ContentInsetEight,
                    top = ContentInsetEight
                )
                .fillMaxWidth()
                .height(ContentInsetThirty)
                .background(brush = brush, shape = RoundedCornerShape(ContentInsetEight))
        )
        Row(
            modifier = Modifier
                .padding(ContentInsetEight)
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .padding(end = ContentInsetTwo)
                    .width(ContentInset)
                    .height(ContentInsetEight)
                    .background(brush = brush, shape = RoundedCornerShape(ContentInsetEight))
            )

            Spacer(
                modifier = Modifier
                    .padding(end = ContentInsetTwo)
                    .width(ContentInsetQuarter)
                    .height(ContentInsetEight)
                    .background(brush = brush, shape = RoundedCornerShape(ContentInsetEight))
            )

            Spacer(
                modifier = Modifier
                    .width(ContentInset)
                    .height(ContentInsetEight)
                    .background(brush = brush, shape = RoundedCornerShape(ContentInsetEight))
            )
        }
    }
}