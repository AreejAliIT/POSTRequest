package com.example.postrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    lateinit var etPk: EditText
    lateinit var etName: EditText
    lateinit var etLoc: EditText
    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        etPk = findViewById(R.id.etPk)
        etName = findViewById(R.id.etName)
        etLoc = findViewById(R.id.etLocation)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDel = findViewById<Button>(R.id.btnDel)

        btnUpdate.setOnClickListener{

                updateData(etPk.text.toString().toInt(),etName.text.toString() , etLoc.text.toString())
            etPk.setText("")
            etName.setText("")
            etLoc.setText("")
        }
        btnDel.setOnClickListener{
            deleteData(etPk.text.toString().toInt())
            etPk.setText("")
            etName.setText("")
            etLoc.setText("")
        }
    }

    private fun deleteData(pk: Int) {
        apiInterface!!.deleteData(pk).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@UpdateActivity, "deleted Successfully", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<Void>, t: Throwable?) {
                call.cancel()
                Toast.makeText(this@UpdateActivity, "something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateData(pk: Int, name: String, loc: String) {
        apiInterface!!.updateData( pk, Data(name, loc)).enqueue(object : Callback<List<Data.Names>> {
            override fun onResponse(call: Call<List<Data.Names>>, response: Response<List<Data.Names>>) {

                Toast.makeText(this@UpdateActivity, "updated Successfully", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<List<Data.Names>>, t: Throwable?) {
                call.cancel()
                Toast.makeText(this@UpdateActivity, "something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

}