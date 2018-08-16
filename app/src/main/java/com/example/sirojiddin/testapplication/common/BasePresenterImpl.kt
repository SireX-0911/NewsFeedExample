package com.example.sirojiddin.testapplication.common

import android.os.Bundle


abstract class BasePresenterImpl<V : BaseView>(protected var view: V?) : BasePresenter {
    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onSaveInstanceState(bundle: Bundle?) {

    }

    override fun onDestroy() {

    }

    override fun onCreateView(bundle: Bundle?) {

    }

}