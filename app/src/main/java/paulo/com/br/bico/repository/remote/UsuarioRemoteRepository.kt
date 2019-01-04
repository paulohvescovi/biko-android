package paulo.com.br.bico.repository.remote

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import paulo.com.br.bico.entity.Usuario
import paulo.com.br.bico.model.UserCredentials
import retrofit2.Response
import retrofit2.http.*

interface UsuarioRemoteRepository {

    @GET("/home")
    fun home(): Deferred<Response<Usuario>>

    @POST("/login")
    @Headers("Content-Type: application/json")
    fun login(@Body usuario: UserCredentials): Deferred<Response<ResponseBody>>

    @GET("/usuario/usuario")
    fun usuarioLogadoServidor(@Header("Authorization") authorization: String): Deferred<Response<Usuario>>

}