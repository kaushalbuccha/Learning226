package com.example.learning226.SearchActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.MainActivity
import com.example.learning226.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class SearchActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })


        var bottomnav: BottomNavigationView = findViewById(R.id.bottom_bar)
        bottomnav.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){

                R.id.home->{
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.search->{
                    startActivity(Intent(this,SearchActivity::class.java))
                    true
                }
                R.id.exit->{
                    finish()
                    true
                }
                else-> false
            }
        }
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(LanguageData("Java", R.drawable.ic_launcher_foreground))
        mList.add(LanguageData("Kotlin", R.drawable.ic_launcher_foreground))
        mList.add(LanguageData("C++", R.drawable.ic_launcher_foreground))
        mList.add(LanguageData("Python", R.drawable.ic_launcher_foreground))
        mList.add(LanguageData("HTML", R.drawable.ic_launcher_background))
        mList.add(LanguageData("Swift", R.drawable.ic_launcher_background))
        mList.add(LanguageData("C#", R.drawable.ic_launcher_background))
        mList.add(LanguageData("JavaScript", R.drawable.ic_launcher_background))
    }
}