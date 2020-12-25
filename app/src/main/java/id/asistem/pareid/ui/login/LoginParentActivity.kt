package id.asistem.pareid.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.ui.dev_opt.DeviceOptActivity
import id.asistem.pareid.ui.home.HomeActivityParent
import id.asistem.pareid.ui.register.RegisterActivity
import id.asistem.pareid.utils.hide
import id.asistem.pareid.utils.show
import id.asistem.pareid.utils.toast

class LoginParentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        val etUsername: EditText = findViewById(R.id.etUsername)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val pbLogin: ProgressBar = findViewById(R.id.pb_login)
        val toRegis: TextView = findViewById(R.id.toRegister)
        
        toRegis.setOnClickListener { 
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        btnSignIn.setOnClickListener {
            if (etUsername.text == null){ toast("username harus terisi")
            return@setOnClickListener}

            if (etPassword.text == null){ toast("password harus terisi")
            return@setOnClickListener}

            pbLogin.show()
            Handler().postDelayed({
                if (etUsername.text.toString() == prefManager.spUsername && etPassword.text.toString() == prefManager.spPassword){
                    prefManager.spIsLogin = true
                    pbLogin.hide()
                    Intent(this, HomeActivityParent::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it) }
                }else{
                    pbLogin.hide()
                    toast("username & password salah")
                }
            }, 2_000)
        }
    }
}