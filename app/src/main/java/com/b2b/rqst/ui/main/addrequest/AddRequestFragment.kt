package com.b2b.rqst.ui.main.addrequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.b2b.rqst.R
import com.b2b.rqst.databinding.FragmentAddRequestBinding

class AddRequestFragment : Fragment() {

    private var _binding: FragmentAddRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val homeViewModel = ViewModelProvider(this).get(ProfileModel::class.java)

        _binding = FragmentAddRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val listForm = listOf<String>("Form 1 name", "Form 2 name", "Form 3 name")
        val spinnerAdapterForm = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, listForm)
        binding.spinnerForm.adapter = spinnerAdapterForm

        val listOption = listOf<String>("Option value", "Option value", "Option value")
        val spinnerAdapterOption = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, listOption)
        binding.spinnerOptionValue.adapter = spinnerAdapterOption



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}