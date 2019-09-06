package com.wpy.wanandroid.ui.wxarticle.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.wxarticle.bean.WxArticleBean

interface WxArticleContract {

    interface View : BaseView {
        fun onWxArticleListSuccess(data: WxArticleBean)
        fun onWxArticleListError(e: ResponseException)
    }

    interface Presenter {
        fun getWxArticleList(id: Int = 0, page: Int = 1, refresh: Boolean = false)
    }
}