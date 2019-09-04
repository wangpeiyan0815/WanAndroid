package com.wpy.wanandroid.ui.tree.bean

data class KnowledgeBean(
    val courseId: String = "",
    val id: Int = 0,
    val name: String = "",
    val order: Int = 0,
    val parentChapterId: Int = 0,
    val userControlSetTop: Boolean = false,
    val visible: Int = 0,
    val children: List<KnowledgeBean>
)