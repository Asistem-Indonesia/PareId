package id.asistem.pareid.ui.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import id.asistem.pareid.PareId
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.ui.dev_opt.DeviceOptActivity
import id.asistem.pareid.ui.home.HomeActivityParent
import id.asistem.pareid.ui.home.HomeChildActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        hideNavigationBar()
        setupView()
    }

    private fun setupView() {
        Handler().postDelayed({

            if (prefManager.spIsLogin){
                startActivity(Intent(this, HomeActivityParent::class.java))
                finish()
            }else if (prefManager.spIsLoginChild){
                startActivity(Intent(this, HomeChildActivity::class.java))
                finish()
            } else{
                startActivity(Intent(this, DeviceOptActivity::class.java))
                finish()
            }

        }, 3_000)
    }

    private fun hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            window.decorView.systemUiVisibility =
                    (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}