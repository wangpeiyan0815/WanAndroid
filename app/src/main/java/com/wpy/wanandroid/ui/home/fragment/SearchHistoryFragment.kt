package com.wpy.wanandroid.ui.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.home.activity.SearchActivity
import com.wpy.wanandroid.ui.home.bean.HotKeyBean
import com.wpy.wanandroid.ui.home.contract.SearchHistoryContract
import com.wpy.wanandroid.ui.home.presenter.SearchHistoryPresenterImpl
import com.wpy.wanandroid.utils.RvScrollTopUtils
import kotlinx.android.synthetic.main.fragment_search_history.*
import java.util.*

class SearchHistoryFragment : BaseMvpFragment<SearchHistoryPresenterImpl>(), SearchHistoryContract.View {

    private lateinit var mHotAdapter: BaseQuickAdapter<HotKeyBean, BaseViewHolder>
    private lateinit var mHistoryAdapter: BaseQuickAdapter<String, BaseViewHolder>

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
        mHotAdapter.setOnItemClickListener { adapter, view, position ->
            val item = mHotAdapter.getItem(position)
            item?.let {
                search(it.name)
            }
        }
        rv_hot.adapter = mHotAdapter
        rv_history.layoutManager = LinearLayoutManager(mContext)
        mHistoryAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_search_history) {
            override fun convert(helper: BaseViewHolder, item: String?) {
                helper.setText(R.id.tv_key, item)
                    .addOnClickListener(R.id.iv_remove)
            }
        }

        mHistoryAdapter.setOnItemClickListener { adapter, view, position ->
            val key = mHistoryAdapter.getItem(position)
            search(key!!)
        }

        mHistoryAdapter.setOnItemChildClickListener { adapter, view, position ->
            mHistoryAdapter.remove(position)
            presenter.saveHistory(mHistoryAdapter.data)
        }
        rv_history.adapter = mHistoryAdapter
    }


    override fun initLoad() {
        presenter.getHotKeyList()
        mHistoryAdapter.setNewData(presenter.getHistoryList())
    }

    override fun initPresenter(): SearchHistoryPresenterImpl {
        return SearchHistoryPresenterImpl(this)
    }

    override fun onHotKeyListSuccess(data: List<HotKeyBean>) {
        mHotAdapter.setNewData(data)
    }

    override fun onHotKeyListError(e: ResponseException) {
    }

    fun addHistory(key: String) {
        val datas = mHistoryAdapter.data
        val index = datas.indexOf(key)
        if (index == 0) {
            return
        }

        if (index > 0) {
            Collections.swap(datas, index, 0)
            mHistoryAdapter.notifyItemMoved(index, 0)
        }else{
            mHistoryAdapter.addData(0, key)
        }
        RvScrollTopUtils.smoothScrollTop(rv_history)
        presenter.saveHistory(mHistoryAdapter.data)
    }

    private fun search(key: String) {
        if (activity is SearchActivity) {
            (activity as SearchActivity).search(key)
        }
    }
}