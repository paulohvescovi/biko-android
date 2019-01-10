package paulo.com.br.bico.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import paulo.com.br.bico.entity.Usuario
import paulo.com.br.bico.model.UserCredentials
import paulo.com.br.bico.service.UsuarioService
import paulo.com.br.bico.ui.states.LoginState

class LoginViewModel (val context: Context, val usuarioService: UsuarioService, application: Application) :
    AndroidViewModel(application), LifecycleObserver {

    var usuario = MutableLiveData<Usuario>().apply { value = Usuario() }
    var loginState = MutableLiveData<LoginState>().apply { value = null }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {
        usuarioService.find(success = { user ->
            usuarioService.findSenhaDigitadaSalva(context, success = {
                user.senha = it
                usuario.value = user
            })
            usuario.value = user
        })
    }

    fun logarClick():Unit {
        loginState.value = LoginState.LOGARCLICK
    }

    fun logar():Unit {
        if (usuario.value!!.email.isNullOrBlank()){
            loginState.value = LoginState.USUARIO_NAO_INFORMADO
            return
        }

        if (usuario.value!!.senha.isNullOrBlank()){
            loginState.value = LoginState.SENHA_NAO_INFORMADA
            return
        }

        loginState.value = LoginState.LOGANDO

        var u = UserCredentials(usuario.value!!.email, usuario.value!!.senha)

        usuarioService.login(u, success = {
            loginState.value = LoginState.USUARIO_LOGADO_SUCESSO
        }, failure = {
            loginState.value = LoginState.USUARIO_SENHA_INVALIDO
        }, failureConnection = {
            loginState.value = LoginState.ERRO_CONEXAO_SERVIDOR
        })

    }

}