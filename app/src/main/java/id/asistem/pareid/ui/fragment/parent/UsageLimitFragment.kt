package id.asistem.pareid.ui.fragment.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.utils.toast


class UsageLimitFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View =  inflater.inflate(R.layout.fragment_dialog, container, false)

        init(v)
        return v
    }

    private fun init(v: View) {
        val number_picker: NumberPicker = v.findViewById(R.id.number_picker)
        val app: TextView = v.findViewById(R.id.tvAppdialog)
        val simpan: Button = v.findViewById(R.id.btn_simpan)
        var number = 0

        app.text = prefManager.spApp
        number_picker.minValue = 1
        number_picker.maxValue = 24

        number_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            number = newVal
        }

        simpan.setOnClickListener {
            db.getUsageDao().updateHour(number, prefManager.spApp!!)
            context?.toast("berhasil dirubah")
        }
    }
}