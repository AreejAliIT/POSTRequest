package com.example.postrequest

import com.google.gson.annotations.SerializedName

 data class Data(val name :String ,val location : String ){

    @SerializedName("data")
    var data : List<Names>? = null

        class Names{
            @SerializedName("name")
            var name :String? = null
            @SerializedName("location")
            var location :String? = null

//            constructor(name: String?, location: String?) {
//                this.name = name
//                this.location = location
//            }
        }
}