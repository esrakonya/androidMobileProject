package com.mobil.myproject;

import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomGeneratorActivity extends AppCompatActivity {

    TextView tvRandomGenerator, tvAdet;
    EditText etAdet;
    ScrollView scrollView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_generator);

        tvRandomGenerator = findViewById(R.id.tv_random_generator);
        tvAdet = findViewById(R.id.tv_adet);
        etAdet = findViewById(R.id.et_adet);
        linearLayout = findViewById(R.id.linear_layout);
        scrollView = findViewById(R.id.scroll_view);

        etAdet.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(android.text.Editable editable) {
                generateInputs(editable.toString());
            }
        });
    }

    private void generateInputs(String adet) {
        linearLayout.removeAllViews();

        if (adet.isEmpty()) {
            return;
        }

        int adetSayisi = Integer.parseInt(adet);

        for (int i = 1; i <= adetSayisi; i++) {
            int minValue = new Random().nextInt(30) + 1;
            int maxValue = new Random().nextInt(50) + 50;

            int middleValue = new Random().nextInt(maxValue - minValue + 1) + minValue;

            int percentage = Math.round((float) (middleValue - minValue) / (maxValue - minValue) * 100);


            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            linearLayout.addView(itemLayout, layoutParams);


            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);


            TextView minText = new TextView(this);
            minText.setText("Min: " + minValue);
            minText.setGravity(Gravity.START | Gravity.TOP);
            itemLayout.addView(minText);


            TextView maxText = new TextView(this);
            maxText.setText("Max: " + maxValue);
            maxText.setGravity(Gravity.START | Gravity.TOP);
            itemLayout.addView(maxText);


            TextView progressText = new TextView(this);
            progressText.setText(middleValue + " = %" + percentage);
            progressText.setGravity(Gravity.CENTER | Gravity.TOP);
            itemLayout.addView(progressText);


            LinearLayout.LayoutParams progressBarParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            progressBarParams.setMargins(20, 5, 20, 50);
            progressBar.setLayoutParams(progressBarParams);

            itemLayout.addView(progressBar);

            progressBar.setProgress(middleValue);
        }
    }
}
