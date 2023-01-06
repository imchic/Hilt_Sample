package com.example.hiltsample

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<B: ViewDataBinding, VM: ViewModel>(
    @LayoutRes private val layoutRes: Int
): AppCompatActivity(){

    lateinit var binding: B
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this

        init()
        initBinding()
        initViewModel()
    }

    abstract fun init()
    abstract fun initBinding()
    abstract fun initViewModel()
}