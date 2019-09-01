package com.wpy.wanandroid.ui.main.entity

import com.flyco.tablayout.listener.CustomTabEntity

data class TabEntity(val title: String, val selectedIcon: Int, val unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}