package com.wpy.wanandroid.ui.project.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.project.adapter.ProjectArticleAdapter
import com.wpy.wanandroid.ui.project.bean.ProjectArticleBean
import com.wpy.wanandroid.ui.project.contract.ProjectArticleContract
import com.wpy.wanandroid.ui.project.presenter.ProjectArticlePresenterImpl
import com.wpy.wanandroid.ui.wxarticle.fragment.WxArticleFragment
import com.wpy.wanandroid.utils.SmartRefreshUtils
import kotlinx.android.synthetic.main.fragment_project_article.refreshLayout
import kotlinx.android.synthetic.main.fragment_project_article.rv

class ProjectArticleFragment : BaseMvpFragment<ProjectArticlePresenterImpl>(), ProjectArticleContract.View {

    private lateinit var mAdapter: ProjectArticleAdapter
    private lateinit var mSmartRefreshUtils: SmartRefreshUtils
    private var currPage = PAGE_START
    private var mId = 0

    companion object {

        const val PAGE_START = 1
        const val ID_EXTRA = "ID_EXTRA"
        const val POSITION_EXTRA = "POSITION_EXTRA"

        fun newInstance(id: Int, position: Int): ProjectArticleFragment {
            val fragment = ProjectArticleFragment()
            val bundle = Bundle()
            bundle.putInt(ID_EXTRA, id)
            bundle.putInt(POSITION_EXTRA, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_project_article
    }

    override fun initData() {

    }

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(activity)
        mAdapter = ProjectArticleAdapter(null)
        rv.adapter = mAdapter
        mSmartRefreshUtils = SmartRefreshUtils.with(refreshLayout).pureScrollMode()
        mSmartRefreshUtils.setRefreshListener {
            currPage = WxArticleFragment.PAGE_START
            getProjectArticleList()
        }
        mSmartRefreshUtils.setLoadMoreListener {
            currPage++
            getProjectArticleList()
        }
    }

    override fun initLoad() {
        mId = arguments?.getInt(WxArticleFragment.ID_EXTRA) ?: 0
        getProjectArticleList()
    }

    override fun initPresenter(): ProjectArticlePresenterImpl {
        return ProjectArticlePresenterImpl(this)
    }

    override fun ontProjectArticleListSuccess(data: ProjectArticleBean) {
        if (currPage == WxArticleFragment.PAGE_START) mAdapter.setNewData(data.datas) else mAdapter.addData(data.datas)
        currPage++
        if (data.over) {
            mAdapter.loadMoreEnd()
        }
        mSmartRefreshUtils.success()
    }

    override fun ontProjectArticleListError(e: ResponseException) {
    }

    fun getProjectArticleList() {
        if (mId != 0) {
            presenter.gettProjectArticleList(mId, currPage)
        }
    }
}