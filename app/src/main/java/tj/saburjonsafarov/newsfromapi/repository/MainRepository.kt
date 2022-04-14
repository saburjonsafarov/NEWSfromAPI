package tj.saburjonsafarov.newsfromapi.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import tj.saburjonsafarov.newsfromapi.repository.model.CategoryModel
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel.Articles

class MainRepository(var context: Context) {

    companion object {
        val categories = arrayListOf(
            CategoryModel(1, "Sport", true),
            CategoryModel(2, "IPone", true),
            CategoryModel(3, "Politics", true),
            CategoryModel(4, "Business", true),
            CategoryModel(5, "Football", true),
            CategoryModel(6, "Programmers", true),
            CategoryModel(7, "Android developers", true),
            CategoryModel(8, "Samsung", true)
        )
    }


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

}



