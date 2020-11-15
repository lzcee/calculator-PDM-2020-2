package com.example.calculadorapdm20202;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView displayTv;
    private final String DISPLAY_TV_VALUE = "DISPLAY_TV_VALUE";
    private final int SETTINGS_REQUEST_CODE = 1;
    public static final String EXTRA_SETTINGS = "EXTRA_SETTINGS";
    private Settings settings = new Settings(false);

    private String getTextButton(View view) {
        Button button = (Button) view;
        return button.getText().toString();
    }

    private void concatDisplayText(String textButton) {
        String displayText = displayTv.getText().toString() + textButton;
        displayTv.setText(displayText);
    }

    public void openSettingsActivity() {
        Intent settingsIntent = new Intent("SETTINGS");
        settingsIntent.putExtra(EXTRA_SETTINGS, settings);
        startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsMi:
                openSettingsActivity();
                return true;
            case R.id.exitMi:
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            settings = data.getParcelableExtra(EXTRA_SETTINGS);
            if (settings != null && settings.getAdvanced()) {
                findViewById(R.id.squareRootBt).setVisibility(View.VISIBLE);
                findViewById(R.id.exponentialBt).setVisibility(View.VISIBLE);
            }
            else {
                findViewById(R.id.squareRootBt).setVisibility(View.GONE);
                findViewById(R.id.exponentialBt).setVisibility(View.GONE);
            }
        }
    }

    public void onClick(View view) {
        String textButton = getTextButton(view);
        concatDisplayText(textButton);
    }

    public void clearDisplay(View view) {
        displayTv.setText("");
    }

    public void showResult(View view) {
        String expressionText = displayTv.getText().toString();
        expressionText = expressionText.replace("√","sqrt");
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