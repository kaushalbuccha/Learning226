package com.example.learning226.RecyclerGridView

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class RecyclerGridViewExample : AppCompatActivity() {
    lateinit var arrayList: ArrayList<RecyclerGridModel>
    lateinit var recyclerView: RecyclerView
    var adapterRecycler:AdapterRecyclerGrid? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_grid_view_example)

        gridListData()
        initRecyclerView()
    }

    private fun gridListData() {
        arrayList = ArrayList<RecyclerGridModel>()
        arrayList.add(RecyclerGridModel("Facebook","Social",R.drawable.fb))
        arrayList.add(RecyclerGridModel("Instagram","Social",R.drawable.instagram))
        arrayList.add(RecyclerGridModel("Twitter","Social",R.drawable.twitter))
        arrayList.add(RecyclerGridModel("Whatsapp","Social",R.drawable.whatsapp))
        arrayList.add(RecyclerGridModel("Telegram","Social",R.drawable.telegram))
        arrayList.add(RecyclerGridModel("Youtube","Social",R.drawable.yt))
        arrayList.add(RecyclerGridModel("Snapchat","Social",R.drawable.snapchat))
        arrayList.add(RecyclerGridModel("LinkedIn","Social",R.drawable.linkedin))
        arrayList.add(RecyclerGridModel("Skype","Social",R.drawable.skype))
    }

    private fun initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerViewGrid)
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this,2)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.setLayoutManager(gridLayoutManager)
        adapterRecycler = AdapterRecyclerGrid(this,arrayList)
        recyclerView.setAdapter(adapterRecycler)
    }
}