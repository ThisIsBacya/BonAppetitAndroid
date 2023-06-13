package com.example.bonappetitandroid.repository.client

import com.example.bonappetitandroid.dto.Profile
import com.example.bonappetitandroid.dto.ProfileRegistration
import com.example.bonappetitandroid.dto.ProfileRegistrationWithoutRoleAndAddress
import io.github.jan.supabase.annotiations.SupabaseExperimental
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.admin.UserBuilder
import io.github.jan.supabase.plugins.standaloneSupabaseModule
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.jsonArray
import java.math.BigInteger
import java.security.MessageDigest


class SupabaseProfileClient {
    private val supabaseUrl = "https://qyqqzftymegxiemintrs.supabase.co/"
    private val supabaseKey =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF5cXF6ZnR5bWVneGllbWludHJzIiwicm9sZSI6ImFub24iLCJpYXQiOjE2Nzk4MzYyNzgsImV4cCI6MTk5NTQxMjI3OH0.7JPVILpQDuevPVj1HK-uihNh9u322o_Ow-J289mocvM"

    companion object {
        val INSTANCE = SupabaseProfileClient()
    }

    val client = createSupabaseClient(
        supabaseUrl, supabaseKey
    ) {
        install(Postgrest)
    }

    suspend fun addProfile(profile: ProfileRegistration) {
        val checkProfile = getProfile(profile.telephoneNumber, profile.password)
        if (checkProfile != null)
            return

        client.postgrest["profile"].insert(profile)
        println("Пользователь добавлен")
    }

    suspend fun addProfileWithoutAddress(profile: ProfileRegistrationWithoutRoleAndAddress) {
        val checkProfile = getProfile(profile.telephoneNumber, profile.password)
        if (checkProfile != null)
            return

        client.postgrest["profile"].insert(profile)
        println("Пользователь добавлен")
    }

    suspend fun setProfile(profile: ProfileRegistration) {
        val checkProfile = getProfile(profile.telephoneNumber, profile.password)
        if (checkProfile != null)
            return
        val md = MessageDigest.getInstance("MD5")
        val hashBytes = md.digest(profile.password.toByteArray())
        val hash = BigInteger(1, hashBytes).toString(16).padStart(32, '0')
        val profileHash = ProfileRegistration(profile.FIO, profile.telephoneNumber, profile.email, hash, profile.role, profile.address)

        client.postgrest["profile"].insert(profileHash)
        println("Пользователь добавлен")
    }

    suspend fun getProfile(numberPhone: String, password: String): Profile? {
        val result = client.postgrest["profile"].select {
            eq("telephoneNumber", numberPhone)
            eq("password", password)
        }

        if (result.body.jsonArray.isNotEmpty()) {
            println("Пользователь существует")
            return result.decodeSingle()
        }

        return null
    }

    suspend fun getProfileByEmail(email: String): Profile? {
        val result = client.postgrest["profile"].select {
            eq("email", email)
        }

        if (result.body.jsonArray.isNotEmpty()) {
            println("Пользователь существует")
            return result.decodeSingle()
        }

        return null
    }

    suspend fun getProfileByEmail(email: String, password: String): Profile? {
        val md = MessageDigest.getInstance("MD5")
        val hashPass = md.digest(password.toByteArray())
        val pass = BigInteger(1, hashPass).toString(16).padStart(32, '0')

        val regPass = INSTANCE.getProfileByEmail(email)?.password
        val hashPassMore = md.digest(password.toByteArray())
        val passMore = BigInteger(1, hashPassMore).toString(16).padStart(32, '0')

        if (regPass == pass) {
            val result = client.postgrest["profile"].select {
                eq("email", email)
                eq("password", password)
            }

            if (result.body.jsonArray.isNotEmpty()) {
                println("Пользователь существует")
                return result.decodeSingle()
            }
        }


        return null
    }


    suspend fun getProfileByPhone(numberPhone: String): Profile? {
        val result = client.postgrest["profile"].select {
            eq("telephoneNumber", numberPhone)
        }

        if (result.body.jsonArray.isNotEmpty()) {
            println("Пользователь существует")
            return result.decodeSingle()
        }

        return null
    }

}