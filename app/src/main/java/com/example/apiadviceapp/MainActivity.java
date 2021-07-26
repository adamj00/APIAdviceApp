package com.example.apiadviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_generateAdvice;
    TextView tv_advice;

    // showing requested advice in text view
    private void showAdvice() {
        AdviceService adviceService = new AdviceService(MainActivity.this);
        adviceService.getRandomAdvice(new AdviceService.GetRandomAdviceCallback() {
            @Override
            public void onError(String message) {
                tv_advice.setText(message);
            }

            @Override
            public void onResponse(AdviceModel adviceModel) {
                tv_advice.setText(adviceModel.toString());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // assign values to the controls
        btn_generateAdvice = findViewById(R.id.btn_GenerateAdvice);
        tv_advice = findViewById(R.id.tv_advice);

        // generate first advice
        showAdvice();

        // set listener to btn_generateAdvice
        btn_generateAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdvice();
            }
        });
    }
}