package paulo.com.br.bico.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import paulo.com.br.bico.entity.User
import paulo.com.br.bico.model.UserCredentials
import paulo.com.br.bico.service.UserService
import paulo.com.br.bico.ui.states.FragmentLoginState

class LoginViewModel (val context: Context, val userService: UserService, application: Application) :
    AndroidViewModel(application), LifecycleObserver {

    var usuario = MutableLiveData<User>().apply { value = User() }
    var loginState = MutableLiveData<FragmentLoginState>().apply { value = null }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {
        userService.find(success = { user ->
            userService.findSenhaDigitadaSalva(context, success = {
                user.senha = it
                usuario.value = user
            })
            usuario.value = user
        })
    }

    fun logarClick():Unit {
        loginState.value = FragmentLoginState.LOGARCLICK
    }

    fun logar():Unit {
        if (usuario.value!!.email.isNullOrBlank()){
            loginState.value = FragmentLoginState.USUARIO_NAO_INFORMADO
            return
        }

        if (usuario.value!!.senha.isNullOrBlank()){
            loginState.value = FragmentLoginState.SENHA_NAO_INFORMADA
            return
        }

        loginState.value = FragmentLoginState.LOGANDO

        var u = UserCredentials(usuario.value!!.email, usuario.value!!.senha)

        userService.login(u, success = {
            loginState.value = FragmentLoginState.USUARIO_LOGADO_SUCESSO
        }, failure = {
            loginState.value = FragmentLoginState.USUARIO_SENHA_INVALIDO
        }, failureConnection = {
            loginState.value = FragmentLoginState.ERRO_CONEXAO_SERVIDOR
        })

    }

}