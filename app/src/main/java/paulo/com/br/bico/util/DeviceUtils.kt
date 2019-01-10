package paulo.com.br.bico.util

import android.content.Context
import android.net.ConnectivityManager


class DeviceUtils {

    // TODO http://useof.org/java-open-source/android.net.ConnectivityManager.NetworkCallback alterar para este tipo
    companion object {
        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }


}