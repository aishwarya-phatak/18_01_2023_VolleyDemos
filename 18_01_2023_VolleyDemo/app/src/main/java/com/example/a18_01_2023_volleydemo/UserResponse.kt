package com.example.a18_01_2023_volleydemo

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("page")
    var pageNumber : Int,
    @SerializedName("per_page")
    var userCountPerPage : Int,
    @SerializedName("total")
    var totalUserCount : Int,
    @SerializedName("total_pages")
    var totalPages : Int,
    @SerializedName("data")
    var users : ArrayList<User>
        ){
    override fun toString(): String {
        return "page -- $pageNumber"
    }
}
