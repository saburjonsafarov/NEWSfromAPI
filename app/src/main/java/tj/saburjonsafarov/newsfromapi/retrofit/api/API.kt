package tj.saburjonsafarov.newsfromapi.retrofit.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel

interface API {

    @GET("everything")
    fun everything(
        @Query("q") q:String = "Sport",

        @Query("sortBy") sortBy:String = "date",

        @Query("apiKey") apiKey:String = "0caf4422b3a441f58448006d639be030"
    ):Call<EverythingModel>
}