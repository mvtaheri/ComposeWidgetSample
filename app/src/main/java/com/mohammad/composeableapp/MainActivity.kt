package com.mohammad.composeableapp

import android.graphics.Paint.Align
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mohammad.composeableapp.ui.theme.ComposeableAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeableAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
//                    test()
//                    radioButtonGroupFun()
//                    var osList = listOf("Android", "Ios", "Windows", "Mac")
//                    PrepareLazyColumn(osList = osList)
                    LayoutTest()
                }
            }
        }
    }
}

@Composable
fun LayoutTest() {
    ConstraintLayout {
        val (redBox, blueBox, yelloBox, blackBox, greenBox) = createRefs()
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.matchParent
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                end.linkTo(redBox.end)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yelloBox) {
                top.linkTo(blueBox.bottom)
            })
        var guildLine = createGuidelineFromBottom(50.dp)
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Black)
            .constrainAs(blackBox) {

                bottom.linkTo(guildLine)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                end.linkTo(parent.end)
                bottom.linkTo(guildLine)
            })
//        createHorizontalChain(greenBox, blackBox, chainStyle = ChainStyle.SpreadInside)
//        createVerticalChain(greenBox,blackBox, chainStyle = ChainStyle.SpreadInside)

    }
}

@Composable
fun PrepareLazyColumn(osList: List<String>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
        content = {
            items(count = osList.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = CardDefaults.cardColors(Color.Blue),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .border(1.dp, Color.Red, RectangleShape)
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        Text(
                            text = osList[index],
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        })
}

private fun prepareBottomItem(): List<BottomItem> {
    var mutableList = ArrayList<BottomItem>()
    val b1 = BottomItem("Home", Icons.Filled.Home)
    val b2 = BottomItem("Setting", Icons.Filled.Settings)
    mutableList.add(b1)
    mutableList.add(b2)
    return mutableList
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun radioButtonGroupFun() {
    var optionList = listOf("vahid", "mohammad", "yaser")
    var (itemSelected, onOptionSelected) = remember {
        mutableStateOf(optionList[0])
    }
    var checked by remember {
        mutableStateOf(false)
    }
    var progressValue by remember {
        mutableStateOf(0f)
    }
    var checkList: List<String> = listOf("one", "two", "three", "four")
    val cntx = LocalContext.current.applicationContext
    val itemList = prepareBottomItem()
    var itemBarSelected by remember {
        mutableStateOf("Home")
    }
    Column {
        TopAppBar(
            title = { Text(text = "Navigation App") },
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(
                        cntx,
                        "This is Message",
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "navigation email")
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
            },
        )
        Column {
            checkList.forEach { item ->
                Row {
                    var check by remember {
                        mutableStateOf(true)
                    }
                    Checkbox(
                        checked = check,
                        onCheckedChange = { newchecked -> check = newchecked })
                    Text(text = item)
                }
            }
        }
        Row {
            Text(
                text = "Is True",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 13.dp)
            )
            Switch(checked = checked, onCheckedChange = { newCheck -> checked = newCheck })

        }
        Column(
            modifier = Modifier
                .selectableGroup()
        ) {
            optionList.forEach { label ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (itemSelected == label),
                            onClick = { onOptionSelected(label) },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(selected = (itemSelected == label),
                        onClick = { onOptionSelected(label) })
                    Text(text = label)
                }
            }

        }
        Row {
            CircularProgressIndicator(
                progress = progressValue.toFloat(),
                color = Color.Blue,
                modifier = Modifier.size(120.dp),
                strokeWidth = 6.dp
            )
            Button(onClick = {
                progressValue += 0.1f
            }) {
                Text(text = "Incress Progress")
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        BottomAppBar(modifier = Modifier.align(Alignment.BottomCenter)) {
            itemList.forEach { menu ->

                NavigationBarItem(
                    selected = (itemSelected == menu.title),
                    onClick = {
                        itemSelected = menu.title
                    },
                    icon = { Icon(imageVector = menu.icon, contentDescription = "Menu") },
                    label = { Text(text = menu.title) },
                    enabled = true
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun test() {
    var enteredValue by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = enteredValue,
            onValueChange = { newText: String -> enteredValue = newText },
            label = { Text(text = "Age:") },
            placeholder = { Text(text = "Enter Your Age") },
            leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit") },
            isError = isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    isError = checkValidate(enteredValue)
                })
        )
        if (isError)
            Text(text = "Age Must Biger than 18", color = MaterialTheme.colorScheme.error)
    }

}

fun checkValidate(age: String): Boolean {
    return age.toInt() < 18
}

@Composable
fun myUI() {
    val ctx = LocalContext.current
    val radioOption = listOf("Pizza", "Meat", "Fish")
    var (selectedItem, onOptionSelected) = remember {
        mutableStateOf(radioOption[0])
    }
    Column(modifier = Modifier.selectableGroup()) {
        radioOption.forEach { label ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (selectedItem == label),
                        onClick = { onOptionSelected(label) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = { onOptionSelected(label) },
                    selected = (selectedItem == label)
                )
                Text(text = label)
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeableAppTheme {
        Greeting("Android")
    }
}