package com.example.myapplication.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApplicationProvider : SchedulerProvider {
  override fun io() = Schedulers.io()
  override fun ui(): Scheduler = AndroidSchedulers.mainThread()
  override fun computation() = Schedulers.computation()
}