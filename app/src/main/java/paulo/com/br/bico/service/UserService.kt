package paulo.com.br.bico.service

import android.content.Context
import paulo.com.br.bico.entity.User
import paulo.com.br.bico.model.UserCredentials

interface UserService {

    fun login(usuarioLoginSI: UserCredentials, success: (User) -> Unit, failure: () -> Unit, failureConnection: () -> Unit)

    fun find(success: (User) -> Unit, failure: () -> Unit = {})

    fun salvarSenhaDigitada(context: Context, senha: String)

    fun findSenhaDigitadaSalva(context: Context, success: (String) -> Unit, failure: () -> Unit = {})

}

