package com.wpy.wanandroid.ui.wxarticle.bean

data class DatasBean(
    val apkLink: String = "",
    val author: String = "",
    val chapterId: Int = 0,
    val chapterName: String = "",
    val collect: Boolean = false,
    val courseId: Int = 0,
    val desc: String = "",
    val envelopePic: String = "",
    val fresh: Boolean = false,
    val id: Int = 0,
    val link: String = "",
    val niceDate: String = "",
    val origin: String = "",
    val prefix: String = "",
    val projectLink: String = "",
    val publishTime: Long = 0,
    val superChapterId: Int = 0,
    val superChapterName: String = "",
    val title: String = "",
    val type: Int = 0,
    val userId: Int = 0,
    val visible: Int = 0,
    val zan: Int = 0,
    val tags: List<TagsBean>
)