package paulo.com.br.bico.util

import android.content.Context
import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import paulo.com.br.bico.R

class AlertUtil {

    companion object {
        fun show(context: Context, dialog: MaterialDialog?, title: Int, message: Int) : MaterialDialog {
            if (dialog != null){
                dialog.dismiss()
            }
            return MaterialDialog(context!!).show {
                title(title)
                message(message)

                positiveButton(text = "OK")
            }
        }
        fun showLoading(context: Context, dialog: MaterialDialog?, title: Int, message: Int) : MaterialDialog {
            if (dialog != null){
                dialog.dismiss()
            }
            return MaterialDialog(context!!).show {
                title(title)
                message(message)
                cancelable(false)
                customView(R.layout.progress_view_for_dialog_indeterminate)
            }
        }
        fun show(context: Context,
                 dialog: MaterialDialog?,
                 title: Int, message: Int,
                 positiveText: Int = R.string.sim,
                 positiveListener: DialogCallback,
                 negativeText: Int = R.string.nao,
                 negativeListener: DialogCallback = {it.dismiss()}) : MaterialDialog {
            if (dialog != null){
                dialog.dismiss()
            }
            return MaterialDialog(context!!).show {
                title(title)
                message(message)
                positiveButton(positiveText, click = positiveListener)
                negativeButton(negativeText, click = negativeListener)
            }
        }
    }

}
