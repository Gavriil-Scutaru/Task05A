package com.example.task05a

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import com.example.logic.StudentConnect4Game

class GameView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

//    private val colCount = 7
//    private val rowCount = 10

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

    var game: StudentConnect4Game = StudentConnect4Game()
        set(value) {
            field = value
            // After the new value is set, make sure to recalculate sizes and then trigger a redraw
            onSizeChanged(width, height, width, height)
            invalidate()
        }

    private val colCount:Int get() = game.columns
    private val rowCount:Int get() = game.rows

    private val gestureDetector = GestureDetectorCompat(context, object:
        GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val columnTouched = ((e.x - circleSpacing * 0.5f) / (circleSpacing + circleDiameter)).toInt()

            if (columnTouched in 0 until game.columns) {
                // handle touch
                game.playToken(columnTouched, game.playerTurn)
                invalidate()
                return true
            } else {
                return false
            }

            return super.onSingleTapUp(e)
        }
    })

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
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
                val paint = when (game.getToken(col, row)) {
                    1 -> player1Paint
                    2 -> player2Paint
                    else -> noPlayerPaint
                }

                // Drawing circles uses the center and radius
                val cx = circleSpacing + ((circleDiameter + circleSpacing) * col) + radius
                val cy = circleSpacing + ((circleDiameter + circleSpacing) * row) + radius

                canvas?.drawCircle(cx, cy, radius, paint)
            }
        }
    }

    private val player1Paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    private val player2Paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.YELLOW
    }



}