package com.wpy.wanandroid.ui.tree

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.tree.bean.KnowledgeBean

class KnowledgePresenter(view: KnowledgeContract.View) : BasePresenter<KnowledgeContract.View>(view),
    KnowledgeContract.Presenter {

    override fun getKnowledgeList() {
        RequestManager.execute(
            this,
            RetrofitManager.create(WanAndroidApis::class.java).getKnowledgeList(),
            object : BaseObserver<List<KnowledgeBean>>() {
                override fun onSuccess(data: List<KnowledgeBean>) {
                    view.onKnowledgeListSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onKnowledgeError(e)
                }
            })
    }
}