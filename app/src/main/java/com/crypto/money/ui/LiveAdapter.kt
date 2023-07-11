package com.crypto.money.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crypto.money.R
import com.crypto.money.model.Coin
import com.crypto.money.model.DataLive

class LiveAdapter : RecyclerView.Adapter<LiveAdapter.CryptocurrencyViewHolder2>() {
    private val cryptocurrencies2: MutableList<DataLive> = mutableListOf()
   // private var cryptocurrencies3: MutableList<Coin> = mutableListOf()

    fun setData(data: List<DataLive>) {
        cryptocurrencies2.clear()
        cryptocurrencies2.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlive, parent, false)
        return CryptocurrencyViewHolder2(view)
    }

    override fun onBindViewHolder(
        holder: CryptocurrencyViewHolder2,
        position: Int
    ) {
        val cryptocurrency2 = cryptocurrencies2[position]
        holder.bind(cryptocurrency2)

    }
    inner class CryptocurrencyViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val param1TextView: TextView = itemView.findViewById(R.id.namecrypto)
        private val param2TextView: TextView = itemView.findViewById(R.id.price)
        private val param3TextView: TextView = itemView.findViewById(R.id.procenti)
        private val param4TextView: TextView = itemView.findViewById(R.id.procenti2)

        fun bind(cryptocurrency: DataLive) {

            Log.d("ArrayCrypto", cryptocurrency.coins.toString())
            //cryptocurrencies3.addAll(cryptocurrency.coins)

            param1TextView.text = cryptocurrency.coins.first().name
            param2TextView.text = cryptocurrency.coins.first().quote!!.USD!!.price.toString()
            param3TextView.text = cryptocurrency.coins.first().quote!!.USD!!.percent_change_1h.toString()
            param4TextView.text = cryptocurrency.coins.first().quote!!.USD!!.percent_change_24h.toString()
        }
    }
    override fun getItemCount(): Int {
        return cryptocurrencies2.size
    }
}