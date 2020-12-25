package id.asistem.pareid.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.ui.home.HomeActivityParent
import id.asistem.pareid.ui.home.HomeChildActivity
import id.asistem.pareid.utils.hide
import id.asistem.pareid.utils.show
import id.asistem.pareid.utils.toast

class LoginChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_child)

        init()
    }

    private fun init() {
        val btnSignIn: Button = findViewById(R.id.btnSignInChild)
        val etUsername: EditText = findViewById(R.id.etUsernameChild)
        val etPassword: EditText = findViewById(R.id.etPasswordChild)
        val pbLogin: ProgressBar = findViewById(R.id.pb_loginChild)

        btnSignIn.setOnClickListener {
            val index = db.getChildDao().getAllChild().size
            var in_db = false

            pbLogin.show()
            Handler().postDelayed({
                for (i in 0 until index) {
                    var username = db.getChildDao().getAllChild()[i].username
                    var password = db.getChildDao().getAllChild()[i].password

                    if (etUsername.text.toString() == username && etPassword.text.toString() == password) {
                        toast("berhasil login")
                        in_db = true

                        prefManager.spIsLoginChild = true
                        prefManager.spIdChild = db.getChildDao().getAllChild()[i].id!!

                        Intent(this, HomeChildActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    } else {
                        pbLogin.hide()
                        if (i + 1 == index && !in_db) {
                            toast("username dan password salah")
                        }
                    }
                }
            },2_000)

        }
    }
}