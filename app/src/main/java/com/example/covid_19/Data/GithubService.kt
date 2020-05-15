package com.example.covid_19.Data

import com.example.covid_19.GithubUserItem
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("users")
    fun getUsers(): Call<List<GithubUserItem>>
}