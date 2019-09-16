package com.wpy.wanandroid.ui.project.presenter

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.project.bean.ProjectArticleBean
import com.wpy.wanandroid.ui.project.contract.ProjectArticleContract

class ProjectArticlePresenterImpl(view : ProjectArticleContract.View) :BasePresenter<ProjectArticleContract.View>(view),ProjectArticleContract.Presenter {

    override fun gettProjectArticleList(id: Int, page: Int) {
        RequestManager.execute(this,RetrofitManager.create(WanAndroidApis::class.java).getProjectArticleList(page,id),
            object : BaseObserver<ProjectArticleBean>(){
                override fun onSuccess(data: ProjectArticleBean) {
                    view.ontProjectArticleListSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.ontProjectArticleListError(e)
                }
            })
    }
}