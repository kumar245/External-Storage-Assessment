package com.example.myapplication.ui.extension

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.entities.Extension
import com.example.myapplication.utils.ExtensionDiffCallback
import kotlinx.android.synthetic.main.recycler_extension.view.*

class ExtensionAdapter : ListAdapter<Extension, ExtensionAdapter.ViewHolder>(ExtensionDiffCallback()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val view = layoutInflater.inflate(R.layout.recycler_extension, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Extension?) = with(itemView) {
      r_extension_name.text = item?.name
      r_extension_freq.text = item?.frequency.toString()
    }
  }
}