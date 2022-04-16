package tj.saburjonsafarov.newsfromapi.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tj.saburjonsafarov.newsfromapi.core.BaseViewModel
import tj.saburjonsafarov.newsfromapi.repository.model.CategoryModel

class SettingsViewModel(app: Application) : BaseViewModel(app) {

//    fun getCategory(): LiveData<ArrayList<CategoryModel>> {
//        val category: MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData<ArrayList<CategoryModel>>()
//        viewModelScope.launch {
//            category.postValue(repository.getCategory())
//        }
//        return category
//    }

}