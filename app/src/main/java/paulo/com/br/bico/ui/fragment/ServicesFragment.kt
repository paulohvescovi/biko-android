package paulo.com.br.bico.ui.fragment

import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import paulo.com.br.bico.R
import paulo.com.br.bico.databinding.ServicesFragmentBinding
import paulo.com.br.bico.extensions.hide
import paulo.com.br.bico.extensions.showAlert
import paulo.com.br.bico.extensions.showLoading
import paulo.com.br.bico.service.impl.CategorieServiceServiceImpl
import paulo.com.br.bico.ui.adapter.CategorieServiceAdapter
import paulo.com.br.bico.ui.states.FragmentServicesState
import paulo.com.br.bico.viewmodel.ServicesViewModel
import paulo.com.br.bico.viewmodelfactory.ServicesViewModelFactory

class ServicesFragment : Fragment() {

    lateinit var viewModel: ServicesViewModel
    var dialog: MaterialDialog? = null

    companion object {
        fun newInstance() = ServicesFragment()
    }

    val categoriaServiceSerice by lazy {
        CategorieServiceServiceImpl.get(context!!)
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: ServicesFragmentBinding = ServicesFragmentBinding.inflate(inflater, container, false)

        this.activity?.application?.let {
            viewModel = createViewModel(it)
            binding.viewModel = viewModel

            binding.recyclerViewServices.adapter = CategorieServiceAdapter(emptyList())
            binding.recyclerViewServices.layoutManager = LinearLayoutManager(activity)

            binding.setLifecycleOwner(this)
            this.lifecycle.addObserver(viewModel)
        }


        viewModel.fragmentServicesState.observe(this, Observer { state ->
            treatState(state)
        })

        return binding.root

    }

    private fun treatState(state: FragmentServicesState?) {
        when (state){
            FragmentServicesState.SEARCHING_ALL_CATEGORIES -> dialog = showLoading(dialog, message = R.string.loading_categories)
            FragmentServicesState.DONE -> hide(dialog)
            FragmentServicesState.ERROR -> dialog = showAlert(dialog, R.string.ops, R.string.ocorreu_erro_comunicacao_servidor)
        }
    }

    fun createViewModel(application: Application): ServicesViewModel {
        val factory = ServicesViewModelFactory(this.context!!, categoriaServiceSerice, application)

        return ViewModelProviders.of(this, factory).get(ServicesViewModel::class.java)
    }


}
