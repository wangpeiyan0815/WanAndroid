package com.wpy.wanandroid.ui.tree.presenter

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.tree.contract.NaviContract
import com.wpy.wanandroid.ui.tree.bean.NaviBean

class NaviPresenterImpl(view: NaviContract.View) : BasePresenter<NaviContract.View>(view),
    NaviContract.Presenter {

    override fun getNaviList() {

        RequestManager.execute(
            this,
            RetrofitManager.create(WanAndroidApis::class.java).getNaviList(),
            object : BaseObserver<List<NaviBean>>() {
                override fun onSuccess(data: List<NaviBean>) {
                    view.onNaviListSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onNaviListError(e)
                }
            })
    }
}