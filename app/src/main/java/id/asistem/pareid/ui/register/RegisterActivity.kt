package id.asistem.pareid.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.ui.login.LoginParentActivity
import id.asistem.pareid.utils.hide
import id.asistem.pareid.utils.show
import id.asistem.pareid.utils.toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        val btnRegister: Button = findViewById(R.id.btnRegister)
        val etUsername: EditText = findViewById(R.id.etUsernameRegister)
        val etPassword: EditText = findViewById(R.id.etPasswordRegsiter)
        val pbRegister: ProgressBar = findViewById(R.id.pb_register)
        val toLogin: TextView= findViewById(R.id.toLogin)

        toLogin.setOnClickListener {
            startActivity(Intent(this, LoginParentActivity::class.java)) }

        btnRegister.setOnClickListener {
            if (etUsername.text == null){ toast("username tidak boleh kosong")
            return@setOnClickListener}
            if (etPassword.text == null){ toast("password tidak boleh kosong")
            return@setOnClickListener}
            if (etUsername.text.length < 4){toast("username minimal 4 karakter")
            return@setOnClickListener}
            if (etPassword.text.length < 4){toast("password minimal 4 karakter")
            return@setOnClickListener}

            pbRegister.show()
            Handler().postDelayed({
                if (etUsername.text != null && etPassword.text != null) {
                    pbRegister.hide()
                    prefManager.spUsername = etUsername.text.toString()
                    prefManager.spPassword = etPassword.text.toString()
                    toast("register berhasil")
                    startActivity(Intent(this, LoginParentActivity::class.java))
                }
            },3_000)
        }
    }
}