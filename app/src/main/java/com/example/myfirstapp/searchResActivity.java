package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class searchResActivity extends AppCompatActivity {

    TextView Amazon;
    TextView Flipkart;
    TextView product;
    private static RequestQueue myRequestQueue = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_res);
        //views Declaration
        Amazon = (TextView) findViewById(R.id.textView3);
        Flipkart = (TextView) findViewById(R.id.textView4);
        product = (TextView) findViewById(R.id.textView2);
        //initial values
        initialize_views();
        //call to web scrapers for individual sites
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
        Amazon.setText("Loading...");
        Flipkart.setText("Loading...");

        //initialize product searched
        product.setText(getProduct());

    }

    //scrapes Amazon for product price
    public void AmazonApiCall() {
        String product = getProduct();

        // setpricefromAmazon

        //prepare string to url parsing
        product = product.replace(" ", "%20");
        final Uri searchquery = Uri.parse("https://www.amazon.in/s?k=" + product);

        final Document[] doc = {null};
        //doc variable which will store the response of the website
            new Thread(new Runnable() {
                //running in new thread as internet calls cannot run on main thread
                @Override
                public void run() {
                    try {
                        //try catch to avoid error 404
                        //use jsoup connect to scrape amazon
                        doc[0] = Jsoup.connect(String.valueOf(searchquery))
                                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                                .get();
                        //query response using cssQuery in doc
                        Elements products = doc[0].select(".a-price-whole");
                        //".a-price-whole" is a cssQuery that can match prices on Amazon.in
                        if (products!=null) {
                            //setValue only if product exist
                            //using 2nd array element to avoid sponsored productS
                            Amazon.setText("₹" + products.get(2).html());
                        }else{
                            //else set not found
                            Amazon.setText("Product Not Found");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();//start thread

        //setOnClicklink on imageview
        ImageView Amazonln = (ImageView) findViewById(R.id.imageView4);
        Amazonln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, searchquery);
                startActivity(intent);
                //launch Amazon on browser
            }
        });
    }//end of Amazon scraper

    public void FlipkartApiCall(){
        String product = getProduct();

        //setpricefromFlipkart

        //setOnClicklink on imageview
        product = product.replace(" ","%20");
        final Uri searchquery = Uri.parse("https://www.flipkart.com/s?k="+product);
        //TODO - Flipkart webscraper functionality here
        ImageView Flipkartln = (ImageView) findViewById(R.id.imageView6);

        Flipkartln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,searchquery);
                startActivity(intent);
                //launch Flipkart on browser
            }
        });

    }



}