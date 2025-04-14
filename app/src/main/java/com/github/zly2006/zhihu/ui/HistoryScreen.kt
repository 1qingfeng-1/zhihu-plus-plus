package com.github.zly2006.zhihu.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.zly2006.zhihu.NavDestination
import com.github.zly2006.zhihu.ui.components.FeedCard
import com.github.zly2006.zhihu.ui.components.PaginatedList
import com.github.zly2006.zhihu.viewmodel.feed.HistoryViewModel

@Composable
fun HistoryScreen(
    onNavigate: (NavDestination) -> Unit
) {
    val viewModel: HistoryViewModel = viewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (viewModel.displayItems.isEmpty()) {
            viewModel.refresh(context)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PaginatedList(
            items = viewModel.displayItems,
            onLoadMore = { /* 不需要loadMore */ },
            isEnd = { true }  // 始终为true，因为没有更多数据需要加载
        ) { item ->
            FeedCard(item) {
                navDestination?.let {
                    onNavigate(it)
                }
            }
        }
    }
}
