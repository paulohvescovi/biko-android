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
import paulo.com.br.bico.R
import paulo.com.br.bico.databinding.LoginFragmentBinding
import paulo.com.br.bico.extensions.showAlert
import paulo.com.br.bico.extensions.showLoading
import paulo.com.br.bico.service.UsuarioService
import paulo.com.br.bico.service.impl.UsuarioServiceImpl
import paulo.com.br.bico.ui.states.LoginState
import paulo.com.br.bico.viewmodel.LoginViewModel
import paulo.com.br.bico.viewmodelfactory.LoginViewModelFactory

class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

    var dialog: MaterialDialog? = null
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

    fun tratarEstado(estado: LoginState?): Unit {
        when (estado){
            LoginState.USUARIO_NAO_INFORMADO -> dialog = showAlert(dialog, R.string.validacao, R.string.informe_o_usuario)

            LoginState.SENHA_NAO_INFORMADA -> dialog = showAlert(dialog, R.string.validacao, R.string.informe_a_senha)

            LoginState.LOGANDO -> dialog = showLoading(dialog, R.string.aguarde, R.string.acessando_o_sistema)

            LoginState.USUARIO_SENHA_INVALIDO -> dialog = showAlert(dialog, R.string.validacao, R.string.senha_invalida)

            LoginState.USUARIO_LOGADO_SUCESSO -> dialog = showAlert(dialog, R.string.validacao, R.string.teste)

            LoginState.SEM_INTERNET -> dialog = showAlert(dialog, R.string.erro, R.string.ocorreu_erro_comunicacao_servidor)
        }
    }

    fun createViewModel(application: Application): LoginViewModel {
        val factory = LoginViewModelFactory(usuarioService, application)

        return ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
    }


}
