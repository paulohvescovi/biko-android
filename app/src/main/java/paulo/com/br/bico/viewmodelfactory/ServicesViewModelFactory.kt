package paulo.com.br.bico.viewmodelfactory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import paulo.com.br.bico.service.CategorieServiceService
import paulo.com.br.bico.viewmodel.ServicesViewModel


class ServicesViewModelFactory constructor(private val context: Context,
                                        private val categorieServiceService: CategorieServiceService,
                                        private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(ServicesViewModel::class.java) ->
                        ServicesViewModel(context, categorieServiceService, application)
                    else ->
                        throw IllegalArgumentException("Classe desconhecida.")
                }
            } as T
}