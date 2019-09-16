package com.wpy.wanandroid.ui.project.bean

import com.wpy.wanandroid.ui.wxarticle.bean.DatasBean

data class ProjectArticleBean(
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val datas:List<DatasBean>
)