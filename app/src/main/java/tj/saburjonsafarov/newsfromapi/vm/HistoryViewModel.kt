package tj.saburjonsafarov.newsfromapi.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tj.saburjonsafarov.newsfromapi.core.BaseViewModel
import tj.saburjonsafarov.newsfromapi.repository.DBHelper
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel

@Deprecated("zameneno na baseFragmentViewModel")
class HistoryViewModel(app:Application):BaseViewModel(app) {

    fun getHistory(): MutableLiveData<ArrayList<EverythingModel.Articles>> {
        val news: MutableLiveData<ArrayList<EverythingModel.Articles>> = MutableLiveData()
        viewModelScope.launch {
            val newData = repository.getNews(DBHelper.History_TABLE)
            Log.d("FDDDDF", newData.size.toString())
            news.postValue(newData)
        }
        return news
    }

}