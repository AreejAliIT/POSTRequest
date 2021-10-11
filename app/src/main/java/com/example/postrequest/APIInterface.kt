package com.example.postrequest

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
   // @Headers("Content-Type: application/json")
    @POST("/test/")
    fun postData(@Body data: Data): Call<List<Data.Names>>


    @GET("/test/")
    fun getData(): Call<List<Data.Names>>

    @PUT("/test/{id}")
    fun updateData(@Path("id") id:Int , @Body data: Data): Call <List<Data.Names>>

    @DELETE("/test/{id}")
    fun deleteData(@Path("id") id:Int): Call <Void>


}