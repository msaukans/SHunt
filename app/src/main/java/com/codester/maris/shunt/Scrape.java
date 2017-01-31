package com.codester.maris.shunt;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Scrape extends AppCompatActivity {

    public Elements content;//variable name can be anything
    public ArrayList<String> contentList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    ListView lv;

    MyDBHandler db;

    private ProgressDialog spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsoup_list);

        lv = (ListView) findViewById(R.id.jlist1);

        spin = new ProgressDialog(this);
        spin.setMessage("Getting Data...");
        spin.show();
        new NewThread().execute();
        adapter = new ArrayAdapter<>(this, R.layout.jlist_item,R.id.jtextItem ,contentList);

        db = new MyDBHandler(this, null, null, 1);//object
    }//end onCreate method

    public class NewThread extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... arg){
            notififeAuto();

            Document doc;
            try{
                doc = Jsoup.connect("https://www.tripadvisor.ie/Attractions-g186605-Activities-c47-Dublin_County_Dublin.html#ATTRACTION_LIST").get();
                content = doc.select(".property_title > a");

                contentList.clear();
                for (Element contents: content){
                    contentList.add(contents.text());
                    //db.addTask(t);
                    //printDatabase();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            spin.dismiss();
            return null;

        }//end doInBackground method

        protected void onPostExecute(String result){
            lv.setAdapter(adapter);
        }//end onPostExecutive method

    }//end newThread class

    private void notififeAuto() {

        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder
                (getApplicationContext()).setContentTitle("").setContentText("Data scraping activity opened").
                setContentTitle("Autonotifcation").setSmallIcon(R.mipmap.ic_launcher).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);

    }
}
