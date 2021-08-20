package cn.nurasoft.learningshadow

import android.nfc.Tag

data class ProductBean(
    //Tag 类型
    val type:TagType,
    /**
     * 坐标X点
     */
    val x:Float,
    /**
     * 坐标y点
     */
    val y:Float,
    /**
     * 文案
     */
    val content:String,
    /**
     * 价格文案
     */
    val priceRange:String?
)

enum class TagType{
    DESIGNER,
    PRODUCT,
    KEYWORD,
    LSUSER,
    BLOG,
    COLLECTION,
    HASHTAG
}


