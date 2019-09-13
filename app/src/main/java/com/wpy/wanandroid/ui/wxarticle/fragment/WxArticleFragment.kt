package com.wpy.wanandroid.ui.wxarticle.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.wxarticle.adapter.WxArticleAdapter
import com.wpy.wanandroid.ui.wxarticle.bean.WxArticleBean
import com.wpy.wanandroid.ui.wxarticle.contract.WxArticleContract
import com.wpy.wanandroid.ui.wxarticle.presenter.WxArticlePresenterImpl
import com.wpy.wanandroid.utils.SmartRefreshUtils
import kotlinx.android.synthetic.main.fragment_wx_article.*


class WxArticleFragment : BaseMvpFragment<WxArticlePresenterImpl>(), WxArticleContract.View {

    private lateinit var mAdapter: WxArticleAdapter
    private lateinit var mSmartRefreshUtils: SmartRefreshUtils
    private var currPage = PAGE_START
    private var mId = 0

    companion object {
        const val PAGE_START = 1
        const val ID_EXTRA = "ID_EXTRA"
        const val POSITION_EXTRA = "POSITION_EXTRA"

        fun newInstance(id: Int, position: Int): WxArticleFragment {
            val fragment = WxArticleFragment()
            val bundle = Bundle()
            bundle.putInt(ID_EXTRA, id)
            bundle.putInt(POSITION_EXTRA, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_wx_article
    }

    override fun initData() {}

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(activity)
        mAdapter = WxArticleAdapter(null)
        rv.adapter = mAdapter
        mSmartRefreshUtils = SmartRefreshUtils.with(refreshLayout).pureScrollMode()
        mSmartRefreshUtils.setRefreshListener {
            currPage = PAGE_START
            getWxArticleList(true)
        }
        mSmartRefreshUtils.setLoadMoreListener {
            currPage++
            getWxArticleList(false)
        }
    }

    override fun initLoad() {
        mId = arguments?.getInt(ID_EXTRA) ?: 0
        getWxArticleList(false)
    }

    override fun initPresenter(): WxArticlePresenterImpl {
        return WxArticlePresenterImpl(this)
    }

    fun getWxArticleList(refresh: Boolean) {
        if (mId != 0) {
            presenter.getWxArticleList(mId, currPage, refresh)
        }
    }

    override fun onWxArticleListSuccess(data: WxArticleBean) {
        if (currPage == PAGE_START) mAdapter.setNewData(data.datas) else mAdapter.addData(data.datas)
        currPage++
        if (data.over) {
            mAdapter.loadMoreEnd()
        }
        mSmartRefreshUtils.success()
    }

    override fun onWxArticleListError(e: ResponseException) {
    }
}