package com.example.mobiledevelopmentcourselabapp.presentation.view.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.view.BaseFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.PlayersAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.decorator.VerticalSpaceItemDecorator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter.CardPresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter.EditPresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter.ListPresenter
import com.example.mobiledevelopmentcourselabapp.utils.dpToPx
import com.example.mobiledevelopmentcourselabapp.utils.orZero
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class ListFragment : BaseFragment(), ListMvpView {

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter by lazy { PlayersAdapter(::onPlayerClicked) }

    @Inject
    lateinit var presenterProvider: Provider<ListPresenter>

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
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Обращайся к элементам View здесь
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playersList.adapter = adapter
        setHasOptionsMenu(true)

        setFragmentResultListener(DEFAULT_RESULT_KEY) { key, bundle ->
            if (bundle.containsKey(EditPresenter.NEED_TO_UPDATE)) presenter.update()
        }
//        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
//        binding.playersList.addItemDecoration(dividerItemDecoration)
//        binding.playersList.addItemDecoration(VerticalSpaceItemDecorator(context?.dpToPx(60).orZero()))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                navigateToEdit()
                true
            }
            else -> true
        }
    }

    private fun onPlayerClicked(player: PlayerUiModel) {
        val bundle = bundleOf(CardFragment.CARD_PLAYER_KEY to player)
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_cardFragment, bundle)
    }

    private fun navigateToEdit() {
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_editFragment)
    }

    override fun showPlayers(players: List<ItemUiModel>) {
        adapter.updateItems(players)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}