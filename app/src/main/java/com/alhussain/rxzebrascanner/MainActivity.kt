package com.alhussain.rxzebrascanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable


class MainActivity : AppCompatActivity() {
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rxZebraScanner = RxZebraScanner(applicationContext)

//        compositeDisposable.add(
//            rxZebraScanner.getScannerAsObserve().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe() {
//                    Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
//                }
//        )


    }
}