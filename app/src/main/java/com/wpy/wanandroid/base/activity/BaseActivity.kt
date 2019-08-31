package com.wpy.wanandroid.base.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContent: BaseActivity

    @LayoutRes
    abstract fun intiLayoutRes(): Int

    abstract fun intiData()

    abstract fun intiView()

    abstract fun intiLoad()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intiLayoutRes())
        mContent = this
        intiData()
        intiView()
        intiLoad()
    }

    protected fun intiStatusView(id: Int, errorRetry: (View) -> UInt) {

    }
}