package com.example.myapplication.ui.main

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.R.id.*
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.data.entities.FileScanned
import com.example.myapplication.di.MainComponent
import com.example.myapplication.ui.main.di.DaggerHomeComponent
import kotlinx.android.synthetic.main.activity_home.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeContract.View {
  override val layoutId = R.layout.activity_home
  override val menuId = R.menu.main_menu

  @Inject
  lateinit var presenter: HomePresenter

  private var listResults: List<FileScanned>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter.attachView(this)

    initViews()

    savedInstanceState?.let { presenter.refreshPreviousState(it.getParcelableArrayList(LIST_KEY)) }
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      m_main_start -> prepareScanning()
      m_main_stop -> presenter.stopScan()
      m_main_share -> shareItems()
      else -> return super.onOptionsItemSelected(item)
    }
    return true
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    listResults?.let {
      outState?.putParcelableArrayList(LIST_KEY, ArrayList(listResults))
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
  }

  override fun injectDependencies(mainComponent: MainComponent) =
      DaggerHomeComponent.builder()
          .mainComponent(mainComponent)
          .build()
          .inject(this)

  override fun onScanFinished(files: List<FileScanned>) {
    Log.d(TAG, files.toString())
    listResults = files

    a_main_viewpager.adapter = HomeAdapter(files, supportFragmentManager)
    a_main_tab.visibility = View.VISIBLE
    a_main_tab.setupWithViewPager(a_main_viewpager)
  }

  override fun onProgressUpdate(progress: Int) {
    a_main_progress.progress = progress
  }

  override fun showError(error: String) = Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
  override fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

  override fun showProgress() {
    a_main_progress.visibility = View.VISIBLE
  }

  override fun hideProgress() {
    a_main_progress.visibility = View.GONE
  }

  override fun setToolbarTitle(title: String) {
    actionBar?.title = title
  }

  override fun enableSharing() {
    menu?.findItem(m_main_share)?.isVisible = true
  }

  private fun initViews() {
    a_main_toolbar?.let {
      it.setTitleTextColor(Color.WHITE)
      it.title = getString(R.string.app_name)
      setSupportActionBar(it)
    }
  }

  private fun shareItems() {
    Intent().apply {
      action = Intent.ACTION_SEND
      type = "text/plain"
      putExtra(Intent.EXTRA_TEXT, listResults?.toString() ?: "")
    }.let {
      if (it.resolveActivity(packageManager) != null) {
        startActivity(it)
      }
    }
  }

  @AfterPermissionGranted(REQUEST_CODE)
  private fun prepareScanning() {
    if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
      presenter.startScan()
    } else {
      val messageRationale = getString(R.string.permissionRationale)
      EasyPermissions.requestPermissions(this, messageRationale, REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE)
    }
  }

  companion object {
    private const val TAG = "HomeActivityTAG_"
    private const val REQUEST_CODE = 10
    private const val LIST_KEY = "LIST_KEY"
  }
}
