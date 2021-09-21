package cn.nurasoft.learningshadow

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.util.*
import kotlin.properties.Delegates

/**
 *@author hugo
 *@time    2021/9/4下午12:17
 *@project  service-juran
 *Think Twice, Code Once!
 */
class MonthDateView : View, AddOnDateClickListener {

    /**
     * M paint
     */
    private lateinit var mPaint: Paint
    private lateinit var cPaint: Paint

    /**
     * Current 当前天
     */
    private var current: Int? = null

    /**
     * Today 当前日期
     */
    private var today: Int by Delegates.notNull()

    /**
     * Start 开始数字
     */
    private var start: Int? = null

    /**
     * Month 当前月
     */
    private var month: Int by Delegates.notNull()

    private var year by Delegates.notNull<Int>()

    /**
     * Last month 上月最多天数
     */
    private var lastMonthMax by Delegates.notNull<Int>()

    /**
     * Current month max  本月最多日
     */
    private var currentMonthMax by Delegates.notNull<Int>()


    constructor(context: Context) : super(context) {
        initView()
        }


    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet, theme: Int) : super(
        context,
        attributeSet,
        theme
    ) {
        initView()
    }

    /**
     * 点击事件的处理。
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x

        if (event.action == MotionEvent.ACTION_DOWN) {
            when (x.toInt()) {
                in 0..width / 7 -> {
                    this.singleClick(1)
                    current = 1
                }
                in width * 1 / 7..width * 2 / 7 -> {
                    this.singleClick(2)
                    current = 2
                }
                in width * 2 / 7..width * 3 / 7 -> {
                    this.singleClick(3)
                    current = 3
                }
                in width * 3 / 7..width * 4 / 7 -> {
                    this.singleClick(4)
                    current = 4
                }
                in width * 4 / 7..width * 5 / 7 -> {
                    this.singleClick(5)
                    current = 5
                }
                in width * 5 / 7..width * 6 / 7 -> {
                    this.singleClick(6)
                    current = 6
                }
                in width * 6 / 7..width -> {
                    this.singleClick(7)
                    current = 7
                }
            }
            initView()
            postInvalidate()
        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //硬性写宽高，高度最少37dp。
        setMeasuredDimension(
            widthMeasureSpec,
            (DisplayUtils.dip2px(37F) + DisplayUtils.dip2Px(paddingBottom+paddingTop)).toInt()
        )
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //index 0...6, value -1...6
        for ((index, value: Int) in (start!!..start!! + 6).withIndex()) {
            mPaint.color = Color.parseColor("#3E3E3F")
            var tempPrintValue = value
            //判断是不是当天日期。
            if (current!! - 1 == index) {
                //判断是否在本月。
                //如果低于本月。
                if (value <= 0) {
                    tempPrintValue = lastMonthMax + value
                }
                //如果超过本月
                if (value > currentMonthMax) {
                    tempPrintValue = value - currentMonthMax
                }
                mPaint.color = Color.WHITE
                canvas?.drawCircle(
                    (index * width) / 7F + DisplayUtils.dip2px(
                        if (tempPrintValue > 9) {
                            31F
                        } else {
                            27F
                        }
                    ),
                    ((height/2).toFloat()),
                    DisplayUtils.dip2px(12F).toFloat(),
                    cPaint
                )
                //描绘天数.
                canvas?.drawText(
                    tempPrintValue.toString(),
                    (index * width) / 7F + DisplayUtils.dip2px(
                        if (index.toString().length > 1) {
                            18F
                        } else {
                            23F
                        }
                    ),
                    (height/2)+DisplayUtils.dip2Px(5),mPaint
                )

            } else {
                //判断
                //如果低于本月。
                if (value <= 0) {
                    tempPrintValue = lastMonthMax + value
                    mPaint.color = Color.parseColor("#7E7E7E")
                }
                //如果超过本月
                if (value > currentMonthMax) {
                    tempPrintValue = value - currentMonthMax
                    mPaint.color = Color.parseColor("#7E7E7E")
                }
                //描绘天数.
                canvas?.drawText(
                    tempPrintValue.toString(),
                    (index * width) / 7F + DisplayUtils.dip2px(
                        if (tempPrintValue.toString().length > 1) {
                            18F
                        } else {
                            23F
                        }
                    ),
                    (height/2)+DisplayUtils.dip2Px(5),
                   // DisplayUtils.dip2px(24F).toFloat(),
                    mPaint
                )
            }
        }
        canvas?.save()
    }


    private fun initView() {
        //获取今天的数据
        val calendar = Calendar.getInstance()
        //时区
        calendar.timeZone = TimeZone.getTimeZone("GMT+8")
        year = calendar.get(Calendar.YEAR)
        //注：月从0开始的。
        month = calendar.get(Calendar.MONTH) + 1
        //今天
        today = calendar.get(Calendar.DAY_OF_MONTH)
        //周数

        if (current == null) {
            //注意：开始是从周日
            current =if (calendar.get(Calendar.DAY_OF_WEEK)==7){
                0
            }else{
                calendar.get(Calendar.DAY_OF_WEEK)-1
            }

        }
        //比较周，然后计算出开始日期.
        if (start == null) {
            start=today- current!! +1

        }
        //获取上个月的天数。
        //如果不再本年。
        with(month - 1) {
            lastMonthMax = if (this == 0) {
                //12月的总数永远是31天，所以不需要从Calendar获取。
                31
            } else {
                getMonthLastDay(year, this)
            }
        }
        //获取本月最大天数.
        currentMonthMax = getMonthLastDay(year, month)

        mPaint = Paint()
        mPaint.textSize = DisplayUtils.dip2px(14F).toFloat()
        mPaint.color = Color.parseColor("#3E3E3F")
        mPaint.isAntiAlias = true

        cPaint = Paint()
        cPaint.color = Color.parseColor("#005BAB")
        cPaint.isAntiAlias = true

    }


    /**
     * Update 更新。
     *
     */
    fun update() {
        this.postInvalidate()
    }

    /**
     * 得到指定月的天数
     */
    private fun getMonthLastDay(year: Int, month: Int): Int {
        val a = Calendar.getInstance()
        a[Calendar.YEAR] = year
        a[Calendar.MONTH] = month - 1
        a[Calendar.DATE] = 1 //把日期设置为当月第一天
        a.roll(Calendar.DATE, -1) //日期回滚一天，也就是最后一天
        return a[Calendar.DATE]
    }

    override fun singleClick(position: Int) {
        Toast.makeText(context,"选中${start!!+position-1}", Toast.LENGTH_LONG).show()
    }
}


/**
 * Add on date click listener
 * 点击事件
 * @constructor Create empty Add on date click listener
 */
interface AddOnDateClickListener {
    fun singleClick(position: Int)
}
