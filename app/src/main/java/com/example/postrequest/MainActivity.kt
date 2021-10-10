package com.example.postrequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etName:EditText
    lateinit var etLoc:EditText
    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        etLoc = findViewById(R.id.etLocation)

        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener{
            CoroutineScope(IO).launch { postData(etName.text.toString() , etLoc.text.toString()) }
            etName.setText("")
            etLoc.setText("")
        }
        val btnView = findViewById<Button>(R.id.btnView)
        btnView.setOnClickListener{
            val intent = Intent(this , ViewActivity::class.java)
            startActivity(intent)
        }
    }
    private suspend fun postData(name:String, loc:String){
         withContext(Dispatchers.Main) {
            apiInterface!!.postData(Data(name , loc)).enqueue(object : Callback<List<Data.Names>> {
                    override fun onResponse(call: Call<List<Data.Names>>, response: Response<List<Data.Names>>) {
                        Toast.makeText(applicationContext, "Added Successfully", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFailure(call: Call<List<Data.Names>>, t: Throwable?) {
                        call.cancel()
                        Toast.makeText(applicationContext, "something went wrong", Toast.LENGTH_SHORT).show()
                    }
                })
      }
    }
}