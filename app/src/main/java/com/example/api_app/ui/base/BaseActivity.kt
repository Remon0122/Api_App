package com.example.api_app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>: AppCompatActivity(){

    lateinit var binding : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflateBinding(layoutInflater)

        setContentView(binding.root)
    }
    abstract fun inflateBinding(inflater: LayoutInflater) : VB
}