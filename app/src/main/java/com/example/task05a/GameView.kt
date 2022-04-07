package com.example.task05a

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val colCount = 7
    private val rowCount = 10

    private var circleDiameter: Float = 0f
    private var circleSpacing: Float = 0f
    private var circleSpacingRatio: Float = 0.2f

    private val gridPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLUE
    }

    private val noPlayerPaint: Paint= Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        val diameterX = w/(colCount + (colCount+1)*circleSpacingRatio)
        val diameterY = h/(rowCount + (rowCount+1)*circleSpacingRatio)

        circleDiameter = minOf(diameterX, diameterY)
        circleSpacing = circleDiameter*circleSpacingRatio

        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {

        val gameWidth: Float = colCount * (circleDiameter+circleSpacing) + circleSpacing
        val gameHeight: Float = rowCount * (circleDiameter+circleSpacing) + circleSpacing

        //draw the game board
        canvas?.drawRect(0f, 0f, gameWidth, gameHeight, gridPaint)

        super.onDraw(canvas)

        val radius = circleDiameter / 2f

        for (row in 0 until rowCount) {
            for (col in 0 until colCount) {
                // We will later on want to use the game data to determine this
                val paint = noPlayerPaint

                // Drawing circles uses the center and radius
                val cx = circleSpacing + ((circleDiameter + circleSpacing) * col) + radius
                val cy = circleSpacing + ((circleDiameter + circleSpacing) * row) + radius

                canvas?.drawCircle(cx, cy, radius, paint)
            }
        }


    }

}