package tj.saburjonsafarov.newsfromapi.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tj.saburjonsafarov.newsfromapi.repository.retrofit.api.API

class RetrofitInstance {
    companion object {
        fun newInstance(): API {
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(API::class.java)
        }
    }
}