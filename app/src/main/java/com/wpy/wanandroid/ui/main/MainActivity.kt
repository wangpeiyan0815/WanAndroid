package com.wpy.wanandroid.ui.main

import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.activity.BaseActivity
import com.wpy.wanandroid.base.fragment.BaseFragment
import com.wpy.wanandroid.ui.home.HomeFragment
import com.wpy.wanandroid.ui.main.adapter.MainViewPagerAdapter
import com.wpy.wanandroid.ui.main.entity.TabEntity
import com.wpy.wanandroid.ui.mine.MineFragment
import com.wpy.wanandroid.ui.project.ProjectFragment
import com.wpy.wanandroid.ui.tree.fragment.TreeFragment
import com.wpy.wanandroid.ui.wxarticle.WxFragment
import com.wpy.wanandroid.utils.status.StatusBarCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mTabEntities = ArrayList<CustomTabEntity>()
    private val titles = arrayOf("首页", "体系", "公众号", "项目", "我的")

    private val mIconUnselectIds = arrayOf(
        R.drawable.ic_home_normal,
        R.drawable.ic_book_normal,
        R.drawable.ic_wechat_normal,
        R.drawable.ic_project_normal,
        R.drawable.ic_mine_normal
    )
    private val mIconSelectIds = arrayOf(
        R.drawable.ic_home_selected,
        R.drawable.ic_book_selected,
        R.drawable.ic_wechat_selected,
        R.drawable.ic_project_selected,
        R.drawable.ic_mine_selected
    )

    override fun intiLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun intiData() {
    }

    override fun intiView() {
        refreshStatusBar()
        val fragments = arrayListOf<BaseFragment>().apply {
            add(HomeFragment.newInstance())
            add(TreeFragment.newInstance())
            add(WxFragment.newInstance())
            add(ProjectFragment.newInstance())
            add(MineFragment.newInstance())
        }

        val vpAdapter = MainViewPagerAdapter(supportFragmentManager)
        vpAdapter.setFragments(fragments)
        mainVp.adapter = vpAdapter
        mainVp.offscreenPageLimit = fragments.size
        mainVp.addOnPageChangeListener(pageChangeListene)

        for (i in titles.indices) {
            mTabEntities.add(TabEntity(titles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        tabLayout.setTabData(mTabEntities)
        tabLayout.setOnTabSelectListener(tabSelectListener)
    }

    fun refreshStatusBar() {
        StatusBarCompat.transparent(this)
        StatusBarCompat.setIconMode(this, true)
    }


    override fun intiLoad() {

    }

    private val pageChangeListene: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            tabLayout.currentTab = position
        }

    }

    private val tabSelectListener: OnTabSelectListener = object : OnTabSelectListener {
        override fun onTabReselect(position: Int) {

        }

        override fun onTabSelect(position: Int) {
            mainVp.currentItem = position
        }
    }
}


