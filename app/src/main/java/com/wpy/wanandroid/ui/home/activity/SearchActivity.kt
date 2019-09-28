package com.wpy.wanandroid.ui.home.activity

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.activity.BaseActivity
import com.wpy.wanandroid.ui.home.fragment.SearchHistoryFragment
import com.wpy.wanandroid.ui.home.fragment.SearchResultFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private lateinit var mSearchHistoryFragment:SearchHistoryFragment
    private lateinit var mSearchResultFragment: SearchResultFragment

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }

    override fun initLayoutRes(): Int {
        return R.layout.activity_search
    }

    override fun initData() {
    }

    override fun initView() {
        abs.setOnLeftIconClickListener {
            finish()
        }

        abs.setOnRightTextClickListener {
            val key = abs.editTextView.text.toString()
            search(key)
        }

        abs.editTextView.setSingleLine()
        abs.editTextView.imeOptions = EditorInfo.IME_ACTION_SEARCH // 设置为搜索模式
        abs.editTextView.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(tv: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val key = abs.editTextView.text.toString()
                    search(key)
                    return true
                }
                return false
            }
        })
        initFragment()
    }

    override fun initLoad() {
    }

    private fun initFragment(){
        val fm = supportFragmentManager
        val searchHistoryFragment = fm.findFragmentByTag(SearchHistoryFragment::class.java.name)
        mSearchHistoryFragment = if(searchHistoryFragment == null){
            SearchHistoryFragment.creatFragment()
        }else{
            searchHistoryFragment as SearchHistoryFragment
        }
        val searchResultFragment = fm.findFragmentByTag(SearchResultFragment::class.java.name)
        mSearchResultFragment = if(searchResultFragment == null ){
            SearchResultFragment.creatFragment()
        }else{
            searchResultFragment as SearchResultFragment
        }
        val t = fm.beginTransaction()
        t.add(R.id.fl,mSearchHistoryFragment,SearchHistoryFragment::class.java.name)
        t.add(R.id.fl,mSearchResultFragment,SearchResultFragment::class.java.name)
        t.show(mSearchHistoryFragment)
        t.hide(mSearchResultFragment)
        t.commit()
    }

    private fun search(key: String) {

    }
}