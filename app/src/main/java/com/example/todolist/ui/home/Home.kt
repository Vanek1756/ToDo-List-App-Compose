package com.example.todolist.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.R
import com.example.todolist.storage.entity.CategoryEntity
import com.example.todolist.ui.categories.CategoryCard
import com.example.todolist.ui.components.appbar.AppBar
import com.example.todolist.ui.home.done.DoneTasks
import com.example.todolist.ui.home.pending.PendingTasks
import com.example.todolist.ui.home.today.TodayTasks
import com.example.todolist.ui.theme.bigBoltTextStyle
import com.example.todolist.ui.theme.normalTextStyle
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { AppBar(drawerState = drawerState) },
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.ic_background_app),
                        contentScale = ContentScale.FillBounds
                    ),

                viewModel
            )
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    Column(modifier = modifier) {
        HelloText()
        CategoriesTitleText()
        val categoriesItems by viewModel.getCategoriesStateFlow().collectAsStateWithLifecycle()
        CategoriesRow(categoriesItems) {
            // TODO() Edit categories
        }
        TabScreen()
    }
}

@Composable
private fun HelloText() {
    Text(
        text = stringResource(id = R.string.home_hello),
        style = bigBoltTextStyle,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
private fun CategoriesTitleText() {
    Text(
        text = stringResource(id = R.string.home_categories),
        style = normalTextStyle,
        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
    )
}

@Composable
private fun CategoriesRow(
    categoriesItems: List<CategoryEntity>,
    onEditClick: (item: CategoryEntity) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
        )
    ) {
        items(categoriesItems) { item ->
            CategoryCard(item, onEditClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TabScreen() {
    val tabs = stringArrayResource(id = R.array.home_tab_bar)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()


    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Color(android.graphics.Color.parseColor("#e1d4f1"))
                )
            },
            divider = { DividerDefaults }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            title,
                            color = if (pagerState.currentPage == index) Color.Black else Color.Gray
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        TabContent(tabs, pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TabContent(tabData: Array<String>, pagerState: PagerState) {
    HorizontalPager(pageCount = tabData.size, state = pagerState) { index ->
        when (index) {
            0 -> {
                TodayTasks()
            }

            1 -> {
                PendingTasks()
            }

            2 -> {
                DoneTasks()
            }
        }

    }

}
