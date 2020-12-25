
package id.asistem.pareid.ui.fragment.parent

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.PareId.Companion.prefManager
import id.asistem.pareid.R
import id.asistem.pareid.data.adapter.ChartAdapter
import id.asistem.pareid.data.adapter.ClickListener
import id.asistem.pareid.data.adapter.UsageAdapter
import id.asistem.pareid.data.db.entities.Child
import id.asistem.pareid.data.db.entities.Usage
import id.asistem.pareid.utils.hide
import id.asistem.pareid.utils.show
import id.asistem.pareid.utils.toast
import lecho.lib.hellocharts.view.LineChartView
import java.util.*

class UsageFragmentParent : androidx.fragment.app.Fragment(), ClickListener {
    private lateinit var chartAdapter: ChartAdapter
    val usage = db.getUsageDao().getAllUsage()
    lateinit var progressBar: ProgressBar
    lateinit var line: LineChartView
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_usage_parent, container, false)

        init(v)
        return v
    }

    private fun init(v: View) {
        val swipe: SwipeRefreshLayout = v.findViewById(R.id.swUsageFragment)
        progressBar = v.findViewById(R.id.pbUsageFragment)

        val ran = Random()
        val time = longArrayOf(2_000,3_000,1_000)
        val timeHand: Int = ran.nextInt(time.size)

        bindView(v)

        swipe.setOnRefreshListener {
            swipe.isRefreshing = true
            recycler.hide()
            line.hide()
            progressBar.show()
            Handler().postDelayed({

                bindView(v)

                progressBar.hide()
                recycler.show()
                line.show()
                swipe.isRefreshing = false
            },time.get(timeHand))
        }


    }

    private fun bindView(v: View) {
        line = v.findViewById(R.id.lcvUsage)
        recycler = v.findViewById(R.id.rvUsage)
        val usage = db.getUsageDao().getAllUsage()

        val ran = Random()
        val time = longArrayOf(2_000,3_000,1_000)
        val timeHand: Int = ran.nextInt(time.size)

        progressBar.show()

        Handler().postDelayed({
            Log.d("testUsage", db.getUsageDao().getAllUsage().toString())
            chartAdapter = ChartAdapter(line, usage)
            recycler.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = UsageAdapter(requireContext(), usage, this)
            }
            progressBar.hide()
        },time.get(timeHand))
    }

    override fun onClickListener(child: Child) {}

    override fun onClickListener(usage: Usage) {
        val dialogFragment = UsageLimitFragment()
        dialogFragment.show(fragmentManager!!,"example")
        prefManager.spApp = usage.app
    }
}