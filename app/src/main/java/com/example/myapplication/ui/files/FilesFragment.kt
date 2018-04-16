package com.example.myapplication.ui.files

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.entities.FileScanned
import kotlinx.android.synthetic.main.fragment_files.*

class FilesFragment : Fragment() {

  private val filesAdapter = FilesAdapter()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_files, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    f_files_recycler.adapter = filesAdapter
    f_files_recycler.layoutManager = LinearLayoutManager(context)
    f_files_recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    arguments?.getParcelableArrayList<FileScanned>(LIST_KEY)?.let {
      it.sortByDescending { it.size }
      filesAdapter.submitList(it)
    }
  }

  companion object {
    private const val LIST_KEY = "LIST_KEY"

    fun newInstance(files: List<FileScanned>): FilesFragment {
      val filesFragment = FilesFragment()

      val arguments = Bundle()
      arguments.putParcelableArrayList(LIST_KEY, ArrayList(files))
      filesFragment.arguments = arguments

      return filesFragment
    }
  }
}