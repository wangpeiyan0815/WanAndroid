package com.wpy.wanandroid.ui.home.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.home.bean.ArticleBean
import com.wpy.wanandroid.ui.home.bean.BannerBean

interface HomeContract {

    interface View : BaseView {

        fun onBannerSuccess(data: List<BannerBean>)
        fun onBannerError(e: ResponseException)

        fun onArticleListSuccess(data: ArticleBean)
        fun onArticleListError(e: ResponseException)
    }

    interface Presenter {
        fun getBannerData()

        fun getArticleList(pageNum: Int)
    }
}