package com.wpy.wanandroid.ui.home.fragment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.home.bean.HotKeyBean
import com.wpy.wanandroid.ui.home.contract.SearchHistoryContract
import com.wpy.wanandroid.ui.home.presenter.SearchHistoryPresenterImpl
import kotlinx.android.synthetic.main.fragment_search_history.*

class SearchHistoryFragment : BaseMvpFragment<SearchHistoryPresenterImpl>(), SearchHistoryContract.View {

    private lateinit var mHotAdapter: BaseQuickAdapter<HotKeyBean, BaseViewHolder>

    companion object {

        fun creatFragment(): SearchHistoryFragment {
            return SearchHistoryFragment()
        }
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_search_history
    }

    override fun initData() {
    }

    override fun initView() {
        isViewPagerPattern(false)
        rv_hot.isNestedScrollingEnabled = false
        rv_hot.setHasFixedSize(true)
        rv_hot.layoutManager = FlexboxLayoutManager(mContext)
        mHotAdapter = object : BaseQuickAdapter<HotKeyBean, BaseViewHolder>(R.layout.rv_item_search_hot) {
            override fun convert(helper: BaseViewHolder, item: HotKeyBean) {
                helper.setText(R.id.tv_key, item.name)
            }
        }
        rv_hot.adapter = mHotAdapter
    }


    override fun initLoad() {
        presenter.getHotKeyList()
    }

    override fun initPresenter(): SearchHistoryPresenterImpl {
        return SearchHistoryPresenterImpl(this)
    }

    override fun onHotKeyListSuccess(data: List<HotKeyBean>) {
        mHotAdapter.setNewData(data)
    }

    override fun onHotKeyListError(e: ResponseException) {
    }
}