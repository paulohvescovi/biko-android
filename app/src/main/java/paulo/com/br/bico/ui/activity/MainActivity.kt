package paulo.com.br.bico.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import paulo.com.br.bico.R
import paulo.com.br.bico.configuration.Constants
import paulo.com.br.bico.extensions.addFragmentTo
import paulo.com.br.bico.ui.fragment.AnnounceFragment
import paulo.com.br.bico.ui.fragment.ProductsFragment
import paulo.com.br.bico.ui.fragment.ServicesFragment
import paulo.com.br.bico.util.AlertUtil


class MainActivity : AppCompatActivity() {

    var dialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()
        configureNavigation()
        openInicialFragment()

    }

    private fun configureNavigation() {
        button_profile.setOnClickListener {

        }
        button_products.setOnClickListener {
            openFragment(fragmentProducts())
        }
        button_services.setOnClickListener {
            openFragment(fragmentServices())
        }
        button_announce.setOnClickListener {
            openFragment(fragmentAnnounce())
        }
        button_logout.setOnClickListener {
            closeDrawer()
            AlertUtil.show(this@MainActivity, dialog, R.string.atencao, R.string.deseja_sair_do_sistema, positiveListener = {
                setResult(Constants.EXIT_APP)
                this@MainActivity.finish()
                //TODO CHAMAR O RESULT DA LOGIN
            })
        }
    }

    override fun onBackPressed() {
        //TODO implementar navegação dos fragments com o botao de voltar
    }

    private fun fragmentProducts(): Fragment {
        hideMenuItemSelectedIndicator()
        showMenuItemSelectedIndicator(image_products_selected)
        return ProductsFragment.newInstance()
    }

    private fun fragmentAnnounce(): Fragment {
        hideMenuItemSelectedIndicator()
        showMenuItemSelectedIndicator(image_announce_selected)
        return AnnounceFragment.newInstance()
    }

    private fun fragmentServices(): Fragment {
        hideMenuItemSelectedIndicator()
        showMenuItemSelectedIndicator(image_services_selected)
        return ServicesFragment.newInstance()
    }

    private fun showMenuItemSelectedIndicator(image_view_selected: ImageView){
        image_view_selected.visibility = View.VISIBLE
    }

    private fun hideMenuItemSelectedIndicator(){
        image_profile_selected.visibility = View.GONE
        image_announce_selected.visibility = View.GONE
        image_products_selected.visibility = View.GONE
        image_services_selected.visibility = View.GONE
    }

    private fun openInicialFragment() {
        openFragment(ServicesFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        addFragmentTo(R.id.content_frame, fragment)
        closeDrawer()
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
