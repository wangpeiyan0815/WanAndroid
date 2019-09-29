package com.wpy.wanandroid.ui.home.activity

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.wpy.wanandroid.R
import com.wpy.wanandroid.base.activity.BaseActivity
import com.wpy.wanandroid.ui.home.fragment.SearchHistoryFragment
import com.wpy.wanandroid.ui.home.fragment.SearchResultFragment
import com.wpy.wanandroid.utils.EditTextUtils
import com.wpy.wanandroid.utils.InputMethodUtils
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private lateinit var mSearchHistoryFragment: SearchHistoryFragment
    private lateinit var mSearchResultFragment: SearchResultFragment
    private var mIsResultPage = false
    private lateinit var mFm: FragmentManager

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
            if (mIsResultPage) showHistoryFragment() else finish()
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

    private fun initFragment() {
        mFm = supportFragmentManager
        val searchHistoryFragment = mFm.findFragmentByTag(SearchHistoryFragment::class.java.name)
        mSearchHistoryFragment = if (searchHistoryFragment == null) {
            SearchHistoryFragment.creatFragment()
        } else {
            searchHistoryFragment as SearchHistoryFragment
        }
        val searchResultFragment = mFm.findFragmentByTag(SearchResultFragment::class.java.name)
        mSearchResultFragment = if (searchResultFragment == null) {
            SearchResultFragment.creatFragment()
        } else {
            searchResultFragment as SearchResultFragment
        }
        val t = mFm.beginTransaction()
        t.add(R.id.fl, mSearchHistoryFragment, SearchHistoryFragment::class.java.name)
        t.add(R.id.fl, mSearchResultFragment, SearchResultFragment::class.java.name)
        t.show(mSearchHistoryFragment)
        t.hide(mSearchResultFragment)
        t.commit()
    }

    public fun search(key: String) {
        InputMethodUtils.hide(abs.editTextView)
        abs.editTextView.clearFocus()
        // key.isEmpty() key.isBlank  非空串可以调用
        if (key.isNullOrBlank()) {
            if (mIsResultPage) {
                showHistoryFragment()
            }
        } else {
            EditTextUtils.setTextWithSelection(abs.editTextView, key)
            if (!mIsResultPage) {
                showResultFragment()
            }
            mSearchHistoryFragment.addHistory(key)
            mSearchResultFragment.search(key)
        }
    }

    private fun showHistoryFragment() {
        mIsResultPage = false
        val t = mFm.beginTransaction()
        t.hide(mSearchResultFragment)
        t.show(mSearchHistoryFragment)
        t.commit()
    }

    private fun showResultFragment() {
        mIsResultPage = true
        val t = mFm.beginTransaction()
        t.hide(mSearchHistoryFragment)
        t.show(mSearchResultFragment)
        t.commit()
    }

    override fun onBackPressed() {
        if (mIsResultPage) {
            showHistoryFragment()
        } else {
            super.onBackPressed()
        }
    }
}