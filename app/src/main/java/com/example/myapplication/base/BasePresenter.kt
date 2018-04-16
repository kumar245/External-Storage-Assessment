package com.example.myapplication.base

interface BasePresenter<in V : BaseView> {
  fun attachView(view: V)
  fun detachView()
}