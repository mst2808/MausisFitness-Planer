package com.example.marcus.mausisfitness_planer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
            loadSportEinheitenFromDatabase();
        }
    }

    private void init() {

        sportLayout = (TableLayout) findViewById(R.id.layoutSportDaten);

        intent = new Intent(this.getApplicationContext(), this.getClass());

        loadSportEinheitenFromDatabase();

        addSport = (Button) findViewById(R.id.buttonSportHinzufuegen);
        addSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(v.getContext(), HinzufuegenSportActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

    }

    private void loadSportEinheitenFromDatabase() {

        Cursor c = MausisFitnessAppMainActivity.db.rawQuery("SELECT * FROM " + MausisFitnessAppMainActivity.TABLE_SPORT_NAME + " ORDER BY year DESC, month DESC, day DESC" , null);

        sportLayout.removeAllViews();

        if(c.moveToFirst()) {
            do {

                TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

                TableRow tempRow = new TableRow(this.getApplicationContext(), null);
                TextView tvDate = new TextView(this.getApplicationContext());
                TextView tvType = new TextView(this.getApplicationContext());
                TextView tvDauer = new TextView(this.getApplicationContext());

                String currentDate = "";

                if(c.getString(c.getColumnIndex("day")).length() == 1) {
                    currentDate += "0" + c.getString(c.getColumnIndex("day")) + ".";
                } else {
                    currentDate += c.getString(c.getColumnIndex("day")) + ".";
                }

                if(c.getString(c.getColumnIndex("month")).length() == 1) {
                    currentDate += "0" + c.getString(c.getColumnIndex("month")) + ".";
                } else {
                    currentDate += c.getString(c.getColumnIndex("month")) + ".";
                }
                currentDate += c.getString(c.getColumnIndex("year"));

                tvDate.setLayoutParams(params);
                tvDate.setTextSize(20f);
                tvDate.setText(currentDate);

                tvType.setLayoutParams(params);
                tvType.setTextSize(20f);
                tvType.setText(c.getString(c.getColumnIndex("type")));

                tvDauer.setLayoutParams(params);
                tvDauer.setTextSize(20f);
                tvDauer.setText(c.getString(c.getColumnIndex("dauer")));

                tempRow.addView(tvDate);
                tempRow.addView(tvType);
                tempRow.addView(tvDauer);

                sportLayout.addView(tempRow);

            } while(c.moveToNext());
        }
    }
}
