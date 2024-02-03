package com.example.mobiledevelopmentcourselabapp.presentation.view.second

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onCreateView")
        // Обращайся к элементам View здесь
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onCreate")
    }


    override fun onStart() {
        super.onStart()
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(LIFECYCLE_TAG, "${this::class.simpleName} - onDetach")
    }

    companion object {
        private const val LIFECYCLE_TAG = "Lifecycle research"
    }
}