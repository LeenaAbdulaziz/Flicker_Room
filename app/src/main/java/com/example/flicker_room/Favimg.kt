package com.example.flicker_room

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flicker_room.data.Flicker
import com.example.flicker_room.data.FlickerDatabase

class Favimg : AppCompatActivity() {
    var title: String = ""
    var link: String = ""

    lateinit var back:Button
    lateinit var recycle: RecyclerView
    lateinit var  ob:FlickerDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favimg)
        recycle = findViewById(R.id.recyclerView)

        back=findViewById(R.id.btnback)

        title = intent.getStringExtra("title").toString()
        link = intent.getStringExtra("link").toString()
        ob=FlickerDatabase.getinstant(applicationContext)

        updateRecycle()



        back.setOnClickListener {
            val info = Intent(this, MainActivity::class.java)
            startActivity(info)
        }




        }
    private fun updateRecycle() {
        val f= ob.FlikerDao().getAllUserInfo()
        recycle.adapter = RVAdapter2( this,f)
        recycle.layoutManager = LinearLayoutManager(this)
    }


     fun confirm(photo: Flicker) {
        var at= AlertDialog.Builder(this)
        at.setTitle("delete Celebrity")
        at.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
            deleteitem(photo)
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        at.show()
    }
    fun deleteitem(photo: Flicker) {
        val ob= FlickerDatabase.getinstant(applicationContext)
        ob.FlikerDao().deleteimg(photo)
        updateRecycle()
        Toast.makeText(applicationContext, "Photo deleted", Toast.LENGTH_SHORT).show()

    }





}






