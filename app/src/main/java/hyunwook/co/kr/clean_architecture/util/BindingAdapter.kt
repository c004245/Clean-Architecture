package hyunwook.co.kr.clean_architecture.util

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("app:loadingData")
fun loadingData(view: View, isLoading: Boolean) {
    Log.d("TEST", "loadingData ->$isLoading")
    view.visibility = if (!isLoading) View.VISIBLE else View.GONE
}