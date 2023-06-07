package com.example.innobuzzapp.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.innobuzzapp.R
import com.example.innobuzzapp.data.localdb.entities.PostsEntity

class DetailsFragment() : Fragment() {
    val TAG = "DatailsFragment.kt"
    lateinit var data: PostsEntity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ${data.body}")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        view.findViewById<TextView>(R.id.id).text = "Id: ${data.id}"
        view.findViewById<TextView>(R.id.userId).text = "User Id: ${data.userId}"
        view.findViewById<TextView>(R.id.title).text = "Title: ${data.title}"
        view.findViewById<TextView>(R.id.body).text = "Body: ${data.body}"
        return view
    }

    constructor(dataObj: PostsEntity): this() {
        this.data = dataObj
    }
}