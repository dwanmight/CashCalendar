package com.might.dwan.cashcalendar.ui.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView



class CircleChart(c: Context, attr: AttributeSet? = null) : ImageView(c, attr) {

    val path = Path()
    private val paint = Paint()
    var matr = Matrix()

    var centerX: Int = 0
    var centerY: Int = 0


    init {
        paint.color = Color.parseColor("#80ffffff")
        paint.style = Paint.Style.FILL

    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        centerX = canvas.width / 2
        centerY = canvas.height / 2
        canvas.drawColor(Color.RED)
        //        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), 140.toFloat(), paint)

        //        path.addCircle(centerX.toFloat(), centerY.toFloat(), 140f, Path.Direction.CW)
        //        canvas.drawPath(path, paint)

//        canvas.drawRect(0.toFloat(), 0.toFloat(), canvas.width.toFloat(), canvas.height.toFloat(), paint)
//        canvas.clipRect(0, 0, canvas.width, canvas.height)

    }

}