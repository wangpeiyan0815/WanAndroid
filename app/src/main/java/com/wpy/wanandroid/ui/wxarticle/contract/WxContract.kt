package com.wpy.wanandroid.ui.wxarticle.contract

import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.base.BaseView
import com.wpy.wanandroid.ui.wxarticle.bean.WxChapterBean

interface WxContract {

    interface View : BaseView{
      fun  onWxArticleChaptersSuccess(data: List<WxChapterBean>)
      fun  onWxArticleChaptersError(e: ResponseException)
    }

    interface Presenter{
        fun getWxArticleChapters()
    }
}