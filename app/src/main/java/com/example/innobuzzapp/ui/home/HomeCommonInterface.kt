package com.example.innobuzzapp.ui.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.innobuzzapp.data.localdb.entities.PostsEntity
import com.example.innobuzzapp.data.repositories.responses.PostResponse

interface HomeCommonInterface {
    fun setRecyclerView(liveData: LiveData<ArrayList<PostsEntity>>)
    fun openFragment(fragment: Fragment)
}