package hyunwook.co.kr.clean_architecture.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI
import kotlinx.coroutines.launch

class BeersViewModel : ViewModel() {

    private val beersLiveData = MutableLiveData<List<BeerUI>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val isLoading: MutableLiveData<Boolean>
        get() = isLoadingLiveData

    init {
        handleBeersLoad()
    }

    fun handleBeersLoad() {
        isLoadingLiveData(true)
        viewModelScope.launch {
            Log.d("TEST", "viewModelScope launch...")
            updateAppropriateLiveData
        }
    }

    private fun updateAppropriateLiveData(result: Result)

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingLiveData.postValue(isLoading)
    }

}