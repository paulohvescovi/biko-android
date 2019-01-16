package paulo.com.br.bico.extensions

import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import paulo.com.br.bico.configuration.Constants

fun AndroidViewModel.postDelayed(run: () -> Unit) {
    Handler().postDelayed(run, Constants.DELAY_POST)
}
