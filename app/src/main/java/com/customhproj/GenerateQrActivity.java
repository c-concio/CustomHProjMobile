package com.customhproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateQrActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);
        Intent intent = getIntent();

        imageView = findViewById(R.id.imageView);

        String qrString = intent.getStringExtra("qrString");

        MultiFormatWriter writer = new MultiFormatWriter();
        try{
            BitMatrix matrix = writer.encode(qrString, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e){
            e.printStackTrace();
        }

    }
}
