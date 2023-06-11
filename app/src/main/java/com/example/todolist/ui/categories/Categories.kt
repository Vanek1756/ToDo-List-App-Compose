package com.example.todolist.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.R
import com.example.todolist.storage.entity.CategoryEntity
import com.example.todolist.ui.components.appbar.AppBar
import com.example.todolist.ui.theme.bigBoltTextStyle
import com.example.todolist.ui.utils.Items
import com.example.todolist.ui.utils.State

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    drawerState: DrawerState,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { AppBar(drawerState = drawerState) },
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            CategoriesContent(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary),
                viewModel
            )
        }
    }
}

@Composable
private fun CategoriesContent(modifier: Modifier, viewModel: CategoriesViewModel) {
    Column(modifier = modifier) {
        MyCategoriesTitleText()
        val categoriesItems by viewModel.getCategoriesStateFlow().collectAsStateWithLifecycle()
        GridCategoriesRow(
            categoriesItems,
            onEditClick = {
                // TODO() Edit category
            },
            onCreateCategoryButtonClick = {
                // TODO() Create category
            }
        )
    }
}

@Composable
private fun GridCategoriesRow(
    categoriesItems: List<Items<*>>,
    onEditClick: (item: CategoryEntity) -> Unit,
    onCreateCategoryButtonClick: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(categoriesItems) { item ->
                when (item.value) {
                    is CategoryEntity -> {
                        CategoryCard(item.value, onEditClick)
                    }

                    is Int -> {
                        ButtonNewCategory(onCreateCategoryButtonClick)
                    }
                }
            }
        })
}

@Composable
private fun ButtonNewCategory(onCreateCategoryButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)

    ) {
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Category",
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp)
                .clickable { onCreateCategoryButtonClick() }
        )
    }
}

@Composable
fun CategoryCard(item: CategoryEntity, onEditClick: (item: CategoryEntity) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp)
    ) {
        val (tvCategoryName, btnEditCategory, tvTaskCount, progressBar) = createRefs()
        Text(
            text = item.categoryName,
            modifier = Modifier.constrainAs(tvCategoryName) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top, margin = 8.dp)
            },
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Image(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit",
            modifier = Modifier
                .constrainAs(btnEditCategory) {
                    top.linkTo(tvCategoryName.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(tvCategoryName.bottom)
                    start.linkTo(tvCategoryName.end, margin = 8.dp)
                }
                .padding(start = 8.dp, end = 8.dp)
                .clickable { onEditClick(item) }
        )

        Text(
            text = "${item.listOfTasks.count()} tasks",
            modifier = Modifier.constrainAs(tvTaskCount) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(tvCategoryName.bottom, margin = 8.dp)
            },
            color = Color.Gray,
            fontSize = 12.sp
        )
        val completedTasks = item.listOfTasks.count { it.property[State.DONE] == true }
        val progress = if (item.listOfTasks.isNotEmpty()) {
            completedTasks.toFloat() / item.listOfTasks.count().toFloat()
        } else {
            0f
        }
        LinearProgressIndicator(
            modifier = Modifier
                .constrainAs(progressBar) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    top.linkTo(tvTaskCount.bottom, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                },
            progress = progress,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun MyCategoriesTitleText() {
    Text(
        text = stringResource(id = R.string.categories_my_categories),
        style = bigBoltTextStyle,
        modifier = Modifier.padding(start = 16.dp)
    )
}
