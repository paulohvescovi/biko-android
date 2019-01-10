package paulo.com.br.bico.extensions


import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import paulo.com.br.bico.util.AlertUtil
import paulo.com.br.bico.util.DeviceUtils

fun Fragment.hide(dialog: MaterialDialog?): Unit {
    dialog?.dismiss()
}
fun Fragment.showAlert(dialog: MaterialDialog?, title: Int, message: Int): MaterialDialog? {
    return AlertUtil.show(this.context!!, dialog, title, message)
}
fun Fragment.showLoading(dialog: MaterialDialog?, title: Int, message: Int): MaterialDialog {
    return AlertUtil.showLoading(this.context!!, dialog, title, message)
}

fun Fragment.isOnline(): Boolean {
    return DeviceUtils.isOnline(this.context!!)
}