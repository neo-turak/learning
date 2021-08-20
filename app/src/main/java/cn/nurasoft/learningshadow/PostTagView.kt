package cn.nurasoft.learningshadow

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import timber.log.Timber

/**
 *@author hugo
 *@time    2021/8/19下午2:51
 *@project  LearningShadow
 *Think Twice, Code Once!
 */
class PostTagView : View {
    /**
     * React 四边形坐标点
     */
    var react= RectF()

    /**
     * Circle radius 第一个圆的半径(dp)
     */
    private var circleRadius=20

    /**
     * M paint 画C1C1C1 为背景。
     * 注：抗锯齿关闭(为了性能)
     * @see Paint.isAntiAlias
     */
    private lateinit var mPaint:Paint

    /**
     * T paint 画文案
     * 抗锯齿已开。
     * 颜色白色。
     */
    private lateinit var tPaint:Paint

    /**
     * C paint 透明背景
     */
    private lateinit var cPaint:Paint

    /**
     * Path 路径 画三角形
     */
    private lateinit var path:Path

    /**
     * Bitmap 解析Bitmap
     */
    private lateinit var bitmap: Bitmap


    constructor(mContext: Context) : super(mContext) {
        initParams()

    }

    constructor(mContext: Context, attributeSet: AttributeSet) : super(mContext, attributeSet) {
     initParams()

    }

    constructor(mContext: Context, attributeSet: AttributeSet, theme: Int) : super(
        mContext,
        attributeSet,
        theme
    ) {
      initParams()
    }


    /**
     * On measure 事件
     * 注明一下：咱们假装产品卡的右边不超过屏幕。
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
      //  setMeasuredDimension(1000,400)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawOtherTag( R.drawable.ic_pinapple,100F,200F,"Blog Name Her there",canvas)
        drawProductTag(R.drawable.raspberry,100F,400F,"GUCCI","$300-$600",canvas)
        drawOtherTag( R.drawable.ic_pitches,100F,600F,"List Name Her over there",canvas)
        drawOtherTag( R.drawable.ic_cherry,100F,800F,"Louis Vuitton",canvas)
    }

    /**
     * Init params
     * 这里会初始化一些变量。
     */
    private fun initParams() {
        //初始化 主要颜色
        mPaint = Paint()
        mPaint.isAntiAlias=false
        mPaint.color = Color.parseColor("#1c1c1c")
        mPaint.style = Paint.Style.FILL
        //初始化 文字
        tPaint= Paint()
        tPaint.isAntiAlias=true
        tPaint.color = Color.WHITE
        tPaint.textSize=DisplayUtils.dip2px(12F).toFloat()
        tPaint.typeface= Typeface.DEFAULT_BOLD

        //初始化Path
        path=Path()

        //初始化cPaint
        cPaint= Paint()
        cPaint.color=Color.parseColor("#B31c1c1c")
        cPaint.isAntiAlias=false
    }

    /**
     * Draw other 除了产品 画其他Tag
     *
     * @param src 资源（R.drawable.xxx）
     * @param x  初始x坐标
     * @param y  初始y坐标
     * @param content 文案
     * @param canvas  Canvas
     */
    fun drawOtherTag(src:Int,x:Float,y:Float,content:String,canvas: Canvas?){
       //解决文字的长度问题
       var temp= if (content.length>13){
       content.substring(0..13)+"..."
        }else{
         content
       }
        val drawable=AppCompatResources.getDrawable(context,src)
        bitmap=drawable!!.toBitmap(DisplayUtils.dip2px(20F),DisplayUtils.dip2px(20F))

        canvas?.drawCircle(x,y,DisplayUtils.dip2px( 15f).toFloat(),cPaint)
        path.moveTo(x+DisplayUtils.dip2px(15F).toFloat(),y)
        path.lineTo(x+DisplayUtils.dip2px(25F),y+DisplayUtils.dip2px(10F))
        path.lineTo(x+DisplayUtils.dip2px(25F),y+DisplayUtils.dip2px(-10F))
        path.close()
        canvas?.drawPath(path,mPaint)
        react= RectF(x+DisplayUtils.dip2px(25F),y-DisplayUtils.dip2px(20F),x+tPaint.measureText(temp)+DisplayUtils.dip2px(50F),y+DisplayUtils.dip2px(20F))
        canvas!!.drawRoundRect(react,30F,30F,mPaint)
        canvas.drawText(temp,x+DisplayUtils.dip2px(35F),y+DisplayUtils.dip2px(3F),tPaint)
        canvas.drawBitmap(bitmap,x-DisplayUtils.dip2px(10F),y-DisplayUtils.dip2px(10F),null)
        canvas.save()
    }

    /**
     * Draw product tag
     * 画产品Tag
     *
     * @param src  资源(R.drawable.ic_xxx)
     * @param x    x坐标
     * @param y    y坐标
     * @param content  产品名
     * @param priceRange  价格区间
     * @param canvas   Canvas
     */
    fun drawProductTag(src:Int,x:Float,y:Float,content:String,priceRange:String,canvas: Canvas?){
        //开始点
        //保证不超过13个
        val tempContent = if (content.length>13){
                content.substring(0..13)+"..."
            }else{
                content
            }
        //保证不超过13个
        val tempPrice = if (priceRange.length>13){
                priceRange.substring(0..13)+"..."
            }else{
                priceRange
            }

        val drawable=AppCompatResources.getDrawable(context,src)
        bitmap=drawable!!.toBitmap(DisplayUtils.dip2px(20F),DisplayUtils.dip2px(20F))

        canvas?.drawCircle(x,y,DisplayUtils.dip2px( 15f).toFloat(),cPaint)
        path.moveTo(x+DisplayUtils.dip2px(15F).toFloat(),y)
        path.lineTo(x+DisplayUtils.dip2px(25F),y+DisplayUtils.dip2px(10F))
        path.lineTo(x+DisplayUtils.dip2px(25F),y+DisplayUtils.dip2px(-10F))
        path.close()
        canvas?.drawPath(path,mPaint)
        //以最长的为主。
        react = if (tempContent.length>tempPrice.length){
            RectF(x+DisplayUtils.dip2px(25F),y-DisplayUtils.dip2px(20F),x+tPaint.measureText(tempContent)+DisplayUtils.dip2px(50F),y+DisplayUtils.dip2px(20F))
        }else{
            RectF(x+DisplayUtils.dip2px(25F),y-DisplayUtils.dip2px(20F),x+tPaint.measureText(tempPrice)+DisplayUtils.dip2px(50F),y+DisplayUtils.dip2px(20F))
        }
        canvas!!.drawRoundRect(react,30F,30F,mPaint)
        canvas.drawText(tempContent,x+DisplayUtils.dip2px(35F),y-DisplayUtils.dip2px(5F),tPaint)
        canvas.drawText(tempPrice,x+DisplayUtils.dip2px(35F),y+DisplayUtils.dip2px(10F),tPaint)
        canvas.drawBitmap(bitmap,x-DisplayUtils.dip2px(10F),y-DisplayUtils.dip2px(10F),null)
        canvas.save()
    }
}