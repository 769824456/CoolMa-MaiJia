package io.github.froger.instamaterial.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * 二维码扫描
 */
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
        mScannerView.setResultHandler(this); // Register java.lang.Stringourselves as a handler for scan results.
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
        Log.v("SimpleScannerActivity", rawResult.getContents()); // Prints scan results
        Log.v("SimpleScannerActivity", rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        if (rawResult.getContents().equals("")) {
            Toast.makeText(SimpleScannerActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        } else {
//            System.out.println("Result:"+resultString);
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", rawResult.getContents());
            resultIntent.putExtras(bundle);
            this.setResult(RESULT_OK, resultIntent);
        }
        SimpleScannerActivity.this.finish();
    }
}
