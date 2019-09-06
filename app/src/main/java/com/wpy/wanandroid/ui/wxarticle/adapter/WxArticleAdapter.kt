package com.wpy.wanandroid.ui.wxarticle.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wpy.wanandroid.R
import com.wpy.wanandroid.ui.wxarticle.bean.DatasBean
import com.wpy.wanandroid.utils.ImageLoader

class WxArticleAdapter(datas: List<DatasBean>?) :
    BaseQuickAdapter<DatasBean, BaseViewHolder>(R.layout.rv_item_article, datas) {

    override fun convert(helper: BaseViewHolder, item: DatasBean) {
        helper.setText(R.id.tv_title, item.title)
            .setText(R.id.tv_author, item.author)
            .setText(R.id.tv_time, item.niceDate)
            .setText(R.id.tv_super_chapter_name, item.superChapterName)
            .setText(R.id.tv_chapter_name, item.chapterName)

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