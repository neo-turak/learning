package cn.nurasoft.learningshadow

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.util.*
import kotlin.properties.Delegates

class FullMonthView : View, AddOnDateClickListener {

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
    private var start by Delegates.notNull<Int>()

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

    private var mHeight by Delegates.notNull<Int>()

    private var mWidth by Delegates.notNull<Int>()

    private var roundBg by Delegates.notNull<RectF>()


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
        mHeight=(DisplayUtils.dip2px(37F)*4 + DisplayUtils.dip2Px(paddingBottom+paddingTop)).toInt()
        mWidth=widthMeasureSpec
        setMeasuredDimension(mWidth, mHeight)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color=Color.parseColor("#E5E8E8")
        canvas?.drawRoundRect(RectF(0F,0F,width.toFloat(),height.toFloat()),
            DisplayUtils.dip2Px(10),
            DisplayUtils.dip2Px(10),mPaint)
        for (v in 1..5){
            //index 0...29, value 1...30
            for ((index, value) in (start + (v-1)*7 ..8+(v-1)*7).withIndex()) {
                mPaint.color = Color.parseColor("#3E3E3F")
                var tempPrintValue:Int?=0
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

                if (tempPrintValue==0){
                    tempPrintValue=value
                }

                //判断是不是当天日期。
                if (today==value){
                    mPaint.color=Color.parseColor("#005BAB")
                    canvas?.drawCircle(    (index * width) / 7F + DisplayUtils.dip2px(
                        if (tempPrintValue.toString().length > 1) {
                            25F
                        } else {
                            25F
                        }
                    ),
                        (v*height/6)-DisplayUtils.dip2Px(14),DisplayUtils.dip2Px(11),mPaint)

                    mPaint.color=Color.WHITE
                    canvas?.drawText(
                        tempPrintValue.toString(),
                        (index * width) / 7F + DisplayUtils.dip2px(
                            if (tempPrintValue.toString().length > 1) {
                                18F
                            } else {
                                23F
                            }
                        ),
                        (v*height/6)-DisplayUtils.dip2Px(10),
                        mPaint
                    )
                }else{
                    canvas?.drawText(
                        tempPrintValue.toString(),
                        (index * width) / 7F + DisplayUtils.dip2px(
                            if (tempPrintValue.toString().length > 1) {
                                18F
                            } else {
                                23F
                            }
                        ),
                        (v*height/6)-DisplayUtils.dip2Px(10),
                        mPaint
                    )
                }
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
        month = calendar.get(Calendar.MONTH)+1
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
        start=1-current!!

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

    //    roundBg= RectF(0F,0F, mWidth.toFloat(), mHeight.toFloat())
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
        Toast.makeText(context,"选中${start+position-1}", Toast.LENGTH_LONG).show()
    }
}
