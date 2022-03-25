package com.example.finnkinotheaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Spinner theatersSpinner;
    Spinner movieSpinner;
    ArrayList<Theater> theaters;
    ArrayList<String> spinnerItems;
    Context context = null;

    TheatersDatabase theatersDatabase = TheatersDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = MainActivity.this;

        theatersSpinner = findViewById(R.id.theatersSpinner);
        movieSpinner = findViewById(R.id.movieSpinner);

        theatersDatabase.XMLreader();
        theaters = theatersDatabase.returnTheaters();
        spinnerItems = new ArrayList<>();

        for (int i = 0; i < theaters.size(); i++) {
            spinnerItems.add(theaters.get(i).getName());
        }

        // adding items to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        theatersSpinner.setAdapter(adapter);
        theatersSpinner.setSelection(0);

        theatersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void readXML(View v) {
        theatersDatabase.XMLreader();
    }
}