package com.example.hiltsample

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hiltsample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {

    override val viewModel: MainViewModel by viewModels()

    override fun init() {

        viewModel.getBaseUrl()

        viewModel.snackBar.observe(this@MainActivity) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun initBinding() {

        binding.apply {

            btnSample2.setOnClickListener {
                viewModel.setMessage("btnSample2")
            }

            btnSample.setOnClickListener {

                //viewModel.setText("This is Hilt Sample")
                //Timber.d("${viewModel.text.value}")

                lifecycleScope.launch {
                    viewModel.getAppVersion()
                    viewModel.res.observe(this@MainActivity) {
                        when (it.status) {
                            Status.SUCCESS -> {
                                viewModel.snackBar.postValue("성공 => ${it.data}")
                            }
                            Status.ERROR -> {
                                viewModel.snackBar.postValue("실패 => ${it.message}")
                            }
                            Status.LOADING -> {
                                viewModel.snackBar.postValue("로딩중")
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