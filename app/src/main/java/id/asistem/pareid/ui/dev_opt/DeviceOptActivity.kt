package id.asistem.pareid.ui.dev_opt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import id.asistem.pareid.R
import id.asistem.pareid.ui.login.LoginChildActivity
import id.asistem.pareid.ui.login.LoginParentActivity

class DeviceOptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_opt)

        init()
    }

    private fun init() {
        val cvChild: CardView = findViewById(R.id.cvChild)
        val cvParent: CardView = findViewById(R.id.cvParent)

        cvChild.setOnClickListener {
            startActivity(Intent(this,LoginChildActivity::class.java))
        }

        cvParent.setOnClickListener {
            startActivity(Intent(this,LoginParentActivity::class.java))
        }
    }
}