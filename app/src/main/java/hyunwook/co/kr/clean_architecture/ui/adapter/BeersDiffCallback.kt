package hyunwook.co.kr.clean_architecture.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import hyunwook.co.kr.clean_architecture.ui.model.BeerAdapterModel
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI

class BeersDiffCallback(private val oldBeers: List<BeerAdapterModel>,
                        private val newBeers: List<BeerAdapterModel>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldBeers[oldItemPosition].id == newBeers[newItemPosition].id

    override fun getOldListSize(): Int = oldBeers.size

    override fun getNewListSize(): Int = newBeers.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldBeers[oldItemPosition] == newBeers[newItemPosition]

}