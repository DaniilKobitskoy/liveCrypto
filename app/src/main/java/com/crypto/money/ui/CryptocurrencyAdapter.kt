import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.crypto.money.R
import com.crypto.money.internet.Cryptocurrency
import com.crypto.money.internet.Data

class CryptocurrencyAdapter : RecyclerView.Adapter<CryptocurrencyAdapter.CryptocurrencyViewHolder>() {
    private val cryptocurrencies: MutableList<Data> = mutableListOf()
    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun setData(data: List<Data>) {
        cryptocurrencies.clear()
        cryptocurrencies.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CryptocurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        val cryptocurrency = cryptocurrencies[position]
        holder.bind(cryptocurrency)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(cryptocurrency, cryptocurrency.id!!)
        }
    }

    override fun getItemCount(): Int {
        return cryptocurrencies.size
    }

    class CryptocurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)

        fun bind(cryptocurrency: Data) {
            nameTextView.text = cryptocurrency.name
            nameTextView.tag = cryptocurrency.id
        }
    }

    interface OnItemClickListener {
        fun onItemClick(cryptocurrency: Data, id: String)
    }
}

