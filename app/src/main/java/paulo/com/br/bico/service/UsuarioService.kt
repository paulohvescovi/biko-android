package paulo.com.br.bico.service

import android.content.Context
import paulo.com.br.bico.entity.Usuario
import paulo.com.br.bico.model.UserCredentials

interface UsuarioService {

    fun login(usuarioLoginSI: UserCredentials, success: (Usuario) -> Unit, failure: () -> Unit, failureConnection: () -> Unit)

    fun find(success: (Usuario) -> Unit, failure: () -> Unit = {})

    fun salvarSenhaDigitada(context: Context, senha: String)

    fun findSenhaDigitadaSalva(context: Context, success: (String) -> Unit, failure: () -> Unit = {})

}

