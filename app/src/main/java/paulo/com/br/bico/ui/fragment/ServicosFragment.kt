package paulo.com.br.bico.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import paulo.com.br.bico.databinding.ServicosFragmentBinding

class ServicosFragment : Fragment() {

    companion object {
        fun newInstance() = ServicosFragment()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: ServicosFragmentBinding = ServicosFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }

}
