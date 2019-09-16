package com.wpy.wanandroid.ui.project.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.project.bean.ProjectChapterBean

interface ProjectContract {

    interface View : BaseView{

        fun onProjectChaptersSuccess(data: List<ProjectChapterBean>)

        fun onProjectChaptersError(e: ResponseException)
    }

    interface Presenter{

        fun getProjectChapters()
    }
}