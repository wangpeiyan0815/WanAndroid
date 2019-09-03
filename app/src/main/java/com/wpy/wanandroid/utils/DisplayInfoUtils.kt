package com.wpy.wanandroid.utils

import android.content.Context
import android.util.DisplayMetrics
import com.wpy.wanandroid.App

class DisplayInfoUtils {

    companion object {
        private val mContent: Context = App.getApp()
        private val mDisplayMetrics: DisplayMetrics = mContent.resources.displayMetrics

        /**
         *  获取屏幕宽度
         */
        fun getWidthPixels(): Int {
            return mDisplayMetrics.widthPixels
        }

        /**
         * 获取屏幕高度像素
         *
         * @return px
         */
        fun getHeightPixels(): Int {
            return mDisplayMetrics.heightPixels
        }

        /**
         * dp: dip，Density-independent pixel(设备独立像素), 是安卓开发用的长度单位，1dp表示在屏幕像素点密度为160ppi时1px长度
         * px: pixel，像素，电子屏幕上组成一幅图画或照片的最基本单元
         * dp转px
         *
         * @param dp dp
         * @return px
         */
        fun dp2px(dp: Float): Float {
            return dp * mDisplayMetrics.density
        }
    }

}