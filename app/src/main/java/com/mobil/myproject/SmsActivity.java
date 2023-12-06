package com.mobil.myproject;

import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity {

    TextView tvSms, tvTelefon, tvMesaj;
    EditText etPhone, etMesaj;

    Button btnGonder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        tvSms = findViewById(R.id.tv_sms);
        tvTelefon = findViewById(R.id.tv_telefon);
        tvMesaj = findViewById(R.id.tv_mesaj);
        etPhone = findViewById(R.id.et_phone);
        etMesaj = findViewById(R.id.et_mesaj);
        btnGonder = findViewById(R.id.btn_gonder);

        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String phoneNumber = etPhone.getText().toString();
        String message = etMesaj.getText().toString();

        if (phoneNumber.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Telefon numarası ve mesaj boş olamaz", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);

            Toast.makeText(this, "SMS gönderildi", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS gönderilemedi", Toast.LENGTH_SHORT).show();
        }
    }
}
