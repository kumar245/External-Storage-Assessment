package com.example.myapplication.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FileScanned(val name: String, val path: String, val size: Long, val extension: String) : Parcelable