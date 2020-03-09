package com.customhproj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class QrActivity extends AppCompatActivity {
    private Button scanButton;
    private ArrayList<String> baseArrayList;
    private ArrayList<String> flavorArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        // get reference to scanButton
        scanButton = findViewById(R.id.scanButton);
        final Activity activity = this;
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        baseArrayList = new ArrayList<String>();
        flavorArrayList = new ArrayList<String>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                // results.getContents() has the results of scanning a valid QR code
                // parse the string and save the bases into array, and the flavors into flavor array
                String qrString = result.getContents();
                String[] queryArray = qrString.split("\n");

                if (queryArray[0].equals("Bases")) {

                    for (int i = 1; i < queryArray.length; i++) {

                        if (queryArray[i].equals("Flavors")) {

                            for (int j = i + 1; j < queryArray.length; j++) {
                                flavorArrayList.add(queryArray[j]);
                            }

                            break;

                        } else {
                            baseArrayList.add(queryArray[i]);
                        }

                    }
                    // go to main activity
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("baseArrayList", baseArrayList);
                    intent.putExtra("flavorArrayList", flavorArrayList);
                    startActivity(intent);
                }
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
