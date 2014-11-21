package com.example.prajwalyashasvi.worktest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class userSellAd extends Activity {

    private ListView status;
    SimpleAdapter simpleAdpt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sell_ad);
        status = (ListView)findViewById(R.id.listViewSell);
        if(new searchBookAllDb(this,status).execute()!=null) {
            Toast.makeText(getApplicationContext(), "connectedDatabase",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_sell_ad, menu);
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
    private class searchBookAllDb extends AsyncTask<String,Void,String> {
        private ListView status;
        private Context context;

        private searchBookAllDb(Context context, ListView status) {
            this.context = context;
            this.status = status;
        }

        protected void onPreExecute() {

        }

        protected String doInBackground(String... urls) {
            try {
                String qstr="uname="+getIntent().getExtras().getString("uname");
                String link = "http://weblab.cs.uml.edu/~ayallapr/prajwalAndroid/sellbookins.php?"+qstr;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader
                        (new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";
                while ((line = in.readLine()) != null) {
                    //if
                    sb.append(line);
                    Log.i("dbsuccess", String.valueOf(sb));
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                Log.i("exp", e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        protected void onPostExecute(String result) {
            String d[] = result.split(",");
            List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < d.length; i++)
                dataList.add(createmap("data", d[i]));
            Log.i("books", String.valueOf(dataList.size()));
            simpleAdpt1=new SimpleAdapter(this.context, dataList, android.R.layout.simple_list_item_1, new String[]{"data"}, new int[]{android.R.id.text1});
            status.setAdapter(simpleAdpt1);
        }

    }
    private HashMap<String,String> createmap(String key,String s) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put(key,s);
        return data;
    }
}
