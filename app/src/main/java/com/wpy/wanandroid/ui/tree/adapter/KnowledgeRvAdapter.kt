package com.wpy.wanandroid.ui.tree.adapter

import android.view.LayoutInflater
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexboxLayout
import com.wpy.wanandroid.R
import com.wpy.wanandroid.ui.tree.bean.KnowledgeBean
import java.util.*

class KnowledgeRvAdapter(data: List<KnowledgeBean>?) :
    BaseQuickAdapter<KnowledgeBean, BaseViewHolder>(R.layout.rv_item_knowledge, data) {

    private val mFlexItemTextViewCaches = LinkedList<TextView>()
    private var mInflater: LayoutInflater? = null
    private var onClick: (bean: KnowledgeBean, pos: Int) -> Unit = { knowledgeBean: KnowledgeBean, i: Int -> }

    fun setItemClickListener(onClick: (bean: KnowledgeBean, pos: Int) -> Unit) {
        this.onClick = onClick
    }

    override fun convert(helper: BaseViewHolder, item: KnowledgeBean) {
        helper.setText(R.id.tv_name, item.name)
        val fbl = helper.getView<FlexboxLayout>(R.id.fbl)
        for (index in item.children.indices) {
            val childBean = item.children[index]
            val childTv = createOrGetCacheFlexItemTextView(fbl)
            childTv.text = childBean.name
            var finalI = index
            fbl.addView(childTv)
            childTv.setOnClickListener {
                this.onClick(item, finalI)
            }
        }
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        super.onViewRecycled(holder)
        val fbl = holder.getView<FlexboxLayout>(R.id.fbl)
        for (i in 0 until fbl.childCount) {
            mFlexItemTextViewCaches.offer(fbl.getChildAt(i) as TextView)
        }
        fbl.removeAllViews()
    }

    private fun createOrGetCacheFlexItemTextView(fbl: FlexboxLayout): TextView {
        val tv = mFlexItemTextViewCaches.poll()
        if (tv != null) {
            return tv
        }
        return createFlexItemTextView(fbl)
    }

    private fun createFlexItemTextView(fbl: FlexboxLayout): TextView {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext)
        }
        return mInflater?.inflate(R.layout.rv_item_knowledge_child, fbl, false) as TextView
    }
}