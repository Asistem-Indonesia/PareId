package id.asistem.pareid.ui.fragment.child

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.ui.child_detail.ChildDetailActivity
import id.asistem.pareid.ui.home.HomeChildActivity
import id.asistem.pareid.utils.toast
import java.util.*

class ProfileFragmentChild : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v:View =  inflater.inflate(R.layout.fragment_profile_child, container, false)
        init(v)
        return v
    }

    private fun init(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val nama: EditText = v.findViewById(R.id.et_nameChild)
        val tanggal: EditText = v.findViewById(R.id.etTglChild)
        val username: EditText = v.findViewById(R.id.etUsernameChild)
        val password: EditText = v.findViewById(R.id.etPassworddChild)
        val button: Button = v.findViewById(R.id.btnupdateChild)

        val child = db.getChildDao().getChildByid(prefManager.spIdChild)

        nama.text = Editable.Factory.getInstance().newEditable(child[0].nama)
        tanggal.text = Editable.Factory.getInstance().newEditable(child[0].tanggal_lahir)
        username.text = Editable.Factory.getInstance().newEditable(child[0].username)
        password.text = Editable.Factory.getInstance().newEditable(child[0].password)

        tanggal.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                tanggal.setText("$dayOfMonth $monthOfYear, $year")
            }, year, month, day)
            dpd.show() }

        button.setOnClickListener {
            db.getChildDao().updateChildById(nama.text.toString(),tanggal.text.toString(),username.text.toString(),password.text.toString(), prefManager.spId!!)
            requireContext().toast("data berhasil dirubah")

            val intent = Intent (getActivity(), HomeChildActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

}