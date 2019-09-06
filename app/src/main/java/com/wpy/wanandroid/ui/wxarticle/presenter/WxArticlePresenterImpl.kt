package com.wpy.wanandroid.ui.wxarticle.presenter

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.wxarticle.bean.WxArticleBean
import com.wpy.wanandroid.ui.wxarticle.contract.WxArticleContract

class WxArticlePresenterImpl(view: WxArticleContract.View) : BasePresenter<WxArticleContract.View>(view),
    WxArticleContract.Presenter {

    override fun getWxArticleList(id: Int, page: Int, refresh: Boolean) {
        RequestManager.execute(this, RetrofitManager.create(WanAndroidApis::class.java).getWxArticleList(id,page),
            object : BaseObserver<WxArticleBean>() {
                override fun onSuccess(data: WxArticleBean) {
                    view.onWxArticleListSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onWxArticleListError(e)
                }
            })
    }
}