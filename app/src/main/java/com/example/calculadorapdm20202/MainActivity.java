package com.example.calculadorapdm20202;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView displayTv;
    private final String DISPLAY_TV_VALUE = "DISPLAY_TV_VALUE";

    private String getTextButton(View view) {
        Button button = (Button) view;
        return button.getText().toString();
    }

    private void concatDisplayText(String textButton) {
        String displayText = displayTv.getText().toString() + textButton;
        displayTv.setText(displayText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTv = findViewById(R.id.displayTv);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DISPLAY_TV_VALUE, displayTv.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayTv.setText(savedInstanceState.getString(DISPLAY_TV_VALUE, ""));
    }

    @Override
    public void onClick(View view) {
        String textButton = getTextButton(view);
        concatDisplayText(textButton);
    }

    public void clearDisplay(View view) {
        displayTv.setText("");
    }

    public void showResult(View view) {
        String expressionText = displayTv.getText().toString();
        Expression expression = new ExpressionBuilder(expressionText).build();

        try {
            double result = expression.evaluate();
            String textResult = "=" + Double.toString(result);
            concatDisplayText(textResult);
        } catch (ArithmeticException ex) {
            String textResult = "=ERRO";
            concatDisplayText(textResult);
        }
    }
}