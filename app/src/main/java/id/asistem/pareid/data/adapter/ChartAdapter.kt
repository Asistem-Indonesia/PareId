package id.asistem.pareid.data.adapter

import android.graphics.Color
import android.util.Log
import id.asistem.pareid.data.db.entities.Usage
import lecho.lib.hellocharts.gesture.ContainerScrollType
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.view.LineChartView

class ChartAdapter(lineChartView: LineChartView, data: List<Usage>) {
    private val xValue = mutableListOf<AxisValue>()
    private val yValue = mutableListOf<PointValue>()
    private val yData = mutableListOf<Int>()

    init {
        for (i in 0 until data.size) {
            val label = data[i].app

            xValue.add(i, AxisValue(i.toFloat()).setLabel(label))
            yValue.add(PointValue(i.toFloat(), data[i].hour!!))

            yData.add(data[i].hour!!.toInt())
        }


        val lines = mutableListOf<Line>().apply {
            add(
                Line(yValue).apply {
                    color = Color.parseColor("#00C08B")
                    strokeWidth = 2
                }
            )
        }

        val chartData = LineChartData().apply {
            setLines(lines)

            axisXBottom = Axis().apply {
                values = xValue
                textColor = Color.parseColor("#00C08B")
                textSize = 10
                name = "Aplikasi"
            }

            axisYLeft = Axis().apply {
                textColor = Color.parseColor("#00C08B")
                textSize = 10
                name = "Jam"
            }
        }

        lineChartView.isInteractive = true
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL)
        lineChartView.lineChartData = chartData
    }
}