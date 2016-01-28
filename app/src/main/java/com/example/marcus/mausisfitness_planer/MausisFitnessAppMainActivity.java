package com.example.marcus.mausisfitness_planer;

import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MausisFitnessAppMainActivity extends TabActivity {

    public static final String TAB_SPORT = "Sport";
    public static final String TAB_GEWICHT = "Gewicht";
    private static final String DATABASE_NAME = "DATABASE.db";

    public static final String TABLE_SPORT_NAME = "SportEinheiten";
    public static final String TABLE_GEWICHT_NAME = "GewichtWerte";

    private final String CREATE_GEWICHT_TABLE = "CREATE TABLE "
            + TABLE_GEWICHT_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT, day INTEGER, month INTEGER, year INTEGER, wert REAL);";

    private final String CREATE_SPORT_TABLE = "CREATE TABLE "
            + TABLE_SPORT_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT, type STRING, day INTEGER, month INTEGER, year INTEGER, dauer REAL);";

    public static SQLiteDatabase db;

    private TabSpec tabSport;
    private TabSpec tabGewicht;


    private LinearLayout layoutTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mausis_app_main_layout);

        db = openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);

        // Tabelle löschen
        try {
            db.execSQL("DROP TABLE " + MausisFitnessAppMainActivity.TABLE_GEWICHT_NAME);
            db.execSQL("DROP TABLE " + MausisFitnessAppMainActivity.TABLE_SPORT_NAME);
        } catch(Exception ex) {
            Log.d("MYLOG ", "Eine der Tabellen war nicht vorhanden aber egal xD");
        }

         db.execSQL(CREATE_GEWICHT_TABLE);
         db.execSQL(CREATE_SPORT_TABLE);

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

    public static void println(String tablename){
        String selectQuery = "SELECT * FROM " + tablename;

        // Achtung!!! ggf die Datenbank-Connection von aussen rein geben
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            // +++ Tabellenkopf +++
            String spalteTrennzeichen = " | ";
            String zeileTrennzeichen = "-----------------------------------------";
            String zeile = spalteTrennzeichen;

            for (String name : cursor.getColumnNames()) {
                zeile = zeile + name + spalteTrennzeichen;
            }

            // Ausgabe
            Log.d(tablename, zeileTrennzeichen);
            Log.d(tablename, zeile);
            Log.d(tablename, zeileTrennzeichen);

            // ueber den Cursor iterieren
            do {
                // +++ Tabellenzeile +++
                zeile = spalteTrennzeichen;
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String neu = cursor.getString(i);

                    // Inhalt formatieren
                    if(neu == null){
                        neu = "null";
                    }else{
                        if(neu.length() > 20){
                            neu = neu.substring(0, 19);
                            neu = neu + "...";
                        }
                    }

                    zeile = zeile + neu;
                    // Trennzeichen
                    zeile = zeile + spalteTrennzeichen;
                }
                Log.d(tablename, zeile);
            } while (cursor.moveToNext());
            Log.d(tablename, zeileTrennzeichen);
        }
    }
}
