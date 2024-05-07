package pe.master.machines.applyDigital.ui.viewComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import pe.master.machines.applyDigital.data.models.Route
import pe.master.machines.applyDigital.data.models.response.HighlightResult
import pe.master.machines.applyDigital.data.uiEvents.UiEventHome
import pe.master.machines.applyDigital.data.uiState.UiState
import pe.master.machines.applyDigital.helpers.Utils.stringToObject
import pe.master.machines.applyDigital.ui.customComposables.LoadingSkeleton
import pe.master.machines.applyDigital.ui.customComposables.RowHomeSwipe
import pe.master.machines.applyDigital.ui.customComposables.animatedShimmer
import pe.master.machines.applyDigital.ui.theme.ColorWhite
import pe.master.machines.applyDigital.ui.theme.ContentInsetEight
import pe.master.machines.applyDigital.ui.theme.ContentInsetQuarter
import pe.master.machines.applyDigital.ui.viewModels.HomeViewModel
import pe.master.machines.applyDigital.ui.viewModels.NavigationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navigationViewModel: NavigationViewModel) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val getCloudHit by homeViewModel.getCloudHit.collectAsStateWithLifecycle()
    val loadSkeleton by homeViewModel.loadSkeleton.collectAsStateWithLifecycle()
    val loadingData by homeViewModel.loadingData.collectAsStateWithLifecycle()
    val dataList by homeViewModel.dataList.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val state = rememberPullToRefreshState()

    if (state.isRefreshing) {
        LaunchedEffect(Unit) {
            homeViewModel.handleEvent(UiEventHome.LoadDataCloud)
            state.endRefresh()
        }
    }

    LaunchedEffect(getCloudHit) {
        when (val state = getCloudHit) {
            is UiState.Loading -> if (state.isLoading) when (homeViewModel.page) {
                0 -> {
                    homeViewModel.updateLoadingData(false)
                    homeViewModel.updateLoadSkeleton(true)
                }

                else -> {
                    homeViewModel.updateLoadSkeleton(false)
                    homeViewModel.updateLoadingData(true)
                }
            }

            is UiState.NotSuccess -> {
                homeViewModel.updateLoadSkeleton(false)
                homeViewModel.updateLoadingData(false)
            }

            is UiState.Success -> {
                homeViewModel.updateLoadSkeleton(false)
                homeViewModel.updateLoadingData(false)
            }
        }
    }
    if (loadSkeleton) {
        LoadingSkeleton()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                state = listState, modifier = Modifier
                    .nestedScroll(state.nestedScrollConnection)
                    .fillMaxSize()
                    .padding(
                        top = ContentInsetEight, bottom = ContentInsetEight
                    ), verticalArrangement = Arrangement.spacedBy(ContentInsetQuarter)
            ) {
                items(dataList) { item ->
                    RowHomeSwipe(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ColorWhite)
                            .clickable {
                                item.highlightResult?.let { highlightResult ->
                                    val classhighlightResult =
                                        highlightResult.stringToObject(HighlightResult::class.java)
                                    val classStoryUrl = classhighlightResult?.storyUrl
                                    classStoryUrl?.value?.let {value->
                                        navigationViewModel.onNavigate(
                                            Route
                                                .HitDetail()
                                                .create(value)
                                        )
                                    }
                                }
                            },
                        title = item.storyTitle.orEmpty(),
                        autor = item.author.orEmpty(),
                        createdAt = item.timeElapsed.orEmpty(),
                        onDeleted = { homeViewModel.handleEvent(UiEventHome.DeleteHit(item)) })
                }
                if (loadingData) {
                    item {
                        animatedShimmer()
                    }
                }
            }
            PullToRefreshContainer(
                state = state,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collectLatest { index ->
            if (!loadingData && index != null && index >= dataList.size - 1) {
                homeViewModel.handleEvent(UiEventHome.LoadMoreDataCloud)
            }
        }
    }
}