package paulo.com.br.bico.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import paulo.com.br.bico.R
import paulo.com.br.bico.extensions.addFragmentTo
import paulo.com.br.bico.extensions.fullScreen
import paulo.com.br.bico.ui.fragment.LoginFragment

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        fullScreen()

        val fragment = createFragment()
        addFragmentTo(R.id.content_frame, fragment)
    }

    fun createFragment(): LoginFragment {
        return LoginFragment.newInstance()
    }

}
