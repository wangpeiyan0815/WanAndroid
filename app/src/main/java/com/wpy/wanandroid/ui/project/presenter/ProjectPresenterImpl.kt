package com.wpy.wanandroid.ui.project.presenter

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.apis.WanAndroidApis
import com.wpy.wanandroid.base.BasePresenter
import com.wpy.wanandroid.base.net.RequestManager
import com.wpy.wanandroid.base.net.RetrofitManager
import com.wpy.wanandroid.base.net.observer.BaseObserver
import com.wpy.wanandroid.ui.project.bean.ProjectChapterBean
import com.wpy.wanandroid.ui.project.contract.ProjectContract

class ProjectPresenterImpl(view: ProjectContract.View) : BasePresenter<ProjectContract.View>(view),
    ProjectContract.Presenter {

    override fun getProjectChapters() {

        RequestManager.execute(this, RetrofitManager.create(WanAndroidApis::class.java).getProjectChapters(),
            object : BaseObserver<List<ProjectChapterBean>>() {
                override fun onSuccess(data: List<ProjectChapterBean>) {
                    view.onProjectChaptersSuccess(data)
                }

                override fun onError(e: ResponseException) {
                    view.onProjectChaptersError(e)
                }
            })
    }
}