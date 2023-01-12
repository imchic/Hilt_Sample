package com.example.hiltsample

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hiltsample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {

    override val viewModel: MainViewModel by viewModels()

    override fun init() {

        viewModel.getBaseUrl()

//        viewModel.snackBar.observe(this@MainActivity) {
//            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
//        }

        lifecycleScope.launch {
            viewModel.snackBar.collect {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    override fun initBinding() {

        binding.apply {

            btnSample2.setOnClickListener {
                //viewModel.setMessage("btnSample2")
                viewModel.setMessage("btnSample2")
            }

            btnSample.setOnClickListener {

                //viewModel.setText("This is Hilt Sample")
                //Timber.d("${viewModel.text.value}")

                lifecycleScope.launch {

                    //viewModel.getAppVersion()
                    viewModel.getSurvHuInfo()

                    viewModel.res.observe(this@MainActivity) {
                        when (it.status) {
                            Status.SUCCESS -> {

                                // null 아닌것만
                                it.data?.let { data ->
                                    if(data.appVersionList.isNullOrEmpty()) {
                                        Timber.d("$data 데이터가 없습니다.")
                                    }
                                }

                                viewModel.setMessage("성공 => ${it.data}")
                            }
                            Status.ERROR -> {
                                viewModel.setMessage("실패 => ${it.message}")
                            }
                            Status.LOADING -> {
                                Timber.d("로딩중")
                                viewModel.setMessage("로딩중")
                            }

                            else -> {}
                        }
                    }

                    viewModel.res2.observe(this@MainActivity){
                        when (it.status) {
                            Status.SUCCESS -> {
                                viewModel.setMessage("성공 => ${it.data}")
                            }
                            Status.ERROR -> {
                                viewModel.setMessage("실패 => ${it.message}")
                            }
                            Status.LOADING -> {
                                Timber.d("로딩중")
                                viewModel.setMessage("로딩중")
                            }

                            else -> {}
                        }
                    }
                }
            }
        }

    }

    override fun initViewModel() {

    }


}