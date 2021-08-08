package cn.nurasoft.learningshadow

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserBean(
    //name 不会为空
    var Name:String,
    //可能会空。
    var userDetails: UserDetails?
): Parcelable

@Parcelize
data class UserDetails(
    var sex:Boolean,
    val age:Int,
    //可能会空
    val address:String?
) : Parcelable
