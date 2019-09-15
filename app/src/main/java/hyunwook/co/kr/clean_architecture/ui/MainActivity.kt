package hyunwook.co.kr.clean_architecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hyunwook.co.kr.clean_architecture.R
import hyunwook.co.kr.clean_architecture.viewmodel.BeersViewModel
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel: BeersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.beers.observe(this, Observer(::onBeersReceived))
    }

    private fun onBeersReceived(beers: List<BeerUI>) {
        showBeers(beers)
    }

    private fun showBeers(beersUI: List<BeerUI>?) {
        beersUI?.let {
            recycler_view_beers.layoutManager = LinearLayoutManager(this)

            val beersAdapter =
        }
    }
}
