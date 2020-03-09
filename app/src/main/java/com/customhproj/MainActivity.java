package com.customhproj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Activity activity = this;
    private ArrayList<String> chosenBasesArrayList;
    private ArrayList<String> chosenFlavorArrayList;

    private Spinner sizeSpinner;
    private Spinner baseSpinner1;
    private Spinner baseSpinner2;
    private Spinner flavorSpinner1;
    private Spinner flavorSpinner2;
    private Spinner flavorSpinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        ArrayList<String> baseArrayList = intent.getStringArrayListExtra("baseArrayList");
        ArrayList<String> flavorArrayList = intent.getStringArrayListExtra("flavorArrayList");
        ArrayList<String> sizeArrayList = new ArrayList<String>();
        sizeArrayList.add("Small");
        sizeArrayList.add("Medium");
        sizeArrayList.add("Large");

        sizeSpinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sizeArrayList);
        sizeSpinner.setAdapter(sizeAdapter);

        baseArrayList.add(0, "None");
        baseSpinner1 = findViewById(R.id.baseSpinner1);
        ArrayAdapter<String> baseAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, baseArrayList);
        baseSpinner1.setAdapter(baseAdapter1);

        baseSpinner2 = findViewById(R.id.baseSpinner2);
        ArrayAdapter<String> baseAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, baseArrayList);
        baseSpinner2.setAdapter(baseAdapter2);

        flavorArrayList.add(0, "None");
        flavorSpinner1 = findViewById(R.id.flavorSpinner1);
        ArrayAdapter<String> flavorAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, flavorArrayList);
        flavorSpinner1.setAdapter(flavorAdapter1);

        flavorSpinner2 = findViewById(R.id.flavorSpinner2);
        ArrayAdapter<String> flavorAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, flavorArrayList);
        flavorSpinner2.setAdapter(flavorAdapter2);

        flavorSpinner3 = findViewById(R.id.flavorSpinner3);
        ArrayAdapter<String> flavorAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, flavorArrayList);
        flavorSpinner3.setAdapter(flavorAdapter3);

        chosenBasesArrayList = new ArrayList<String>();
        chosenFlavorArrayList = new ArrayList<String>();

        Button generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent generateIntent = new Intent(activity, GenerateQrActivity.class);

                if (!baseSpinner1.getSelectedItem().toString().equals("None"))
                    chosenBasesArrayList.add(baseSpinner1.getSelectedItem().toString());

                if (!baseSpinner2.getSelectedItem().toString().equals("None"))
                    chosenBasesArrayList.add(baseSpinner2.getSelectedItem().toString());

                if (!flavorSpinner1.getSelectedItem().toString().equals("None"))
                    chosenFlavorArrayList.add(flavorSpinner1.getSelectedItem().toString());

                if (!flavorSpinner2.getSelectedItem().toString().equals("None"))
                    chosenFlavorArrayList.add(flavorSpinner2.getSelectedItem().toString());

                if (!flavorSpinner3.getSelectedItem().toString().equals("None"))
                    chosenFlavorArrayList.add(flavorSpinner3.getSelectedItem().toString());

                String qrString = "";
                qrString += sizeSpinner.getSelectedItem().toString() + "\n";
                for(String s : chosenBasesArrayList)
                    qrString += s + "\n";
                for(String s : chosenFlavorArrayList)
                    qrString += s + "\n";

                generateIntent.putExtra("qrString", qrString);
                startActivity(generateIntent);
            }
        });
    }
}
