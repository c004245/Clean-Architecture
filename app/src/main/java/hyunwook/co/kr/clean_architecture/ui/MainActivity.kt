package hyunwook.co.kr.clean_architecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hyunwook.co.kr.clean_architecture.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
