package com.might.dwan.cashcalendar.test

import android.util.Log
import com.might.dwan.cashcalendar.utils.ConstantManager

/**
 * Created by Ilya on 20.06.2018.
 */
open class MyString {
    var msg: String? = "Print1"

    fun demo() {
        Log.i(ConstantManager.TAG, msg)
    }

    fun getData() = msg
}

fun MyString?.demo1() {
    if (this == null) return

    this.msg = "print2"
    this.demo()
}

fun test() {
    MyString().let { it.demo() }
    val data = MyString().getData() ?: "Unknown"

    var obj: MyString? = MyString()

    obj = null

    obj.also { }

}