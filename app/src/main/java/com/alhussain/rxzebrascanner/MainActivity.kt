package com.alhussain.rxzebrascanner

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var zebraClient: ZebraClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable.add(
            zebraClient.getScannerAsObserve().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe() {
                    Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()

                    findViewById<TextView>(R.id.barcode).text = it
                }
        )


    }
}