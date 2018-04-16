package com.example.myapplication.ui.extension

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.entities.FileScanned
import com.example.myapplication.utils.ExtensionUtils.convertToExtensions
import kotlinx.android.synthetic.main.fragment_extension.*

class ExtensionFragment : Fragment() {

  private val extensionAdapter = ExtensionAdapter()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_extension, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    f_extension_recycler.adapter = extensionAdapter
    f_extension_recycler.layoutManager = LinearLayoutManager(context)
    f_extension_recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    arguments?.getParcelableArrayList<FileScanned>(LIST_KEY)?.let {
      extensionAdapter.submitList(it.convertToExtensions())
    }
  }

  companion object {
    private const val LIST_KEY = "LIST_KEY"

    fun newInstance(files: List<FileScanned>): ExtensionFragment {
      val extensionFragment = ExtensionFragment()

      val arguments = Bundle()
      arguments.putParcelableArrayList(LIST_KEY, ArrayList(files))
      extensionFragment.arguments = arguments

      return extensionFragment
    }
  }
}