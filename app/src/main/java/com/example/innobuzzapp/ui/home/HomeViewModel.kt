package com.example.innobuzzapp.ui.home

import android.content.Context
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils.SimpleStringSplitter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innobuzzapp.data.localdb.entities.PostsEntity
import com.example.innobuzzapp.data.repositories.HomeRepo
import com.example.innobuzzapp.ui.details.DetailsFragment
import com.example.innobuzzapp.utils.DisplayUtilities
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {
    var homeTv: String? = null
    var homeInterfaceListener: HomeCommonInterface? = null
    val TAG = "HomeViewModel.kt"

    /**Below method call method of Repository to get data from content providers
     * @param view It is mandatory parameter whenever calling method from layout element
     * using dataBinding support library expression*/
    fun getPostsData(context: Context) {
        if (DisplayUtilities.getConnectionStatus(context)) {
            var liveDataRes: LiveData<ArrayList<PostsEntity>>?
            viewModelScope.launch {
                liveDataRes = HomeRepo().getPosts(context)
//                Log.d(TAG, "data: ${liveDataRes?.value.toString()}")
                liveDataRes?.let {
                    homeInterfaceListener?.setRecyclerView(liveDataRes!!)
                }
            }
        } else {
            Log.d(TAG, "getPostsData: network not avaialable")
        }
    }

    fun openDetails(detailsObj: PostsEntity) {
        //Do Fragment transaction to display details of list item
        homeInterfaceListener?.openFragment(DetailsFragment(detailsObj))
    }

    fun isAccessibilityEnabled(context: Context): Boolean {
        var isGranted: Int = 0
        try {
            isGranted = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, "isAccessibilityEnabled: $e")
        }
        if(isGranted == 1){
            return true
        }
        return false
    }
}