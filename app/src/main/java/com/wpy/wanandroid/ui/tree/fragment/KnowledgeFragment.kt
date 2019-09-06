package com.wpy.wanandroid.ui.tree.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.shehuan.wanandroid.base.net.exception.ResponseException
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseMvpFragment
import com.wpy.wanandroid.ui.tree.contract.KnowledgeContract
import com.wpy.wanandroid.ui.tree.presenter.KnowledgePresenterImpl
import com.wpy.wanandroid.ui.tree.adapter.KnowledgeRvAdapter
import com.wpy.wanandroid.ui.tree.bean.KnowledgeBean
import com.wpy.wanandroid.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_knowledge.*

class KnowledgeFragment : BaseMvpFragment<KnowledgePresenterImpl>(), KnowledgeContract.View {

    private lateinit var mAdapter: KnowledgeRvAdapter

    companion object {
        fun newInstance(): KnowledgeFragment {
            return KnowledgeFragment()
        }
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_knowledge
    }

    override fun initData() {
    }

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(activity)
        mAdapter = KnowledgeRvAdapter(null)
        rv.adapter = mAdapter
        mAdapter.setItemClickListener { bean, pos ->
            ToastUtil.show(activity, "${bean.children[pos].name + pos}")
        }
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val datas = adapter.data as List<KnowledgeBean>
            ToastUtil.show(activity, "${datas[position].name}")
        }
    }

    override fun initLoad() {
        presenter.getKnowledgeList()
    }

    override fun initPresenter(): KnowledgePresenterImpl {
        return KnowledgePresenterImpl(this)
    }

    override fun onKnowledgeListSuccess(data: List<KnowledgeBean>) {
        mAdapter.setNewData(data)
    }

    override fun onKnowledgeError(e: ResponseException) {

    }
}