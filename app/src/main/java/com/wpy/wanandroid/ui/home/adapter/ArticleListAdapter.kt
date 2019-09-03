package com.wpy.wanandroid.ui.home.adapter

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wpy.wanandroid.R
import com.wpy.wanandroid.ui.home.bean.DatasItem
import com.wpy.wanandroid.utils.ImageLoader

class ArticleListAdapter(data: List<DatasItem>?) :
    BaseQuickAdapter<DatasItem, BaseViewHolder>(R.layout.rv_item_article, data) {

    override fun convert(helper: BaseViewHolder, item: DatasItem) {

        helper.setText(R.id.tv_title, Html.fromHtml(item.title))
        helper.setText(R.id.tv_author, item.author)
        helper.setText(R.id.tv_time, item.niceDate)
        helper.setText(R.id.tv_super_chapter_name, item.superChapterName)
        helper.setText(R.id.tv_chapter_name, item.chapterName)
        if (item.fresh) helper.setGone(R.id.ll_new, true) else helper.setGone(R.id.ll_new, false)

        if (item.envelopePic.isNotEmpty()) {
            ImageLoader.image(helper.getView(R.id.iv_img), item.envelopePic)
            helper.setGone(R.id.iv_img, true)
        } else {
            helper.setGone(R.id.iv_img, false)
        }

        if (item.tags.isNotEmpty()) {
            helper.setText(R.id.tv_tag, item.tags[0].name)
            helper.setGone(R.id.tv_tag, true)
        } else {
            helper.setGone(R.id.tv_tag, false)
        }

        // 收藏
//        val cv_collect = helper.getView(R.id.cv_collect)
//        if (item.isCollect()) {
//            cv_collect.setChecked(true)
//        } else {
//            cv_collect.setChecked(false)
//        }
    }
}