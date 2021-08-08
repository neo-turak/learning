package cn.nurasoft.learningshadow

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF





/**
 * @author hugo
 * @time 2021/8/2下午5:09
 * @project LearningShadow
 * Think Twice, Code Once!
 */
class UnRuleView:View {
    var mPaint=Paint(Paint.ANTI_ALIAS_FLAG)
    var rectF=RectF(0F, 0F, 45F, 300F)
    var paint=Paint()
    constructor(mContext:Context):super(mContext){
    }

    constructor(mContext: Context, attributeSet: AttributeSet):super(mContext,attributeSet){

    }
    constructor(mContext: Context, attributeSet: AttributeSet, theme:Int):super(mContext,attributeSet,theme){

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val canvasWidth = canvas!!.width
        val deltaX = canvasWidth / 4
        val deltaY = (deltaX * 0.5).toInt()

        paint.color = -0x743a46 //设置画笔颜色
        paint.strokeWidth = 4F //设置线宽

        /*-----------------使用lineTo、arcTo、quadTo、cubicTo画线--------------*/

        /*-----------------使用lineTo、arcTo、quadTo、cubicTo画线--------------*/
        paint.style = Paint.Style.STROKE //设置画笔为线条模式

        val path3 = Path()
        //用pointList记录不同的path的各处的连接点
        //用pointList记录不同的path的各处的连接点
        val pointList: MutableList<Point> = ArrayList()
        //1. 第一部分，绘制线段
        //1. 第一部分，绘制线段


        pointList.add(Point((deltaX * 1.5).toInt(), deltaY))
        //4. 第四部分，绘制二阶贝塞尔曲线
        //二阶贝塞尔曲线的起点就是当前画笔的位置，然后需要添加一个控制点，以及一个终点
        //再次通过调用path的moveTo方法，移动画笔
        //4. 第四部分，绘制二阶贝塞尔曲线
        //二阶贝塞尔曲线的起点就是当前画笔的位置，然后需要添加一个控制点，以及一个终点
        //再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 1.5f, deltaY.toFloat())
        //绘制二阶贝塞尔曲线
        //绘制二阶贝塞尔曲线
        path3.quadTo((deltaX * 2).toFloat(), 0f, deltaX * 2.5f, (deltaY / 2).toFloat())
        pointList.add(Point((deltaX * 2.5).toInt(), deltaY / 2))
        //5. 第五部分，绘制三阶贝塞尔曲线，三阶贝塞尔曲线的起点也是当前画笔的位置
        //其需要两个控制点，即比二阶贝赛尔曲线多一个控制点，最后也需要一个终点
        //再次通过调用path的moveTo方法，移动画笔
        //5. 第五部分，绘制三阶贝塞尔曲线，三阶贝塞尔曲线的起点也是当前画笔的位置
        //其需要两个控制点，即比二阶贝赛尔曲线多一个控制点，最后也需要一个终点
        //再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 2.5f, (deltaY / 2).toFloat())
        //绘制三阶贝塞尔曲线
        //绘制三阶贝塞尔曲线
        path3.cubicTo(
            (deltaX * 3).toFloat(),
            0f,
            deltaX * 3.5f,
            0f,
            (deltaX * 4).toFloat(),
            deltaY.toFloat()
        )
        pointList.add(Point(deltaX * 4, deltaY))

        //Path准备就绪后，真正将Path绘制到Canvas上

        //Path准备就绪后，真正将Path绘制到Canvas上
        canvas!!.drawPath(path3, paint)

        //最后绘制Path的连接点，方便我们大家对比观察

        //最后绘制Path的连接点，方便我们大家对比观察
        paint.strokeWidth = 10F //将点的strokeWidth要设置的比画path时要大

        paint.strokeCap = Paint.Cap.ROUND //将点设置为圆点状

        paint.color = -0xffff01 //设置圆点为蓝色

        for (p in pointList) {
            //遍历pointList，绘制连接点
            canvas.drawPoint(p.x.toFloat(), p.y.toFloat(), paint)
        }

    }
}