package com.crypto.money

import CryptocurrencyAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crypto.money.databinding.ActivityCryptoBinding
import com.crypto.money.internet.Cryptocurrency
import com.crypto.money.internet.CryptocurrencyService
import com.crypto.money.internet.Data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*
import retrofit2.http.Header


class Crypto : AppCompatActivity(), CryptocurrencyAdapter.OnItemClickListener {
    lateinit var binding: ActivityCryptoBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CryptocurrencyAdapter
    private lateinit var dataParser: DataParser
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.razdel
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CryptocurrencyAdapter()
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptocurrencyService::class.java)
        dataParser = DataParser()

        fetchData(service, API_KEY)
    }

    private fun fetchData(service: CryptocurrencyService, apiKey: String) {
        job = CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = service.getCryptocurrencies(apiKey)
                val cryptocurrencies = response.data
                Log.d("ArrayCrupto", cryptocurrencies.toString())
                dataParser.updateData(cryptocurrencies!!)
                adapter.setData(dataParser.getCryptocurrencies())
            } catch (e: Exception) {
                Log.d("ErrorItem", e.message.toString())
                // Обработка ошибок
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onItemClick(cryptocurrency: Data, id: String) {
        // Обработка нажатия на элемент
        val intent = Intent(this, CryptoLive::class.java)
        intent.putExtra("CRYPTO_NAME", id)
        startActivity(intent)
        Toast.makeText(this, "Нажали на криптовалюту: ${cryptocurrency.name} - ${cryptocurrency.id}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/"
        private const val API_KEY = "75e8feaa-578d-4c8b-942a-825588cbe8a6" // Замените на ваш реальный ключ авторизации
    }
}

