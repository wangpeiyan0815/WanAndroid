package com.wpy.wanandroid.ui.project.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.project.bean.ProjectArticleBean

interface ProjectArticleContract {

    interface View : BaseView {
        fun ontProjectArticleListSuccess(data: ProjectArticleBean)
        fun ontProjectArticleListError(e: ResponseException)
    }

    interface Presenter {
        fun gettProjectArticleList(id: Int, page: Int)
    }
}