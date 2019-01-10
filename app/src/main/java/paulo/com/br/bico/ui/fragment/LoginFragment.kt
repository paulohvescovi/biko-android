package paulo.com.br.bico.ui.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.login_fragment.*
import paulo.com.br.bico.R
import paulo.com.br.bico.databinding.LoginFragmentBinding
import paulo.com.br.bico.extensions.hide
import paulo.com.br.bico.extensions.isOnline
import paulo.com.br.bico.extensions.showAlert
import paulo.com.br.bico.extensions.showLoading
import paulo.com.br.bico.service.impl.UsuarioServiceImpl
import paulo.com.br.bico.ui.states.LoginState
import paulo.com.br.bico.viewmodel.LoginViewModel
import paulo.com.br.bico.viewmodelfactory.LoginViewModelFactory

class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

    var dialog: MaterialDialog? = null
    var loginConcluido: (() -> Unit)? = null

    val usuarioService by lazy {
        UsuarioServiceImpl.get(context!!)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: LoginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false)

        this.activity?.application?.let {
            viewModel = createViewModel(it)
            binding.viewModel = viewModel
            binding.setLifecycleOwner(this)
            this.lifecycle.addObserver(viewModel)
        }

        viewModel.loginState.observe(this, Observer { loginState ->
            tratarEstado(loginState)
        })

        return binding.root
    }

    fun logarClick() {
        if (isOnline()){
            viewModel.logar()
        } else {
            dialog = showAlert(dialog, R.string.ops, R.string.sem_conexao)
        }
    }

    fun tratarEstado(estado: LoginState?) {
        when (estado){
            LoginState.LOGARCLICK -> logarClick()

            LoginState.USUARIO_NAO_INFORMADO -> dialog = showAlert(dialog, R.string.validacao, R.string.informe_o_usuario)

            LoginState.SENHA_NAO_INFORMADA -> dialog = showAlert(dialog, R.string.validacao, R.string.informe_a_senha)

            LoginState.LOGANDO -> dialog = showLoading(dialog, R.string.aguarde, R.string.acessando_o_sistema)

            LoginState.USUARIO_SENHA_INVALIDO -> dialog = showAlert(dialog, R.string.validacao, R.string.senha_invalida)

            LoginState.USUARIO_LOGADO_SUCESSO -> {
                direcionarParaTelaInicial()
            }

            LoginState.ERRO_CONEXAO_SERVIDOR -> dialog = showAlert(dialog, R.string.erro, R.string.ocorreu_erro_comunicacao_servidor)
        }
    }

    private fun direcionarParaTelaInicial() {
        usuarioService.salvarSenhaDigitada(this.context!!, edit_text_senha.text.toString())
        hide(dialog)
        loginConcluido?.invoke()
    }

    fun createViewModel(application: Application): LoginViewModel {
        val factory = LoginViewModelFactory(this.context!!, usuarioService, application)

        return ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
    }


}
