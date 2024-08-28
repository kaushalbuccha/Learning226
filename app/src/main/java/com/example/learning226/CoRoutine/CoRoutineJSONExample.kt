package com.example.learning226.CoRoutine

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class CoRoutineJSONExample : AppCompatActivity() {
    private lateinit var fetchJsonButton: Button
    private lateinit var jsonTextView: TextView
    private val jsonUrl = "https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine_jsonexample)

        fetchJsonButton = findViewById(R.id.fetchJsonButton)
        jsonTextView = findViewById(R.id.jsonTextView)

        fetchJsonButton.setOnClickListener {
            fetchAndDisplayJson()
        }
    }

    private fun fetchAndDisplayJson() {
        lifecycleScope.launch(Dispatchers.IO) {
            val jsonData = fetchJsonData(jsonUrl)
            val formattedJson = parseAndFormatJsonData(jsonData)
            withContext(Dispatchers.Main) {
                jsonTextView.text = formattedJson
            }
        }
    }

    private suspend fun fetchJsonData(url: String): String? {
        return withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string()
            } else {
                null
            }
        }
    }

    private fun parseAndFormatJsonData(jsonData: String?): String {
        val formattedString = StringBuilder()
        jsonData?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                formattedString.append("ID: ${jsonObject.getInt("id")}\n")
                formattedString.append("Name: ${jsonObject.getString("name")}\n")
                formattedString.append("Email: ${jsonObject.getString("email")}\n\n")
            }
        }
        return formattedString.toString()
    }
}