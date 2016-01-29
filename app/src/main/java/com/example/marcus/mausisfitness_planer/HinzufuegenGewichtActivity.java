package com.example.marcus.mausisfitness_planer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class HinzufuegenGewichtActivity extends Activity {

    private Intent intent;
    private Button buttonSendFormular;
    private EditText etGewicht;
    private CheckBox checkBox;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinzufuegen_gewicht);

        intent = getIntent();
        intent.setClass(this.getApplicationContext(), GewichtActivity.class);

        etGewicht = (EditText) findViewById(R.id.editTextGewicht);

        buttonSendFormular = (Button)findViewById(R.id.buttonAddGewicht);
        buttonSendFormular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double temp = Double.parseDouble(etGewicht.getText().toString());

                Calendar cal = Calendar.getInstance();

                // wenn ID != -1 dann wird ein eintrag bearbeitet!
                if (intent.getIntExtra("ID", -1) != -1) {
                    if (checkBox.isChecked()) {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "UPDATE " + MausisFitnessAppMainActivity.TABLE_GEWICHT_NAME + " SET " +
                                "day=" + cal.get(Calendar.DAY_OF_MONTH) + ", " +
                                "month=" + (1 + cal.get(Calendar.MONTH)) + ", " +
                                "year=" + cal.get(Calendar.YEAR) + ", " +
                                "wert=" + temp + " " +
                                "WHERE id=" + intent.getIntExtra("ID", -1) + ";");
                    } else {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "UPDATE " + MausisFitnessAppMainActivity.TABLE_GEWICHT_NAME + " SET " +
                                "day=" + datePicker.getDayOfMonth() + ", " +
                                "month=" + (1 + datePicker.getMonth()) + ", " +
                                "year=" + datePicker.getYear() + ", " +
                                "wert=" + temp + " " +
                                "WHERE id=" + intent.getIntExtra("ID", -1) + ";");
                    }
                }
                // wenn ID = -1 dann wird ein neuer wert hinzugefügt!
                else {
                    if (checkBox.isChecked()) {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "INSERT INTO "
                                + MausisFitnessAppMainActivity.TABLE_GEWICHT_NAME + " (day, month, year, wert) VALUES ("
                                + cal.get(Calendar.DAY_OF_MONTH) + ", "
                                + (1 + cal.get(Calendar.MONTH)) + ", "
                                + cal.get(Calendar.YEAR) + ", "
                                + temp + ");");
                    } else {
                        MausisFitnessAppMainActivity.db.execSQL("" +
                                "INSERT INTO "
                                + MausisFitnessAppMainActivity.TABLE_GEWICHT_NAME + " (day, month, year, wert) VALUES ("
                                + datePicker.getDayOfMonth() + ", "
                                + (1 + datePicker.getMonth()) + ", "
                                + datePicker.getYear() + ", "
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
                if(checkBox.isChecked()) {
                    datePicker.setVisibility(View.INVISIBLE);
                } else {
                    datePicker.setVisibility(View.VISIBLE);
                }
            }
        });

        datePicker = (DatePicker) findViewById(R.id.datePicker);

    }

}
