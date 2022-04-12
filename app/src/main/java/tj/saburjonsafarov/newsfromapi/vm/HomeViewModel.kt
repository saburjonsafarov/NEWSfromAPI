package tj.saburjonsafarov.newsfromapi.vm

import android.app.Application
import androidx.lifecycle.LiveData
import tj.saburjonsafarov.newsfromapi.core.BaseViewModel
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel

class HomeViewModel(app: Application) : BaseViewModel(app) {

    fun get(q:String): LiveData<ArrayList<EverythingModel.Articles>> {
       return getNewsFromRetrofit(q)
    }
}