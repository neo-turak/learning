package cn.nurasoft.learningshadow
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import timber.log.Timber
import kotlin.properties.Delegates

/**
 *@author hugo
 *@time    2021/8/2下午1:41
 *@project  Modesens
 *Think Twice, Code Once!
 */
class IndicatorView : View {
    private val tag = this.javaClass.simpleName
    private var mainColor by Delegates.notNull<Int>()
    private var secondColor by Delegates.notNull<Int>()
    var count by Delegates.notNull<Int>()
    var position: Int = 0
    private lateinit var mPaint: Paint
    private var bgColor by Delegates.notNull<Int>()
    private var radius by Delegates.notNull<Float>()
    private var mWidth by Delegates.notNull<Int>()
    private var mHeight by Delegates.notNull<Int>()

    constructor(mContext: Context) : super(mContext) {
        initView(mContext, null)
    }

    constructor(mContext: Context, attributes: AttributeSet) : super(mContext, attributes) {
        initView(mContext, attributes)
    }

    constructor(mContext: Context, attributes: AttributeSet, theme: Int) : super(
        mContext,
        attributes,
        theme
    ) {
        initView(mContext, attributes)

    }


    //请注意执行顺序. 第一次，会多次执行
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = getMySize(30 * (count + 2), widthMeasureSpec)
        mHeight = getMySize(20, heightMeasureSpec)
        setMeasuredDimension(mWidth, mHeight)
    }

    /**
     * Get default size
     * this will get the real size of the View
     */
    private fun getMySize(size: Int, measureSpec: Int): Int {
        var result = size
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            //未指定,
            // wrap_content
            MeasureSpec.UNSPECIFIED,
            MeasureSpec.AT_MOST -> {
                result = size
                return result
            }
            //Match_parent
            MeasureSpec.EXACTLY -> {
                result = specSize
                return result
            }
        }
        return result
    }

    //执行顺序，第二次，可能会多次触发。
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        //  mWidth = right - left
        //  mHeight = bottom - top
    }


    //执行顺序,第三次,可能会多次
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.isAntiAlias = true
        //画背景先
        canvas?.drawColor(bgColor)
        //遍历时画图

        if (count != 0) {
            for (i: Int in 1..count) {
                //如果是当前位置，颜色要MainColor。
                if (position == i) {
                    mPaint.color = mainColor
                    //如果普通位置，用普通颜色。
                } else {
                    mPaint.color = secondColor
                }
                //画圆,参数中高度要刚好在View中间。
                canvas?.drawCircle(i * mWidth / (count + 1).toFloat(), mHeight / 2F, radius, mPaint)
            }
        }
        canvas?.save()
    }


    private fun initView(mContext: Context, attributes: AttributeSet?) {
        mPaint = Paint()
        val ta = mContext.obtainStyledAttributes(attributes, R.styleable.IndicatorView)
        mainColor = ta.getColor(R.styleable.IndicatorView_indicateMainColor, Color.RED)
        secondColor = ta.getColor(R.styleable.IndicatorView_indicateSecondColor, Color.GRAY)
        position = ta.getInt(R.styleable.IndicatorView_indicatePosition, 1)
        bgColor = ta.getColor(R.styleable.IndicatorView_android_background, Color.WHITE)
        radius = ta.getFloat(R.styleable.IndicatorView_indicatorRadius, 10F)
        count = ta.getInt(R.styleable.IndicatorView_indicatorCount, 3)
        /*    //判断总数不要0
            if (count==0){
                throw  IllegalArgumentException("Can not set 0 to the Total")
            }
            //判断position不能大于总数
            if (count < position){
                throw IllegalArgumentException("Position must be greater than Count")
            }*/
        //回收
        ta.recycle()
    }

    /**
     * Update position
     *
     * @param mPosition 需要更新的位置
     */
    fun updatePosition(mPosition: Int) {
        Timber.e("位置->:${mPosition}  总数->:${count}")
        position = if (mPosition <= count) {
            mPosition
        } else {
            1
        }
        this.postInvalidate()
    }
}

