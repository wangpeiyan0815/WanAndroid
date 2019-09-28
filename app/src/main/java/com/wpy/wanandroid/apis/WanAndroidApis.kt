package com.wpy.wanandroid.apis

import com.wpy.wanandroid.base.BaseResponse
import com.wpy.wanandroid.ui.home.bean.ArticleBean
import com.wpy.wanandroid.ui.home.bean.BannerBean
import com.wpy.wanandroid.ui.home.bean.HotKeyBean
import com.wpy.wanandroid.ui.project.bean.ProjectArticleBean
import com.wpy.wanandroid.ui.project.bean.ProjectChapterBean
import com.wpy.wanandroid.ui.tree.bean.KnowledgeBean
import com.wpy.wanandroid.ui.tree.bean.NaviBean
import com.wpy.wanandroid.ui.wxarticle.bean.WxArticleBean
import com.wpy.wanandroid.ui.wxarticle.bean.WxChapterBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    /**
     * 搜索热词
     */
    @GET("navi/json")
    fun getNaviList(): Observable<BaseResponse<List<NaviBean>>>

    /**
     * 获取公众号列表
     * 方法： GET
     */
    @GET("wxarticle/chapters/json")
    fun getWxArticleChapters(): Observable<BaseResponse<List<WxChapterBean>>>

    /**
     * 查看某个公众号历史数据
     * 方法：GET
     * 参数：
     * 公众号 ID：拼接在 url 中，eg:405
     * 公众号页码：拼接在 url 中，eg:1
     */
    @GET("wxarticle/list/{id}/{page}/json")
    fun getWxArticleList(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): Observable<BaseResponse<WxArticleBean>>


    /**
     * 项目分类
     * 方法： GET
     */
    @GET("project/tree/json")
    fun getProjectChapters(): Observable<BaseResponse<List<ProjectChapterBean>>>

    /**
     * 项目列表数据
     * 方法：GET
     * 参数：
     * cid 分类的id，上面项目分类接口
     * 页码：拼接在链接中，从1开始。
     */
    @GET("project/list/{page}/json")
    fun getProjectArticleList(
        @Path("page") page: Int,
        @Query("cid") id: Int
    ): Observable<BaseResponse<ProjectArticleBean>>


    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    fun getHotKeyList(): Observable<BaseResponse<List<HotKeyBean>>>
}