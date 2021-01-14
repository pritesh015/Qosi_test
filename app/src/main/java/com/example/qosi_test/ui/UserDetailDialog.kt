package com.example.qosi_test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.qosi_test.R
import com.example.qosi_test.databinding.ViewDialogUserDetailBinding

class UserDetailDialog: DialogFragment() {
    private var _binding: ViewDialogUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.BaseDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewDialogUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val picture = this.arguments?.get("picture")?.toString()
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

        if (picture == null) {
            Glide.with(this)
                .load(R.drawable.placeholder)
                .apply(requestOptions)
                .into(binding.ivPhoto)
        } else {
            Glide.with(this)
                .load(picture)
                .apply(requestOptions)
                .into(binding.ivPhoto)
        }

        binding.tvName.text = this.arguments?.get("name")?.toString()
        binding.tvEmail.text =  this.arguments?.get("email")?.toString()

        binding.clMainDialog.setOnClickListener {
            this.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}