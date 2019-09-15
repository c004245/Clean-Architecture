package hyunwook.co.kr.clean_architecture.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hyunwook.co.kr.clean_architecture.commons.Result
import hyunwook.co.kr.clean_architecture.commons.ResultType
import hyunwook.co.kr.clean_architecture.domain.GetBeersUseCase
import hyunwook.co.kr.clean_architecture.domain.model.BeersEntity
import hyunwook.co.kr.clean_architecture.viewmodel.mapper.ViewModelMapper
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BeersViewModel(private val getBeersUseCase: GetBeersUseCase) : ViewModel() {

    private val beersLiveData = MutableLiveData<List<BeerUI>>() //Beer Info LiveData
    private val areEmptyBeersLiveData = MutableLiveData<Boolean>() //Empty Beer LiveData
    private val isLoadingLiveData = MutableLiveData<Boolean>() //Loading Progress LiveData
    private val isErrorLiveData = MutableLiveData<Boolean>() //Error LiveData

    val beers: MutableLiveData<List<BeerUI>>
        get() = beersLiveData

    val areEmptyBeers: MutableLiveData<Boolean>
        get() = areEmptyBeersLiveData

    val isLoading: MutableLiveData<Boolean>
        get() = isLoadingLiveData

    val isError: MutableLiveData<Boolean>
        get() = isErrorLiveData

    init {
        handleBeersLoad()
    }

    fun handleBeersLoad() {
        isLoadingLiveData(true)
        viewModelScope.launch {
            Log.d("TEST", "viewModelScope launch...")
            updateAppropriateLiveData(getBeersUseCase.execute())
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
        val beers = ViewModelMapper.EntityToUI.map(beersEntity?.beers)

        if (beers.isEmpty()) {
            areEmptyBeersLiveData.postValue(true)
        } else {
            beersLiveData.postValue(beers)
        }
        isLoadingLiveData(false)
    }

    private fun onResultError() {
        viewModelScope.launch {
            delay(300)
            isLoadingLiveData(false)
        }.invokeOnCompletion {
            isErrorLiveData.postValue(true)
        }
    }


    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingLiveData.postValue(isLoading)
    }

}