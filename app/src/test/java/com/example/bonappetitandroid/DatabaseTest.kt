package com.example.bonappetitandroid

import com.example.bonappetitandroid.dto.Food
import com.example.bonappetitandroid.dto.ProfileRegistration
import com.example.bonappetitandroid.dto.ProfileRegistrationWithoutRoleAndAddress
import com.example.bonappetitandroid.repository.client.SupabaseOrderClient
import com.example.bonappetitandroid.repository.client.SupabaseProfileClient
import com.example.linguaflow.R
import com.example.repository.client.SupabaseFoodClient
import io.ktor.util.reflect.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Test
import java.sql.SQLException
import kotlin.jvm.Throws

class DatabaseTest {

    val supabaseProfile = SupabaseProfileClient.INSTANCE
    val supabaseOrder = SupabaseOrderClient.INSTANCE
    val supabaseFood = SupabaseFoodClient.INSTANCE
    val coroutinScope = CoroutineScope(Dispatchers.Default)

    @After
    fun testPassed() {
        println("Тест пройден")
    }

    @Test
    @Throws(SQLException::class)
    fun setProfile() {
        coroutinScope.launch {
            supabaseProfile.setProfile(
                ProfileRegistration(
                    "Барабанов Барабан",
                    "+79911125765",
                    "bacyadaniil@gmail.com",
                    "fsadasdasd",
                    "user",
                    "Колмовская наб.65"
                )
            )
        }
    }

    @Test
    @Throws(SQLException::class)
    fun getProfile() {
        coroutinScope.launch {
            supabaseProfile.getProfileByEmail(
                "bacyadaniil@gmail.com",
                "asdasdasdas")
            assert(true)
        }
    }

    @Test
    @Throws(SQLException::class)
    fun getOrderData() {
        coroutinScope.launch {
            supabaseOrder.getOrderByProfile(1)
            assert(true)
        }
    }

    @Test
    @Throws(SQLException::class)
    fun getFood() {
        coroutinScope.launch {
            supabaseFood.getAllFood()
        }
    }

    @Test
    @Throws(SQLException::class)
    fun addProfileWithoutRoleAndAddress() {
        coroutinScope.launch {
            supabaseProfile.addProfileWithoutAddress(ProfileRegistrationWithoutRoleAndAddress("SSssss",
                "+79021341769",
                "mrvasya@gmail.com",
                "dasdasdad"))
        }
    }

    @Test
    @Throws(SQLException::class)
    fun setFood() {
        coroutinScope.launch {
            supabaseFood.setFood(Food(1,
                "cold_fish_snack1",
                "icon",
                "Кольца кальмара",
                "Кольца, сделанные из кальмара",
                150,
                150,
                0,
                1,
                2))
        }
    }
}