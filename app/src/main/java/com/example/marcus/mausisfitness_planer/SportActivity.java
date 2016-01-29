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
import android.widget.LinearLayout;
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

    private LinearLayout sportLayout;
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

        sportLayout = (LinearLayout) findViewById(R.id.layoutSportDaten);

        intent = new Intent(this.getApplicationContext(), this.getClass());

        loadSportEinheitenFromDatabase();

        addSport = (Button) findViewById(R.id.buttonSportHinzufuegen);
        addSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(v.getContext(), HinzufuegenSportActivity.class);
                intent.putExtra("ID", -1);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

    }

    private void loadSportEinheitenFromDatabase() {

        Cursor c = MausisFitnessAppMainActivity.db.rawQuery("SELECT * FROM " + MausisFitnessAppMainActivity.TABLE_SPORT_NAME + " ORDER BY year DESC, month DESC, day DESC" , null);

        sportLayout.removeAllViews();

        if(c.moveToFirst()) {
            do {

                int currentValueID = c.getInt(c.getColumnIndex("id"));
                int currentValueGewicht = c.getInt(c.getColumnIndex("dauer"));
                String currentValueType = c.getString(c.getColumnIndex("type"));
                String currentValueDay = c.getString(c.getColumnIndex("day"));
                String currentValueMonth = c.getString(c.getColumnIndex("month"));
                String currentValueYear = c.getString(c.getColumnIndex("year"));

                final MyRowLayout tempRow = new MyRowLayout(this.getApplicationContext(), currentValueID, currentValueType, currentValueGewicht, currentValueDay, currentValueMonth, currentValueYear);
                tempRow.getBtEdit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.setClass(v.getContext(), HinzufuegenSportActivity.class);
                        intent.putExtra("ID", tempRow.getID());
                        startActivityForResult(intent, PICK_CONTACT_REQUEST);
                    }
                });

                sportLayout.addView(tempRow);

            } while(c.moveToNext());
        }
    }
}
