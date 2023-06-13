package com.example.bonappetitandroid.dto

import com.example.bonappetitandroid.Eat
import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable



@Serializable
data class OrderGet(val idOrder: Int, val orderCategory: String, val listFood: String, val user: Int? = null, val price: Int, val date: String, val address: String)

@Serializable
data class Order(val idOrder: Int, val orderCategory: String, val listFood: List<Food>, val user: Int? = null, val price: Int, val date: String, val address: String)

@Serializable
data class OrderSet(val orderCategory: String, val listFood: String, val user: Int?, val price: Int, val date: String, val address: String)