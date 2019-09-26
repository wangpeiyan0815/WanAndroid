package com.wpy.wanandroid.ui.mine

import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment() {

    companion object {
        fun newInstance() = MineFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_mine
    }

    override fun initData() {
    }

    override fun initView() {
        refreshLayout.setOnMultiPurposeListener(OnMultiPurposeListener)
    }

    override fun initLoad() {
    }

    private val OnMultiPurposeListener: OnMultiPurposeListener = object : OnMultiPurposeListener {
        override fun onHeaderStartAnimator(header: RefreshHeader?, headerHeight: Int, maxDragHeight: Int) {

        }

        override fun onFooterReleased(footer: RefreshFooter?, footerHeight: Int, maxDragHeight: Int) {
        }

        override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {
        }

        override fun onFooterFinish(footer: RefreshFooter?, success: Boolean) {
        }

        override fun onFooterStartAnimator(footer: RefreshFooter?, footerHeight: Int, maxDragHeight: Int) {
        }

        override fun onHeaderReleased(header: RefreshHeader?, headerHeight: Int, maxDragHeight: Int) {
        }

        override fun onLoadMore(refreshLayout: RefreshLayout) {
        }

        override fun onRefresh(refreshLayout: RefreshLayout) {
        }

        override fun onHeaderFinish(header: RefreshHeader?, success: Boolean) {
        }

        override fun onHeaderMoving(
            header: RefreshHeader,
            isDragging: Boolean,
            percent: Float,
            offset: Int,
            headerHeight: Int,
            maxDragHeight: Int
        ) {
            iv_blur.layoutParams.height = cy_user_info.measuredHeight + offset
            iv_blur.requestLayout()
        }

        override fun onFooterMoving(
            footer: RefreshFooter,
            isDragging: Boolean,
            percent: Float,
            offset: Int,
            footerHeight: Int,
            maxDragHeight: Int
        ) {
            iv_blur.layoutParams.height = cy_user_info.measuredHeight - offset
            iv_blur.requestLayout()
        }
    }
}