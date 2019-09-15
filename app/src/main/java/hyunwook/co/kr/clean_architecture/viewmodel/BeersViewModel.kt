package hyunwook.co.kr.clean_architecture.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hyunwook.co.kr.clean_architecture.commons.Result
import hyunwook.co.kr.clean_architecture.commons.ResultType
import hyunwook.co.kr.clean_architecture.domain.model.BeersEntity
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
            updateAppropriateLiveData()
        }
    }

    private fun updateAppropriateLiveData(result: Result<BeersEntity>) {
        if (isResultSuccess(result.resultType)) {
            onResultSuccess(result.data)
        } else {
            onResultError()
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultSuccess(beersEntity: BeersEntity?) {
        val beers = ViewModelMa
    }

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingLiveData.postValue(isLoading)
    }

}