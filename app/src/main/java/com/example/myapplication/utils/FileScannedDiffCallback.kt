package com.example.myapplication.utils

import android.support.v7.util.DiffUtil
import com.example.myapplication.data.entities.FileScanned

class FileScannedDiffCallback : DiffUtil.ItemCallback<FileScanned>() {
  override fun areItemsTheSame(oldItem: FileScanned?, newItem: FileScanned?) =
      oldItem?.path == newItem?.path

  override fun areContentsTheSame(oldItem: FileScanned?, newItem: FileScanned?) =
      oldItem?.equals(newItem) ?: false
}