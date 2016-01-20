package com.example.marcus.mausisfitness_planer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HinzufuegenSportActivity extends Activity {

    private Intent intent;
    private Button buttonSendFormular;
    private Spinner spType;

    private static final String[] items = {"Stepper", "Fahrrad", "Schwimmen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinzufuegen_sport);

        intent = getIntent();
        intent.setClass(this.getApplicationContext(), SportActivity.class);

        spType = (Spinner) findViewById(R.id.spType);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra(SportActivity.TAG_TYPE, (String) spType.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        buttonSendFormular = (Button)findViewById(R.id.buttonAddSport);
        buttonSendFormular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra(SportActivity.TAG_DAUER, "");
                intent.putExtra(SportActivity.TAG_DATE, System.currentTimeMillis());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

}
