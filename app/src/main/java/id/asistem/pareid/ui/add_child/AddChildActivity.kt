package id.asistem.pareid.ui.add_child

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import de.hdodenhof.circleimageview.CircleImageView
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.R
import id.asistem.pareid.data.db.entities.Child
import id.asistem.pareid.ui.home.HomeActivityParent
import id.asistem.pareid.utils.hide
import id.asistem.pareid.utils.show
import id.asistem.pareid.utils.toast
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AddChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_child)

        init()
    }

    private fun init() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val photos = intArrayOf(R.drawable.anak1, R.drawable.anak2, R.drawable.anak3, R.drawable.anak4)
        val ran = Random()
        val i: Int = ran.nextInt(photos.size)

        val imageChild: CircleImageView = findViewById(R.id.ciChildAdd)
        val nama_anak:EditText = findViewById(R.id.etNama_anak)
        val tgl_lahir:EditText = findViewById(R.id.etTgl_lahir)
        val username:EditText = findViewById(R.id.etUsernameAnak)
        val password:EditText = findViewById(R.id.etPasswordAnak)
        val tambah:Button = findViewById(R.id.btnTambahAnak)
        val pbBar:ProgressBar = findViewById(R.id.pbAdd)

        imageChild.setImageResource(photos.get(i))

        tgl_lahir.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                tgl_lahir.setText("$dayOfMonth $monthOfYear, $year")
            }, year, month, day)
            dpd.show() }

        tambah.setOnClickListener {
            Log.d("cek_db_child", nama_anak.text.toString())


            if (nama_anak.text.isNullOrEmpty() && tgl_lahir.text.isNullOrEmpty() && username.text.isNullOrEmpty() && password.text.isNullOrEmpty()){
                toast("harus terisi semua")
                return@setOnClickListener }
            if (nama_anak.text.length < 4){toast("nama anak minimal 4 karakter")
            return@setOnClickListener}
            if (username.text.length < 4){toast("username minimal 4 karakter")
            return@setOnClickListener}
            if (password.text.length < 4){toast("password minimal 4 karakter")
            return@setOnClickListener }

            pbBar.show()
            Handler().postDelayed({
                db.getChildDao().insert(
                    Child(
                        null, nama_anak.text.toString(),
                        tgl_lahir.text.toString(),
                        username.text.toString(),
                        password.text.toString()
                    )
                )
                pbBar.hide()
                Intent(this, HomeActivityParent::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
                toast("${nama_anak.text.toString()} berhasil di tambahkan")
                Log.d("cek_db_child", db.getChildDao().getAllChild().toString())
            },3_000)
        }

    }
}