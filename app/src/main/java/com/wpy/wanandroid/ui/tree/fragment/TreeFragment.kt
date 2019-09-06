package com.wpy.wanandroid.ui.tree.fragment

import androidx.fragment.app.Fragment
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseFragment
import com.wpy.wanandroid.utils.DisplayInfoUtils
import com.wpy.wanandroid.utils.status.StatusBarCompat
import kotlinx.android.synthetic.main.fragment_tree.*

class TreeFragment : BaseFragment() {

    private val titles = arrayOf("体系", "导航")

    companion object {
        fun newInstance() = TreeFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_tree
    }

    override fun initData() {
    }

    override fun initView() {
        initToolBar()
        val fragments = arrayListOf<Fragment>().apply {
            add(KnowledgeFragment.newInstance())
            add(NaviFragment.newInstance())
        }
        tabLayout.setViewPager(tree_vp, titles, activity, fragments)
    }

    fun initToolBar() {
        val statusBarheight = StatusBarCompat.getHeight(activity)
        title_fy.setPadding(0, statusBarheight, 0, 0)
        title_fy.layoutParams.height = (DisplayInfoUtils.dp2px(40f) + statusBarheight).toInt()
    }

    override fun initLoad() {

    }
}