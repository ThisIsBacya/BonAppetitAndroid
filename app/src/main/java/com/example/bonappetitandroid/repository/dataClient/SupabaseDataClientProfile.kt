package com.example.bonappetitandroid.repository.dataClient

import com.example.bonappetitandroid.dto.Profile

interface SupabaseDataClientProfile {
    suspend fun getProfileData(): List<Profile>
    suspend fun addProfileData()
    suspend fun deleteProfileData()
}