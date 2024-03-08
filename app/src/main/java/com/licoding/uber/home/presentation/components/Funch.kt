import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

//import androidx.compose.foundation.gestures.detectVerticalDragGestures
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.launch
//
////package com.licoding.uber.home.presentation.components
////
////import androidx.compose.foundation.LocalOverscrollConfiguration
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.foundation.pager.HorizontalPager
////import androidx.compose.foundation.pager.rememberPagerState
////import androidx.compose.material3.Text
////import androidx.compose.runtime.CompositionLocalProvider
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.res.painterResource
////import androidx.compose.ui.unit.sp
////
////val pagerState = rememberPagerState(
////    initialPage = 0,
////    initialPageOffsetFraction = 0f,
////    pageCount = {
////        Int.MAX_VALUE
////    }
////)
////
////val items = listOf(
////    CardClass(
////        name = "GLOBAL PAY",
////        image = painterResource(id = R.drawable.mpesaglobalpay),
////        iconImage = painterResource(R.drawable.globalpayicon),
////        description = "This world is yours",
////        color = Color(0xFF50a400)
////    ),
////    CardClass(
////        name = "M-SHWARI",
////        image = painterResource(id = R.drawable.mshwari),
////        iconImage = painterResource(R.drawable.mshwarilogo),
////        description = "Go for it",
////        color = Color.White
////    )
////)
////
////Column(
////modifier = Modifier.fillMaxWidth()
////) {
////    Text(
////        text = "HIGHLIGHTED",
////        fontSize = 17.sp,
////        modifier = Modifier.fillMaxWidth()
////    )
////    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
////        HorizontalPager(
////            state = pagerState,
////        ) {page ->
////            items.getOrNull(
////                page % (items.size)
////            ).let { cardClass ->
////                Card(cardClass)
////            }
////        }
////    }
////}
////
////LaunchedEffect(key1 = Unit, block = {
////    var initialPage = Int.MAX_VALUE
////
////    while(initialPage % items.size != 0) {
////        initialPage++
////    }
////    pagerState.scrollToPage(initialPage)
////})
//
//
//.pointerInput(Unit) {
//    detectVerticalDragGestures { _, dragAmount ->
//        coroutineScope.launch {
//            previousOffset += dragAmount
////                        delay(1000)
////                        val remainingHeight = height.value - previousOffset
////                        val halfRemainingHeight = remainingHeight / 2
////                        if (dragAmount < halfRemainingHeight) {
////                            previousOffset = 0f
////                            return@launch
////                        }
////                        if (dragAmount > halfRemainingHeight) {
////                            previousOffset = remainingHeight
////                            return@launch
////                        }
////                        if (dragAmount > halfRemainingHeight || dragAmount < -halfRemainingHeight) {
////                            previousOffset = 0f
////                        }
////                        if (dragAmount in halfRemainingHeight - 1f..halfRemainingHeight + 1f) {
////                            previousOffset = remainingHeight
////                        }
////                        val bottomPosition = (previousOffset + height.value).coerceAtMost(400.dp.toPx())
////                        if (bottomPosition < 200.dp.toPx()) {
////                            previousOffset = 200.dp.toPx() - height.value
////                        }
//            val remainingHeight = height.value - previousOffset
//            // Calculate the half of the remaining height
//            val halfRemainingHeight = remainingHeight / 2
//            // Snap back to original position if drag amount is not equal to half of remaining height
//            if (dragAmount != halfRemainingHeight) {
//                previousOffset = 0f
//            }
//            // Snap to the bottom if drag amount is equal to half of remaining height
//            if (dragAmount == halfRemainingHeight) {
//                previousOffset = remainingHeight
//            }
//            // Ensure the component floats at the bottom of the screen when dragged
//            val bottomPosition = (previousOffset + height.value).coerceAtMost(400.dp.toPx()) // Adjust 400.dp as necessary
//            if (bottomPosition < 200.dp.toPx()) {
//                previousOffset = 200.dp.toPx() - height.value
//            }
//        }
//    }
//}
//.offset { IntOffset(0, previousOffset.toInt()) }
//
//var height by remember { mutableStateOf(200.dp) }
//var previousOffset by remember { mutableStateOf(0f) }
//val coroutineScope = rememberCoroutineScope()

//    val titleHeight = 50.dp
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    val visibleHeight = screenHeight - titleHeight
//
//    val hiddenOffset = height - visibleHeight
//
//    LaunchedEffect(height) {
//        previousOffset = hiddenOffset.value
//