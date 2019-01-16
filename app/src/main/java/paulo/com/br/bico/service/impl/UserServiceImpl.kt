package paulo.com.br.bico.service.impl

import android.content.Context
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import paulo.com.br.bico.configuration.Constants
import paulo.com.br.bico.configuration.CoroutinesScope
import paulo.com.br.bico.entity.User
import paulo.com.br.bico.model.UserCredentials
import paulo.com.br.bico.repository.local.AppDatabase
import paulo.com.br.bico.repository.local.SharedPreferences
import paulo.com.br.bico.repository.remote.UserRemoteRepository
import paulo.com.br.bico.repository.remote.configuration.WebserviceApi
import paulo.com.br.bico.service.UserService

class UserServiceImpl private constructor(context: Context): UserService, CoroutinesScope() {

    val usuarioRemoteRepository = WebserviceApi.get().createService(UserRemoteRepository::class.java)
    val usuarioLocalRepository = AppDatabase.get(context).usuarioDao()

    companion object {
        lateinit var token: String

        fun get(context: Context): UserServiceImpl {
            return UserServiceImpl(context)
        }
    }

    override fun salvarSenhaDigitada(context: Context, senha: String) {
        scopeAsync.async {
            SharedPreferences.save(context, Constants.SENHA_SALVA, senha)
        }
    }

    override fun findSenhaDigitadaSalva(context: Context, success: (String) -> Unit, failure: () -> Unit) {
        scopeAsync.async {
            try {
                val senha = SharedPreferences.get(context, Constants.SENHA_SALVA)
                scopeUI.launch { success(senha) }
            } catch (e: Throwable){
                failure()
            }
        }
    }

    override fun find(success: (User) -> Unit, failure: () -> Unit) {
        scopeAsync.async {
            val usuario = try { usuarioLocalRepository.find() } catch (e: Exception){ null }
            scopeUI.launch {
                if (usuario != null) success(usuario) else failure()
            }
        }
    }

    override fun login(usuarioLoginSI: UserCredentials, success: (User) -> Unit, failure: () -> Unit, failureConnection: () -> Unit) {
        scopeAsync.async {
            try {
                val requestLogin = usuarioRemoteRepository.login(usuarioLoginSI)
                val responseLogin = requestLogin.await()

                if (responseLogin.code() == Constants.HTTPCODE_403){
                    scopeUI.launch {
                        failure()
                    }
                } else {
                    token = responseLogin.body()!!.string()

                    val requestUsuarioLogado = usuarioRemoteRepository.usuarioLogadoServidor()
                    val responseUsuarioLogado = requestUsuarioLogado.await()

                    val usuario = responseUsuarioLogado.body()!!
                    usuario.id = 1

                    usuarioLocalRepository.save(usuario)

                    scopeUI.launch {
                        success(usuario)
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