@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todolist.ui.categories

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.ui.theme.bigBoltTextStyle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddNewCategoryScreen(
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getInsertFinishStateFlow().collectLatest {
            it?.let { navController.navigateUp() }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.ic_background_app),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            EnterCategoryName {
                viewModel.setNewCategoryName(it)
            }
            Spacer(modifier = Modifier.height(40.dp))
            CardColorPicker {
                viewModel.setNewCategoryColor(it)
            }

        }
        FloatingActionButtonWithCheck(
            onClick = {
                viewModel.createNewCategory()
            },
            Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
        )
    }
}

@Composable
fun FloatingActionButtonWithCheck(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(R.string.check_icon),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun EnterCategoryName(
    onInputTextChanged: (txt: String) -> Unit
) {
    var textValue by remember { mutableStateOf("") }
    Column {
        Spacer(modifier = Modifier.height(80.dp))
        TextField(
            modifier = Modifier.padding(start = 16.dp),
            value = textValue,
            onValueChange = {
                textValue = it
                onInputTextChanged(it)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.categories_enter_category_name),
                    style = bigBoltTextStyle
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            ),
            textStyle = TextStyle(fontSize = 32.sp),
        )
    }
}

@Composable
private fun CardColorPicker(onColorClick: (Color) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TitleCardColorPicker()
        Spacer(modifier = Modifier.height(16.dp))
        ColorPicker(onColorClick)
    }
}

@Preview
@Composable
private fun TitleCardColorPicker() {
    Text(
        text = stringResource(id = R.string.categories_choose_color),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        style = bigBoltTextStyle
    )
}

@Composable
private fun ColorPicker(onColorClick: (Color) -> Unit) {
    val colorList: List<Color> = listOf(
        Color(0xFFFF0000), // Червоний
        Color(0xFFEE6531), // Помаранчевий
        Color(0xFFFFA500), // Салатовий
        Color(0xFFFFFF00), // Жовтий
        Color(0xFFADFF2F), // Зелений
        Color(0xFF00BFFF), // Блакитний
        Color(0xFF0000FF), // Синій
        Color(0xFF8A2BE2), // Фіолетовий
        Color(0xFFFF69B4), // Рожевий
        Color(0xFF800000)  // Бордовий
    )
    var currentlySelected by remember { mutableStateOf(Color(0xFFFF0000)) }
    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        state = gridState,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(colorList) { color ->
            // Add a border around the selected colour only
            var modifier: Modifier = Modifier.padding(8.dp)
            var requiredSize = 32.dp
            if (currentlySelected == color) {
                requiredSize = 20.dp
                modifier = Modifier
                    .padding(8.dp)
                    .border(
                        width = 4.dp,
                        color = color,
                        shape = RoundedCornerShape(100.dp)
                    )
            }
            Box(
                modifier = modifier
            ) {
                Canvas(modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color)
                    .align(Alignment.Center)
                    .requiredSize(requiredSize)
                    .clickable {
                        currentlySelected = color
                        onColorClick(color)
                    }
                ) {}
            }
        }
    }
}

