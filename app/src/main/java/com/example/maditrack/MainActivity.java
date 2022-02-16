package com.example.maditrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.maditrack.activity.AboutUsActivity;
import com.example.maditrack.activity.AppointmentActivity;
import com.example.maditrack.activity.CategoryActivity;
import com.example.maditrack.activity.ConsumptionActivity;
import com.example.maditrack.activity.HealthBioActivity;
import com.example.maditrack.activity.HelpActivity;
import com.example.maditrack.activity.ICETabLayoutActivity;
import com.example.maditrack.activity.MeasurementActivity;
import com.example.maditrack.activity.MedicineActivity;
import com.example.maditrack.activity.PersonActivity;

import com.example.maditrack.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ImageView categoryimage, appoinmentimage, iceimage, healthbioimage, medicineimage, measurementimage, consumptionimage, aboutusimage;
    View mview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mview = getLayoutInflater().inflate(R.layout.content_main,null);

        healthbioimage = (ImageView) findViewById(R.id.HEALTH);
        healthbioimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HealthBioActivity.class);
                startActivity(intent);
            }
        });

        iceimage = (ImageView) findViewById(R.id.ICE);
        iceimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ICETabLayoutActivity.class);
                startActivity(intent);
            }
        });

        consumptionimage = (ImageView) findViewById(R.id.Consumption);
        consumptionimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsumptionActivity.class);
                startActivity(intent);
            }
        });

        categoryimage = (ImageView) findViewById(R.id.Category);
        categoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);


            }
        });

        medicineimage = (ImageView) findViewById(R.id.Medicine);
        medicineimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicineActivity.class);
                startActivity(intent);
            }
        });

        measurementimage = (ImageView) findViewById(R.id.Measurement);
        measurementimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeasurementActivity.class);
                startActivity(intent);
            }
        });

        appoinmentimage = (ImageView) findViewById(R.id.Appointment);
        appoinmentimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
                startActivity(intent);
            }
        });

        aboutusimage = (ImageView) findViewById(R.id.About_Us);
        aboutusimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), HelpActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_person) {
          startActivity(new Intent(getApplicationContext(), PersonActivity.class));
        }
        if (id == R.id.nav_healthBio) {
            startActivity(new Intent(getApplicationContext(), HealthBioActivity.class));
        }
        if (id == R.id.nav_ICE) {
            startActivity(new Intent(getApplicationContext(), ICETabLayoutActivity.class));
        }
        if (id == R.id.nav_category) {
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        }
        if (id == R.id.nav_medicine) {
            startActivity(new Intent(getApplicationContext(), MedicineActivity.class));
        }
        if (id == R.id.nav_measurement) {
            startActivity(new Intent(getApplicationContext(), MeasurementActivity.class));
        }
        if (id == R.id.nav_appointment) {
            startActivity(new Intent(getApplicationContext(), AppointmentActivity.class));
        }

        if (id == R.id.nav_consumption) {
            startActivity(new Intent(getApplicationContext(), ConsumptionActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
