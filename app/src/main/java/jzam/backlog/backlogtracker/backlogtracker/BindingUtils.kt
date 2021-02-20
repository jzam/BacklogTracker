package jzam.backlog.backlogtracker.backlogtracker

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jzam.backlog.backlogtracker.R
import jzam.backlog.backlogtracker.database.BacklogItem
import com.google.android.material.textfield.TextInputEditText

//Bindings for list_backlog_item
@BindingAdapter("itemName")
fun TextView.setBacklogItemName(item: BacklogItem) {
    text = item.name
}

@BindingAdapter("itemSortValue")
fun TextView.setBacklogItemSortValue(item: BacklogItem) {
    text = ("Sort:" + item.sortValue.toString())
}

@BindingAdapter("itemTypeImage")
fun ImageView.setBacklogItemImage(item: BacklogItem) {
    setImageResource(when (item.type) {
        "game" -> R.drawable.ic_baseline_videogame_asset_24
        "book" -> R.drawable.ic_baseline_menu_book_24
        else -> R.drawable.ic_baseline_live_tv_24
    })
}

@BindingAdapter("itemProgress")
fun TextView.setBacklogItemProgress(item: BacklogItem) {
    text = (item.progressDone.toString() + "/" + item.progressTotal.toString())
}

@BindingAdapter("itemPlatform")
fun TextView.setBacklogItemPlatform(item: BacklogItem) {
    text = item.platform
}

@BindingAdapter("itemGenre")
fun TextView.setBacklogItemGenre(item: BacklogItem) {
    text = item.genre
}

//Bindings for fragment_edit_item
@BindingAdapter("itemNameEdit")
fun TextInputEditText.setBacklogItemNameEdit(item: BacklogItem?) {
    item?.let{
        this.setText(item.name)
    }
}

@BindingAdapter("itemSortEdit")
fun TextInputEditText.setBacklogItemSortEdit(item: BacklogItem?) {
    item?.let{
        this.setText(item.sortValue.toString())
    }
}

@BindingAdapter("itemTypeEdit")
fun TextInputEditText.setBacklogItemTypeEdit(item: BacklogItem?) {
    item?.let {
        this.setText(item.type)
    }
}

@BindingAdapter("itemPlatformEdit")
fun TextInputEditText.setBacklogItemPlatformEdit(item: BacklogItem?) {
    item?.let {
        this.setText(item.platform)
    }
}

@BindingAdapter("itemGenreEdit")
fun TextInputEditText.setBacklogItemGenreEdit(item: BacklogItem?) {
    item?.let {
        this.setText(item.genre)
    }
}

@BindingAdapter("itemProgressDoneEdit")
fun TextInputEditText.setBacklogProgressDoneEdit(item: BacklogItem?) {
    item?.let {
        this.setText(item.progressDone.toString())
    }
}

@BindingAdapter("itemProgressTotalEdit")
fun TextInputEditText.setBacklogItemProgressTotalEdit(item: BacklogItem?) {
    item?.let {
        this.setText(item.progressTotal.toString())
    }
}

@BindingAdapter("itemNotesEdit")
fun TextInputEditText.setBacklogItemNotesEdit(item: BacklogItem?) {
    item?.let {
        this.setText(item.notes)
    }
}

//Load image
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.broken_image))
            .into(imgView)
    }
}

//Load main item image
@BindingAdapter("imageUrlFromItem")
fun bindImageFromItem(imgView: ImageView, item: BacklogItem?) {
    item?.let {
        val imgUri = item.detailImageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(when (item.type) {
                    "game" -> R.drawable.ic_baseline_videogame_asset_24
                    "book" -> R.drawable.ic_baseline_menu_book_24
                    else -> R.drawable.ic_baseline_live_tv_24
                }))
            .into(imgView)
    }
}

//Load icon image for item
@BindingAdapter("iconUrlFromItem")
fun bindIconFromItem(imgView: ImageView, item: BacklogItem?) {
    item?.let {
        val imgUri = item.iconImageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(when (item.type) {
                                    "game" -> R.drawable.ic_baseline_videogame_asset_24
                                    "book" -> R.drawable.ic_baseline_menu_book_24
                                    else -> R.drawable.ic_baseline_live_tv_24
                                }))
                .into(imgView)
    }
}

//Binding for game search screen
@BindingAdapter("setText")
fun TextView.setTextViewText(str: String?) {
    str?.let {
        text = str
    }
}
