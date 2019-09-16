package com.wpy.wanandroid.ui.project.fragment

import androidx.fragment.app.Fragment
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.project.bean.ProjectChapterBean
import com.wpy.wanandroid.ui.project.contract.ProjectContract
import com.wpy.wanandroid.ui.project.presenter.ProjectPresenterImpl
import com.wpy.wanandroid.utils.DisplayInfoUtils
import com.wpy.wanandroid.utils.status.StatusBarCompat
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.android.synthetic.main.fragment_project.tabLayout

class ProjectFragment : BaseMvpFragment<ProjectPresenterImpl>(),ProjectContract.View {

    companion object{
        fun newInstance() = ProjectFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_project
    }

    override fun initData() {
    }

    override fun initView() {
        initToolBar()
    }

    override fun initLoad() {
        presenter.getProjectChapters()
    }

    override fun initPresenter(): ProjectPresenterImpl {
        return ProjectPresenterImpl(this)
    }

    override fun onProjectChaptersSuccess(data: List<ProjectChapterBean>) {
        val titles = arrayOfNulls<String>(data.size)
        val fragments = arrayListOf<Fragment>()
        for (i in data.indices) {
            titles[i] = data[i].name
            fragments.add(ProjectArticleFragment.newInstance(data[i].id, i))
        }
        tabLayout.setViewPager(pro_vp, titles, activity, fragments)
    }

    override fun onProjectChaptersError(e: ResponseException) {
    }

    fun initToolBar() {
        val statusBarheight = StatusBarCompat.getHeight(activity)
        tabLayout.setPadding(0, statusBarheight, 0, 0)
        tabLayout.layoutParams.height = (DisplayInfoUtils.dp2px(40f) + statusBarheight).toInt()
    }
}