package cn.nurasoft.learningshadow
import com.google.gson.annotations.SerializedName

//
//	Tag.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport
class TagBean {
    @SerializedName("designer_name")
    var designerName: String? = null

    @SerializedName("keywords")
    var keywords: String? = null

    @SerializedName("link")
    var link: Any? = null

    @SerializedName("position")
    var position = 0

    //@SerializedName("product")
   // var product: ProductBean? = null

    @SerializedName("x")
    var x = 0f

    @SerializedName("y")
    var y = 0f
}