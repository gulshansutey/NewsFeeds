package com.gulshansutey.newsfeeds.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


/**
 * An implementation of [MutableLiveData] that only emit a value when called [setValue]
 *
 * */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    /**
     * #isPending a boolean to check if there
     * is something new value pending to be emitted to a subscriber
     * */
    private val isPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        //Comparing if there is something new to be emitted to the subscriber

        super.observe(owner, Observer { t ->
            if (isPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        isPending.set(true)
        super.setValue(t)
    }


}