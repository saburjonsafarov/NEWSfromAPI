package tj.saburjonsafarov.newsfromapi.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tj.saburjonsafarov.newsfromapi.core.BaseViewModel
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel

class SavedsViewModel(app: Application) : BaseViewModel(app) {

    fun getNew(table: String): LiveData<ArrayList<EverythingModel.Articles>> {
        val news: MutableLiveData<ArrayList<EverythingModel.Articles>> = MutableLiveData()
        viewModelScope.launch {
            news.postValue(repository.getNews(table))
        }
        return news
    }

}