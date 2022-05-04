package dev.ky3he4ik.pract15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.ky3he4ik.pract15.databinding.ActivityMainBinding
import dev.ky3he4ik.pract15.network.VkApi
import dev.ky3he4ik.pract15.network.VkResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val API_SERVER_URL = "https://api.vk.com/"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val service = Retrofit.Builder().baseUrl(API_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(VkApi::class.java)

        binding.getInfo.setOnClickListener {
            service.getUserInfo(BuildConfig.VK_AUTH_KEY).enqueue(
                object : Callback<VkResponse?> {
                    override fun onFailure(call: Call<VkResponse?>, t: Throwable) {
                        binding.userId.text = "Error"
                    }

                    override fun onResponse(
                        call: Call<VkResponse?>,
                        response: Response<VkResponse?>
                    ) {
                        val id = response.body()?.response?.getOrNull(0)?.id
                        if (id == 0)
                            binding.userId.text = "Error"
                        else
                            binding.userId.text = "User id: $id"
                    }
                })
        }
    }
}
