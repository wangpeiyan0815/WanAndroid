package com.wpy.wanandroid.ui.home.fragment

import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.fragment.BaseFragment

class SearchResultFragment : BaseFragment(){


    companion object{

        fun creatFragment():SearchResultFragment{
            return SearchResultFragment()
        }
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_search_result
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initLoad() {
    }

    public fun search(key:String){

    }
}