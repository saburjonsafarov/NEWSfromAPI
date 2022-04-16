package tj.saburjonsafarov.newsfromapi.repository.retrofit.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel

interface API {

    @GET("everything")
    fun everything(
        @Query("q") q:String = "Sport",

        @Query("sortBy") sortBy:String = "date",

        @Query("apiKey") apiKey:String = "2479cd94f1b64d7f86727f1343a282db"
    ):Call<EverythingModel>
}