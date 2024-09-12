package com.example.learning226

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.BroadcastReceiverDemo.AirplaneReceiverActivityDemo
import com.example.learning226.CoRoutine.CoRoutineExample
import com.example.learning226.CoRoutine.CoRoutineImageExample
import com.example.learning226.CustomListViewArrayAdapter.CustomListViewArrayAdapterExample
import com.example.learning226.CustomListViewBaseAdapter.CustomListViewBaseAdapter
import com.example.learning226.DragNDrop.DragDropActivity
import com.example.learning226.DyanmicCustomListView.CustomListViewDemo
import com.example.learning226.DynamicCustomGridView.CustomGridViewDemo
import com.example.learning226.FloatingButtonExample.FloatingButtonDemo
import com.example.learning226.ForegroundServices.ForegroundServicesExample
import com.example.learning226.GridViewExample.CustomGridViewExample
import com.example.learning226.RecyclerGridView.RecyclerGridViewExample
import com.example.learning226.RecyclerView.RecyclerViewExample
import com.example.learning226.SearchActivity.SearchActivity
import com.example.learning226.Shopping.ShoppingMain
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonContainer: LinearLayout = findViewById(R.id.buttonContainer)

        for (i in 0 until buttonContainer.childCount) {
            val view: View = buttonContainer.getChildAt(i)
            if (view is Button) {
                view.setOnClickListener {
                    handleButtonClick(view)
                }
            }
        }

        var headnav:MaterialToolbar = findViewById(R.id.toolbar)
        headnav.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.customListViewArray ->{
                    startActivity(Intent(this,CustomListViewArrayAdapterExample::class.java))
                    true
                }
                R.id.customListViewBase ->{
                    startActivity(Intent(this,CustomListViewBaseAdapter::class.java))
                    true
                }

                else -> false
            }
        }


        var bottomnav: BottomNavigationView = findViewById(R.id.bottom_bar)
        bottomnav.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){

                R.id.home->{
                    startActivity(Intent(this,MainActivity::class.java))
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

    private fun handleButtonClick(button: Button) {
        when (button.id) {
            R.id.customListViewArray -> {
                startActivity(Intent(this,CustomListViewArrayAdapterExample::class.java))
            }
            R.id.customListViewBase -> {
                startActivity(Intent(this,CustomListViewBaseAdapter::class.java))
            }
            R.id.gridView -> {
                startActivity(Intent(this,CustomGridViewExample::class.java))
            }
            R.id.dyanmicListViewBtn ->{
                startActivity(Intent(this,CustomListViewDemo::class.java))
            }
            R.id.dynamicGridViewBtn ->{
                startActivity(Intent(this,CustomGridViewDemo::class.java))
            }
            R.id.recyclerViewBtn ->{
                startActivity(Intent(this,RecyclerViewExample::class.java))
            }
            R.id.recyclerGridView ->{
                startActivity(Intent(this,RecyclerGridViewExample::class.java))
            }
            R.id.dragNDrop ->{
                startActivity(Intent(this,DragDropActivity::class.java))
            }
            R.id.shoopingActivity ->{
                startActivity(Intent(this,ShoppingMain::class.java))
            }
            R.id.coroutines ->{
                startActivity(Intent(this,CoRoutineExample::class.java))
            }
            R.id.foregroundServices->{
                startActivity(Intent(this,ForegroundServicesExample::class.java))
            }
            R.id.broadcastReceiver->{
                startActivity(Intent(this,AirplaneReceiverActivityDemo::class.java))
            }
            R.id.floatingButton->{
                startActivity(Intent(this,FloatingButtonDemo::class.java))
            }
        }
    }
}