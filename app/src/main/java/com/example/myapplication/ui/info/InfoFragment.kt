package com.example.myapplication.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.entities.FileScanned
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_info, container, false)

  @SuppressLint("SetTextI18n")
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    arguments?.getParcelableArrayList<FileScanned>(LIST_KEY)?.let {
      val average = it.map { it.size }.average()
      f_info_average.text = "$average bytes"
      f_info_number.text = it.size.toString()
    }
  }

  companion object {
    private const val LIST_KEY = "LIST_KEY"

    fun newInstance(files: List<FileScanned>): InfoFragment {
      val infoFragment = InfoFragment()

      val arguments = Bundle()
      arguments.putParcelableArrayList(LIST_KEY, ArrayList(files))
      infoFragment.arguments = arguments

      return infoFragment
    }
  }
}
