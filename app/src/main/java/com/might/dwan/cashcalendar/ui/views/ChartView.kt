package com.might.dwan.cashcalendar.ui.views

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import com.might.dwan.cashcalendar.utils.BuildUtils
import com.might.dwan.cashcalendar.utils.DisplayUtils

/**
 * Created by Ilya on 21.06.2018.
 */
open class ChartView : View {


    private val paint = Paint()
    private var rect = RectF()
    private val path = Path()
    private var height: Float = 0f
    private var heightRatio: Float = 1f

    private val topRoundOffset = DisplayUtils.pxToDpi(context, 16)
    private val radius = DisplayUtils.pxToDpi(context, 5)

    constructor(c: Context, attr: AttributeSet? = null, heightRatio: Float = 1f) : super(c, attr) {
        this.heightRatio = heightRatio
    }

    init {
        paint.color = Color.parseColor("#B71C1C")
        paint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (height == 0f && measuredHeight > 0) {
            height = measuredHeight.toFloat()
            rect.right = MeasureSpec.getSize(widthMeasureSpec).toFloat()
            rect.left = 0f
            rect.top = 0f
            rect.bottom = MeasureSpec.getSize(heightMeasureSpec).toFloat()
            animateChart()
        }
    }

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        if (BuildUtils.isPostLollipop()) {
            canvas.drawRoundRect(rect.left, rect.top, rect.right, rect.bottom + topRoundOffset, radius, radius, paint)
        } else {
            canvas.drawRect(rect, paint)
        }
    }


    private fun animateChart() {
        val maxHeight = height * heightRatio
        val anim = ValueAnimator.ofFloat(0f, maxHeight)
        anim.interpolator = FastOutSlowInInterpolator()
        anim.duration = 300
        anim.addUpdateListener {
            rect.top = (rect.bottom - it.animatedValue as Float)
            invalidate()
        }
        anim.start()

    }


}