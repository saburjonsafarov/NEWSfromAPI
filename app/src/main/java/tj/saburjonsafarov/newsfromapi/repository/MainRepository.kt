package tj.saburjonsafarov.newsfromapi.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel.Articles
import tj.saburjonsafarov.newsfromapi.repository.retrofit.RetrofitInstance

class MainRepository(var context: Context) {
    val db = DBHelper(context)

    fun saveNew(table: String, data: Articles) {
        db.saveNews(table, data)
    }

    @SuppressLint("Range")
    fun getNews(table: String): ArrayList<Articles> {
        val arrayList = ArrayList<Articles>()
        val cursor = db.getNews(table)
        cursor!!.moveToFirst()

        Log.i("CURSOR", cursor.toString())

        if (cursor.moveToFirst()) {

            do {
                arrayList.add(
                    Articles(
                        cursor.getString(cursor.getColumnIndex(DBHelper.AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.TITLE)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.URL)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.URL_TO_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.PUBLISHED_AT)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.CONTENT))
                    )
                )
            } while (cursor.moveToNext())
            cursor.close()
        }


        return arrayList
    }

    fun saveNew(): LiveData<ArrayList<Articles>> {

        val datas: MutableLiveData<ArrayList<Articles>> = MutableLiveData<ArrayList<Articles>>()
        RetrofitInstance
            .newInstance()
            .everything()
            .enqueue(object : Callback<EverythingModel> {
                override fun onResponse(
                    call: Call<EverythingModel>,
                    response: Response<EverythingModel>
                ) {
                    val newdata = response.body()?.articles!!
                    datas.postValue(newdata)
                }

                override fun onFailure(call: Call<EverythingModel>, t: Throwable) {
                    Log.d("RES", "RES")
                }

            })
        return datas
    }

}


//
//@SuppressLint("Range")
//fun getNews(): ArrayList<DetailModel> {
//    val articles = ArrayList<DetailModel>()
//    val cursor = db.getNews()
//    cursor!!.moveToFirst()
//    while (cursor.moveToNext()) {
//        articles.add(
//            DetailModel(
//                cursor.getString(cursor.getColumnIndex(DBHelper.AUTHOR)),
//                cursor.getString(cursor.getColumnIndex(DBHelper.TITLE)),
//                cursor.getString(cursor.getColumnIndex(DBHelper.DESCRIPTION)),
//                cursor.getString(cursor.getColumnIndex(DBHelper.PUBLISHED_AT)),
//                cursor.getString(cursor.getColumnIndex(DBHelper.URL)),
//                cursor.getString(cursor.getColumnIndex(DBHelper.URL_TO_IMAGE)),
//                cursor.getString(cursor.getColumnIndex(DBHelper.CONTENT))
//            )
//        )
//    }

//    cursor.close()
//    return articles
//
//}
