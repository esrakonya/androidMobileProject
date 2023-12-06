package com.mobil.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOkulno, tvIsim;
    Button btnConvertor, btnRandomGenerator, btnSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOkulno = findViewById(R.id.tv_okulno);
        tvIsim = findViewById(R.id.tv_isim);
        btnConvertor = findViewById(R.id.btn_convertor);
        btnRandomGenerator = findViewById(R.id.btn_random_generator);
        btnSms = findViewById(R.id.btn_sms);

        btnConvertor.setVisibility(View.VISIBLE);
        btnRandomGenerator.setVisibility(View.VISIBLE);
        btnSms.setVisibility(View.VISIBLE);

        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        btnConvertor.startAnimation(fadeIn);
        btnRandomGenerator.startAnimation(fadeIn);
        btnSms.startAnimation(fadeIn);

        btnConvertor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConvertorActivity.class);
                startActivity(intent);
            }
        });

        btnRandomGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RandomGeneratorActivity.class);
                startActivity(intent);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SmsActivity.class);
                startActivity(intent);
            }
        });
    }
}