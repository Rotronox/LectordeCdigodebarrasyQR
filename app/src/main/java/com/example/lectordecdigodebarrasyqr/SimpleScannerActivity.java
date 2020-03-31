package com.example.lectordecdigodebarrasyqr;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

import static android.content.ContentValues.TAG;

public class SimpleScannerActivity extends Activity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
//        Log.v(TAG, rawResult.getContents()); // Prints scan results
//        Log.v(TAG, rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
//
//        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);

        final String code = rawResult.getContents();
        final String format = rawResult.getBarcodeFormat().getName();

        final String fullMessage = "Contents = "+code+", Format = "+format;

        try{
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),notification);
            r.play();
            Toast.makeText(getApplicationContext(),fullMessage,Toast.LENGTH_LONG).show();

            //si queremos que siga indefinidamente leyendo:
//            mScannerView.resumeCameraPreview(this);
            Intent intento =  new Intent(this,MainActivity.class);
            startActivity(intento);
        }catch (Exception e){
            Log.e(TAG, e.getLocalizedMessage());
        }

    }



}