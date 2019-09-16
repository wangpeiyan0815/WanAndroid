package com.wpy.wanandroid.base.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.wpy.wanandroid.utils.status.StatusBarCompat

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContent: BaseActivity

    @LayoutRes
    abstract fun initLayoutRes(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun initLoad()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayoutRes())
        mContent = this
        initData()
        initView()
        initLoad()
    }

    protected fun intiStatusView(id: Int, errorRetry: (View) -> UInt) {

    }

    protected fun refreshStatusBar() {
        StatusBarCompat.transparent(this)
        StatusBarCompat.setIconMode(this, true)
    }
}