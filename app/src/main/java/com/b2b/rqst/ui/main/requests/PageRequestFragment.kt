package com.b2b.rqst.ui.main.requests

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.b2b.rqst.databinding.FragmentPageRequestBinding
import com.b2b.rqst.databinding.FragmentRequestsBinding
import com.b2b.rqst.model.Request
import com.b2b.rqst.model.Request.Companion.getTestRequests

class PageRequestFragment : Fragment() {

    private var _binding: FragmentPageRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val dashboardViewModel = ViewModelProvider(this).get(RequestsViewModel::class.java)

        _binding = FragmentPageRequestBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.recyclerView
        val requestAdapter = RequestAdapter(getTestRequests()) { request -> adapterOnClick(request) }
        recyclerView.adapter = requestAdapter

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return binding.root
    }
    private fun adapterOnClick(request: Request) {

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}