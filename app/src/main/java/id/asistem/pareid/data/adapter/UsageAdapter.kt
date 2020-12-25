package id.asistem.pareid.data.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import id.asistem.pareid.R
import id.asistem.pareid.data.db.entities.Usage
import lecho.lib.hellocharts.model.AxisValue
import lecho.lib.hellocharts.model.PointValue
import java.util.*


class UsageAdapter(
    private val context: Context,
    private val list: List<Usage>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<UsageAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val app: TextView = view.findViewById(R.id.tvApp)
        val hour: TextView = view.findViewById(R.id.tvHour)
        val data: TextView = view.findViewById(R.id.tvData)
        val image: CircleImageView = view.findViewById(R.id.cvApp)
        val batas: TextView = view.findViewById(R.id.tvBatas)
        val card: CardView = view.findViewById(R.id.cvUsage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_usage, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

//            val red: Int = context.resources.getColor(R.color.red_dark)
//            val white: Int = context.resources.getColor(R.color.white)
//
//            if (data.usage_data!!.toInt() >= data.batas!!.toInt()){
//                Log.d("red",position.toString() +" red")
//                holder.card.setCardBackgroundColor(red)
//            }else{
//                Log.d("white",position.toString() +" white")
//                holder.card.setCardBackgroundColor(white)
//            }


        holder.batas.text = data.batas.toString()+" jam Batas Penggunaan"
        holder.app.text = data.app
        holder.hour.text = data.hour!!.toInt().toString()+" jam Pemakaian"
        holder.data.text = data.usage_data!!.toInt().toString()
        holder.image.setImageResource(data.image!!)

        holder.itemView.setOnClickListener {
            clickListener.onClickListener(data)
        }
    }
}