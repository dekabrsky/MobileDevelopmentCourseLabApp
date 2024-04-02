package com.example.mobiledevelopmentcourselabapp.presentation.view.article.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentArticleBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.presenter.ArticlePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.view.WebViewFragment.Companion.WEB_VIEW_LINK_CODE
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.view.BaseFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class ArticleFragment : BaseFragment(), ArticleView {

    private var _binding: FragmentArticleBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<ArticlePresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        context?.let {
//            Glide
//                .with(it)
//                .load("https://img.championat.com/s/732x488/news/big/b/g/stal-izvesten-novyj-kandidat-na-zamenu-kloppu-v-liverpule_17065467721853904716.jpg")
//                .placeholder(AppCompatResources.getDrawable(it, R.drawable.photo))
//                .into(binding.mainPhoto)
//        }

        binding.update.setOnClickListener { presenter.onLoadClicked() }
        binding.openLink.setOnClickListener { presenter.onOpenLinkClicked() }

        binding.likeBtn.setOnClickListener { presenter.onLikeClicked() }
        binding.dislikeBtn.setOnClickListener { presenter.onDislikeClicked() }
    }

    override fun setScore(score: String) {
        binding.likeResult.text = score
    }

    override fun setJoke(joke: String) {
        binding.part1.text = joke
    }

    override fun setJokeTime(dateTime: String) {
        binding.dateTime.text = dateTime
    }

    override fun setJokeNinja(joke: String) {
        binding.part2.text = joke
    }

    override fun openWebView(link: String) {
        val bundle = bundleOf(WEB_VIEW_LINK_CODE to link)
        view?.findNavController()?.navigate(R.id.action_navigation_article_to_webView, bundle)
    }

    override fun setupSpinner(categories: List<String>) {
        val adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, categories) }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = parent.getItemAtPosition(position) as String
                presenter.onCategorySelected(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinner.onItemSelectedListener = listener
        binding.spinner.adapter = adapter
    }

    override fun setSelectedCategory(index: Int?) {
        index?.let { binding.spinner.setSelection(index) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}