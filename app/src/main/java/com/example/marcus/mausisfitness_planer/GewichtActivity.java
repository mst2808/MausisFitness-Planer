package com.example.marcus.mausisfitness_planer;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GewichtActivity extends Activity {

    private ArrayList<Gewicht> gewichte = new ArrayList<Gewicht>();
    private TableLayout gewichteLayout;
    private TableRow[] rows;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gewicht);

        gewichteLayout = (TableLayout) findViewById(R.id.layoutGewichtDaten);

        gewichte.add(new Gewicht(75));

        rows = new TableRow[gewichte.size()];

        for(int i = 0; i < gewichte.size(); i++) {

            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

            TextView tvDate = new TextView(this.getApplicationContext());
            tvDate.setText("" + sdf.format(gewichte.get(i).getDateMillis()));
            tvDate.setLayoutParams(params);
            tvDate.setTextSize(20f);

            TextView tvValue = new TextView(this.getApplicationContext());
            tvValue.setText("" + gewichte.get(i).getGewicht());
            tvValue.setLayoutParams(params);
            tvValue.setTextSize(20f);

            rows[i] = new TableRow(this.getApplicationContext());
            rows[i].addView(tvDate);
            rows[i].addView(tvValue);

            gewichteLayout.addView(rows[i]);
        }


    }

}
