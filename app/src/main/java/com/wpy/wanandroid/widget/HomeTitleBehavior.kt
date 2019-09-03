package com.wpy.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

class HomeTitleBehavior : CoordinatorLayout.Behavior<View> {

    private var deltaY = 0f

    constructor()
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is RecyclerView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if (deltaY == 0f) {
            deltaY = dependency.y - child.height
        }

        var dy = dependency.y - child.height
        dy = if (dy < 0) 0f else dy
        var y = -(dy / deltaY) * child.height
//        child.translationY = y

        val alpha = 1 - (dy / deltaY)
        child.alpha = alpha
        return true
    }
}