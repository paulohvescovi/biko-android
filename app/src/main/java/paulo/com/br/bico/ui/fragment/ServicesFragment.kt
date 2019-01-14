package paulo.com.br.bico.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import paulo.com.br.bico.databinding.ServicesFragmentBinding

class ServicesFragment : Fragment() {

    companion object {
        fun newInstance() = ServicesFragment()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: ServicesFragmentBinding = ServicesFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }

}
