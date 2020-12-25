package id.asistem.pareid.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import de.hdodenhof.circleimageview.CircleImageView
import id.asistem.pareid.PareId
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.ui.dev_opt.DeviceOptActivity
import id.asistem.pareid.ui.fragment.child.ProfileFragmentChild
import id.asistem.pareid.ui.fragment.child.UsageFragmentChild
import java.util.*

class HomeChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_child)

        init()
    }

    private fun init() {
        val photos = intArrayOf(R.drawable.anak1, R.drawable.anak2, R.drawable.anak3, R.drawable.anak4)
        val ran = Random()
        val i: Int = ran.nextInt(photos.size)

        val imageChild: CircleImageView = findViewById(R.id.ciChildDetailChild)
        val tvProfile: TextView = findViewById(R.id.tvProfileChild)
        val tvUsage: TextView = findViewById(R.id.tvUsageChild)
        val tglLahir: TextView = findViewById(R.id.tvTglLahirDetailChild)
        val tvAnak: TextView = findViewById(R.id.tvAnakDetailChild)
        val logout: LinearLayout = findViewById(R.id.ll_logoutChild)

        val usageFragment: Fragment = UsageFragmentChild()
        val profile: Fragment = ProfileFragmentChild()

        tvAnak.text = PareId.db.getChildDao().getChildByid(PareId.prefManager.spIdChild)[0].nama
        tglLahir.text = PareId.db.getChildDao().getChildByid(PareId.prefManager.spIdChild)[0].tanggal_lahir
        imageChild.setImageResource(photos.get(i))

        fragment(usageFragment)
        logout.setOnClickListener {
            prefManager.spIsLoginChild = false
            logout()
        }

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
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutChild, fragment).commitAllowingStateLoss()
    }

    private fun logout() {
        Intent(this, DeviceOptActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it) }
    }
}