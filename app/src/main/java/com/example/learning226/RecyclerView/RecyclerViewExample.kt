package com.example.learning226.RecyclerView

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class RecyclerViewExample : AppCompatActivity() {
    lateinit var arrayList:ArrayList<RecyclerModel>
    lateinit var recyclerView: RecyclerView
    var adapterRecycler:AdapterRecycler ?= null
    private lateinit var editTextVersionName: EditText
    private lateinit var editTextVersion: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_example)

        arrayList = ArrayList<RecyclerModel>()
        arrayList.add(RecyclerModel("Base", "1.0"))
        arrayList.add(RecyclerModel("Base 1.1", "1.1"))
        arrayList.add(RecyclerModel("Cupcake", "1.5"))
        arrayList.add(RecyclerModel("Donut", "1.6"))
        arrayList.add(RecyclerModel("Eclair", "2.0 - 2.1"))
        arrayList.add(RecyclerModel("Froyo", "2.2 - 2.2.3"))
        arrayList.add(RecyclerModel("Gingerbread", "2.3 - 2.3.7"))
        arrayList.add(RecyclerModel("Honeycomb", "3.0 - 3.2.6"))
        arrayList.add(RecyclerModel("Ice Cream Sandwich", "4.0 - 4.0.4"))
        arrayList.add(RecyclerModel("Jelly Bean", "4.1 - 4.3.1"))
        arrayList.add(RecyclerModel("KitKat", "4.4 - 4.4.4"))
        arrayList.add(RecyclerModel("Lollipop", "5.0 - 5.1.1"))
        arrayList.add(RecyclerModel("Marshmallow", "6.0 - 6.0.1"))
        arrayList.add(RecyclerModel("Nougat", "7.0 - 7.1.2"))
        arrayList.add(RecyclerModel("Oreo", "8.0 - 8.1"))
        arrayList.add(RecyclerModel("Pie", "9.0"))
        arrayList.add(RecyclerModel("Android 10", "10"))
        arrayList.add(RecyclerModel("Android 11", "11"))
        arrayList.add(RecyclerModel("Android 12", "12"))
        arrayList.add(RecyclerModel("Android 13", "13"))
        arrayList.add(RecyclerModel("Android 14", "14"))

        recyclerView = findViewById(R.id.recyclerViewLinear)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        adapterRecycler = AdapterRecycler(this,arrayList)
        recyclerView.adapter = adapterRecycler

        editTextVersionName = findViewById(R.id.editTextVersionName)
        editTextVersion = findViewById(R.id.editTextVersion)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val versionName = editTextVersionName.text.toString()
            val version = editTextVersion.text.toString()
            if (versionName.isNotEmpty() && version.isNotEmpty()) {
                val newItem = RecyclerModel(versionName, version)
                arrayList.add(newItem)
                adapterRecycler?.notifyItemInserted(arrayList.size - 1)
                editTextVersionName.text.clear()
                editTextVersion.text.clear()
            }
        }

    }

    fun deleteItem(position: Int) {
        if (position >= 0 && position < arrayList.size) {
            arrayList.removeAt(position)
            adapterRecycler?.notifyItemRemoved(position)
            // Optional: Notify remaining items to adjust positions if needed
            adapterRecycler?.notifyItemRangeChanged(position, arrayList.size)
        }
    }
}