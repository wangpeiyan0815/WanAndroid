package com.wpy.wanandroid.ui.wxarticle.bean

data class WxArticleBean(
    val curPage: Int = 0,
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0,
    val datas: List<DatasBean>
)