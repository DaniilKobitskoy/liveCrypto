package com.crypto.money

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crypto.money.databinding.ActivityCryptoLiveBinding
import com.crypto.money.internet.CryptocurrencyService
import com.crypto.money.internet.Data
import com.crypto.money.internet.LiveResponce
import com.crypto.money.ui.LiveAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.Lazy


class CryptoLive : AppCompatActivity() {
    lateinit var binding: ActivityCryptoLiveBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LiveAdapter
    private lateinit var dataParser: DataParser
    private lateinit var job: Job
    private val updateIntervalMillis = 5000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    val cryptoID = intent.getStringExtra("CRYPTO_NAME")

    Toast.makeText(this, "Нажали на криптовалюту:${cryptoID}", Toast.LENGTH_SHORT).show()

        recyclerView = binding.razdel2
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = LiveAdapter()
        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LiveResponce::class.java)
        dataParser = DataParser()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                fetchData(service, API_KEY, cryptoID!!)
                handler.postDelayed(this, updateIntervalMillis)
            }
        }, updateIntervalMillis)
    }

    private fun fetchData(service: LiveResponce, apiKey: String, id: String) {
        job = CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = service.getLive(apiKey,id)
                val cryptocurrencies = response.data
                Log.d("ArrayItem", cryptocurrencies.toString())
                dataParser.updateData1(cryptocurrencies!!)
                adapter.setData(dataParser.getCryptocurrencies1())
            } catch (e: Exception) {
                Log.d("UrlMain", service.getLive(apiKey,id).toString())
                Log.d("ErrorTyt", e.message.toString())
                // Обработка ошибок
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

//    override fun onItemClick(cryptocurrency: Data, id: String) {
//        // Обработка нажатия на элемент
//        val intent = Intent(this, CryptoLive::class.java)
//        intent.putExtra("CRYPTO_NAME", id)
//        startActivity(intent)
//        Toast.makeText(this, "Нажали на криптовалюту: ${cryptocurrency.name} - ${cryptocurrency.id}", Toast.LENGTH_SHORT).show()
//    }

    companion object {
        private const val BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/"
        private const val API_KEY = "75e8feaa-578d-4c8b-942a-825588cbe8a6" // Замените на ваш реальный ключ авторизации
    }
}



