package com.example.myapplication.utils

import android.support.v7.util.DiffUtil
import com.example.myapplication.data.entities.Extension

class ExtensionDiffCallback : DiffUtil.ItemCallback<Extension>() {
  override fun areItemsTheSame(oldItem: Extension?, newItem: Extension?): Boolean =
      oldItem?.name == newItem?.name

  override fun areContentsTheSame(oldItem: Extension?, newItem: Extension?): Boolean =
      oldItem?.equals(newItem) ?: false
}