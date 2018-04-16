package com.example.myapplication.ui.main

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.base.BaseView
import com.example.myapplication.data.entities.FileScanned

interface HomeContract {
  interface View : BaseView {
    fun onScanFinished(files: List<FileScanned>)
    fun onProgressUpdate(progress: Int)
    fun showProgress()
    fun hideProgress()
    fun setToolbarTitle(title: String)
    fun enableSharing()
  }

  interface Presenter : BasePresenter<View> {
    fun startScan()
    fun stopScan()
    fun refreshPreviousState(files: List<FileScanned>?)
  }
}