package com.licoding.uber.home.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.licoding.uber.R
import com.licoding.uber.home.domain.models.SaveModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Save(){
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = {
            3
        }
    )
    val savemodels = listOf(
        SaveModel(
            image = painterResource(R.drawable.ubermap),
            label = "Uber Moto rides",
            description = "Affordable motorcycle pickups"
        ),
        SaveModel(
            image = painterResource(R.drawable.ubermoto),
            label = "Uber Moto rides",
            description = "Affordable motorcycle pickups"
        ),
        SaveModel(
            image = painterResource(R.drawable.ubershare),
            label = "Uber Moto rides",
            description = "Affordable motorcycle pickups"
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Save everyday",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            HorizontalPager(
                state = pagerState,
            ) {page ->
                savemodels.getOrNull(
                    page % (savemodels.size)
                ).let { model ->
                    Card(model)
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        var initialPage = Int.MAX_VALUE

        while(initialPage % savemodels.size != 0) {
            initialPage++
        }
        pagerState.scrollToPage(initialPage)
    })
}
