package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class searchResActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_res);
        initialize_views();
        AmazonApiCall();
        FlipkartApiCall();


    }

    public String getProduct(){
        //gets product entered from mainActivity
        final String product = getIntent().getStringExtra("query");
        if (product != null){
            return product;
        }else{
            return "Not Found";
        }
    }

    public void initialize_views(){
        //price
        TextView Amazon = (TextView) findViewById(R.id.textView3);
        TextView Flipkart = (TextView) findViewById(R.id.textView4);
        Amazon.setText("Product Not Found");
        Flipkart.setText("Product Not Found");

        //initialize product searched
        TextView product = (TextView) findViewById(R.id.textView2);
        product.setText(getProduct());

        //links
        ImageView Amazonln = (ImageView) findViewById(R.id.imageView4);
        ImageView Flipkartln = (ImageView) findViewById(R.id.imageView6);
    }

    public void AmazonApiCall(){
        //TODO - setAmazonApi functionality
        class Amazonprice extends siteprice{

        }
    }

    public void FlipkartApiCall(){
        //TODO - setFlipkartApi functionality
        class Flipkartprice extends siteprice{

        }
    }


}