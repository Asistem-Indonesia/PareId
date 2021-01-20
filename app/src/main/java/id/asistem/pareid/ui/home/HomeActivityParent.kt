package id.asistem.pareid.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.data.adapter.ChildAdapter
import id.asistem.pareid.data.adapter.ClickListener
import id.asistem.pareid.data.db.entities.Child
import id.asistem.pareid.data.db.entities.Usage
import id.asistem.pareid.ui.add_child.AddChildActivity
import id.asistem.pareid.ui.child_detail.ChildDetailActivity
import id.asistem.pareid.ui.dev_opt.DeviceOptActivity
import id.asistem.pareid.utils.hide
import id.asistem.pareid.utils.show
import java.util.*

class HomeActivityParent : AppCompatActivity(), ClickListener {
    private val child = db.getChildDao().getAllChild()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_parent)

        init()
    }

    private fun init() {
        val child = db.getChildDao().getAllChild()
        val usage = db.getUsageDao().getAllUsage()
        val jsonChild = Gson().toJson(child)
        val jsonUsage = Gson().toJson(usage)

        Log.d("jsonChild",jsonChild)
        Log.d("jsonUsage",jsonUsage)

        if (db.getUsageDao().getAllUsage().isNullOrEmpty()){
            dummyUsage()
        }

        Log.d("testDb",child.toString())
        val photos = intArrayOf(R.drawable.ortu1, R.drawable.ortu2, R.drawable.ortu3, R.drawable.ortu4)
        val ran = Random()
        val i: Int = ran.nextInt(photos.size)

        val time = longArrayOf(2_000,3_000,1_000)
        val timeHand: Int = ran.nextInt(time.size)

        val ivAddChild: ImageView = findViewById(R.id.ivAddChild)
        val tvParent: TextView = findViewById(R.id.tvParent)
        val imageParent: CircleImageView = findViewById(R.id.cvParentHome)
        val rv: RecyclerView = findViewById(R.id.rvHome)
        val logout: LinearLayout = findViewById(R.id.llLogout)
        val shimer: ShimmerFrameLayout = findViewById(R.id.shimmerLayout)


        Handler().postDelayed({
            ivAddChild.show()
            tvParent.text = prefManager.spUsername
            imageParent.setImageResource(photos.get(i))

            rv.also {
                rv.show()
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = ChildAdapter(this,child,this)
                shimer.stopShimmer()
                shimer.hide()}
        },time.get(timeHand))

        logout.setOnClickListener{
            prefManager.spIsLogin = false
            Intent(this, DeviceOptActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it) }
        }

        ivAddChild.setOnClickListener {
            startActivity(Intent(this,AddChildActivity::class.java))
        }
    }

    private fun dummyUsage() {
        db.getUsageDao().insert(Usage(null,3F,"youtube",R.drawable.yt,3,400.0F))
        db.getUsageDao().insert(Usage(null,4F,"twitter",R.drawable.twitter,3,300.0F))
        db.getUsageDao().insert(Usage(null,6F,"mobile legend",R.drawable.mlbb,3,200.0F))
        db.getUsageDao().insert(Usage(null,5F,"pubg mobile",R.drawable.pubgm,3,100.0F))
        db.getUsageDao().insert(Usage(null,5F,"facebook",R.drawable.fb,3,500.0F))
        db.getUsageDao().insert(Usage(null,3F,"tiktok",R.drawable.tiktok,3,300.0F))
        db.getUsageDao().insert(Usage(null,2F,"whatsapp",R.drawable.wa,3,100.0F))
    }

    override fun onClickListener(child: Child) {
        prefManager.spId = child.id
        startActivity(Intent(this,ChildDetailActivity::class.java))
    }

    override fun onClickListener(usage: Usage) {}
}