package com.example.sirojiddin.testapplication.ui

import android.os.Bundle
import com.example.sirojiddin.testapplication.R
import com.example.sirojiddin.testapplication.common.BaseActivity

class MainActivity : BaseActivity(), MainActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
