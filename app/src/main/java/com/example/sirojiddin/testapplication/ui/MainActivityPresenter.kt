package com.example.sirojiddin.testapplication.ui

import com.example.sirojiddin.testapplication.common.BasePresenterImpl
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(view: MainActivityContract.View?) : BasePresenterImpl<MainActivityContract.View>(view), MainActivityContract.Presenter {
}