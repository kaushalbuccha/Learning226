package com.example.learning226.SQLite

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R
import java.io.ByteArrayOutputStream

class SQLiteMain : AppCompatActivity() {
    private lateinit var addData: Button
    private lateinit var printData: Button
    private lateinit var deleteData: Button
    private lateinit var selectImage: Button
    private lateinit var nameed: EditText
    private lateinit var ageed: EditText
    private lateinit var selectedImageView: ImageView
    private lateinit var userListView: ListView
    private var selectedImageBitmap: Bitmap? = null

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_main)

        addData = findViewById(R.id.sqliteadddata)
        printData = findViewById(R.id.sqliteprintdata)
        deleteData = findViewById(R.id.sqlitedeletedata)
        selectImage = findViewById(R.id.sqliteimagebutton)
        nameed = findViewById(R.id.sqllitenameed)
        ageed = findViewById(R.id.sqlliteageed)
        selectedImageView = findViewById(R.id.sqliteimageview)
        userListView = findViewById(R.id.userlistview)

        val db = SQLiteDBHelper(this, null)

        // Handle image selection
        selectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        // Add data to the database
        addData.setOnClickListener {
            if (selectedImageBitmap == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convert the selected image to byte array
            val byteArrayOutputStream = ByteArrayOutputStream()
            selectedImageBitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()

            val insertionStatus = db.addData(nameed.text.toString(), ageed.text.toString(), imageBytes)

            if (insertionStatus != -1L) {
                Toast.makeText(this, "Inserted: ${nameed.text}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Insertion failed", Toast.LENGTH_SHORT).show()
            }
        }

        // Fetch and display data
        printData.setOnClickListener {
            val cursor = db.fetchData()
            val userList = ArrayList<UserDataModel>()

            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_NAME))
                    val age = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_AGE))
                    val image = cursor.getBlob(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_IMAGE))

                    userList.add(UserDataModel(name, age, image))
                } while (cursor.moveToNext())
            }

            val adapter = UserAdapter(this, userList)
            userListView.adapter = adapter
        }

        // Delete all data
        deleteData.setOnClickListener {
            db.delAll()
            Toast.makeText(this, "Deleted all data", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the result of image selection
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val imageUri: Uri? = data?.data
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            selectedImageView.setImageBitmap(selectedImageBitmap)
        }
    }
}
