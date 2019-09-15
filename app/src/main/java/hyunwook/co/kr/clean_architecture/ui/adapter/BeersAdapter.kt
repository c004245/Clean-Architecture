package hyunwook.co.kr.clean_architecture.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI

class BeersAdapter(private var beers: MutableList<BeerUI>, private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHol

    }


}

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val beerAbvTextView: AppCompatT
        }