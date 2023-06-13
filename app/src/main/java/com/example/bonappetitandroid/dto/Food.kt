package com.example.bonappetitandroid.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(val id: Int, val category: String, val route: String)

@Serializable
data class CategoryToSubcategoryDTO(val category: Int, val subcategory: Int)

@Serializable
data class SubcategoryDTO(val id: Int, val subcategory: String, val route: String)

@Serializable
data class Category(val category: String, val route: String, val subcategory: MutableList<Subcategory>)

@Serializable
data class Subcategory(val subcategory: String? = null, val route: String? = null, val eat: List<Food>)

@Serializable
data class Food(
    var id: Int,
    var route: String,
    var icon: String,
    var title: String,
    var description: String,
    var calories: Int,
    var price: Int,
    var num: Int,
    val category: Int?,
    val subcategory: Int?
)