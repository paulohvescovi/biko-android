package paulo.com.br.bico.ui.states

enum class LoginState {
    USUARIO_NAO_INFORMADO,
    SENHA_NAO_INFORMADA,
    SEM_INTERNET,
    LOGANDO,
    USUARIO_SENHA_INVALIDO,
    USUARIO_LOGADO_SUCESSO
}