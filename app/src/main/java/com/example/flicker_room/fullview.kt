package com.example.flicker_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.flicker_room.data.Flicker
import com.example.flicker_room.data.FlickerDatabase

class fullview : AppCompatActivity() {

    lateinit var img:ImageView
      var title: String=""
      var link: String=""
    lateinit var back:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullview)
        img=findViewById(R.id.imageView)
         title= intent.getStringExtra("title").toString()
         link= intent.getStringExtra("link").toString()
        back=findViewById(R.id.btnback)
        Glide.with(this)
            .load(link)
            .into(img)

        back.setOnClickListener {
            val info = Intent(this, MainActivity::class.java)
            startActivity(info)
        }
    }

    fun saveimg(title:String,link:String) {
        val ob= FlickerDatabase.getinstant(applicationContext)
            ob.FlikerDao().insertimg(Flicker(0,title,link))
        Toast.makeText(applicationContext,"Image is saved",Toast.LENGTH_SHORT).show()
    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save-> {
                saveimg(title,link)
                return true
            }


        }
        return super.onOptionsItemSelected(item)

    }



}