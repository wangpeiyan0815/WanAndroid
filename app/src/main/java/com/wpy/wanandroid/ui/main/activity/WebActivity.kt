package com.wpy.wanandroid.ui.main.activity

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.widget.FrameLayout
import com.just.agentweb.AgentWeb
import com.wpy.wanandroid.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*
import android.view.KeyEvent
import com.just.agentweb.DefaultWebClient
import com.wpy.wanandroid.R


class WebActivity : BaseActivity() {

    protected var mTitle = ""
    protected var mUrl = ""
    protected var mArticleId = -1
    private var mCurrTitle = ""
    private var mCurrUrl = ""

    protected lateinit var mAgentWeb: AgentWeb

    companion object {
        const val ARTICLE_ID_EXTRA = "ARTICLE_ID_EXTRA"
        const val TITLE_EXTRA = "TITLE_EXTRA"
        const val URL_EXTRA = "URL_EXTRA"

        fun start(context: Context, articleId: Int, title: String, url: String) {
            val intent = Intent(context, WebActivity::class.java).putExtra(ARTICLE_ID_EXTRA, articleId)
                .putExtra(TITLE_EXTRA, title)
                .putExtra(URL_EXTRA, url)
            context.startActivity(intent)
        }
    }

    override fun initLayoutRes(): Int {
        return com.wpy.wanandroid.R.layout.activity_web
    }

    override fun initData() {
        mTitle = intent.getStringExtra(TITLE_EXTRA)
        mArticleId = intent.getIntExtra(ARTICLE_ID_EXTRA, -1)
        mUrl = intent.getStringExtra(URL_EXTRA)

        mCurrUrl = mUrl
        mCurrTitle = mTitle
    }

    override fun initView() {
        refreshStatusBar()

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(web_container, FrameLayout.LayoutParams(-1, -1))
            .useDefaultIndicator(-1, 3)
//            .setAgentWebWebSettings(getSettings())
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
            .interceptUnkownUrl()
            .createAgentWeb()
            .ready()
            .go(mUrl)
    }

    override fun initLoad() {
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }
}