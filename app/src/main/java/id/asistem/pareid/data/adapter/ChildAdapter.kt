package id.asistem.pareid.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import id.asistem.pareid.R
import id.asistem.pareid.data.db.entities.Child
import org.w3c.dom.Text
import java.util.*


class ChildAdapter(
    private val context: Context,
    private val list: List<Child>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nama: TextView = view.findViewById(R.id.tvAnakRv)
        val tanggal_lahir: TextView = view.findViewById(R.id.tvtglLahirRv)
        val image: CircleImageView = view.findViewById(R.id.circleImageView2)
        val umur: TextView = view.findViewById(R.id.tvUmur)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_child, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        val photos = intArrayOf(R.drawable.anak1, R.drawable.anak2, R.drawable.anak3, R.drawable.anak4)
        val ran = Random()
        val i: Int = ran.nextInt(photos.size)

        val umur = intArrayOf(12,13,14,15,16,17,18,19,20,21,22)
        val u : Int =ran.nextInt(umur.size)

        holder.nama.text = data.nama
        holder.tanggal_lahir.text = data.tanggal_lahir
        holder.image.setImageResource(photos.get(i))
        holder.umur.text = umur.get(u).toString()

        holder.itemView.setOnClickListener {
            clickListener.onClickListener(data)
        }
    }
}