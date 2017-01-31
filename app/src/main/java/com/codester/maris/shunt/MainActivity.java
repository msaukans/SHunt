package com.codester.maris.shunt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ListView list;
    String[] text = {
            "Temple Bar",
            "Google Plus",
            "Twitter",
            "Windows"
    };
    Integer[] imageId = {
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera
    };
    ImageView img1,img2,img3,img4,img5,img6,img7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });//end setOnClickListener*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /////////////////////////// ListView code starts ////////////////////////////////
        /*CustomList adapter = new CustomList(MainActivity.this, text, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, text[+position] + "\n 5 points", Toast.LENGTH_SHORT).show();

            }
        });*/

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);
        img7 = (ImageView) findViewById(R.id.img7);

        img1.setImageResource(R.mipmap.ic_launcher);
        img1.setOnClickListener(this);
        img2.setImageResource(R.drawable.ic_link);
        img2.setOnClickListener(this);
        img3.setImageResource(R.drawable.ic_con);
        img3.setOnClickListener(this);
        img4.setImageResource(R.drawable.ic_flag);
        img4.setOnClickListener(this);
        img5.setImageResource(R.drawable.ic_walk2);
        img5.setOnClickListener(this);
        img6.setImageResource(R.drawable.ic_movie);
        img6.setOnClickListener(this);
        img7.setImageResource(R.drawable.ic_menu_manage);
        img7.setOnClickListener(this);
    }//end onCreate method

    public void onClick(View v) {
        if(v == img1){
            startActivity(new Intent(MainActivity.this,Scrape.class));
        }

        else if(v == img2){
            startActivity(new Intent(MainActivity.this,web.class));
        }
        else if(v == img3){
           startActivity(new Intent(MainActivity.this,connectivity.class));
        }
        else if(v == img4){
            startActivity(new Intent(MainActivity.this,MapsActivity.class));
        }
        else if(v == img5){
           startActivity(new Intent(MainActivity.this,StepCounter.class));
        }
        else if(v == img6){
            startActivity(new Intent(MainActivity.this,vid.class));
        }
        else if(v == img7){
            startActivity(new Intent(MainActivity.this,createT.class));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //new
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        RelativeLayout content_view = (RelativeLayout)findViewById(R.id.content_main);
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.menu_Red:
                if (item.isChecked()){
                    item.setChecked(false);
                }
                else
                    item.setChecked(true);
                content_view.setBackgroundColor(Color.RED);
                return true;

            case R.id.menu_Green:
                if (item.isChecked()){
                    item.setChecked(false);
                }
                else
                    item.setChecked(true);
                content_view.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.menu_White:
                if (item.isChecked()){
                    item.setChecked(false);
                }
                else
                    item.setChecked(true);
                content_view.setBackgroundColor(Color.WHITE);
                return true;

            case R.id.menu_Yellow:
                if (item.isChecked()){
                    item.setChecked(false);
                }
                else
                    item.setChecked(true);
                content_view.setBackgroundColor(Color.YELLOW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end method onOptionsItemSelected

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_rank) {
            startActivity(new Intent(MainActivity.this, web.class));//open website
        } else  if(id == R.id.nav_createT){
            startActivity(new Intent(MainActivity.this,createT.class));
        } else if (id == R.id.nav_settio) {
            startActivity(new Intent(MainActivity.this,MapsActivity.class));//open map --change auto search
        } else if (id == R.id.nav_share) {
           startActivity(new Intent(MainActivity.this,connectivity.class));//switch on/off bluetooth/wifi
        }
        else if (id == R.id.nav_newTask) {
            this.startActivity(new Intent(MainActivity.this, Scrape.class));//scrape data ---move
        }
        else if (id == R.id.nav_walked) {
            startActivity(new Intent(MainActivity.this,StepCounter.class));//steps taken
        }
        else if (id == R.id.nav_out) {
            startActivity(new Intent(MainActivity.this,vid.class));//play video
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }//end onNavigationItemSelected
}//end class MainActivity