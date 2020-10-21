package com.example.calculadorapdm20202;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView displayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTv = findViewById(R.id.displayTv);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)view;
        String textButton = button.getText().toString();

        if (view.getId() == R.id.clearBt) {
            displayTv.setText("");
        } else {
            displayTv.setText(displayTv.getText().toString() + textButton);
        }
    }
}