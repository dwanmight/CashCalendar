package com.might.dwan.cashcalendar.ui.views

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import com.might.dwan.cashcalendar.R
import com.might.dwan.cashcalendar.utils.BuildUtils
import com.might.dwan.cashcalendar.utils.DisplayUtils

/**
 * Created by Ilya on 21.06.2018.
 */
open class ChartView(c: Context,
                     attr: AttributeSet? = null,
                     private var heightRatio: Float = 1f
) : View(c, attr) {


    private val paint = Paint()
    private var rect = RectF()
    private var graphHeight: Float = 0f
    var graphSpace: Int = 0

    private val topRoundOffset = DisplayUtils.pxToDpi(context, 16)
    private val radius = DisplayUtils.pxToDpi(context, 5)

    init {
        paint.color = ContextCompat.getColor(context, R.color.red)
        paint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (graphHeight == 0f && measuredHeight > 0) {
            initRectAndStartAnimation()
        }
    }

    private fun initRectAndStartAnimation() {
        graphHeight = measuredHeight.toFloat() * heightRatio
        if (graphHeight < 5) {
            graphHeight = 10f
        }
        graphSpace = (measuredHeight - graphHeight).toInt()
        rect.right = measuredWidth.toFloat()
        rect.left = 0f
        rect.top = 0f
        rect.bottom = measuredHeight.toFloat()
        animateChart()
    }

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        if (BuildUtils.isPostLollipop()) {
            canvas.drawRoundRect(rect.left,
                    rect.top, rect.right,
                    rect.bottom + topRoundOffset,
                    radius, radius,
                    paint)
        } else {
            canvas.drawRect(rect, paint)
        }
    }

    private fun animateChart() {
        val anim = ValueAnimator.ofFloat(0f, graphHeight)
        anim.interpolator = FastOutSlowInInterpolator()
        anim.duration = 300
        anim.addUpdateListener {
            rect.top = (rect.bottom - it.animatedValue as Float)
            invalidate()
        }
        anim.start()
    }
}