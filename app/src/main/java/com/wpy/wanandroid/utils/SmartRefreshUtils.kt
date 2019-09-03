package com.wpy.wanandroid.utils

import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.wpy.wanandroid.base.Const

/**
 *  刷新辅助类
 */
class SmartRefreshUtils(val mRefreshLayout: RefreshLayout) {

    private var onRefresh: () -> Unit = {}
    private var onLoadMore: () -> Unit = {}

    companion object {
        fun with(layout: RefreshLayout): SmartRefreshUtils {
            return SmartRefreshUtils(layout)
        }
    }

    fun pureScrollMode(): SmartRefreshUtils {
        mRefreshLayout.setEnableRefresh(false) //是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(false)//是否启用上拉加载功能
        mRefreshLayout.setEnablePureScrollMode(true) //是否启用纯滚动模式
        mRefreshLayout.setEnableNestedScroll(true) //是否启用嵌套滚动
        mRefreshLayout.setEnableOverScrollDrag(true) //是否启用越界拖动（仿苹果效果）1.0.4
        return this
    }

    fun setRefreshListener(onRefresh: () -> Unit): SmartRefreshUtils {
        this.onRefresh = onRefresh
        if (onRefresh == null) mRefreshLayout.setEnableRefresh(false) else {
            mRefreshLayout.setEnablePureScrollMode(false)
            mRefreshLayout.setEnableRefresh(true)
            mRefreshLayout.setOnRefreshListener { refreshLayout ->
                refreshLayout.finishRefresh(Const.REFRESH_TIMEOUT, false)
                this.onRefresh()
            }
        }
        return this
    }

    fun setLoadMoreListener(onLoadMore: () -> Unit): SmartRefreshUtils {
        this.onLoadMore = onLoadMore
        if (onLoadMore == null) mRefreshLayout.setEnableLoadMore(false) else {
            mRefreshLayout.setEnablePureScrollMode(false)
            mRefreshLayout.setEnableLoadMore(true)
            mRefreshLayout.setOnLoadMoreListener { refreshLayout ->
                refreshLayout.finishLoadMore()
                this.onLoadMore()
            }
        }
        return this
    }

    fun autoRefresh() {
        mRefreshLayout.autoRefresh()
    }

    fun autoLoadMore() {
        mRefreshLayout.autoLoadMore()
    }

    fun success() {
        mRefreshLayout.finishRefresh(true)
        mRefreshLayout.finishLoadMore(true)
    }

    fun fail() {
        mRefreshLayout.finishRefresh(false)
        mRefreshLayout.finishLoadMore(false)
    }
}