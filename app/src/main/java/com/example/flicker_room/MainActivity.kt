package com.example.flicker_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flicker_room.API.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var images = ArrayList<Photo>()
    lateinit var recycle: RecyclerView
    lateinit var search: EditText
    lateinit var btnsearch: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle = findViewById(R.id.rv)
        search = findViewById(R.id.edsearch)
        //imgv = findViewById(R.id.imageView)
        btnsearch = findViewById(R.id.button)

        updateRecycle()

        btnsearch.setOnClickListener {
            Log.d("RESULT-Wrong", "hi")
            if (edsearch.text.isNotEmpty()) {

                RequestAPI()
                search.text.clear()
                images.clear()
            } else {
                Toast.makeText(applicationContext, "Search is empty", Toast.LENGTH_SHORT).show()
            }

        }
        // imgv.setOnClickListener { closeImg() }
    }

    private fun updateRecycle() {

        recycle.adapter = RVAdapter( images)
        recycle.layoutManager = LinearLayoutManager(this)
    }


    private fun RequestAPI() {
        val apiInterface = APIClinent().GetClient()?.create(APIInterface::class.java)
        val call: Call<photoDetails?>? = apiInterface!!.doGetListResources(search.text.toString())
        call?.enqueue(object : Callback<photoDetails?> {
            override fun onResponse(
                call: Call<photoDetails?>,
                response: Response<photoDetails?>
            ) {
                for (photo in response.body()!!.photos.photo) {
                    images.add(photo)
                }


                recycle.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<photoDetails?>, t: Throwable) {
                call.cancel()
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
        R.id.viewfav->{
        val info = Intent(this, Favimg::class.java)
            startActivity(info)
            return true
        }

    }
    return super.onOptionsItemSelected(item)

}
}