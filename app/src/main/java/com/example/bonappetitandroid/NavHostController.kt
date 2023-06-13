package com.example.bonappetitandroid

import com.example.linguaflow.R

sealed class NavHostController(var route: String, var icon: Int, var title: String) {
    object home : NavHostController("home", R.drawable.eat, "Дом")
    object basket : NavHostController("basket", R.drawable.basket, "Корзина")
    object profile: NavHostController("profile", R.drawable.profile, "Профиль")
}
