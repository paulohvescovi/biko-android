package paulo.com.br.bico.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import paulo.com.br.bico.R
import paulo.com.br.bico.extensions.addFragmentTo
import paulo.com.br.bico.ui.fragment.LoginFragment
import paulo.com.br.bico.ui.fragment.ServicosFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()

        abrirFragmentInicial()
    }

    private fun abrirFragmentInicial() {
        addFragmentTo(R.id.content_frame, createServicosFragment())
    }

    fun createServicosFragment(): ServicosFragment {
        return ServicosFragment.newInstance()
    }

    private fun configureToolbar() {
        appBar.setOutlineProvider(null)
        setSupportActionBar(toolbar)
        setTitle("")
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun closeDrawer(){
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
    }
}
