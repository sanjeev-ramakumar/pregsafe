package com.pregsafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    private Button mScan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.home);
		
        mScan = (Button) findViewById(R.id.scan_button);
        mScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, 0);
            }
        });		
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
//                String format = data.getStringExtra("SCAN_RESULT_FORMAT");

                // Handle successful scan
                Log.i("PREGSAFE", "Barcode = " + contents);

                // Show Product page
                Intent productIntent = new Intent(this, ProductActivity.class);
                productIntent.putExtra("com.pregsafe.BARCODE", contents);
                startActivity(productIntent);

            } else if (resultCode == RESULT_CANCELED) {
                // Handle Cancel
                Log.i("PREGSAFE", "Scan unsuccessful");
            }
        }
    }
	
}
