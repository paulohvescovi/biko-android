package paulo.com.br.bico.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import paulo.com.br.bico.service.UsuarioService
import paulo.com.br.bico.viewmodel.LoginViewModel

class LoginViewModelFactory constructor(private val usuarioService: UsuarioService, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(LoginViewModel::class.java) ->
                        LoginViewModel(usuarioService, application)
                    else ->
                        throw IllegalArgumentException("Classe desconhecida.")
                }
            } as T
}