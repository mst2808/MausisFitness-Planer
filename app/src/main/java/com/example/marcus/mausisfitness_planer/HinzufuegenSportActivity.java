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
                String tempType = (String) spType.getSelectedItem();

                Calendar cal = Calendar.getInstance();

                // wenn ID != -1 dann wird ein eintrag bearbeitet!
                if (intent.getIntExtra("ID", -1) != -1) {
                    if (checkBox.isChecked()) {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "UPDATE " + MausisFitnessAppMainActivity.TABLE_SPORT_NAME + " SET " +
                                "day=" + cal.get(Calendar.DAY_OF_MONTH) + ", " +
                                "month=" + (1 + cal.get(Calendar.MONTH)) + ", " +
                                "year=" + cal.get(Calendar.YEAR) + ", " +
                                "type='" + tempType + "', " +
                                "dauer=" + temp + " " +
                                "WHERE id=" + intent.getIntExtra("ID", -1) + ";");
                    } else {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "UPDATE " + MausisFitnessAppMainActivity.TABLE_SPORT_NAME + " SET " +
                                "day=" + datePicker.getDayOfMonth() + ", " +
                                "month=" + (1 + datePicker.getMonth()) + ", " +
                                "year=" + datePicker.getYear() + ", " +
                                "type='" + tempType + "', " +
                                "dauer=" + temp + " " +
                                "WHERE id=" + intent.getIntExtra("ID", -1) + ";");
                    }
                }
                // wenn ID = -1 dann wird ein neuer wert hinzugefügt!
                else {
                    if (checkBox.isChecked()) {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "INSERT INTO "
                                + MausisFitnessAppMainActivity.TABLE_SPORT_NAME + " (day, month, year, type, dauer) VALUES ("
                                + cal.get(Calendar.DAY_OF_MONTH) + ", "
                                + (1 + cal.get(Calendar.MONTH)) + ", "
                                + cal.get(Calendar.YEAR) + ", '"
                                + tempType + "', "
                                + temp + ");");
                    } else {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "INSERT INTO "
                                + MausisFitnessAppMainActivity.TABLE_SPORT_NAME + " (day, month, year, type, dauer) VALUES ("
                                + datePicker.getDayOfMonth() + ", "
                                + (1 + datePicker.getMonth()) + ", "
                                + datePicker.getYear() + ", '"
                                + tempType + "', "
                                + temp + ");");
                    }
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
