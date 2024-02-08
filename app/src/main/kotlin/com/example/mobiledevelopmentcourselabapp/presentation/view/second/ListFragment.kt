package com.example.mobiledevelopmentcourselabapp.presentation.view.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.PlayersAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.decorator.VerticalSpaceItemDecorator
import com.example.mobiledevelopmentcourselabapp.utils.dpToPx
import com.example.mobiledevelopmentcourselabapp.utils.orZero

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter by lazy { PlayersAdapter() }

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
        adapter.updateItems(Generator.generate())
        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
        binding.playersList.addItemDecoration(dividerItemDecoration)
        binding.playersList.addItemDecoration(VerticalSpaceItemDecorator(context?.dpToPx(60).orZero()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}