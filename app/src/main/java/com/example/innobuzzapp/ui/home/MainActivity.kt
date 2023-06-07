package com.example.innobuzzapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innobuzzapp.R
import com.example.innobuzzapp.data.localdb.entities.PostsEntity
import com.example.innobuzzapp.data.repositories.responses.PostResponse
import com.example.innobuzzapp.databinding.ActivityMainBinding
import com.example.innobuzzapp.ui.adapters.HomeListAdapter
import com.example.innobuzzapp.ui.details.DetailsFragment
import com.example.innobuzzapp.utils.DisplayUtilities
import com.example.innobuzzapp.utils.DisplayUtilities.Companion.toast

class MainActivity : AppCompatActivity(), HomeCommonInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HomeViewModel
    lateinit var adapter: HomeListAdapter
    lateinit var layoutManager: LinearLayoutManager
    val REQUEST_CODE = 101
    val TAG = "MainActivity.kt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        viewModel = ViewModelProvider(this@MainActivity)[HomeViewModel::class.java]
        binding.homeViewModel = viewModel
        viewModel.homeInterfaceListener = this
        layoutManager = LinearLayoutManager(this@MainActivity)

        //get data and setup recycler view
        viewModel.getPostsData(this@MainActivity)
        if(viewModel.isAccessibilityEnabled(this@MainActivity)) {
            binding.onOffService.isChecked
        }
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.onOffService.setOnClickListener {
            if(binding.onOffService.isChecked) {
                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                startActivityForResult(intent, REQUEST_CODE)
            } else {
                DisplayUtilities.toast("Accessibility service is disabled", this)
            }
        }
    }

    /**Below method is setup recycler view list with data from room */
    override fun setRecyclerView(liveData: LiveData<ArrayList<PostsEntity>>) {
        binding.bar.visibility = View.GONE
        adapter = HomeListAdapter(this, liveData.value!!, viewModel)
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = layoutManager
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(requestCode == REQUEST_CODE) {
            val isEnabled = viewModel.isAccessibilityEnabled(this@MainActivity)
            if(!isEnabled) {
                binding.onOffService.isChecked = false
            }
            Log.d(TAG, "onActivityResult: $isEnabled" )
        }
    }
    /**Below method open Fragment using Fragment Transaction */
    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, fragment).commit()
    }
}