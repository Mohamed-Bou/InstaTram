package com.example.myapplication.util

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.Ecran2.ImageViewModel
import com.example.myapplication.ui.Ecran2.ImagesAdapter

class SwipToRemove(var adapter: ImagesAdapter,var viewModel: ImageViewModel):ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        val pos=viewHolder.adapterPosition
        adapter.delet(pos,viewModel)
    }
}