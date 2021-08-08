package cn.nurasoft.learningshadow

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import timber.log.Timber
import android.graphics.RectF




/**
 * @author hugo
 * @time 2021/8/2下午5:44
 * @project LearningShadow
 * Think Twice, Code Once!
 */
/**
 * 二阶
 * Created by Deeson on 2017/5/24.
 */
class MyBezierCurveQuadratic : View {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var centerX = 0
    private var centerY = 0
    private lateinit var start: PointF
    private lateinit var end: PointF
    private lateinit var control: PointF

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPath = Path()
        mPaint!!.color = Color.BLACK
        mPaint!!.strokeWidth = 8f
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.textSize = 60f
        start = PointF(0F, 0F)
        end = PointF(0F, 0F)
        control = PointF(0F, 0F)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2
        centerY = h / 2
        //初始化数据点和控制点的位置
       // start.x = (centerX - 400).toFloat()
      //  start.y = centerY.toFloat()
       // end.x = (centerX + 400).toFloat()
       // end.y = centerY.toFloat()
       // control.x = centerX.toFloat()
      // control.y = (centerY - 100).toFloat()

        start.x=420F
        start.y=700F
        control.x=575F
        control.y=700F
        end.x=600F
        end.y=658F
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制数据点和控制点
        mPaint!!.color = Color.GRAY
        mPaint!!.strokeWidth = 1f
     //   canvas.drawPoint(start.x, start.y, mPaint!!)
     //   canvas.drawPoint(end.x, end.y, mPaint!!)
      //  canvas.drawPoint(control.x, control.y, mPaint!!)

        //绘制辅助线
/*        mPaint!!.strokeWidth = 4f
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint!!)
        canvas.drawLine(control.x, control.y, end.x, end.y, mPaint!!)*/

        //绘制二阶贝塞尔曲线
        mPaint!!.color = Color.RED
        mPaint!!.strokeWidth = 4f
        mPath!!.reset()

        mPath!!.moveTo(start.x, start.y)
        Timber.e("SX-->${start.x} SY-->${start.y}")
        mPath!!.quadTo(control.x, control.y, end.x, end.y)
        Timber.e("CX-->${control.x} CY-->${control.y}  EX${end.x}   EY${end.y}")
        canvas.drawPath(mPath!!, mPaint!!)


        mPath!!.reset()
        mPath!!.moveTo(420F,1300F)
        mPath!!.quadTo(575F,1300F,600F,1330F)
        canvas.drawPath(mPath!!,mPaint!!)
        canvas.drawLine(600F,658F,600F,1330F,mPaint!!)
        mPath!!.reset()
        mPath!!.moveTo(370F,750F)
        mPath!!.quadTo(375F,700F,420F,700F)
        canvas.drawPath(mPath!!,mPaint!!)

        canvas.drawLine(370F,750F,370F,1300F,mPaint!!)

        mPath!!.reset()
        mPath!!.moveTo(370F,1280F)
        mPath!!.quadTo(400F,1330F,420F,1300F)
        canvas.drawPath(mPath!!,mPaint!!)

      /*  path.moveTo(500F,500F)
        path.quadTo(600F,500F,612.8F,387F)
        path.fillType= Path.FillType.WINDING
        canvas?.drawPath(path,paint)
        canvas?.drawLine(400F,500F,505F,500F,paint)

        path.moveTo(400F,500F)
        path.quadTo(287F,500F,287F,613F)
        path.fillType= Path.FillType.WINDING
        canvas?.drawPath(path,paint)

        canvas?.drawLine(287F,613F,287F,1013F,paint)

        path.moveTo(287F,1013F)
        path.quadTo(287F,1126F,400F,1126F)
        path.fillType= Path.FillType.WINDING
        canvas?.drawPath(path,paint)

        canvas?.drawLine(400F,1126F,500F,1126F,paint)


        path.moveTo(500F,1126F)
        path.quadTo(600F,1126F,612.8F,1239F)
        path.fillType= Path.FillType.WINDING
        canvas?.drawLine(612.8F,1239F,612.8F,387F,paint)
        path.close()*/

        canvas?.save()

    }


}