package cn.nurasoft.learningshadow

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

/**
 *@author hugo
 *@time    2021/8/8下午6:22
 *@project  LearningShadow
 *Think Twice, Code Once!
 */
class StarView : View {

    val paint=Paint()
    val path=Path()
    constructor(mContext:Context):super(mContext){
        StarView(mContext,null)
    }
    constructor(mContext: Context,attributeSet: AttributeSet?):super(mContext,attributeSet){

    }
    constructor(mContext: Context,attributeSet: AttributeSet?,theme:Int):super(mContext,attributeSet,theme){


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style=Paint.Style.STROKE
        paint.color= Color.BLACK
        paint.isAntiAlias=true
        paint.strokeWidth=4F
        //E
        path.moveTo(300+16F,250F)

        path.lineTo(300F,200F)
        //
        path.lineTo(300-16F,250F)

        path.lineTo(300-16F-50,250F+16)

        path.lineTo(274F,250F+16+16)

        path.lineTo(300-16F-28F,250F+16+50+16)


      //  path.lineTo()
        canvas.drawPath(path, paint)
        canvas.save()
    }



}