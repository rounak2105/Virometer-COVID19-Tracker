package com.example.uiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Guid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
        Content content = new Content();
        content.execute();

    }

    private class Content extends AsyncTask<Void, Void, Void> {

        Document hdoc=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                hdoc = Jsoup.connect("https://www.livemint.com/topic/coronavirus").timeout(3000).get();
            }catch (IOException e){Toast.makeText(getApplicationContext(),"Cant connect",Toast.LENGTH_LONG).show(); }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                TextView htv1 = findViewById(R.id.tt1);
                TextView htv2 = findViewById(R.id.tt2);
                TextView htv3 = findViewById(R.id.tt3);
                TextView htv4 = findViewById(R.id.tt4);
                TextView htv5 = findViewById(R.id.tt5);
                TextView htv6 = findViewById(R.id.tt6);
                TextView htv7 = findViewById(R.id.tt7);
                ImageView iv1 = findViewById(R.id.imageView1);
                ImageView iv2 = findViewById(R.id.imageView2);
                ImageView iv3 = findViewById(R.id.imageView3);
                ImageView iv4 = findViewById(R.id.imageView4);
                ImageView iv5 = findViewById(R.id.imageView5);
                ImageView iv6 = findViewById(R.id.imageView6);
                ImageView iv7 = findViewById(R.id.imageView7);
                Elements ttr = hdoc.getElementsByClass("headline");
                htv1.setText(ttr.get(0).text());
                htv2.setText(ttr.get(1).text());
                htv3.setText(ttr.get(2).text());
                htv4.setText(ttr.get(3).text());
                htv5.setText(ttr.get(4).text());
                htv6.setText(ttr.get(5).text());
                htv7.setText(ttr.get(6).text());
                Elements ddd = hdoc.getElementsByClass("imgSec");
                Element image0 = ddd.select("img").get(0);
                String iurl0 = image0.absUrl("data-src");
                Picasso.with(getApplicationContext()).load(iurl0).resize(iv3.getWidth(),iv3.getHeight()).into(iv1);
                Element image1 = ddd.select("img").get(1);
                String iurl1 = image1.absUrl("data-src");
                Picasso.with(getApplicationContext()).load(iurl1).resize(iv3.getWidth(),iv3.getHeight()).into(iv2);
                Element image2 = ddd.select("img").get(2);
                String iurl2 = image2.absUrl("data-src");
                Picasso.with(getApplicationContext()).load(iurl2).resize(iv3.getWidth(),iv3.getHeight()).into(iv3);
                Element image3 = ddd.select("img").get(3);
                String iurl3 = image3.absUrl("data-src");
                Picasso.with(getApplicationContext()).load(iurl3).resize(iv3.getWidth(),iv3.getHeight()).into(iv4);
                Element image4 = ddd.select("img").get(4);
                String iurl4 = image4.absUrl("data-src");
                Picasso.with(getApplicationContext()).load(iurl4).resize(iv3.getWidth(),iv3.getHeight()).into(iv5);
                Element image5 = ddd.select("img").get(5);
                String iurl5 = image5.absUrl("data-src");
                Picasso.with(getApplicationContext()).load(iurl5).resize(iv3.getWidth(),iv3.getHeight()).into(iv6);
                Element image6 = ddd.select("img").get(6);
                String iurl6 = image6.absUrl("data-src");
                Picasso.with(getApplicationContext()).load(iurl6).resize(iv3.getWidth(),iv3.getHeight()).into(iv7);


            }catch (NullPointerException e){  Toast.makeText(getApplicationContext(),"Check your network connection",Toast.LENGTH_LONG).show();    }
        }
    }


    public void hOnAction(View view) {
        Toast.makeText(getApplicationContext(),"Loading",Toast.LENGTH_LONG).show();
        Content content = new Content();
        content.execute();

    }
}

