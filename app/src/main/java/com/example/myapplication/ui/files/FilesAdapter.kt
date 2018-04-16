package com.example.myapplication.ui.files

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.entities.FileScanned
import com.example.myapplication.utils.FileScannedDiffCallback
import kotlinx.android.synthetic.main.recycler_files.view.*

class FilesAdapter : ListAdapter<FileScanned, FilesAdapter.ViewHolder>(FileScannedDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val view = layoutInflater.inflate(R.layout.recycler_files, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: FileScanned?) = with(itemView) {
      r_info_name.text = item?.name
      r_info_path.text = item?.path
      r_info_size.text = item?.size.toString()
      r_info_extension.text = item?.extension
    }
  }
}