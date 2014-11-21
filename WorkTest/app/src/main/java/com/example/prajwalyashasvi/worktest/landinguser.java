package com.example.prajwalyashasvi.worktest;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.franckamayou.barcodescanningapp.ScanActivity;


public class landinguser extends Activity {

    private Button sAllBook;
    int counter = 3;
    String uname;
    private Intent intent1,intent2,intent3,intent4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landinguser);
        sAllBook = (Button)findViewById(R.id.buttonBook);
        intent1 = new Intent(this, allBooks.class);
        intent2 = new Intent(this, userSellAd.class);
        intent3 = new Intent(this, userBuyReq.class);
        intent4=new Intent(this, ScanActivity.class);
        uname=getIntent().getExtras().getString("uname");
    }

    public void searchDB(View view){
        Log.i("db", "Dbconnect()");
        if (intent1 != null) {
            Log.i("intentq", "started");
            // Create an intent stating which Activity you would like to start
            // Launch the Activity using the intent
            startActivity(intent1);
        }
    }
    public void searchSellUser(View view) {
        Log.i("db", "Dbconnect()");
        if (intent2 != null) {
            Log.i("intentq", "started");
            // Create an intent stating which Activity you would like to start
            intent2.putExtra("uname",uname);
            // Launch the Activity using the intent
            startActivity(intent2);
        }
    }
    public void searchBuyUser(View view){
        Log.i("db", "Dbconnect()");
        if (intent3 != null) {
            Log.i("intentq", "started");
            // Create an intent stating which Activity you would like to start
            intent3.putExtra("uname",uname);
            // Launch the Activity using the intent
            startActivity(intent3);
        }
    }
    public void sellNewBook(View view){
        Log.i("db", "Dbconnect()");
        if (intent4 != null) {
            Log.i("intentq", "started");
            // Create an intent stating which Activity you would like to start
            intent4.putExtra("uname",uname);
            // Launch the Activity using the intent
            startActivity(intent4);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landinguser, menu);
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

}
