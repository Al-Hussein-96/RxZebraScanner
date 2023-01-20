package com.alhussain.rxzebrascanner

import android.content.Context
import com.symbol.emdk.EMDKManager
import com.symbol.emdk.barcode.*
import timber.log.Timber

internal class EmdkScanner(
    val context: Context,
    private val onDataScanning: (text: String) -> Unit
) :
    EMDKManager.EMDKListener,
    Scanner.StatusListener,
    Scanner.DataListener,
    BarcodeManager.ScannerConnectionListener {

    private var emdManager: EMDKManager? = null
    private var barcodeManager: BarcodeManager? = null
    private var scanner: Scanner? = null


    init {
        Timber.tag(tag = EmdkScanner::class.java.name).i("init")
        Timber.tag(tag = EmdkScanner::class.java.name)
            .i("MANUFACTURER : %s", android.os.Build.MANUFACTURER)

        if (android.os.Build.MANUFACTURER.contains("Zebra Technologies")) {
            val result  = EMDKManager.getEMDKManager(context, this)

            Timber.tag(tag = EmdkScanner::class.java.name)
                .i("MANUFACTURER : %s", result.statusCode.name)
        }

    }


    override fun onOpened(emdkManager: EMDKManager) {
        Timber.tag(tag = EmdkScanner::class.java.name).i("OnOpened")
        this.emdManager = emdkManager
        this.barcodeManager =
            emdManager?.getInstance(EMDKManager.FEATURE_TYPE.BARCODE) as BarcodeManager?

        initScanner()
    }

    private fun initScanner() {
        Timber.tag(tag = EmdkScanner::class.java.name).i("initScanner")

        barcodeManager?.addConnectionListener(this)
        scanner = barcodeManager?.getDevice(BarcodeManager.DeviceIdentifier.DEFAULT)

        scanner?.addDataListener(this)
        scanner?.addStatusListener(this)
        scanner?.triggerType = Scanner.TriggerType.HARD
        scanner?.enable()

    }

    override fun onClosed() {
        Timber.tag(tag = EmdkScanner::class.java.name).i("onClosed")

        emdManager?.release()
        emdManager = null
    }

    override fun onConnectionChange(
        scannerInfo: ScannerInfo?,
        connectionState: BarcodeManager.ConnectionState?
    ) {
        Timber.tag(tag = EmdkScanner::class.java.name).i("BASE_SCANNER")


    }

    private fun readBarcode() {
        scanner?.let {
            Timber.tag(tag = EmdkScanner::class.java.name).d("scanner.read()")
            if (it.isEnabled) {
                if (!it.isReadPending) {
                    it.read()
                } else {
                    Timber.tag(tag = EmdkScanner::class.java.name).i("readBarcode isReadPending")
                }
            } else {
                Timber.tag(tag = EmdkScanner::class.java.name)
                    .w("scanner.read() scanner is disabled")
            }
        }

    }

    override fun onData(dataCollection: ScanDataCollection?) {
        Timber.tag(tag = EmdkScanner::class.java.name).i("onData")

        checkNotNull(dataCollection)


        val resultData = dataCollection.scanData.firstOrNull()?.data

        checkNotNull(resultData)

        onDataScanning.invoke(resultData)
    }

    override fun onStatus(p0: StatusData) {
        Timber.tag(tag = EmdkScanner::class.java.name).i("onStatus %s", p0.state.toString())

        when (p0.state) {
            StatusData.ScannerStates.IDLE -> readBarcode()
            else -> {}
        }


    }


}