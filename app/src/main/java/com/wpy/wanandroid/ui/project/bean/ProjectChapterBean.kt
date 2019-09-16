package com.wpy.wanandroid.ui.project.bean

data class ProjectChapterBean(
    val courseId: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val order: Int = 0,
    val parentChapterId: Int = 0,
    val userControlSetTop: Boolean = false,
    val visible: Int = 0,
    val children: List<*>
)