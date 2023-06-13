package com.example.bonappetitandroid.repository.dataClient

import com.example.bonappetitandroid.dto.Address

interface SupabaseDataClientAddress {
    suspend fun getAddress() : List<Address>
}