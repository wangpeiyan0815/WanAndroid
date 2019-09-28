package com.wpy.wanandroid.ui.home.presenter

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.home.bean.HotKeyBean
import com.wpy.wanandroid.ui.home.contract.SearchHistoryContract

class SearchHistoryPresenterImpl(view: SearchHistoryContract.View) : BasePresenter<SearchHistoryContract.View>(view),
    SearchHistoryContract.Presenter {


    override fun getHotKeyList() {
        RequestManager.execute(
            this,
            RetrofitManager.create(WanAndroidApis::class.java).getHotKeyList(),
            object : BaseObserver<List<HotKeyBean>>() {
                override fun onSuccess(data: List<HotKeyBean>) {
                    view.onHotKeyListSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onHotKeyListError(e)
                }
            })
    }
}