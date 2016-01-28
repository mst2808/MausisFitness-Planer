package com.example.marcus.mausisfitness_planer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class HinzufuegenSportActivity extends Activity {

    private Intent intent;
    private Button buttonSendFormular;
    private Spinner spType;
    private EditText etDauer;
    private CheckBox checkBox;
    private DatePicker datePicker;

    private static final String[] items = {"Stepper", "Fahrrad", "Schwimmen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinzufuegen_sport);

        intent = getIntent();
        intent.setClass(this.getApplicationContext(), SportActivity.class);

        spType = (Spinner) findViewById(R.id.spType);

        etDauer = (EditText) findViewById(R.id.etDauer);

        buttonSendFormular = (Button)findViewById(R.id.buttonAddSport);
        buttonSendFormular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double temp = Double.parseDouble(etDauer.getText().toString());

                Calendar cal = Calendar.getInstance();

                if (checkBox.isChecked()) {
                    MausisFitnessAppMainActivity.db.execSQL("" +
                            "INSERT INTO "
                            + MausisFitnessAppMainActivity.TABLE_SPORT_NAME + " (type, day, month, year, dauer) VALUES ('"
                            + spType.getSelectedItem().toString() + "', "
                            + cal.get(Calendar.DAY_OF_MONTH) + ", "
                            + (1 + cal.get(Calendar.MONTH)) + ", "
                            + cal.get(Calendar.YEAR) + ", "
                            + temp + ");");
                } else {
                    MausisFitnessAppMainActivity.db.execSQL("" +
                            "INSERT INTO "
                            + MausisFitnessAppMainActivity.TABLE_GEWICHT_NAME + " (type, day, month, year, dauer) VALUES ('"
                            + spType.getSelectedItem().toString() + "', "
                            + datePicker.getDayOfMonth() + ", "
                            + (1 + datePicker.getMonth()) + ", "
                            + datePicker.getYear() + ", "
                            + temp + ");");
                }


                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        checkBox = (CheckBox) findViewById(R.id.checkBoxHeute);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    datePicker.setVisibility(View.INVISIBLE);
                } else {
                    datePicker.setVisibility(View.VISIBLE);
                }
            }
        });

        datePicker = (DatePicker) findViewById(R.id.datePicker);

    }

}
