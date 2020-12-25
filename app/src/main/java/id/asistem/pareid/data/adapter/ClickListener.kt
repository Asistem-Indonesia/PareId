package id.asistem.pareid.data.adapter

import id.asistem.pareid.data.db.entities.Child
import id.asistem.pareid.data.db.entities.Usage

interface ClickListener {
    fun onClickListener(child: Child)
    fun onClickListener(usage: Usage)
}