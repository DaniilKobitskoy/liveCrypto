package com.crypto.money

import com.crypto.money.internet.Cryptocurrency
import com.crypto.money.internet.Data
import com.crypto.money.model.DataLive
import com.crypto.money.model.ApiResponse

class DataParser {
    private val cryptocurrencies: MutableList<Cryptocurrency> = mutableListOf()
    private val cryptocurrencies1: MutableList<ApiResponse> = mutableListOf()
    private val data1: MutableList<Data> = mutableListOf()
    private val dataLive2: MutableList<com.crypto.money.model.DataLive> = mutableListOf()

    fun updateData(newData: MutableList<Data>) {
        cryptocurrencies.clear()
        data1.clear()
        data1.addAll(newData)
    }
    fun updateData1(newData1: DataLive) {
        cryptocurrencies1.clear()
        dataLive2.clear()
        dataLive2.addAll(listOf(newData1))
    }
    fun getCryptocurrencies(): List<Data> {
        return data1
    }
    fun getCryptocurrencies1(): List<DataLive> {
        return dataLive2
    }
}

