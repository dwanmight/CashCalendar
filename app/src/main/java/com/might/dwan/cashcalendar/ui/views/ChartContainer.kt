package com.might.dwan.cashcalendar.ui.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.annotation.Dimension
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.might.dwan.cashcalendar.utils.DisplayUtils

/**
 * Created by Ilya on 22.06.2018.
 */
class ChartContainer(c: Context, attr: AttributeSet? = null) : ViewGroup(c, attr) {
    @Dimension private val verticalMargin = DisplayUtils.pxToDpi(context, 16)
    @Dimension private val childWidth = DisplayUtils.pxToDpi(context, 56).toInt()
    private val groupItemCount = 3

    init {
        addChart()
        addChart()
    }

    private fun addChart() {
        addView(addAmountView())
        addView(addChartView())
        addView(addTitleView())

    }

    private fun addAmountView(): View {
        val v = TextView(context)
        v.setTextColor(Color.BLACK)
        v.text = "1234356"
        v.typeface = Typeface.SANS_SERIF
        val params = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        params.topMargin = DisplayUtils.pxToDpi(context, 8).toInt()
        v.layoutParams = params
        return v
    }

    private fun addChartView(): View {
        val margin = DisplayUtils.pxToDpi(context, 36).toInt()
        val v = ChartView(context)
        val params = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.MATCH_PARENT)
        params.topMargin = margin
        params.bottomMargin = margin
        v.layoutParams = params
        return v
    }

    private fun addTitleView(): View {
        val v = TextView(context)
        v.setTextColor(Color.BLACK)
        v.text = "title"
        v.typeface = Typeface.SANS_SERIF
        val params = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        params.bottomMargin = DisplayUtils.pxToDpi(context, 8).toInt()
        v.layoutParams = params
        return v
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)

        var index: Int
        var child: View
        var params: MarginLayoutParams
        var verticalMargin: Int
        var horizontalMargin: Int

        for (i in 0 until childCount) {
            index = getIndexFromPos(i)
            child = getChildAt(i)
            params = child.layoutParams as MarginLayoutParams
            verticalMargin = params.topMargin + params.bottomMargin
            horizontalMargin = params.leftMargin + params.rightMargin

            if (index == 1) {
                measureChild(child,
                        MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(h - verticalMargin, MeasureSpec.EXACTLY))
            } else {
                measureChild(child,
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            }

        }
        println("onMeasure w = $w, h = $h")
        setMeasuredDimension(w, h)
    }



    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        println("left $l, top $t, right $r, bottom $b, ")
        if (childCount == 0) return

        val width = r - l
        val height = b - t


        var child: View
        var params: MarginLayoutParams
        var leftOffset = measuredWidth / ((childCount / groupItemCount) + 1)
        var left = leftOffset

        var index: Int

        for (i in 0 until childCount) {
            index = getIndexFromPos(i)
            child = getChildAt(i)
            params = child.layoutParams as MarginLayoutParams
            if (index == 1) {
                println("onLayout w = ${child.measuredWidth}, h = ${child.measuredHeight}")
                child.layout(left - child.measuredWidth / 2,
                        0 + params.topMargin,
                        left + child.measuredWidth / 2,
                        params.topMargin + child.measuredHeight)
                continue
            }

            if (index == 0) {
                child.layout(left - (child.measuredWidth / 2),
                        0 + params.topMargin,
                        left + (child.measuredWidth / 2),
                        params.topMargin + child.measuredHeight)

                continue
            }


            if (index == 2) {
                child.layout(left - (child.measuredWidth / 2),
                        height - child.measuredHeight - params.bottomMargin,
                        left + (child.measuredWidth / 2),
                        height - params.bottomMargin)
            }

            left += leftOffset
        }
    }

    private fun getIndexFromPos(pos: Int): Int {
        return pos % groupItemCount
    }

}