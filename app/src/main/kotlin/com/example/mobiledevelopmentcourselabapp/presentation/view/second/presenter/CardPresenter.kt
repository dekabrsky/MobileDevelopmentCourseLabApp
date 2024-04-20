package com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardMvpView
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CardPresenter @Inject constructor(
    private val context: Context
): MvpPresenter<CardMvpView>() {

    private var isCommentsOpen = false
    private var message: String? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupTextLinks(
            text = context.getString(R.string.textLinks),
            links = mapOf(
                context.getString(R.string.linkSocial) to ::onSocialLinkClicked,
                context.getString(R.string.linkResume) to {}
            )
        )
    }

    fun onCommentTitleClicked() {
        isCommentsOpen = isCommentsOpen.not()
        viewState.setHiddenGroupVisibility(isCommentsOpen)
        viewState.setCommentChevronIcon(
            if (isCommentsOpen) {
                R.drawable.arrow_up
            } else {
                R.drawable.chevron_down
            }
        )
    }

    fun onCommentChanged(text: CharSequence?) {
        message = text.toString()
        viewState.setSendButtonEnabled(text?.isNotBlank().orFalse())
    }

    fun onSendButtonClicked() {
//        if (message?.equals("ошибка").orFalse()) {
//            viewState.setMessageError("Это ошибка!")
//        } else {
//            viewState.showSnackbar()
//        }
        message?.let { viewState.addComment(it) }
    }

    private fun onSocialLinkClicked() {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://vk.com/institutdo"))
        viewState.openBrowser(intent)
    }
}