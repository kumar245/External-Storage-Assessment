package com.example.myapplication.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.myapplication.data.entities.FileScanned
import com.example.myapplication.ui.extension.ExtensionFragment
import com.example.myapplication.ui.files.FilesFragment
import com.example.myapplication.ui.info.InfoFragment

class HomeAdapter(private val files: List<FileScanned>,
                  fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

  override fun getItem(position: Int): Fragment {
    require(position in 0..(count)) { "Position needs to be in the range (0..3)" }

    return when (position) {
      0 -> FilesFragment.newInstance(files)
      1 -> InfoFragment.newInstance(files)
      else -> ExtensionFragment.newInstance(files)
    }
  }

  override fun getCount() = 3
  override fun getPageTitle(position: Int) = TAB_TITLES[position]

  companion object {
    private val TAB_TITLES = arrayOf("Largest files", "Info", "Extensions")
  }
}