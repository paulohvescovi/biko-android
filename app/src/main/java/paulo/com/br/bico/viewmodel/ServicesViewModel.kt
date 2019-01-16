package paulo.com.br.bico.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import paulo.com.br.bico.entity.CategorieService
import paulo.com.br.bico.entity.CategorieServiceFull
import paulo.com.br.bico.extensions.postDelayed
import paulo.com.br.bico.service.CategorieServiceService
import paulo.com.br.bico.ui.states.FragmentServicesState


class ServicesViewModel (val context: Context, val categorieServiceService: CategorieServiceService, application: Application) :
        AndroidViewModel(application), LifecycleObserver {

    var allCategories = MutableLiveData<List<CategorieServiceFull>>().apply { value = ArrayList() }
    var fragmentServicesState = MutableLiveData<FragmentServicesState>().apply { value = null }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        postDelayed({load()})
    }

    fun load(){
        fragmentServicesState.value = FragmentServicesState.SEARCHING_ALL_CATEGORIES
        categorieServiceService.allRemote(success = {
            allCategories.value = it
            fragmentServicesState.value = FragmentServicesState.DONE
        }, failure = {
            fragmentServicesState.value = FragmentServicesState.ERROR
        })
    }

}