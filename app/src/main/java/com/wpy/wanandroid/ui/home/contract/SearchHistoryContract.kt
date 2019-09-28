package com.wpy.wanandroid.ui.home.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.home.bean.HotKeyBean

interface SearchHistoryContract {

    interface View : BaseView{
        fun onHotKeyListSuccess(data: List<HotKeyBean>)
        fun onHotKeyListError(e: ResponseException)
    }

    interface Presenter{

        fun getHotKeyList()
    }
}