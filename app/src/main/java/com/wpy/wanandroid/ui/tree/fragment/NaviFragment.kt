package com.wpy.wanandroid.ui.tree.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.tree.contract.NaviContract
import com.wpy.wanandroid.ui.tree.presenter.NaviPresenterImpl
import com.wpy.wanandroid.ui.tree.adapter.NaviRvAdapter
import com.wpy.wanandroid.ui.tree.bean.NaviBean
import kotlinx.android.synthetic.main.fragment_navi.*

class NaviFragment : BaseMvpFragment<NaviPresenterImpl>(), NaviContract.View {

    private lateinit var mAdapter:NaviRvAdapter

    companion object{
        fun newInstance():NaviFragment{
            return NaviFragment()
        }
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_navi
    }

    override fun initData() {
    }

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(activity)
        mAdapter = NaviRvAdapter(null)
        rv.adapter = mAdapter
    }

    override fun initLoad() {
        presenter.getNaviList()
    }

    override fun initPresenter(): NaviPresenterImpl {
        return NaviPresenterImpl(this)
    }

    override fun onNaviListSuccess(data: List<NaviBean>) {
        mAdapter.setNewData(data)
    }

    override fun onNaviListError(e: ResponseException) {

    }
}