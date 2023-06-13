package com.example.restaurantandroid.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.linguaflow.R
import com.example.bonappetitandroid.basket
import com.example.bonappetitandroid.Eat
import com.example.bonappetitandroid.dto.OrderSet
import com.example.bonappetitandroid.repository.client.SupabaseOrderClient
import com.example.bonappetitandroid.repository.client.SupabaseProfileClient
import com.example.bonappetitandroid.ui.screens.coroutineScope
import com.example.bonappetitandroid.ui.screens.orders
import com.example.bonappetitandroid.ui.screens.profileLogin
import com.example.repository.client.SupabaseFoodClient
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*

val buttonHall = mutableStateOf(R.color.button_price)
val buttonDelivery = mutableStateOf(R.color.white)
var order = mutableListOf<Eat>()
var price = mutableStateOf(0)
var select = mutableStateOf("В зале")

@SuppressLint("SimpleDateFormat")
@Composable
fun BasketScreen() {
    var expanded by remember { mutableStateOf(false) }
    var orderList = mutableListOf<com.example.bonappetitandroid.dto.Food>()
    val snackbarHostState = remember {SnackbarHostState()}
    AnimatedVisibility(visible = basket.value, enter = fadeIn(), exit = fadeOut()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.black))
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Шапка
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.10f)
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
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp, 15.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                if (order.isEmpty())
                    OrderEmpty()
                else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(ScrollState(0))
                            .padding(0.dp, 0.dp, 0.dp, 60.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .width(300.dp)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Column(modifier = Modifier.fillMaxWidth(1f)) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Button(
                                        onClick = {
                                            buttonHall.value = R.color.button_price
                                            buttonDelivery.value = R.color.white
                                            select.value = "В зале"
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f),
                                        shape = RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(buttonHall.value)
                                        )
                                    ) {
                                        Text(
                                            text = "В зале",
                                            color = colorResource(buttonDelivery.value)
                                        )
                                    }
                                    Button(
                                        onClick = {
                                            buttonDelivery.value = R.color.button_price
                                            buttonHall.value = R.color.white
                                            select.value = "Доставка"
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(buttonDelivery.value)
                                        )
                                    ) {
                                        Text(
                                            text = "Доставка",
                                            color = colorResource(buttonHall.value)
                                        )
                                    }
                                }
                                Box {
                                    Button(
                                        onClick = { expanded = true },
                                        modifier = Modifier
                                            .height(32.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        IconButton(onClick = { expanded = true }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.drop),
                                                contentDescription = "Показать меню",
                                                modifier = Modifier.fillMaxWidth(0.95f)
                                            )
                                        }
                                        IconButton(onClick = { expanded = true }) {
                                            Icon(
                                                Icons.Default.ArrowDropDown,
                                                contentDescription = "Показать меню"
                                            )
                                        }
                                    }
                                    //                                    DropdownMenu(
//                                        expanded = expanded,
//                                        onDismissRequest = { expanded = false }) {
//                                        Text(
//                                            "Скопировать",
//                                            fontSize = 18.sp,
//                                            modifier = Modifier
//                                                .padding(10.dp)
//                                                .clickable(onClick = {})
//                                        )
//                                        Text(
//                                            "Вставить",
//                                            fontSize = 18.sp,
//                                            modifier = Modifier
//                                                .padding(10.dp)
//                                                .clickable(onClick = {})
//                                        )
//                                        Divider()
//                                        Text(
//                                            "Настройки",
//                                            fontSize = 18.sp,
//                                            modifier = Modifier
//                                                .padding(10.dp)
//                                                .clickable(onClick = {})
//                                        )
//                                }
                                }
                                // Заказ
                                if (order.isNotEmpty()) {
                                    order.forEachIndexed { index, item ->
                                        coroutineScope.launch {
                                            val food =
                                                SupabaseFoodClient.INSTANCE.getFoodByTitle(item.title!!)
                                            orderList.add(food)
                                        }
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
                                                        Text(
                                                            "калорийность - ${item.calories!!} ккал",
                                                            fontSize = 3.em,
                                                            color = Color.White
                                                        )
                                                    }
                                                }
                                                Box(
                                                    modifier = Modifier.padding(
                                                        0.dp,
                                                        10.dp,
                                                        0.dp,
                                                        0.dp
                                                    )
                                                ) {
                                                    AddFood(item = item, index = index)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(0.9f),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Column(modifier = Modifier.padding(0.dp, 0.dp, 15.dp, 0.dp)) {
                            Text(text = "Итог: ${price.value}р", color = Color.White)
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        try {
                                            val json = Json.encodeToString(orderList)
                                            println(profileLogin)
                                            println(json)
                                            val profileId =
                                                SupabaseProfileClient.INSTANCE.getProfileByEmail(
                                                    profileLogin.value.email
                                                )

                                            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                                            val currentDate = sdf.format(Date())
                                            SupabaseOrderClient.INSTANCE.setOrder(
                                                OrderSet(
                                                    select.value,
                                                    json,
                                                    profileId?.id,
                                                    price.value,
                                                    currentDate,
                                                    profileId?.address ?: ""
                                                )
                                            )

                                            snackbarHostState.showSnackbar("Заказ успешно оформлен")

                                            for (index in 0..order.lastIndex) {
                                                countEat[index] = order[index].num!!
                                                price.value = price.value

                                                order[index].num = 0
                                                price.value -= (countEat[index] * order[index].price!!)
                                                countEat[index] = order[index].num!!
                                                if (order[index].num == 0) {
                                                    order.remove(order[index])
                                                }
                                            }

                                            val profile =
                                                SupabaseProfileClient.INSTANCE.getProfileByEmail(
                                                    profileLogin.value.email,
                                                    profileLogin.value.password
                                                )
                                            if (profile != null) {
                                                SupabaseOrderClient.INSTANCE.getOrderByProfile(
                                                    profile.id!!
                                                ).forEach {
                                                    orders.add(it)
                                                }
                                            }
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = R.color.button_price)
                                )
                            ) {
                                Text(text = "Оплатить", color = Color.White)
                            }
                            SnackbarHost(
                                hostState = snackbarHostState,
                                snackbar = { data ->
                                    Snackbar(
                                        modifier = Modifier.padding(8.dp),
                                        backgroundColor = Color.Red, // Цвет фона Snackbar
                                        contentColor = Color.White // Цвет текста Snackbar
                                    ) {
                                        Text(text = data.message)
                                    }
                                }
                            )
                        }
                    }
                }
            }

        }
    }

}

@Composable
fun OrderEmpty() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.order_empty),
                contentDescription = "order empty",
                modifier = Modifier.size(250.dp)
            )
            Text(
                text = "Ой, вы пока ничего не заказали",
                style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily.Cursive),
                color = Color.White,
            )
        }
    }

}
