package com.mobil.myproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConvertorActivity extends AppCompatActivity {

    TextView tvConvertor, tvDecimal, tvDecimalSonuc, tvMegabyte, tvMegabyteSonuc, tvCelcius, tvCelciusSonuc;
    EditText etDecimal, etMegabyte, etCelcius;
    Spinner spinnerDecimal, spinnerMegabyte;
    RadioButton radioButtonFahrenayt, radioButtonKelvin;
    Button btnDecimalSonuc, btnMegabyteSonuc, btnCelciusSonuc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        tvConvertor = findViewById(R.id.tv_convertor);
        tvDecimal = findViewById(R.id.tv_decimal);
        tvDecimalSonuc = findViewById(R.id.tv_decimal_sonuc);
        tvMegabyte = findViewById(R.id.tv_megabyte);
        tvMegabyteSonuc = findViewById(R.id.tv_megabyte_sonuc);
        tvCelcius = findViewById(R.id.tv_celcius);
        tvCelciusSonuc = findViewById(R.id.tv_celcius_sonuc);
        etDecimal = findViewById(R.id.et_decimal);
        etMegabyte = findViewById(R.id.et_megabyte);
        etCelcius = findViewById(R.id.et_celcius);
        spinnerDecimal = findViewById(R.id.spinner_decimal);
        spinnerMegabyte = findViewById(R.id.spinner_megabyte);
        radioButtonFahrenayt = findViewById(R.id.radioButton_fahrenayt);
        radioButtonKelvin = findViewById(R.id.radioButton_kelvin);
        btnDecimalSonuc = findViewById(R.id.btn_decimal_sonuc);
        btnMegabyteSonuc = findViewById(R.id.btn_megabyte_sonuc);
        btnCelciusSonuc = findViewById(R.id.btn_celcius_sonuc);

        ArrayAdapter<CharSequence> decimalAdapter = ArrayAdapter.createFromResource(
                this, R.array.decimal_options, android.R.layout.simple_spinner_item);
        decimalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDecimal.setAdapter(decimalAdapter);

        btnDecimalSonuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertDecimal();
            }
        });

        ArrayAdapter<CharSequence> megabyteAdapter = ArrayAdapter.createFromResource(
                this, R.array.megabyte_options, android.R.layout.simple_spinner_item);
        megabyteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMegabyte.setAdapter(megabyteAdapter);

        btnMegabyteSonuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertMegabyte();
            }
        });

        btnCelciusSonuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertCelcius();
            }
        });
    }

    private void convertDecimal() {
        try {
            String decimalValue = etDecimal.getText().toString();
            String selectedOption = spinnerDecimal.getSelectedItem().toString();

            int base = 10;

            switch (selectedOption) {
                case "Binary":
                    base = 2;
                    break;
                case "Octal":
                    base = 8;
                    break;
                case "Hexadecimal":
                    base = 16;
                    break;
            }

            String convertedValue;
            try {
                int decimalIntValue = Integer.parseInt(decimalValue);
                convertedValue = Integer.toString(decimalIntValue, base);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Geçersiz Giriş");
            }
            tvDecimalSonuc.setText(convertedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void convertMegabyte() {
        try {
            double megabyteValue = Double.parseDouble(etMegabyte.getText().toString());
            String selectedOption = spinnerMegabyte.getSelectedItem().toString();

            double convertedValue = 0;

            switch (selectedOption) {
                case "Kilobyte":
                    convertedValue = megabyteValue * 1024;
                    break;
                case "Byte":
                    convertedValue = megabyteValue * 1024 * 1024;
                    break;
                case "Kibibyte":
                    convertedValue = megabyteValue * 8 * 1024;
                    break;
                case "Bit":
                    convertedValue = megabyteValue * 8 * 1024 * 1024;
                    break;
            }

            tvMegabyteSonuc.setText(String.valueOf(convertedValue));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void convertCelcius() {
        try {
            double celciusValue = Double.parseDouble(etCelcius.getText().toString());
            double convertedValue;

            if (radioButtonFahrenayt.isChecked()) {
                convertedValue = (celciusValue * 9 / 5) + 32;
            } else if (radioButtonKelvin.isChecked()) {
                convertedValue = celciusValue + 273.15;
            } else {
                Toast.makeText(this, "Lütfen herhangi bir dönüştürücü seçin", Toast.LENGTH_LONG).show();
                return;
            }
            tvCelciusSonuc.setText(String.valueOf(convertedValue));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
