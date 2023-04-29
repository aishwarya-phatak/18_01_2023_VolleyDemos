package com.example.a18_01_2023_volleydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.a18_01_2023_volleydemo.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var users = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        VolleySingleton.initializeRequestQueue(this)
        binding.btnCreateUser.setOnClickListener {
            addUser()
        }

        binding.btnStringRequest.setOnClickListener {

        }

        binding.btnJSONObjectRequest.setOnClickListener {
                getAllUsers()
        }
    }
    private fun getAllUsers(){
        var jsonObjectRequestToGetAllUsers = JsonObjectRequest(
            Request.Method.GET,
            "https://reqres.in/api/users?page=2",
            null,
            JSONObjectResponseSuccessListener(),
            StringRequestErrorListener()
        )
        VolleySingleton.volleyRequestQueue?.add(jsonObjectRequestToGetAllUsers)
    }

    inner class JSONObjectResponseSuccessListener : Response.Listener<JSONObject>{
        override fun onResponse(response: JSONObject?) {
           //Log.e("tag","${response.toString()}")
            var userResponse = Gson().fromJson<UserResponse>(
                response.toString(),
                UserResponse::class.java
            )
            users.addAll(userResponse.users)
            for(eachUser in users){
                Log.e("tag","${eachUser.toString()}")
            }
        }
    }

    private fun addUser(){
        var inputJSONObject = JSONObject()
        inputJSONObject.put("username",binding.edtUsername.text.toString())
        inputJSONObject.put("password",binding.edtPassword.text.toString())
        var jsonObjectRequestQueue = JsonObjectRequest(
            Request.Method.POST,
            "https://reqres.in/api/register",
            inputJSONObject,
            AddUserListener(),
            StringRequestErrorListener()
        )
        VolleySingleton.volleyRequestQueue?.add(jsonObjectRequestQueue)
    }


    class AddUserListener : Response.Listener<JSONObject>{
        override fun onResponse(response: JSONObject?) {
            Log.e("tag","${response.toString()}")
        }

    }

    class StringRequestErrorListener : Response.ErrorListener{
        override fun onErrorResponse(error: VolleyError?) {
           Log.e("tag","${error.toString()}")
        }
    }
}