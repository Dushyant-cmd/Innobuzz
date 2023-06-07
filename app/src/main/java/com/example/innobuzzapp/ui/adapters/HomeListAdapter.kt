package com.example.innobuzzapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.innobuzzapp.R
import com.example.innobuzzapp.data.localdb.entities.PostsEntity
import com.example.innobuzzapp.data.repositories.responses.PostResponse
import com.example.innobuzzapp.ui.home.HomeViewModel
import org.w3c.dom.Text

class HomeListAdapter(var context: Context, var list: ArrayList<PostsEntity>, var viewModel: HomeViewModel): RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    //Below method calls only when list items layout created like only for 7 times if 7 item fits in
    //whole screen which give parameters
    /**@param parent is root View of list item layout View Hierarchy
     * @param whichType is int type value indicate type of layout like a flag. */
    override fun onCreateViewHolder(parent: ViewGroup, whichType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    /**Below method LayoutManager calls every time item scroll onto screen to bind data to item object.
     * @param holder is ViewHolder wrapper class object contains list item object to make abstraction
     * @param pos is position of item in array list of recycler view.*/
    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val dataObj = list.get(pos)
        holder.titleTv.text = dataObj.title
        holder.detailsBtn.setOnClickListener {
            viewModel.openDetails(dataObj)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var titleTv = view.findViewById<TextView>(R.id.title)
        var detailsBtn = view.findViewById<TextView>(R.id.detailsBtn)
    }
}