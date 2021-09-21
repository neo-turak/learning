package cn.nurasoft.learningshadow

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.displayMetrics
import kotlin.properties.Delegates

/**
 *@author hugo
 *@time    2021/9/3下午9:24
 *@project  service-juran
 *Think Twice, Code Once!
 */
class MonthNameView : View {
    /**
     * 屏幕总宽度(pixel)
     */
    private var len by Delegates.notNull<Int>()

    /**
     * 打印的文字
     */
    private var textArray = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    /**
     * 刷子
     */
    private lateinit var mPaint: Paint

    constructor(context: Context) : super(context) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, theme: Int) : super(
        context,
        attributeSet,
        theme
    ) {
        initData(context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, DisplayUtils.dip2px(20F))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GRAY)
        for (i: Int in textArray.indices) {
            canvas?.drawText(
                textArray[i],
                (i * len) / 7F + DisplayUtils.dip2px(25F),
                DisplayUtils.dip2px(15F).toFloat(),
                mPaint
            )
        }
        canvas?.save()
    }


    private fun initData(context: Context) {
        len = context.displayMetrics.widthPixels
        mPaint = Paint()
        mPaint.color = Color.parseColor("#005bab")
        mPaint.textSize = DisplayUtils.dip2px(12F).toFloat()
        mPaint.textAlign=Paint.Align.CENTER
    }
}

