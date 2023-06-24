package com.b2b.rqst.ui.main.pagereq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.b2b.rqst.databinding.FragmentPageRequestBinding
import com.b2b.rqst.model.ChatTest
import com.b2b.rqst.model.ChatTest.Companion.getChats
import com.b2b.rqst.model.RequestTest.Companion.getTestRequests
import com.b2b.rqst.ui.main.requests.FormAdapter
import com.b2b.rqst.ui.main.requests.RequestsViewModel

class PageRequestFragment : Fragment() {

    private var _binding: FragmentPageRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        val dashboardViewModel = ViewModelProvider(this).get(RequestsViewModel::class.java)

        _binding = FragmentPageRequestBinding.inflate(inflater, container, false)

        val testRequests = getTestRequests()
        val pageRequestAdapter = PageRequestAdapter(getChats()) { request -> adapterOnClick(request) }
        binding.recyclerView.adapter = pageRequestAdapter
        val formAdapter = FormAdapter(testRequests[0].forms)
        binding.recyclerForm.adapter = formAdapter
        binding.chevron.setOnClickListener {
            if (binding.chevronDown.visibility == View.VISIBLE){
                clickDown()
            }else{
                clickUp()
            }
        }

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return binding.root
    }
    private fun adapterOnClick(request: ChatTest) {

    }
    private fun clickDown(){
        binding.chevronDown.visibility = View.GONE
        binding.chevronUp.visibility = View.VISIBLE
        binding.recyclerForm.visibility = View.VISIBLE
        binding.line.visibility = View.VISIBLE

    }
    private fun clickUp(){
        binding.chevronDown.visibility = View.VISIBLE
        binding.chevronUp.visibility = View.GONE
        binding.recyclerForm.visibility = View.GONE
        binding.line.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}