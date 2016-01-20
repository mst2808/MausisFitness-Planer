package com.example.marcus.mausisfitness_planer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GewichtActivity extends Activity {

    public static final String TAG_GEWICHT = "gewicht";
    public static final String TAG_DATE = "gewicht";
    public static final int PICK_CONTACT_REQUEST = 1;  // The request code


    private ArrayList<Gewicht> gewichte = new ArrayList<Gewicht>();
    private TableLayout gewichteLayout;
    private ArrayList<TableRow> rows = new ArrayList<TableRow>();
    private Button addGewicht;
    private Intent intent;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gewicht);

        init();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            gewichte.add(new Gewicht(data.getDoubleExtra(TAG_GEWICHT, 0), data.getLongExtra(TAG_DATE, 0)));

            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

            TextView tvDate = new TextView(this.getApplicationContext());
            tvDate.setText("" + sdf.format(data.getLongExtra(TAG_DATE, 0)));
            tvDate.setLayoutParams(params);
            tvDate.setTextSize(20f);

            TextView tvValue = new TextView(this.getApplicationContext());
            tvValue.setText("" + data.getStringExtra(TAG_GEWICHT));
            tvValue.setLayoutParams(params);
            tvValue.setTextSize(20f);

            rows.add(new TableRow(this.getApplicationContext()));
            rows.get(rows.size() - 1).addView(tvDate);
            rows.get(rows.size() - 1).addView(tvValue);

            gewichteLayout.addView(rows.get(rows.size() - 1));

        }
    }

    private void init() {

        gewichteLayout = (TableLayout) findViewById(R.id.layoutGewichtDaten);

        intent = new Intent(this.getApplicationContext(), this.getClass());

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

            rows.add(new TableRow(this.getApplicationContext()));
            rows.get(i).addView(tvDate);
            rows.get(i).addView(tvValue);

            gewichteLayout.addView(rows.get(i));
        }

        addGewicht = (Button) findViewById(R.id.buttonGewichtHinzufuegen);
        addGewicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(v.getContext(), HinzufuegenGewichtActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

    }
}
