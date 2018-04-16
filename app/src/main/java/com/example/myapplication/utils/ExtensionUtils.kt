package com.example.myapplication.utils

import com.example.myapplication.data.entities.Extension
import com.example.myapplication.data.entities.FileScanned

object ExtensionUtils {
  fun ArrayList<FileScanned>.convertToExtensions(): MutableList<Extension>? {
    val newList = mutableListOf<Extension>()
    val map = this.groupingBy { it.extension }.eachCount()
    map.forEach { newList.add(Extension(it.key, it.value)) }
    newList.sortByDescending { it.frequency }
    return newList
  }
}