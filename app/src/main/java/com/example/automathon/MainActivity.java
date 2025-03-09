package com.example.automathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String gamePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button1);
        Button buttonStart = findViewById(R.id.buttonStart);
        EditText editText1 = findViewById(R.id.editText1);

        Spinner spinner = (Spinner) findViewById(R.id.availableGames);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.availableGames,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String popupText = "Not implemented yet";

                if (position == 0){
                    popupText = "Selected: " + parent.getItemAtPosition(position).toString();
                    gamePath = "com.ruotogames.pocketcrafter2";
                    buttonStart.setEnabled(true);
                }else{
                    buttonStart.setEnabled(false);
                }

                Toast.makeText(MainActivity.this, popupText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
                //we can leave this empty for now
            }
        };
        spinner.setOnItemSelectedListener(spinnerListener);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text = editText1.getText().toString();

                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Toast.makeText(MainActivity.this, gamePath, Toast.LENGTH_SHORT).show();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(gamePath);
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

    }

}


