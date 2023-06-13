package com.example.repository.client

import com.example.bonappetitandroid.dto.CategoryDTO
import com.example.bonappetitandroid.dto.CategoryToSubcategoryDTO
import com.example.bonappetitandroid.dto.Food
import com.example.bonappetitandroid.dto.SubcategoryDTO
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

class SupabaseFoodClient {
    private val supabaseUrl = "https://qyqqzftymegxiemintrs.supabase.co/"
    private val supabaseKey =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF5cXF6ZnR5bWVneGllbWludHJzIiwicm9sZSI6ImFub24iLCJpYXQiOjE2Nzk4MzYyNzgsImV4cCI6MTk5NTQxMjI3OH0.7JPVILpQDuevPVj1HK-uihNh9u322o_Ow-J289mocvM"
    companion object {
        val INSTANCE = SupabaseFoodClient()
    }
    val client = createSupabaseClient(
        supabaseUrl, supabaseKey
    ) {
        install(Postgrest)
    }

    suspend fun getAllFood(): List<Food> {
        val result = client.postgrest["food"].select().decodeList<Food>()

        return result
    }

    suspend fun getAllFoodByCategories(category: Int, subcategory: Int): List<Food> {
        val result = client.postgrest["food"].select {
            eq("category", category)
            eq("subcategory", subcategory)
        }.decodeList<Food>()

        return result
    }

    suspend fun getAllCategories(): List<CategoryDTO> {
        val result = client.postgrest["category"].select().decodeList<CategoryDTO>()

        return result
    }

    suspend fun getAllCategoryToSubcategory(category: Int): List<CategoryToSubcategoryDTO> {
        val result = client.postgrest["category_to_subcategory"].select("category, subcategory") {
            eq("category", category)
        }.decodeList<CategoryToSubcategoryDTO>()

        return result
    }

    suspend fun getSubcategory(id: Int): SubcategoryDTO {
        val result = client.postgrest["subcategory"].select {
            eq("id", id)
        }.decodeSingle<SubcategoryDTO>()

        return result
    }

    suspend fun getFoodById(id: Int): Food {
        val result = client.postgrest["food"].select {
            eq("id", id)
        }.decodeSingle<Food>()

        return result
    }

    suspend fun getFoodByTitle(title: String): Food {
        val result = client.postgrest["food"].select {
            eq("title", title)
        }.decodeSingle<Food>()
        return result
    }
    suspend fun setFood(food: Food) {
        client.postgrest["food"].insert(food)
    }
}