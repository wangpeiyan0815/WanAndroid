package com.wpy.wanandroid.ui.tree.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.tree.bean.NaviBean

interface NaviContract {

    interface View : BaseView {

        fun onNaviListSuccess(data: List<NaviBean>)
        fun onNaviListError(e: ResponseException)
    }

    interface Presenter {
        fun getNaviList()
    }
}