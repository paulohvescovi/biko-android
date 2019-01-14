package paulo.com.br.bico.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import paulo.com.br.bico.R
import paulo.com.br.bico.configuration.Constants
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

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        (childFragment as? LoginFragment).let {
            it?.loginConcluido = {
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent, Constants.EXIT_APP, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.EXIT_APP){
            this@LoginActivity.finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
