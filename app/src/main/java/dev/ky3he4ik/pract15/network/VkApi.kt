package dev.ky3he4ik.pract15.network

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface VkApi {
    @POST("method/users.get")
    fun getUserInfo(
        @Query("access_token") token: String,
        @Query("v") version: String = "5.131",
    ): Call<VkResponse>
}
