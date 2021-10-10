package com.example.postrequest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
   // @Headers("Content-Type: application/json")
    @POST("/test/")
    fun postData(@Body data: Data): Call<List<Data.Names>>


    @GET("/test/")
    fun getData(): Call<List<Data.Names>>
}