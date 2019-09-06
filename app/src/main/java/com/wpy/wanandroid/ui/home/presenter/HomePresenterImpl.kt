package com.wpy.wanandroid.ui.home.presenter

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.home.contract.HomeContract
import com.wpy.wanandroid.ui.home.bean.ArticleBean
import com.wpy.wanandroid.ui.home.bean.BannerBean

class HomePresenterImpl(view: HomeContract.View) : BasePresenter<HomeContract.View>(view),
    HomeContract.Presenter {

    override fun getBannerData() {
        RequestManager.execute(
            this,
            RetrofitManager.create(WanAndroidApis::class.java).getBanner(),
            object :BaseObserver<List<BannerBean>>(){
                override fun onSuccess(data: List<BannerBean>) {
                    view.onBannerSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onBannerError(e)
                }
            })
    }

    override fun getArticleList(pageNum: Int) {
        RequestManager.execute(
            this,
            RetrofitManager.create(WanAndroidApis::class.java).articleList(pageNum),
            object : BaseObserver<ArticleBean>() {
                override fun onSuccess(data: ArticleBean) {
                    view.onArticleListSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onBannerError(e)
                }
            })
    }
}