package com.example.marcus.mausisfitness_planer;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MausisFitnessAppMainActivity extends TabActivity {

    public static final String TAB_SPORT = "Sport";
    public static final String TAB_GEWICHT = "Gewicht";

    private TabSpec tabSport;
    private TabSpec tabGewicht;

    private LinearLayout layoutTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mausis_app_main_layout);


        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        tabSport = tabHost.newTabSpec("Sport");
        tabGewicht = tabHost.newTabSpec("Gewicht");

        tabSport.setIndicator(TAB_SPORT);
        tabSport.setContent(new Intent(this, SportActivity.class));

        tabGewicht.setIndicator(TAB_GEWICHT);
        tabGewicht.setContent(new Intent(this, GewichtActivity.class));

        tabHost.addTab(tabSport);
        tabHost.addTab(tabGewicht);

    }
}
