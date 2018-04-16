package com.example.myapplication.ui.main

import com.example.myapplication.data.FilesRepository
import com.example.myapplication.data.entities.FileScanned
import com.example.myapplication.di.scopes.PerView
import com.example.myapplication.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerView
class HomePresenter @Inject constructor(private val filesRepository: FilesRepository,
                                        private val schedulerProvider: SchedulerProvider) : HomeContract.Presenter {

  private var view: HomeContract.View? = null
  private val compositeDisposable = CompositeDisposable()

  override fun startScan() {
    view?.showMessage("Scanning started")
    view?.showProgress()

    if (!filesRepository.isSdkCardMounted()) {
      view?.hideProgress()
      view?.showError("No SDK mounted!")
      return
    }

    val disposable = filesRepository.listFilesCallable()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ scanFinished(it) }, { scanFailed(it) })
    compositeDisposable.add(disposable)
  }

  override fun stopScan() {
    view?.hideProgress()
    view?.setToolbarTitle("Scan stopped!")
    compositeDisposable.dispose()
  }

  override fun refreshPreviousState(files: List<FileScanned>?) {
    files?.let {
      scanFinished(files.toMutableList())
    }
  }

  override fun attachView(view: HomeContract.View) {
    this.view = view
  }

  override fun detachView() {
    this.view = null
  }

  private fun scanFailed(throwable: Throwable) {
    view?.hideProgress()
    view?.showError(throwable.localizedMessage)
  }

  private fun scanFinished(listFiles: MutableList<FileScanned>) {
    view?.onProgressUpdate(100)
    view?.enableSharing()
    view?.hideProgress()
    view?.onScanFinished(listFiles)
  }
}