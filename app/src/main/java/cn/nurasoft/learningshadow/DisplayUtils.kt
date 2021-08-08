package cn.nurasoft.learningshadow

import android.content.res.Resources

/**
 *
 * @author wuhan
 * @date 2018/11/23 10:23
 */
object DisplayUtils {
    /**
     * dp转px
     *
     * @param dipValue
     * @return
     */
    fun dip2px(dipValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun px2dip(px: Int): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }
}