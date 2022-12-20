package com.alhussain.rxzebrascanner

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

open class RxZebraScanner constructor(val context: Context) {
    private lateinit var onDataScanning: (text: String) -> Unit

    private var emdkScanner: EmdkScanner

    init {
        emdkScanner = EmdkScanner(context = context) {
            onDataScanning.invoke(it)
        }
    }


    fun getScannerAsObserve(): Observable<String> =
        Observable.create { emitter ->
            onDataScanning = {
                emitter.onNext(it)
            }
        }



}