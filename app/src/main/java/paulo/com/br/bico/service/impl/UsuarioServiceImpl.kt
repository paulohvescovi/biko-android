package paulo.com.br.bico.service.impl

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import paulo.com.br.bico.configuration.Constants
import paulo.com.br.bico.configuration.CoroutinesScope
import paulo.com.br.bico.entity.Usuario
import paulo.com.br.bico.model.UserCredentials
import paulo.com.br.bico.repository.remote.UsuarioRemoteRepository
import paulo.com.br.bico.repository.remote.WebserviceApi
import paulo.com.br.bico.service.UsuarioService
import retrofit2.HttpException

class UsuarioServiceImpl private constructor(): UsuarioService, CoroutinesScope() {

    val usuarioRemoteRepository = WebserviceApi.get().createService(UsuarioRemoteRepository::class.java)

    companion object {
        fun get(): UsuarioServiceImpl {
            return UsuarioServiceImpl()
        }
    }

    override fun login(usuarioLoginSI: UserCredentials, success: (Usuario) -> Unit, failure: () -> Unit, failureConnection: () -> Unit) {
        scopeAsync.async {
            try {
                val requestLogin = usuarioRemoteRepository.login(usuarioLoginSI)
                val responseLogin = requestLogin.await()

                if (responseLogin.code() == Constants.HTTPCODE_403){
                    scopeUI.launch {
                        failure()
                    }
                } else {
                    val requestUsuarioLogado = usuarioRemoteRepository.usuarioLogadoServidor(responseLogin.body()!!.string())
                    val responseUsuarioLogado = requestUsuarioLogado.await()

                    scopeUI.launch {
                        success(responseUsuarioLogado.body()!!)
                    }

                }
            } catch (e: Throwable) {
                scopeUI.launch {
                    failureConnection()
                }
            }
        }
    }

}