package com.example.myapplication.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.myapplication.FilesApplication
import com.example.myapplication.di.MainComponent

abstract class BaseActivity : AppCompatActivity() {

  open val menuId: Int? = null
  abstract val layoutId: Int

  var menu: Menu? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutId)

    injectDependencies((application as FilesApplication).mainComponent)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    this.menu = menu
    menuId?.let {
      menuInflater?.inflate(it, menu)
      return true
    }
    return super.onCreateOptionsMenu(menu)
  }

  abstract fun injectDependencies(mainComponent: MainComponent)
}