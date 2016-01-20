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

public class SportActivity extends Activity {

    public static final String TAG_DAUER = "dauer";
    public static final String TAG_DATE = "milis";
    public static final String TAG_TYPE = "type";

    public static final int PICK_CONTACT_REQUEST = 2;  // The request code


    private ArrayList<Sport> sportAktivitaeten = new ArrayList<Sport>();
    private TableLayout sportLayout;
    private ArrayList<TableRow> rows = new ArrayList<TableRow>();
    private Button addSport;
    private Intent intent;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        init();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            sportAktivitaeten.add(new Sport(data.getDoubleExtra(TAG_DAUER, 0), data.getLongExtra(TAG_DATE, 0), data.getStringExtra(TAG_TYPE)));

            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

            TextView tvDate = new TextView(this.getApplicationContext());
            tvDate.setText("" + sdf.format(data.getLongExtra(TAG_DATE, 0)));
            tvDate.setLayoutParams(params);
            tvDate.setTextSize(20f);

            TextView tvType = new TextView(this.getApplicationContext());
            tvType.setText("" + data.getDoubleExtra(TAG_TYPE, 1.1));
            tvType.setLayoutParams(params);
            tvType.setTextSize(20f);

            TextView tvDauer = new TextView(this.getApplicationContext());
            tvDauer.setText("" + data.getDoubleExtra(TAG_DAUER, 1.1));
            tvDauer.setLayoutParams(params);
            tvDauer.setTextSize(20f);

            rows.add(new TableRow(this.getApplicationContext()));
            rows.get(rows.size() - 1).addView(tvDate);
            rows.get(rows.size() - 1).addView(tvType);
            rows.get(rows.size() - 1).addView(tvDauer);

            sportLayout.addView(rows.get(rows.size() - 1));

        }
    }

    private void init() {

        sportLayout = (TableLayout) findViewById(R.id.layoutSportDaten);

        intent = new Intent(this.getApplicationContext(), this.getClass());

        for(int i = 0; i < sportAktivitaeten.size(); i++) {

            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

            TextView tvDate = new TextView(this.getApplicationContext());
            tvDate.setText("" + sdf.format(sportAktivitaeten.get(i).getDateMillis()));
            tvDate.setLayoutParams(params);
            tvDate.setTextSize(20f);

            TextView tvType = new TextView(this.getApplicationContext());
            tvType.setText("" + sportAktivitaeten.get(i).getType());
            tvType.setLayoutParams(params);
            tvType.setTextSize(20f);

            TextView tvDauer = new TextView(this.getApplicationContext());
            tvDauer.setText("" + sportAktivitaeten.get(i).getDauer());
            tvDauer.setLayoutParams(params);
            tvDauer.setTextSize(20f);

            rows.add(new TableRow(this.getApplicationContext()));
            rows.get(i).addView(tvDate);
            rows.get(i).addView(tvType);
            rows.get(i).addView(tvDauer);

            sportLayout.addView(rows.get(i));
        }

        addSport = (Button) findViewById(R.id.buttonSportHinzufuegen);
        addSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(v.getContext(), HinzufuegenSportActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

    }
}
