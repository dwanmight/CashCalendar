package com.might.dwan.cashcalendar.ui.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import com.might.dwan.cashcalendar.utils.DisplayUtils

/**
 * Created by Ilya on 21.06.2018.
 */
//open class DiagramView(c: Context, attr: AttributeSet?, defStyle: Int?) : View(c, attr, 0) {

open class DiagramView : View {

    private var maxAmount: Int = 0
    lateinit var monthName: String

    private val paint = Paint()
    private var rect = Rect()
    private var height: Float = 0f

    private val chartWidth = DisplayUtils.pxToDp(context, 120)

    constructor(c: Context, attr: AttributeSet?) : super(c, attr)

    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (height == 0f && measuredHeight > 0) {
            height = measuredHeight.toFloat()

            rect.right = measuredWidth / 2 + chartWidth
            rect.left = measuredWidth / 2 - chartWidth
            rect.top = 0
            rect.bottom = measuredHeight
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return

        canvas.drawRect(rect, paint)
    }


    fun animateChart() {
        val anim = ValueAnimator.ofFloat(0f, 1f)
        anim.interpolator = FastOutSlowInInterpolator()
        anim.duration = 300
        anim.addUpdateListener {
            rect.top = (height - (height * it.animatedValue as Float)).toInt()
            invalidate()
            println("aniamtedVAlue ${it.animatedValue}")
        }
        anim.start()

    }


}