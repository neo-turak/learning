package cn.nurasoft.learningshadow




import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor

/**
 * @author hugo
 * @time 2021-5-24
 * Think twice, Code once.
 * 主页控制台
 */
class HomeHierarchyView : ConstraintLayout {

    /**
     *  文字还是背景图
     */
    var textOrImage: Boolean = false

    /**
     *  待量房
     */
    var waitingMeasure: String? = ""

    /**
     * 待预交底
     */
    var waitingSubmit: String? = ""

    /**
     *待签约
     */
    var waitingSign: String? = ""

    /**
     * 未开工
     */
    var statUnStart: String? = ""

    /**
     * 施工中
     */
    var statInProgress: String? = ""

    /**
     * 已竣工
     */
    var statOnFinished: String? = ""

    /**
     * 已结算
     */
    var statClearing: String? = ""

    /**
     * 文案1
     */
    lateinit var tv_top1: TextView
    lateinit var tv_top2: TextView
    lateinit var tv_top3: TextView
    lateinit var tv_top4: TextView
    lateinit var tv_top5: TextView
    lateinit var tv_top6: TextView
    lateinit var tv_top7: TextView

    var cn: Context = context

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        //获取资源
        initParams(attributeSet)
        initView()
    }

    constructor(cn: Context, attributeSet: AttributeSet, style: Int) : super(
        cn,
        attributeSet,
        style
    ) {
        //获取资源
        initParams(attributeSet)
        initView()
    }

    private fun initParams(attributeSet: AttributeSet) {
        val ta = cn.obtainStyledAttributes(attributeSet, R.styleable.HomeHierarchyView)
        textOrImage = ta.getBoolean(R.styleable.HomeHierarchyView_texture, true)
        waitingMeasure = ta.getString(R.styleable.HomeHierarchyView_waitingMeasure)
        waitingSubmit = ta.getString(R.styleable.HomeHierarchyView_waitingSubmit)
        waitingSign = ta.getString(R.styleable.HomeHierarchyView_waitingSign)
        statInProgress = ta.getString(R.styleable.HomeHierarchyView_statInProgress)
        statUnStart = ta.getString(R.styleable.HomeHierarchyView_statUnStart)
        statOnFinished = ta.getString(R.styleable.HomeHierarchyView_statOnFinished)
        statClearing = ta.getString(R.styleable.HomeHierarchyView_statClearing)
        ta.recycle()
    }

    private fun addTextView(textView: TextView, id: Int): TextView {
        textView.layoutParams = LinearLayout.LayoutParams(-2, DisplayUtils.dip2px(30F))
        textView.textSize = 22F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#0166B3")
        textView.id = id
        textView.gravity = Gravity.CENTER
        textView.text = ""
        return textView
    }

    private fun addLabel(s: String): TextView {
        val textView = TextView(cn)
        textView.layoutParams = LinearLayout.LayoutParams(-2, -2)
        textView.text = s
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#3e3e3e")
        (textView.layoutParams as LinearLayout.LayoutParams).topMargin = DisplayUtils.dip2px(5F)
        return textView
    }

    fun initView() {

        //初始化整个textview系列
        tv_top1 = TextView(cn)
        tv_top2 = TextView(cn)
        tv_top3 = TextView(cn)
        tv_top4 = TextView(cn)
        tv_top5 = TextView(cn)
        tv_top6 = TextView(cn)
        tv_top7 = TextView(cn)

        this.background = ContextCompat.getDrawable(cn, R.drawable.round_corder_w)
        val parentParams = ViewGroup.LayoutParams(-1, DisplayUtils.dip2px(160F))
        this.layoutParams = parentParams

        //ll1
        val l1 = LinearLayout(cn)
        l1.id = R.id.ll_1
        val l1Params = LayoutParams(0, DisplayUtils.dip2px(80F))
        l1.gravity = Gravity.CENTER
        l1.orientation = LinearLayout.VERTICAL
        l1Params.leftToLeft = this.id
        l1Params.topToTop = this.id
        l1Params.matchConstraintPercentWidth = 0.33F
        l1.addView(addTextView(tv_top1, R.id.tv_top1))
        l1.addView(addLabel("待量房"))
        l1.layoutParams = l1Params

        //ll2
        val l2 = LinearLayout(cn)
        l2.id = R.id.ll_2
        val l2Params = LayoutParams(0, DisplayUtils.dip2px(80F))
        l2.gravity = Gravity.CENTER
        l2.orientation = LinearLayout.VERTICAL
        l2Params.leftToRight = R.id.ll_1
        l2Params.topToTop = this.id
        l2Params.matchConstraintPercentWidth = 0.33F
        l2.addView(addTextView(tv_top2, R.id.tv_top2))
        l2.addView(addLabel("待预交底"))
        l2.layoutParams = l2Params


        //ll3
        val l3 = LinearLayout(cn)
        l3.id = R.id.ll_3
        val l3Params = LayoutParams(0, DisplayUtils.dip2px(80F))
        l3.gravity = Gravity.CENTER
        l3.orientation = LinearLayout.VERTICAL
        l3Params.leftToRight = R.id.ll_2
        l3Params.topToTop = this.id
        l3Params.matchConstraintPercentWidth = 0.33F
        l3.addView(addTextView(tv_top3, R.id.tv_top3))
        l3.addView(addLabel("待签约"))
        l3.layoutParams = l3Params

        //v1
        val v1 = View(cn)
        v1.id = R.id.v_1
        v1.layoutParams = LayoutParams(DisplayUtils.dip2px(1F), DisplayUtils.dip2px(60F))
        v1.backgroundColor = Color.parseColor("#EDEDED")
        (v1.layoutParams as LayoutParams).leftToRight = R.id.ll_1
        (v1.layoutParams as LayoutParams).topToTop = this.id
        (v1.layoutParams as LayoutParams).bottomToBottom = R.id.ll_1

        //v2
        val v2 = View(cn)
        v1.id = R.id.v_2
        v2.layoutParams = LayoutParams(DisplayUtils.dip2px(1F), DisplayUtils.dip2px(60F))
        v2.backgroundColor = Color.parseColor("#EDEDED")
        (v2.layoutParams as LayoutParams).leftToRight = R.id.ll_2
        (v2.layoutParams as LayoutParams).topToTop = this.id
        (v2.layoutParams as LayoutParams).bottomToBottom = R.id.ll_2

        //v3
        val v3 = View(cn)
        v1.id = R.id.v_3
        v3.layoutParams = LayoutParams(-1, DisplayUtils.dip2px(1F))
        v3.backgroundColor = Color.parseColor("#EDEDED")
        (v3.layoutParams as LayoutParams).marginEnd = DisplayUtils.dip2px(20F)
        (v3.layoutParams as LayoutParams).marginStart = DisplayUtils.dip2px(20F)
        (v3.layoutParams as LayoutParams).topToBottom = R.id.ll_1


        //v4
        val l4 = LinearLayout(cn)
        l4.id = R.id.ll_4
        val l4Params = LayoutParams(0, DisplayUtils.dip2px(80F))
        l4.gravity = Gravity.CENTER
        l4.orientation = LinearLayout.VERTICAL
        l4Params.leftToLeft = this.id
        l4Params.bottomToBottom = this.id
        l4Params.topToBottom = R.id.v_3
        l4Params.matchConstraintPercentWidth = 0.25F
        l4.addView(addTextView(tv_top4, R.id.tv_top4))
        l4.addView(addLabel("未开工"))
        l4.layoutParams = l4Params

        //ll5
        val l5 = LinearLayout(cn)
        l5.id = R.id.ll_5
        val l5Params = LayoutParams(0, DisplayUtils.dip2px(80F))
        l5.gravity = Gravity.CENTER
        l5.orientation = LinearLayout.VERTICAL
        l5Params.leftToRight = R.id.ll_4
        l5Params.bottomToBottom = this.id
        l5Params.topToBottom = R.id.v_3
        l5Params.matchConstraintPercentWidth = 0.25F
        l5.addView(addTextView(tv_top5, R.id.tv_top5))
        l5.addView(addLabel("施工中"))
        l5.layoutParams = l5Params

        //ll6
        val l6 = LinearLayout(cn)
        l6.id = R.id.ll_6
        val l6Params = LayoutParams(0, DisplayUtils.dip2px(80F))
        l6.gravity = Gravity.CENTER
        l6.orientation = LinearLayout.VERTICAL
        l6Params.bottomToBottom = this.id
        l6Params.leftToRight = R.id.ll_5
        l6Params.topToBottom = R.id.v_3
        l6Params.matchConstraintPercentWidth = 0.25F
        l6.addView(addTextView(tv_top6, R.id.tv_top6))
        l6.addView(addLabel("已竣工"))
        l6.layoutParams = l6Params

        //ll7
        val l7 = LinearLayout(cn)
        l7.id = R.id.ll_7
        val l7Params = LayoutParams(0, DisplayUtils.dip2px(80F))
        l7.gravity = Gravity.CENTER
        l7.orientation = LinearLayout.VERTICAL
        l7Params.bottomToBottom = this.id
        l7Params.leftToRight = R.id.ll_6
        l7Params.topToBottom = R.id.v_3
        l7Params.matchConstraintPercentWidth = 0.25F
        l7.addView(addTextView(tv_top7, R.id.tv_top7))
        l7.addView(addLabel("已结算"))
        l7.layoutParams = l7Params

        //v4
        val v4 = View(cn)
        v4.layoutParams = LayoutParams(DisplayUtils.dip2px(1F), DisplayUtils.dip2px(60F))
        v4.backgroundColor = Color.parseColor("#EDEDED")
        (v4.layoutParams as LayoutParams).leftToRight = R.id.ll_4
        (v4.layoutParams as LayoutParams).topToTop = R.id.ll_4
        (v4.layoutParams as LayoutParams).bottomToBottom = R.id.ll_4

        //v5
        val v5 = View(cn)
        v5.layoutParams = LayoutParams(DisplayUtils.dip2px(1F), DisplayUtils.dip2px(60F))
        v5.backgroundColor = Color.parseColor("#EDEDED")
        (v5.layoutParams as LayoutParams).leftToRight = R.id.ll_5
        (v5.layoutParams as LayoutParams).topToTop = R.id.ll_5
        (v5.layoutParams as LayoutParams).bottomToBottom = R.id.ll_5

        //v6
        val v6 = View(cn)
        v6.layoutParams = LayoutParams(DisplayUtils.dip2px(1F), DisplayUtils.dip2px(60F))
        v6.backgroundColor = Color.parseColor("#EDEDED")
        (v6.layoutParams as LayoutParams).leftToRight = R.id.ll_6
        (v6.layoutParams as LayoutParams).topToTop = R.id.ll_6
        (v6.layoutParams as LayoutParams).bottomToBottom = R.id.ll_6

        this.addView(l1)
        this.addView(l2)
        this.addView(l3)

        this.addView(v1)
        this.addView(v2)
        this.addView(v3)
        this.addView(v4)
        this.addView(v5)
        this.addView(v6)

        this.addView(l4)
        this.addView(l5)
        this.addView(l6)
        this.addView(l7)

        //注：切记，都加完了才去写入值的操作,不然会空指针。

        if (textOrImage) {
            tv_top1.background = null
            tv_top2.background = null
            tv_top3.background = null
            tv_top4.background = null
            tv_top5.background = null
            tv_top6.background = null
            tv_top7.background = null
            tv_top1.text = waitingMeasure
            tv_top2.text = waitingSubmit
            tv_top3.text = waitingSign
            tv_top4.text = statUnStart
            tv_top5.text = statInProgress
            tv_top6.text = statOnFinished
            tv_top7.text = statClearing
        } else {
            tv_top1.text = ""
            tv_top2.text = ""
            tv_top3.text = ""
            tv_top4.text = ""
            tv_top5.text = ""
            tv_top6.text = ""
            tv_top7.text = ""
            tv_top1.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_measure)
            tv_top2.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_send)
            tv_top3.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_sign)
            tv_top4.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_notstart)
            tv_top5.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_doing)
            tv_top6.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_done)
            tv_top7.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_money)
        }

        //下面的是对应的点击事件
        l1.setOnClickListener {
          //  cn.startActivity<NewProjectListActivity>("status" to "2,3,4")
        }
        //
        l2.setOnClickListener {
         //   cn.startActivity<NewProjectListActivity>("status" to "5,6,7,8")
        }

        l3.setOnClickListener {
         //   cn.startActivity<NewProjectListActivity>("status" to "9")
        }

        l4.setOnClickListener {
         //   cn.startActivity<NewProjectListActivity>("status" to "10,11")
        }

        l5.setOnClickListener {
          //  cn.startActivity<NewProjectListActivity>("status" to "12")
        }

        l6.setOnClickListener {
         //   cn.startActivity<NewProjectListActivity>("status" to "13")
        }

        l7.setOnClickListener {
       //     cn.startActivity<NewProjectListActivity>("status" to "14")
        }
    }

    fun updateStatus() {
        if (textOrImage) {
            tv_top1.background = null
            tv_top2.background = null
            tv_top3.background = null
            tv_top4.background = null
            tv_top5.background = null
            tv_top6.background = null
            tv_top7.background = null
            tv_top1.text = waitingMeasure
            tv_top2.text = waitingSubmit
            tv_top3.text = waitingSign
            tv_top4.text = statUnStart
            tv_top5.text = statInProgress
            tv_top6.text = statOnFinished
            tv_top7.text = statClearing
        } else {
            tv_top1.text = ""
            tv_top2.text = ""
            tv_top3.text = ""
            tv_top4.text = ""
            tv_top5.text = ""
            tv_top6.text = ""
            tv_top7.text = ""
            tv_top1.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_measure)
            tv_top2.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_send)
            tv_top3.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_sign)
            tv_top4.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_notstart)
            tv_top5.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_doing)
            tv_top6.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_done)
            tv_top7.background = ContextCompat.getDrawable(cn, R.drawable.ic_main_money)
        }
    }
}

