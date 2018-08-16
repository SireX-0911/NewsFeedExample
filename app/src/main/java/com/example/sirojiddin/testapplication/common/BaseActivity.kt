package com.example.sirojiddin.testapplication.common

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Named

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    @Named("BaseActivityModule.activityFragmentManager")
    lateinit var activityFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        if (fragment.isAdded) return
        activityFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit()
    }

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        if (fragment.isAdded) return
        activityFragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .commit()
    }

    protected fun addFragmentWithBackStack(@IdRes containerViewId: Int, fragment: Fragment) {
        if (fragment.isAdded) return
        activityFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .addToBackStack(null)
                .commit()
    }

    protected fun addFragmentWithTag(@IdRes containerViewId: Int, fragment: Fragment, tag: String) {
        if (fragment.isAdded) return
        activityFragmentManager.beginTransaction()
                .add(containerViewId, fragment, tag)
                .commit()
    }

    protected fun addFragmentWithTagAndBackStack(@IdRes containerViewId: Int, fragment: Fragment, tag: String) {
        if (fragment.isAdded) return
        activityFragmentManager.beginTransaction()
                .add(containerViewId, fragment, tag)
                .addToBackStack(null)
                .commit()
    }

    public fun popBackStack() {
        activityFragmentManager.popBackStack()
    }
}