package tj.saburjonsafarov.newsfromapi.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        val query =
            """
            create table if not exists $FAVORITES_TABLE(
            $ID_COLUMN integer primary key autoincrement,
            $AUTHOR text,
            $TITLE ext,
            $DESCRIPTION text,
            $PUBLISHED_AT text,
            $CONTENT text,
            $URL text,
            $URL_TO_IMAGE text,
            UNIQUE ($TITLE, $URL)

            )
            
        """.trimIndent()


        val query1 =
            """
            create table if not exists $History_TABLE(
            $ID_COLUMN integer primary key autoincrement,
            $AUTHOR text,
            $TITLE ext,
            $DESCRIPTION text,
            $PUBLISHED_AT text,
            $CONTENT text,
            $URL text,
            $URL_TO_IMAGE text,
            UNIQUE ($TITLE, $URL))            
        """.trimIndent()

        db?.execSQL(query)
        db?.execSQL(query1)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}


    @SuppressLint("Range")
    fun saveNews(table:String, articles: EverythingModel.Articles) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(TITLE, articles.title)
        value.put(AUTHOR, articles.author)
        value.put(DESCRIPTION, articles.description)
        value.put(CONTENT, articles.content)
        value.put(PUBLISHED_AT, articles.publishedAt)
        value.put(URL, articles.url)
        value.put(URL_TO_IMAGE, articles.urlToImage)
        db.insert(table, null, value)
        db.close()
    }

    fun getNews(table:String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("select * from $table ORDER BY $ID_COLUMN DESC", null)
    }


    fun delete(table:String, url:String) {
        val db = this.writableDatabase
        db.execSQL("delete from $table where $table.$URL = '$url';")
        db.close()
    }
    fun delete(table:String) {
        val db = this.writableDatabase
        db.execSQL("delete from $table;")
        db.close()
    }

    companion object {
        private const val DATABASE_NAME = "NEWS_DB"
        private const val DATABASE_VERSION = 1
        const val ID_COLUMN = "ID"
        const val FAVORITES_TABLE = "FAVORITES"
        const val History_TABLE = "HISTORY"
        const val AUTHOR = "AUTHOR"
        const val TITLE = "TITLE"
        const val PUBLISHED_AT = "PUBLISHED_AT"
        const val DESCRIPTION = "DESCRIPTION"
        const val CONTENT = "CONTENT"
        const val URL = "URL"
        const val URL_TO_IMAGE = "URL_TO_IMAGE"
    }


}