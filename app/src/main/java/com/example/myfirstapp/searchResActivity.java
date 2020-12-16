package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class searchResActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_res);
        TextView priceAmazon = (TextView) findViewById(R.id.textView2);
        String query = getIntent().getStringExtra("query");
        priceAmazon.setText(query);
    }


}