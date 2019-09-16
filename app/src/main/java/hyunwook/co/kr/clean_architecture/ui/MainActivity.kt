package hyunwook.co.kr.clean_architecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hyunwook.co.kr.clean_architecture.R
import hyunwook.co.kr.clean_architecture.ui.adapter.BeersAdapter
import hyunwook.co.kr.clean_architecture.ui.mapper.BeerUIMapper
import hyunwook.co.kr.clean_architecture.ui.model.BeerAdapterModel
import hyunwook.co.kr.clean_architecture.viewmodel.BeersViewModel
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    companion object {
        private const val BUNDLE_KEY_BEERS_ADAPTER_MODEL = "BUNDLE_KEY_BEERS_ADAPTER_MODEL"
    }

    private val viewModel: BeersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.beers.observe(this, Observer(::onBeersReceived))
        viewModel.isError.observe(this, Observer { onErrorReceived() })
        viewModel.areEmptyBeers.observe(this, Observer { onEmptyBeersReceived() })
        viewModel.isLoading.observe(this, Observer(::onLoadingStateReceived))
    }

    private fun onBeersReceived(beers: List<BeerUI>) {
        showBeers(beers)
    }

    private fun showBeers(beersUI: List<BeerUI>?) {
        beersUI?.let {
            populateRecyclerView(BeerUIMapper.map(it))
        }
    }

    private fun populateRecyclerView(beersAdapterModel: List<BeerAdapterModel>) {
        recycler_view_beers.layoutManager = LinearLayoutManager(this)

        val beersAdapter = BeersAdapter(beersAdapterModel, this)
        recycler_view_beers.adapter = beersAdapter
        beersAdapter.updateAdapter(beersAdapterModel)

        recycler_view_beers.setHasFixedSize(true)
        recycler_view_beers.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
    }

    private fun onErrorReceived() {
        AlertDialog.Builder(this)
            .setTitle(R.string.network_connection_error_title)
            .setCancelable(false)
            .setNegativeButton(R.string.network_connection_error_cancel) { _, _ ->
                finish()
            }
            .setPositiveButton(R.string.network_connection_error_action) { _, _ ->
                viewModel.handleBeersLoad()
            }.show()
    }

    private fun onEmptyBeersReceived() {

    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        showSpinner(isLoading)
    }

    private fun showSpinner(isLoading: Boolean) {
        main_spinner.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }


}
