package paulo.com.br.bico.repository.remote

import kotlinx.coroutines.Deferred
import paulo.com.br.bico.entity.CategorieService
import retrofit2.Response
import retrofit2.http.GET

interface CategorieServiceRemoteRepository {

    @GET("/categorieService/all")
    fun all(): Deferred<Response<List<CategorieService>>>

}