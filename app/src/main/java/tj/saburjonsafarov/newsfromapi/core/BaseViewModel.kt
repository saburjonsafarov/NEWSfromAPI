package tj.saburjonsafarov.newsfromapi.core

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel
import tj.saburjonsafarov.newsfromapi.repository.MainRepository
import tj.saburjonsafarov.newsfromapi.repository.retrofit.RetrofitInstance

open class BaseViewModel(app: Application) : AndroidViewModel(app) {
    protected var repository: MainRepository = MainRepository(app)

    fun getNewsFromRetrofit(q: String): LiveData<ArrayList<EverythingModel.Articles>> {
        val articles: MutableLiveData<ArrayList<EverythingModel.Articles>> =
            MutableLiveData<ArrayList<EverythingModel.Articles>>()
        RetrofitInstance
            .newInstance()
            .everything(q)
            .enqueue(object : Callback<EverythingModel> {
                override fun onResponse(
                    call: Call<EverythingModel>,
                    response: Response<EverythingModel>
                ) {
                    articles.postValue(response.body()!!.articles)
                }

                override fun onFailure(call: Call<EverythingModel>, t: Throwable) {
                    Log.i("RESPONSE", "google")
                }
            })


        return articles
    }

}