//package com.licoding.uber.home.presentation.components
//
//import androidx.compose.foundation.LocalOverscrollConfiguration
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.material3.Text
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.sp
//
//val pagerState = rememberPagerState(
//    initialPage = 0,
//    initialPageOffsetFraction = 0f,
//    pageCount = {
//        Int.MAX_VALUE
//    }
//)
//
//val items = listOf(
//    CardClass(
//        name = "GLOBAL PAY",
//        image = painterResource(id = R.drawable.mpesaglobalpay),
//        iconImage = painterResource(R.drawable.globalpayicon),
//        description = "This world is yours",
//        color = Color(0xFF50a400)
//    ),
//    CardClass(
//        name = "M-SHWARI",
//        image = painterResource(id = R.drawable.mshwari),
//        iconImage = painterResource(R.drawable.mshwarilogo),
//        description = "Go for it",
//        color = Color.White
//    )
//)
//
//Column(
//modifier = Modifier.fillMaxWidth()
//) {
//    Text(
//        text = "HIGHLIGHTED",
//        fontSize = 17.sp,
//        modifier = Modifier.fillMaxWidth()
//    )
//    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
//        HorizontalPager(
//            state = pagerState,
//        ) {page ->
//            items.getOrNull(
//                page % (items.size)
//            ).let { cardClass ->
//                Card(cardClass)
//            }
//        }
//    }
//}
//
//LaunchedEffect(key1 = Unit, block = {
//    var initialPage = Int.MAX_VALUE
//
//    while(initialPage % items.size != 0) {
//        initialPage++
//    }
//    pagerState.scrollToPage(initialPage)
//})