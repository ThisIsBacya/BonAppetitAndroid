package com.example.bonappetitandroid.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.bonappetitandroid.dto.OrderGet
import com.example.bonappetitandroid.dto.ProfileRegistration
import com.example.bonappetitandroid.profile
import com.example.bonappetitandroid.repository.client.SupabaseProfileClient
import com.example.linguaflow.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

var forgotPassword = mutableStateOf(false)
var logIn = mutableStateOf(true)
var signUp = mutableStateOf(false)
val coroutineScope = CoroutineScope(Dispatchers.Main)
val profileLogin = mutableStateOf<ProfileRegistration>(
    ProfileRegistration(
        "", "", "", "", "", ""
    )
)

var orders = mutableStateListOf<OrderGet>()

@Composable
fun ProfileScreen() {
    val sharedPreferences = LocalContext.current.getSharedPreferences("Restaurant", 0)
    AnimatedVisibility(visible = profile.value, enter = fadeIn(), exit = fadeOut()) {
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

            val str = sharedPreferences.getString("profile", "null").toString()
            if (str != "null") {
                profileLogin.value = Json.decodeFromString<ProfileRegistration>(str)
                println(profileLogin.value.FIO)
                Profile()
            } else if (forgotPassword.value)
                ForgotPassword()
            else if (logIn.value)
                LoginPage()
            else if (signUp.value)
                SignUp()
            else
                Profile()


        }
    }
}

@Composable
fun Profile() {
    val sharedPreferences = LocalContext.current.getSharedPreferences("Restaurant", 0)
    val firstName = profileLogin.value.FIO.split(" ")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding(0.dp, 15.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(modifier = Modifier.width(300.dp)) {
            Column() {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            sharedPreferences.edit().remove("profile").apply()
                            profile.value = false
                            logIn.value = false
                            logIn.value = true
                            profile.value = true
                        }
                    },
                    modifier = Modifier.size(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),

                ) {
                    Text("x")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(text = firstName.first(), color = Color.White, fontSize = 6.em)
                        Text(text = firstName.last(), color = Color.White, fontSize = 6.em)
                    }

                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "profile",
                        modifier = Modifier.size(60.dp),
                        alignment = Alignment.BottomEnd
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "line",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Телефон: ${profileLogin.value.telephoneNumber}",
                        color = Color.White,
                        fontSize = 6.em,

                        )
                    print(profileLogin.value.telephoneNumber)
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Email: ${profileLogin.value.email}",
                        color = Color.White,
                        fontSize = 6.em,
                        modifier = Modifier.width(260.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(30.dp, 120.dp)
                ) {
                    Text(
                        text = "Адрес доставки: ${profileLogin.value.address}",
                        color = Color.White,
                        fontSize = 6.em,
                        modifier = Modifier.width(260.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(30.dp, 120.dp)
                        .padding(0.dp, 25.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = "История заказов:",
                        color = Color.White,
                        fontSize = 6.em,
                        modifier = Modifier.width(260.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(30.dp, 120.dp)
                        .padding(0.dp, 5.dp, 0.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Время заказа",
                        color = Color.White,
                        fontSize = 5.em,
                    )
                    Text(
                        text = "Сумма",
                        color = Color.White,
                        fontSize = 5.em,
                    )
                    Text(
                        text = "Чек",
                        color = Color.White,
                        fontSize = 5.em,
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "line",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds
                )
                orders.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(30.dp, 120.dp)
                            .padding(0.dp, 5.dp, 0.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = it.date,
                            color = Color.White,
                            fontSize = 4.em,
                        )
                        Text(
                            text = "${it.price} ₽",
                            color = Color.White,
                            fontSize = 4.em,
                        )
                        Text(
                            text = "Посмотреть",
                            color = Color.White,
                            fontSize = 4.em,
                            textDecoration = TextDecoration.Underline
                        )
                    }

                }
            }
        }


    }
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = {
                profile.value = false
                logIn.value = true
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_price)
            )
        ) {
            Text(text = "Выйти из профиля", color = Color.White)
        }
    }
}


@Composable
fun ForgotPassword() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding(0.dp, 15.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .width(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val username = remember {
                mutableStateOf(TextFieldValue())
            }

            Text(
                text = "Forgot Password",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label = { Text(text = "Email", color = Color.White) },
                value = username.value,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White
                ),
                onValueChange = { username.value = it },
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.button_price)
                    )
                ) {
                    Text(text = "Забыли пароль", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun LoginPage() {
    val sharedPreferences = LocalContext.current.getSharedPreferences("Restaurant", 0)
    val snackbarHostState = remember { SnackbarHostState() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding(0.dp, 15.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .width(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val email = remember {
                mutableStateOf(TextFieldValue())
            }
            val password = remember {
                mutableStateOf(TextFieldValue())
            }


            Text(
                text = "Логин",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label = { Text(text = "Email", color = Color.White) },
                value = email.value,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.White
                ),
                onValueChange = { email.value = it }
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label = { Text(text = "Пароль", color = Color.White) },
                value = password.value,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.White
                ),
                onValueChange = { password.value = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {

                        coroutineScope.launch {
                            try {
                                val result =
                                    SupabaseProfileClient.INSTANCE.getProfileByEmail(email.value.text)
                                if (result == null) {
                                    snackbarHostState.showSnackbar("Такого пользователя не существует")
                                } else {
                                    val profileObject = ProfileRegistration(
                                        result.FIO,
                                        result.telephoneNumber,
                                        result.email, result.password,
                                        result.role,
                                        result.address ?: ""
                                    )
                                    val json = Json.encodeToString(profileObject)
                                    sharedPreferences.edit().putString("profile", json).apply()
                                    profile.value = true
                                    logIn.value = false
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.button_price)
                    )
                ) {
                    Text(text = "Войти", color = Color.White)
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { data ->
                    Snackbar(
                        modifier = Modifier.padding(8.dp),
                        backgroundColor = Color.Blue, // Цвет фона Snackbar
                        contentColor = Color.White // Цвет текста Snackbar
                    ) {
                        Text(text = data.message)
                    }
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            ClickableText(
                text = AnnotatedString("Забыли пароль?"),
                onClick = {
                    forgotPassword.value = true
                    logIn.value = false
                },
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Default,
                    color = Color.White
                )
            )
            ClickableText(
                text = AnnotatedString("Зарегистрироваться"),
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    signUp.value = true
                    logIn.value = false
                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(id = R.color.button_price)
                )
            )
        }
    }

}

@Composable
fun SignUp() {
    val sharedPreferences = LocalContext.current.getSharedPreferences("Restaurant", 0)
    val snackbarHostState = remember { SnackbarHostState() }
    val showSnackbar = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding(0.dp, 15.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .width(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            ФИО
            val username = remember {
                mutableStateOf(TextFieldValue())
            }
            var phoneNumber = remember {
                mutableStateOf(TextFieldValue())
            }

//            Email
            val email = remember {
                mutableStateOf(TextFieldValue())
            }
//            Пароль
            val password = remember {
                mutableStateOf(TextFieldValue())
            }
////            Подтверждение пароля
//            val passwordConfirm = remember {
//                mutableStateOf(TextFieldValue())
//            }
            var showPassword = remember { mutableStateOf(value = false) }

            Text(
                text = "Регистрация",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label = { Text(text = "ФИО", color = Color.White) },
                value = username.value,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.White
                ),
                onValueChange = { username.value = it }
            )

            Spacer(modifier = Modifier.height(15.dp))


            TextField(

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                label = { Text(text = "Номер телефона", color = Color.White) },
                value = phoneNumber.value,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.White,
                ),
                onValueChange = { phoneNumber.value = it },
            )



            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = "Email", color = Color.White) },
                value = email.value,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.White
                ),
                onValueChange = { email.value = it }
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label = { Text(text = "Введите пароль", color = Color.White) },
                value = password.value,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.White
                ),
                onValueChange = { password.value = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(15.dp))

//            TextField(
//                label = { Text(text = "Подтвердите пароль", color = Color.White) },
//                value = passwordConfirm.value,
//                colors = TextFieldDefaults.textFieldColors(
//                    cursorColor = Color.White,
//                    focusedIndicatorColor = Color.White
//                ),
//                onValueChange = { passwordConfirm.value = it },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//            )
//
//            Spacer(modifier = Modifier.height(15.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val result =
                                SupabaseProfileClient.INSTANCE.getProfileByEmail(email.value.text)
                            if (result == null) {
                                val profileObject = ProfileRegistration(
                                    username.value.text,
                                    phoneNumber.value.text,
                                    email.value.text,
                                    password.value.text,
                                    "user",
                                    ""
                                )
                                SupabaseProfileClient.INSTANCE.setProfile(
                                    profileObject
                                )
                                val json = Json.encodeToString(profileObject)
                                sharedPreferences.edit().putString("profile", json).apply()

                                profileLogin.value = ProfileRegistration(
                                    username.value.text,
                                    phoneNumber.value.text,
                                    email.value.text,
                                    password.value.text,
                                    "",
                                    ""
                                )
                                signUp.value = false
                                logIn.value = false
                                profile.value = true
                            } else {
                                snackbarHostState.showSnackbar("Такой пользователь уже существует")
                            }
                            println(result)
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.button_price)
                    )
                ) {
                    Text(text = "Зарегистрироваться", color = Color.White)
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

            if (showSnackbar.value) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        Button(onClick = { showSnackbar.value = false }) {
                            Text(text = "OK")
                        }
                    },
                    content = {
                        Text(text = "Такой пользователь уже существует.")
                    }
                )
            }

            ClickableText(
                text = AnnotatedString("Войти"),
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    signUp.value = false
                    logIn.value = true
                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(id = R.color.button_price)
                )
            )
        }
    }
}
