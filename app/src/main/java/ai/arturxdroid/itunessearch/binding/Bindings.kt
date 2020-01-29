package ai.arturxdroid.itunessearch.binding

import ai.arturxdroid.itunessearch.R
import ai.arturxdroid.itunessearch.data.Track
import ai.arturxdroid.itunessearch.databinding.TrackItemBinding
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.xwray.groupie.databinding.BindableItem

public class TrackItem(val track: Track) : BindableItem<TrackItemBinding>() {

    override fun getLayout(): Int {
        return R.layout.track_item
    }

    override fun bind(viewBinding: TrackItemBinding, position: Int) {
        viewBinding.track = track
    }
}

public object PicassoBinding {

    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    public fun loadImage(imageView: ImageView, url: String?) {
        if (url != null)
            Picasso.get().load(url).into(imageView)
    }

}