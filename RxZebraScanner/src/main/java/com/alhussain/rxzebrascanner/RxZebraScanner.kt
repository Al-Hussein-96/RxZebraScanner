package com.alhussain.rxzebrascanner

import android.content.Context
import com.alhussain.rxzebrascanner.EmdkScanner
import io.reactivex.Observable

open class RxZebraScanner constructor(private val context: Context) {
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