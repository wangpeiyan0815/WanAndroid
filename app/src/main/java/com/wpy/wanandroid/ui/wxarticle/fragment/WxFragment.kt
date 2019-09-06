package com.wpy.wanandroid.ui.wxarticle.fragment

import androidx.fragment.app.Fragment
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.App
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.wxarticle.bean.WxChapterBean
import com.wpy.wanandroid.ui.wxarticle.contract.WxContract
import com.wpy.wanandroid.ui.wxarticle.presenter.WxPresenterImpl
import com.wpy.wanandroid.utils.DisplayInfoUtils
import com.wpy.wanandroid.utils.ToastUtil
import com.wpy.wanandroid.utils.status.StatusBarCompat
import kotlinx.android.synthetic.main.fragment_tree.tabLayout
import kotlinx.android.synthetic.main.fragment_wx.*

class WxFragment : BaseMvpFragment<WxPresenterImpl>(), WxContract.View {

    companion object {
        fun newInstance() = WxFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_wx
    }

    override fun initData() {
    }

    override fun initView() {
        initToolBar()
    }

    override fun initLoad() {
        presenter.getWxArticleChapters()
    }

    override fun initPresenter(): WxPresenterImpl {
        return WxPresenterImpl(this)
    }

    override fun onWxArticleChaptersSuccess(data: List<WxChapterBean>) {
        val titles = arrayOfNulls<String>(data.size)
        val fragments = arrayListOf<Fragment>()
        for (i in data.indices) {
            titles[i] = data[i].name
            fragments.add(WxArticleFragment.newInstance(data[i].id, i))
        }
        tabLayout.setViewPager(wx_vp, titles, activity, fragments)
    }

    override fun onWxArticleChaptersError(e: ResponseException) {
        ToastUtil.show(App.getApp(), e.getErrorMessage())
    }

    fun initToolBar() {
        val statusBarheight = StatusBarCompat.getHeight(activity)
        tabLayout.setPadding(0, statusBarheight, 0, 0)
        tabLayout.layoutParams.height = (DisplayInfoUtils.dp2px(40f) + statusBarheight).toInt()
    }
}