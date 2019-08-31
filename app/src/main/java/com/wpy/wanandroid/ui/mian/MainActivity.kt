package com.wpy.wanandroid.ui.mian

import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.activity.BaseActivity
import com.wpy.wanandroid.base.fragment.BaseFragment
import com.wpy.wanandroid.ui.mian.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun intiLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun intiData() {
    }

    override fun intiView() {

        val fragments  = arrayListOf<BaseFragment>().apply {

        }

        val vpAdapter = MainViewPagerAdapter(supportFragmentManager)
        vpAdapter.setFragments(fragments)
        mainVp.adapter = vpAdapter
        mainVp.offscreenPageLimit = fragments.size
    }

    override fun intiLoad() {
    }
}


