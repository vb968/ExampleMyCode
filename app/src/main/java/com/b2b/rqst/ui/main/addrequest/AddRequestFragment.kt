package com.b2b.rqst.ui.main.addrequest

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.b2b.rqst.R
import com.b2b.rqst.databinding.FileBinding
import com.b2b.rqst.databinding.FragmentAddRequestBinding
import com.b2b.rqst.utils.FileUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRequestFragment : Fragment() {
    private val listUri = ArrayList<Uri>()

    private var _binding: FragmentAddRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val addRequestModel = ViewModelProvider(this).get(AddRequestModel::class.java)

        _binding = FragmentAddRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val listForm = listOf<String>("Form 1 name", "Form 2 name", "Form 3 name", "Form 3 name", "Form 3 name", "Form 3 name")
        val spinnerAdapterForm = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, listForm)
        binding.spinnerForm.adapter = spinnerAdapterForm

        val listOption = listOf<String>("Option value", "Option value", "Option value", "Option value", "Option value", "Option value")
        val spinnerAdapterOption = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, listOption)
        binding.spinnerOptionValue.adapter = spinnerAdapterOption

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                addFile(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
        binding.chooseFileIcon.setOnClickListener {
            if (listUri.size < 10) {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }

        addRequestModel.getRequest()
        addRequestModel.beforeСreate()

        return root
    }

    private fun addFile(uri: Uri){
        val fileLayout = FileBinding.inflate(LayoutInflater.from(requireContext()), binding.constraintRoot, false)
        fileLayout.fileIcon.setImageURI(uri)
        val fileUtils = FileUtils(requireContext())
        fileLayout.fileName.text = fileUtils.getFileName(uri)
        fileLayout.fileSize.text = fileUtils.getSizeKb(uri) + "kb"
        fileLayout.fileDelete.setOnClickListener {
            binding.fileContainer.removeView(fileLayout.root)
            listUri.remove(uri)
        }
        binding.fileContainer.addView(fileLayout.root)
        listUri.add(uri)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}