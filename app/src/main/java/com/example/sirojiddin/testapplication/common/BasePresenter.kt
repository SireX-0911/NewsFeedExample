package com.example.sirojiddin.testapplication.common

import android.os.Bundle

interface BasePresenter {
    abstract fun onStart()

    abstract fun onResume()

    abstract fun onPause()

    abstract fun onSaveInstanceState(bundle: Bundle?)

    abstract fun onDestroy()

    abstract fun onCreateView(bundle: Bundle?)
}