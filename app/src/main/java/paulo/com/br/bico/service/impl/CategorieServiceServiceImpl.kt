package paulo.com.br.bico.service.impl

import android.content.Context
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import paulo.com.br.bico.configuration.Constants
import paulo.com.br.bico.configuration.CoroutinesScope
import paulo.com.br.bico.entity.CategorieServiceFull
import paulo.com.br.bico.repository.local.AppDatabase
import paulo.com.br.bico.repository.remote.CategorieServiceRemoteRepository
import paulo.com.br.bico.repository.remote.configuration.WebserviceApi
import paulo.com.br.bico.service.CategorieServiceService

class CategorieServiceServiceImpl private constructor(context: Context): CategorieServiceService, CoroutinesScope() {


    val categorieRemoteRepository = WebserviceApi.get().createService(CategorieServiceRemoteRepository::class.java)
    val caregorieLocalRepository = AppDatabase.get(context).categorieServiceDao()

    companion object {
        fun get(context: Context): CategorieServiceServiceImpl {
            return CategorieServiceServiceImpl(context)
        }
    }

    override fun allRemote(success: (List<CategorieServiceFull>) -> Unit, failure: () -> Unit) {
        scopeAsync.async {
            try {
                val requestAllCategories = categorieRemoteRepository.all()
                val responseAllCategories = requestAllCategories.await()

                if (responseAllCategories.code() == Constants.HTTPCODE_403){
                    scopeUI.launch {
                        failure()
                    }
                } else {
                    scopeUI.launch {
                        val result = responseAllCategories.body()!!.map {
                            CategorieServiceFull(it, it.subCategoriaList)
                        }
                        success(result)
                    }
                }
            } catch (e: Throwable) {
                scopeUI.launch {
                    failure()
                }
            }
        }
    }

    override fun allLocal(success: (List<CategorieServiceFull>) -> Unit, failure: () -> Unit) {
        scopeAsync.async {
            try {
                val all = caregorieLocalRepository.all()
                scopeUI.launch { success(all) }
            } catch (e: Throwable){
                failure()
            }
        }
    }
}