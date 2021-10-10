package com.example.postrequest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewActivity : AppCompatActivity() {

    lateinit var  data : ArrayList<String>
    lateinit var  textview : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        textview =findViewById(R.id.tv)
        data  = ArrayList()
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.adapter = RVAdapter(data)
//        recyclerView.layoutManager = LinearLayoutManager(this)

        getData()

        val btn = findViewById<ImageButton>(R.id.btn)
        btn.setOnClickListener{
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
        private fun getData(){
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            val call: Call<List<Data.Names>> = apiInterface!!.getData()
            call.enqueue(object : Callback<List<Data.Names>> {
                override fun onResponse(
                    call: Call<List<Data.Names>>,
                    response: Response<List<Data.Names>>)
                {
                    var text:String? = "";
                    for(e in response.body()!!){
                        text = text +e.name+ "\n"+e.location + "\n"+"\n"
                    }
                    textview.text= text
                    Toast.makeText(applicationContext,"shown success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<List<Data.Names>>, t: Throwable) {
                    call.cancel()
                    Toast.makeText(applicationContext,"something went wrong", Toast.LENGTH_SHORT).show()
                }
            })
        }
}