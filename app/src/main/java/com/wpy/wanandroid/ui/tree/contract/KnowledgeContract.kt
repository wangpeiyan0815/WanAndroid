package com.wpy.wanandroid.ui.tree.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.tree.bean.KnowledgeBean

interface KnowledgeContract {

    interface View : BaseView{
        fun onKnowledgeListSuccess(data:List<KnowledgeBean>)
        fun onKnowledgeError(e: ResponseException)
    }

    interface Presenter{
        fun getKnowledgeList()
    }
}