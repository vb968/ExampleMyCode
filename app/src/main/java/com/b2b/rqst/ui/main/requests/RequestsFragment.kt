package com.b2b.rqst.ui.main.requests

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.b2b.rqst.R
import com.b2b.rqst.databinding.FragmentRequestsBinding
import com.b2b.rqst.model.RequestTest
import com.b2b.rqst.model.RequestTest.Companion.getTestRequests
import com.b2b.rqst.ui.dialog.PushDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestsFragment : Fragment() {

    private var _binding: FragmentRequestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val requestsViewModel = ViewModelProvider(this).get(RequestsViewModel::class.java)

        _binding = FragmentRequestsBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.recyclerView
        val requestAdapter = RequestsAdapter(getTestRequests()) { request -> adapterOnClick(request) }
        recyclerView.adapter = requestAdapter

        binding.textAll.setOnClickListener {
            val intent = (Intent(context, PushDialog()::class.java))
            requireContext().startActivity(intent)
        }

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        requestsViewModel.getForms()
        requestsViewModel.statuses()

        return binding.root
    }
    private fun adapterOnClick(request: RequestTest) {
        findNavController().navigate(R.id.action_navigation_requests_to_navigation_page_request)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}