package com.example.uiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Content content = new Content();
        content.execute();
    }

    float x1,x2,y1,y2;

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                Intent i = new Intent(MainActivity.this, News.class);
                startActivity(i);
            }else if(x1 > x2){
                Intent i = new Intent(MainActivity.this, Guid.class);
                startActivity(i);
            }
            break;
        }
        return false;
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        Document doc=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                doc = Jsoup.connect("https://www.worldometers.info/coronavirus/country/india/").timeout(2000).get();
            }catch (IOException e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                TextView tv1 = findViewById(R.id.o_tc);
                TextView tv2 = findViewById(R.id.o_dc);
                TextView tv3 = findViewById(R.id.o_ac);
                Element on = doc.getElementById("maincounter-wrap");
                Elements cc = on.getElementsByTag("span");
                tv1.setText(cc.text());//cases

                Element dt = doc.getElementById("maincounter-wrap").nextElementSibling();
                Elements dd = dt.getElementsByTag("span");
                tv2.setText(dd.text());//death

                Element rv = doc.getElementById("maincounter-wrap").nextElementSibling().nextElementSibling();
                Elements rc = rv.getElementsByTag("span");
                tv3.setText(rc.text());//recovered

            }catch (NullPointerException e){  Toast.makeText(getApplicationContext(),"Check your network connection",Toast.LENGTH_LONG).show();    }
        }
    }


    public void OnAction(View view) {
        Toast.makeText(getApplicationContext(),"Loading",Toast.LENGTH_LONG).show();
        Content content = new Content();
        content.execute();

    }
}
