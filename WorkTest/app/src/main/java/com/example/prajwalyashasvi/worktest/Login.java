package com.example.prajwalyashasvi.worktest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;


public class Login extends Activity {

    private EditText username = null;
    private EditText password = null;

    private Button login;
    int counter = 7;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button1);
        intent = new Intent(this, landinguser.class);
    }

    public void login(View view) {
        Log.i("intentq", "started login"+username.getText().toString());
        if(new logindemoDb().execute()!=null){//.toString().equalsIgnoreCase("Success")) {
            if (intent != null) {
                Log.i("intentq", "started login intent");
                // Create an intent stating which Activity you would like to start
                intent.putExtra("uname",username.getText().toString());
                // Launch the Activity using the intent
                startActivity(intent);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "wrong details",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    private class logindemoDb extends AsyncTask<String,Void,Object> {
        @Override
        protected Object doInBackground(String... urls){
            try{
                String qstr = "uname="+username.getText().toString()+"&pswd="+password.getText().toString();
                String link = "http://weblab.cs.uml.edu/~ayallapr/prajwalAndroid/logindemo.php?"+qstr;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader
                        (new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line="";
                while ((line = in.readLine()) != null) {
                    if (line.equalsIgnoreCase("Success")) {
                        sb.append(line);
                    }
                }
                in.close();
                return sb.toString();
            }catch(Exception e){
                Log.i("exp", e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

    }
}
