package com.example.zoli.weatherxml;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String url2 = "&mode=xml";
    private EditText city,temperature,windspeed;
    private MyString obj;
    private HandleXML myxml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.button1);

        button1.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View z){
                        city = (EditText)findViewById(R.id.editText1);
                        String url = city.getText().toString();
                        String finalUrl = url1 + url + url2;
                        temperature = (EditText)findViewById(R.id.editText2);
                        windspeed = (EditText)findViewById(R.id.editText3);
                        myxml =new HandleXML(finalUrl);
                        myxml.fetchXML();
                        while(myxml.parsingComplete);
                        temperature.setText(KelvintoCelsius(myxml.getTemperature()));
                        windspeed.setText(myxml.getWindspeed());

                    }

                }

        );

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String KelvintoCelsius (String s) {
        double kelvin=Integer.parseInt(s);
        double cel=kelvin-272.1;
        String r=String.valueOf(cel);
        return r;


    }
}
