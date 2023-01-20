package com.alhussain.rxzebrascanner

import android.content.Context
import com.alhussain.rxzebrascanner.RxZebraScanner
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZebraClient @Inject constructor(@ApplicationContext context: Context) : RxZebraScanner(context)