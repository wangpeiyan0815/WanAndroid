package com.wpy.wanandroid.apis

import com.wpy.wanandroid.base.BaseResponse
import com.wpy.wanandroid.ui.home.bean.ArticleBean
import com.wpy.wanandroid.ui.home.bean.BannerBean
import com.wpy.wanandroid.ui.tree.bean.KnowledgeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidApis {

    /**
     *  首页文章列表
     */
    @GET("article/list/{page}/json")
    fun articleList(@Path("page") pageNum: Int): Observable<BaseResponse<ArticleBean>>

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<BannerBean>>>

    /**
     * 体系数据
     */
    @GET("tree/json")
    fun getKnowledgeList(): Observable<BaseResponse<List<KnowledgeBean>>>
}