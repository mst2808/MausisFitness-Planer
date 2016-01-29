package com.example.marcus.mausisfitness_planer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GewichtActivity extends Activity {

    public static final String TAG_GEWICHT = "gewicht";
    public static final String TAG_DATE = "milis";
    public static final int PICK_CONTACT_REQUEST = 1;  // The request code


    private LinearLayout gewichteLayout;
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
            loadGewichteFromDatabase();
        }
    }

    private void loadGewichteFromDatabase() {

        Cursor c = MausisFitnessAppMainActivity.db.rawQuery("SELECT * FROM " + MausisFitnessAppMainActivity.TABLE_GEWICHT_NAME + " ORDER BY year DESC, month DESC, day DESC" , null);

        gewichteLayout.removeAllViews();

        if(c.moveToFirst()) {
            do {
                int currentValueID = c.getInt(c.getColumnIndex("id"));
                int currentValueGewicht = c.getInt(c.getColumnIndex("wert"));
                String currentValueDay = c.getString(c.getColumnIndex("day"));
                String currentValueMonth = c.getString(c.getColumnIndex("month"));
                String currentValueYear = c.getString(c.getColumnIndex("year"));

                final MyRowLayout tempRow = new MyRowLayout(this.getApplicationContext(), currentValueID, currentValueGewicht, currentValueDay, currentValueMonth, currentValueYear);
                tempRow.getBtEdit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.setClass(v.getContext(), HinzufuegenGewichtActivity.class);
                        intent.putExtra("ID", tempRow.getID());
                        startActivityForResult(intent, PICK_CONTACT_REQUEST);
                    }
                });

                gewichteLayout.addView(tempRow);

            } while(c.moveToNext());
        }
    }

    private void init() {

        gewichteLayout = (LinearLayout) findViewById(R.id.layoutGewichtDaten);

        intent = new Intent(this.getApplicationContext(), this.getClass());

        loadGewichteFromDatabase();

        addGewicht = (Button) findViewById(R.id.buttonGewichtHinzufuegen);
        addGewicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("ID", -1);
                intent.setClass(v.getContext(), HinzufuegenGewichtActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

    }
}
