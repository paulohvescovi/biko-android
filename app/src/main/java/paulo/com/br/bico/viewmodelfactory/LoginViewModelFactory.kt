package paulo.com.br.bico.viewmodelfactory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import paulo.com.br.bico.service.UserService
import paulo.com.br.bico.viewmodel.LoginViewModel

class LoginViewModelFactory constructor(private val context: Context,
                                        private val userService: UserService,
                                        private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(LoginViewModel::class.java) ->
                        LoginViewModel(context, userService, application)
                    else ->
                        throw IllegalArgumentException("Classe desconhecida.")
                }
            } as T
}