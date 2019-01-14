package paulo.com.br.bico.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import paulo.com.br.bico.databinding.ProdutosFragmentBinding

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: ProdutosFragmentBinding = ProdutosFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }

}
