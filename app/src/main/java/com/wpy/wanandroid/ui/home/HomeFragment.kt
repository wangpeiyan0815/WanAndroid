package com.wpy.wanandroid.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.home.adapter.ArticleListAdapter
import com.wpy.wanandroid.ui.home.bean.ArticleBean
import com.wpy.wanandroid.utils.status.StatusBarCompat
import kotlinx.android.synthetic.main.fragment_home.*
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isEmpty
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.wpy.wanandroid.ui.home.bean.BannerBean
import com.wpy.wanandroid.utils.DisplayInfoUtils
import com.wpy.wanandroid.utils.SmartRefreshUtils
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader

/**
 *   使用ConstraintLayout 等控件因Mainacti
 */

class HomeFragment : BaseMvpFragment<HomePresenterImpl>(), HomeContract.View {

    private var pageNum = 0
    private lateinit var mArticleListAdapter: ArticleListAdapter
    private lateinit var mBannerBeans: List<BannerBean>
    private lateinit var mSmartRefreshUtils: SmartRefreshUtils

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
    }

    override fun initView() {

        mSmartRefreshUtils = SmartRefreshUtils.with(refreshLayout)
        mSmartRefreshUtils.pureScrollMode()
        mSmartRefreshUtils.setRefreshListener{
            Log.i("TAG","刷新成功")
        }
        mArticleListAdapter = ArticleListAdapter(null)
        home_rv.layoutManager = LinearLayoutManager(activity)
        home_rv.adapter = mArticleListAdapter

        initToolBar()
        createBanner()
    }

    fun initToolBar() {
        val statusBarheight = StatusBarCompat.getHeight(activity)
        title_ly.setPadding(0, statusBarheight, 0, 0)
        title_ly.layoutParams.height = (DisplayInfoUtils.dp2px(46f) + statusBarheight).toInt()
    }

    override fun initLoad() {
        presenter.getArticleList(pageNum)
        presenter.getBannerData()
    }

    override fun initPresenter(): HomePresenterImpl {
        return HomePresenterImpl(this)
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }

    override fun onBannerSuccess(data: List<BannerBean>) {
        mBannerBeans = data
        val urls = ArrayList<String>(data.size)
        val titles = ArrayList<String>(data.size)
        for (bean in data) {
            urls.add(bean.imagePath)
            titles.add(bean.title)
        }
        banner.setImages(urls)
        banner.setBannerTitles(titles)
        banner.start()
    }

    override fun onBannerError(e: ResponseException) {
    }

    override fun onArticleListSuccess(data: ArticleBean) {
        val datas = data.datas
        mArticleListAdapter.setNewData(datas)
    }

    override fun onArticleListError(e: ResponseException) {
    }

    fun createBanner() {
        banner.apply {
            val height = (DisplayInfoUtils.getWidthPixels() * (9f / 16f)).toInt()
            // 加入该代码  behavior  没有视差动画
//            layoutParams = CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, height)
            setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                    com.wpy.wanandroid.utils.ImageLoader.image(imageView, path.toString())
                }
            })
            setIndicatorGravity(BannerConfig.CENTER)
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            banner.setBannerAnimation(Transformer.Default)
            banner.startAutoPlay()
            banner.setDelayTime(5000)
            banner.setOnBannerListener { position ->
                val bean = mBannerBeans[position]
            }
        }
    }
}