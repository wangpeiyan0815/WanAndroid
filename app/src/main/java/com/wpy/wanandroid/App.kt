package com.wpy.wanandroid

import android.app.Application
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import org.litepal.LitePal
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger.addLogAdapter
import com.wpy.wanandroid.utils.LogUtils
import leakcanary.LeakCanary


class App : Application() {

    init {
//        ClassicsHeader.REFRESH_HEADER_PULLING = "下拉可以刷新";
//        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新...";
//        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载...";
//        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新";
//        ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成";
//        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败";
//        ClassicsHeader.REFRESH_HEADER_SECONDARY = "释放进入二楼";
//        ClassicsHeader.REFRESH_HEADER_UPDATE = "上次更新 M-d HH:mm";
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            ClassicsHeader(context).setLastUpdateText(ClassicsHeader.REFRESH_HEADER_FINISH).setEnableLastTime(false)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> ClassicsFooter(context) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LitePal.initialize(this)
        // 初始化日志打印框架
        addLogAdapter(AndroidLogAdapter())
        LogUtils.init(this)
    }

    companion object {
        private lateinit var instance: App
        fun getApp(): App {
            return instance
        }
    }
}