package tj.saburjonsafarov.newsfromapi.repository.model

import com.google.gson.annotations.SerializedName

data class EverythingModel(

    @SerializedName("status")
    var status: String,

    @SerializedName("totalResults")
    var totalResults: Int,

    @SerializedName("articles")
    var articles: ArrayList<Articles>


) {
    data class Articles(

        @SerializedName("author")
        val author: String?,

        @SerializedName("title")
        var title: String,

        @SerializedName("description")
        var description: String,

        @SerializedName("url")
        var url: String,

        @SerializedName("urlToImage")
        var urlToImage: String,

        @SerializedName("publishedAt")
        var publishedAt: String,

        @SerializedName("content")
        var content: String
    )
}