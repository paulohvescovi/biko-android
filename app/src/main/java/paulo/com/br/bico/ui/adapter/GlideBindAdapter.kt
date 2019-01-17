package paulo.com.br.bico.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object GlideBindAdapter {

    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.getContext()).load(url).into(view)
    }
}
