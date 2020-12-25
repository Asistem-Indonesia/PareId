package id.asistem.pareid.ui.child_detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.ui.fragment.parent.ProfileFragmentParent
import id.asistem.pareid.ui.fragment.parent.UsageFragmentParent
import id.asistem.pareid.ui.home.HomeActivityParent
import java.util.*

class ChildDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_detail)

        init()
    }

    private fun init() {
        val photos = intArrayOf(R.drawable.anak1, R.drawable.anak2, R.drawable.anak3, R.drawable.anak4)
        val ran = Random()
        val i: Int = ran.nextInt(photos.size)

        val imageChild: CircleImageView = findViewById(R.id.ciChildDetail)
        val tvProfile: TextView = findViewById(R.id.tvProfile)
        val tvUsage: TextView = findViewById(R.id.tvUsage)
        val tglLahir: TextView = findViewById(R.id.tvTglLahirDetail)
        val delete: LinearLayout = findViewById(R.id.ll_delete)
        val tvAnak: TextView = findViewById(R.id.tvAnakDetail)
        val usageFragment:Fragment = UsageFragmentParent()
        val profile: Fragment = ProfileFragmentParent()
        val root: ConstraintLayout = findViewById(R.id.rootLayout)

        tvAnak.text = db.getChildDao().getChildByid(prefManager.spId!!)[0].nama
        tglLahir.text = db.getChildDao().getChildByid(prefManager.spId!!)[0].tanggal_lahir
        imageChild.setImageResource(photos.get(i))

        fragment(usageFragment)
        delete.setOnClickListener { root.snackbarDelete("apakah mau menghapus data ini ?") }
        tvProfile.setOnClickListener {
            tvProfile.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.white))
            tvUsage.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.grey))
            fragment(profile) }
        tvUsage.setOnClickListener {
            tvUsage.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.white))
            tvProfile.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.grey))
            fragment(usageFragment) }
    }

    private fun fragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commitAllowingStateLoss()
    }

    fun View.snackbarDelete(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
            snackbar.setAction("OK") {
                db.getChildDao().deleteById(prefManager.spId!!)
                toHome()
            }
        }.show()
    }

    private fun toHome() {
        Intent(this, HomeActivityParent::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it) }
    }

}