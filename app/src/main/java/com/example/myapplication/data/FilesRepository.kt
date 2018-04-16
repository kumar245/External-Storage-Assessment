package com.example.myapplication.data

import android.content.Context
import android.os.Environment
import com.example.myapplication.data.entities.FileScanned
import io.reactivex.Single
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilesRepository @Inject constructor(private val context: Context) {

  fun isSdkCardMounted() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

  fun listFilesCallable(): Single<MutableList<FileScanned>> = Single.fromCallable { listFiles() }

  private fun listFiles(): MutableList<FileScanned> {
    val dir = Environment.getExternalStorageDirectory()
    val filesList = mutableListOf<FileScanned>()

    traverseDirectory(dir, filesList)

    return filesList
  }

  private fun traverseDirectory(dir: File, filesList: MutableList<FileScanned>) {
    dir.listFiles()?.let {
      it.forEach {
        if (it.isDirectory) {
          traverseDirectory(it, filesList)
        } else {
          val fileScanned = FileScanned(it.name, it.toURI().toString(), it.length(), it.extension)
          filesList.add(fileScanned)
        }
      }
    }
  }
}