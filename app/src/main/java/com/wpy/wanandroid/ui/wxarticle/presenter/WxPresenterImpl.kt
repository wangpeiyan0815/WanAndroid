package com.wpy.wanandroid.ui.wxarticle.presenter

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.wxarticle.bean.WxChapterBean
import com.wpy.wanandroid.ui.wxarticle.contract.WxContract

class WxPresenterImpl(view: WxContract.View) : BasePresenter<WxContract.View>(view), WxContract.Presenter {

    override fun getWxArticleChapters() {

        RequestManager.execute(this, RetrofitManager.create(WanAndroidApis::class.java).getWxArticleChapters(),
            object : BaseObserver<List<WxChapterBean>>() {
                override fun onSuccess(data: List<WxChapterBean>) {
                    view.onWxArticleChaptersSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onWxArticleChaptersError(e)
                }

            })
    }
}