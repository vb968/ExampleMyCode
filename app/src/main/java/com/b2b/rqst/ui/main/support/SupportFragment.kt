package com.b2b.rqst.ui.main.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.b2b.rqst.databinding.FragmentSupportBinding
import com.b2b.rqst.model.ChatTest
import com.b2b.rqst.ui.main.pagereq.PageRequestAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupportFragment : Fragment() {

    private var _binding: FragmentSupportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val supportViewModel = ViewModelProvider(this).get(SupportViewModel::class.java)

        _binding = FragmentSupportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val pageRequestAdapter = PageRequestAdapter(ChatTest.getChats()) { request -> adapterOnClick(request) }
        binding.recyclerView.adapter = pageRequestAdapter

        supportViewModel.chatSupport()

        return root
    }
    private fun adapterOnClick(request: ChatTest) {

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}