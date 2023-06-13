package com.example.restaurantandroid.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.linguaflow.R
import com.example.bonappetitandroid.colorText
import com.example.bonappetitandroid.eat
import com.example.bonappetitandroid.showDialog
import com.example.bonappetitandroid.Eat
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

val w = mutableStateOf(
    Modifier.fillMaxWidth()
)

val color = mutableStateOf(R.color.black)
val countEat = mutableStateListOf<Int>()

@Composable
fun HomeScreen() {
    val coroutineScope = rememberCoroutineScope()
    val scrollStateY = rememberScrollState()
    val scrollStateX = rememberScrollState()
    var scrollToPosition by remember { mutableStateOf(0F) }

    val listScrollY = mutableListOf<Float>()
    val listScrollX = mutableListOf<Float>()



    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.18f)
        ) {
            Image(
                painter = painterResource(R.drawable.header),
                contentDescription = null, modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp, 70.dp)
                        .padding(20.dp, 0.dp, 0.dp, 0.dp),
                )
                Box(contentAlignment = Alignment.BottomStart) {
                    Row(
                        modifier = Modifier
                            .horizontalScroll(scrollStateX),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        eat.forEachIndexed { index, item ->

                            when (item.route) {
                                "lineCategory" -> {
                                    listScrollY.add(scrollToPosition)
                                    listScrollX.add(scrollToPosition)
                                    Button(
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(id = R.color.button_price),
//                                            contentColor = Color.White
                                        ),
                                        onClick = {
                                            coroutineScope.launch {
                                                scrollStateY.animateScrollTo(
                                                    listScrollY[item.price ?: 0].roundToInt(),
                                                    tween(2000),
                                                )

                                            }
                                            colorText.value = index
                                        },
                                        modifier = Modifier
                                            .onGloballyPositioned { coordinates ->
                                                listScrollX[item.price ?: 0] =
                                                    coordinates.positionInParent().x
                                            }
                                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                                    ) {
                                        if (colorText.value == index)
                                            Text(text = item.title ?: "null", color = Color.White)
                                        else
                                            Text(text = item.title ?: "null", color = Color.Gray)
                                    }
                                }
                            }

                        }

                    }
                }
            }

        }
        BoxWithConstraints {
            if (maxWidth < 420.dp)
                w.value = Modifier.fillMaxWidth()
            else
                w.value = Modifier.width(420.dp)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 50.dp)
                .verticalScroll(scrollStateY)
        ) {
            Column() {
                eat.forEachIndexed { index, item ->
                    countEat.add(0)
                    when (item.route) {
                        "lineCategory" -> {
                            Text(
                                item.title!!,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
                                    .onGloballyPositioned { coordinates ->
                                        listScrollY[item.price ?: 0] =
                                            coordinates.positionInParent().y
                                        if (coordinates.positionInRoot().y <= 2200.0f) {
                                            coroutineScope.launch {
                                                colorText.value = index
                                                scrollStateX.animateScrollTo(
                                                    listScrollX[item.price ?: 0].roundToInt(),
                                                    tween(1000)
                                                )
                                            }
                                        }
                                    },
                                fontSize = 6.em,
                                color = Color.White
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Image(
                                    painter = painterResource(id = item.icon!!),
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(350.dp, 10.dp)
                                        .clip(RoundedCornerShape(25.dp)),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.FillBounds
                                )
                            }

                        }
                        "lineSubcategory" -> {
                            Text(
                                item.title!!,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp),
                                color = Color.White
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Image(
                                    painter = painterResource(id = item.icon!!),
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(250.dp, 10.dp)
                                        .clip(RoundedCornerShape(25.dp)),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                        else -> {
                            Spacer(modifier = Modifier.size(15.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp, 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Column(modifier = w.value) {
                                    Row() {
                                        Image(
                                            painter = painterResource(id = item.icon!!),
                                            contentDescription = item.title,
                                            modifier = Modifier
                                                .size(150.dp)
                                                .clip(RoundedCornerShape(25.dp)),
                                            alignment = Alignment.Center,
                                            contentScale = ContentScale.FillBounds
                                        )
                                        Column(modifier = Modifier.padding(10.dp)) {
                                            Text(item.title!!, color = Color.White)
                                            Image(
                                                painter = painterResource(id = R.drawable.line),
                                                contentDescription = item.title,
                                                modifier = Modifier
                                                    .size(150.dp, 3.dp)
                                                    .clip(RoundedCornerShape(25.dp)),
                                                alignment = Alignment.Center,
                                                contentScale = ContentScale.FillBounds
                                            )
                                            Text(item.description!!, color = Color.White)
                                            Image(
                                                painter = painterResource(id = R.drawable.line),
                                                contentDescription = item.title,
                                                modifier = Modifier
                                                    .size(150.dp, 3.dp)
                                                    .clip(RoundedCornerShape(25.dp)),
                                                alignment = Alignment.Center,
                                                contentScale = ContentScale.FillBounds
                                            )
                                            Text(
                                                "каллорийность - ${item.calories!!} ккал",
                                                fontSize = 3.em,
                                                color = Color.White
                                            )
                                        }
                                    }
                                    Price(item, index)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Price(item: Eat, index: Int) {
    Box(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
        if (showDialog.value == index) {
            AddFood(item, index)
        } else {
            Button(
                onClick = {
                    showDialog.value = index
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.button_price),
                    contentColor = Color.White
                )
            ) {
                val cena = Text(text = "${item.price} ₽")
            }
        }
    }
}

@Composable
fun AddFood(item: Eat, index: Int) {
    Row(modifier = Modifier.width(150.dp), horizontalArrangement = Arrangement.Center) {
        countEat[index] = item.num!!
        price.value = price.value
        Button(
            onClick = {
                if (item.num != 0) {
                    item.num = item.num!! - 1
                    countEat[index] = item.num!!
                    price.value -= item.price!!
                    if (item.num == 0) {
                        order.remove(item)
                    }
                }

            },
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .border(0.dp, Color.White, CircleShape),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_price),
                contentColor = Color.White
            )
        ) {
            Text(text = "-")
        }
        Spacer(modifier = Modifier.size(7.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(0.dp, Color.White, CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center

        ) {
            Text(text = countEat[index].toString(), color = Color.Black)
        }
        Spacer(modifier = Modifier.size(7.dp))
        Button(
            onClick = {
                if (item.num == 0)
                    order.add(item)
                item.num = item.num!! + 1
                countEat[index] = item.num!!
                price.value += item.price!!
            },
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .border(0.dp, Color.White, CircleShape),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_price),
                contentColor = Color.White
            )
        ) {
            Text(text = "+")
        }
    }
}