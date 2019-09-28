package com.wpy.wanandroid.ui.home.fragment

import android.animation.ObjectAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.home.adapter.ArticleListAdapter
import com.wpy.wanandroid.ui.home.bean.ArticleBean
import com.wpy.wanandroid.utils.status.StatusBarCompat
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.google.android.material.appbar.AppBarLayout
import com.wpy.wanandroid.ui.home.bean.BannerBean
import com.wpy.wanandroid.utils.DisplayInfoUtils
import com.wpy.wanandroid.utils.SmartRefreshUtils
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import android.view.animation.LinearInterpolator
import android.animation.PropertyValuesHolder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.wpy.wanandroid.ui.home.activity.SearchActivity
import com.wpy.wanandroid.ui.home.bean.DatasItem
import com.wpy.wanandroid.ui.home.contract.HomeContract
import com.wpy.wanandroid.ui.home.presenter.HomePresenterImpl
import com.wpy.wanandroid.ui.main.activity.WebActivity


/**
 *   使用ConstraintLayout 等控件因Mainacti
 */

class HomeFragment : BaseMvpFragment<HomePresenterImpl>(),
    HomeContract.View {

    private var currPage = PAGE_START
    private lateinit var mArticleListAdapter: ArticleListAdapter
    private lateinit var mBannerBeans: List<BannerBean>
    private lateinit var mSmartRefreshUtils: SmartRefreshUtils
    private var isSearch = true
    private var mScaleAnimator: ObjectAnimator? = null

    companion object {
        const val PAGE_START = 1

        fun newInstance() = HomeFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        mSmartRefreshUtils = SmartRefreshUtils.with(refreshLayout)
        mSmartRefreshUtils.pureScrollMode()
        mSmartRefreshUtils.setRefreshListener {}
        mArticleListAdapter = ArticleListAdapter(null)
        home_rv.layoutManager = LinearLayoutManager(activity)
        home_rv.adapter = mArticleListAdapter
        initToolBar()
        createBanner()
        val maxOffset = DisplayInfoUtils.dp2px(220f)
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset > -maxOffset) {
                if (!isSearch) {
                    startAnima()
                    isSearch = true
                    floatingBtn.setImageIcon(Icon.createWithResource(activity, R.drawable.ic_search))
                }
            } else {
                if (isSearch) {
                    startAnima()
                    isSearch = false
                    floatingBtn.setImageIcon(Icon.createWithResource(activity, R.drawable.icon_home_stick))
                }
            }
        })

        floatingBtn.setOnClickListener {
            if (!isSearch) scrollToTop() else openSearch()
        }

        mArticleListAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val datas = adapter.data as List<DatasItem>
            WebActivity.start(activity!!, datas[position].id, datas[position].title, datas[position].link)
        }

        mSmartRefreshUtils.setRefreshListener {
            currPage = PAGE_START
            getArticleList()
        }

        mSmartRefreshUtils.setLoadMoreListener {
            currPage++
            getArticleList()
        }
    }

    fun initToolBar() {
        val statusBarheight = StatusBarCompat.getHeight(activity)
        title_ly.setPadding(0, statusBarheight, 0, 0)
        title_ly.layoutParams.height = (DisplayInfoUtils.dp2px(46f) + statusBarheight).toInt()
    }

    override fun initLoad() {
        presenter.getBannerData()
        getArticleList()
    }

    fun getArticleList() {
        presenter.getArticleList(currPage)
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
        if (currPage == PAGE_START) {
            mArticleListAdapter.setNewData(datas)
        } else {
            mArticleListAdapter.addData(datas)
        }
        if (data.over) {
            mArticleListAdapter.loadMoreEnd()
        }
        mSmartRefreshUtils.success()
    }

    override fun onArticleListError(e: ResponseException) {
        mSmartRefreshUtils.fail()
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

    private fun startAnima() {
        cancelAnima()
        if (mScaleAnimator == null) {
            val holderX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.5f, 1.0f)
            val holderY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.5f, 1.0f)
            mScaleAnimator = ObjectAnimator.ofPropertyValuesHolder(floatingBtn, holderX, holderY)
            mScaleAnimator?.interpolator = LinearInterpolator()
            mScaleAnimator?.duration = 200
        }
        mScaleAnimator?.start()
    }

    private fun cancelAnima() {
        if (mScaleAnimator?.isRunning == true) {
            mScaleAnimator?.repeatCount = 0
            mScaleAnimator?.cancel()
            mScaleAnimator?.end()
        }
    }

    private fun scrollToTop() {
        app_bar.setExpanded(true)
        home_rv.stopScroll()
        home_rv.scrollToPosition(0)
    }

    private fun openSearch() {
        SearchActivity.start(mContext)
    }
}