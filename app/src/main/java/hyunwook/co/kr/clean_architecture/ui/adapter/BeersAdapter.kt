package hyunwook.co.kr.clean_architecture.ui.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hyunwook.co.kr.clean_architecture.R
import hyunwook.co.kr.clean_architecture.ui.model.BeerAdapterModel
import hyunwook.co.kr.clean_architecture.viewmodel.model.AbvColorType
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI
import kotlinx.android.synthetic.main.item_list_beer.view.*

class BeersAdapter(private var beers: List<BeerAdapterModel>, private val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_beer, parent, false)
        )

    override fun getItemCount(): Int = beers.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            val abv = beers[position].abv.toShort()
            val formattedAbv: String = context.resources.getString(R.string.abv, abv)

            beerAbvTextView.text = formattedAbv
            beerNameTextView.text = beers[position].name
            beerTaglineTextView.text = beers[position].tagline

            Glide.with(context)
                .load(beers[position].image)
                .placeholder(R.drawable.ic_close_black)
                .override(200, 300)
                .into(beerImageTextView)

            val backgroundColor = ContextCompat.getColor(context, beers.get(position).abvColor)
            (beerAbvTextView.background as GradientDrawable).setColor(backgroundColor)
        }
    }

    fun updateAdapter(updatedList: List<BeerAdapterModel>) {
        val result = DiffUtil.calculateDiff(BeersDiffCallback(this.beers, updatedList))
        this.beers = updatedList.toMutableList()
        result.dispatchUpdatesTo(this)
    }

    fun getBeers(): List<BeerAdapterModel> = beers


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val beerAbvTextView: AppCompatTextView = view.item_list_beer_abv
    val beerNameTextView: AppCompatTextView = view.item_list_beer_name
    val beerTaglineTextView: AppCompatTextView = view.item_list_beer_tagline
    val beerImageTextView: AppCompatImageView = view.item_list_beer_image
}