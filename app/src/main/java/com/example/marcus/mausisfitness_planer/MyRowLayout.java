package com.example.marcus.mausisfitness_planer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcus on 29.01.2016.
 */
public class MyRowLayout extends LinearLayout{

    private int ID;
    private double value;
    private String day, month, year;
    private String type;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    private TextView tvDate;
    private TextView tvValue;
    private TextView tvType;
    private Button btEdit;

    private Intent intent = new Intent();

    public MyRowLayout(final Context context, final int ID, double value, String day, String month, String year) {
        super(context);

        this.ID = ID;
        this.value = value;
        this.day = day;
        this.month = month;
        this.year = year;

        tvDate = new TextView(context);
        tvValue = new TextView(context);
        btEdit = new Button(context);

        String currentDate = "";

        if(day.length() == 1) {
            currentDate += "0" + day + ".";
        } else {
            currentDate += day + ".";
        }

        if(month.length() == 1) {
            currentDate += "0" + month + ".";
        } else {
            currentDate += month + ".";
        }

        currentDate += year;

        tvDate.setTextSize(20f);
        tvDate.setText(currentDate);

        tvValue.setText("" + value);
        tvValue.setTextSize(20f);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 5f);
        params.weight = 5f;

        tvDate.setLayoutParams(params);
        tvValue.setLayoutParams(params);

        params.weight = 1;
        btEdit.setLayoutParams(params);

        this.addView(tvDate);
        this.addView(tvValue);
        this.addView(btEdit);
    }

    public MyRowLayout(final Context context, final int ID, String type, double value, String day, String month, String year) {
        super(context);

        this.ID = ID;
        this.value = value;
        this.day = day;
        this.month = month;
        this.year = year;
        this.type = type;

        tvDate = new TextView(context);
        tvValue = new TextView(context);
        tvType = new TextView(context);
        btEdit = new Button(context);

        String currentDate = "";

        if(day.length() == 1) {
            currentDate += "0" + day + ".";
        } else {
            currentDate += day + ".";
        }

        if(month.length() == 1) {
            currentDate += "0" + month + ".";
        } else {
            currentDate += month + ".";
        }

        currentDate += year;

        tvDate.setTextSize(20f);
        tvDate.setText(currentDate);

        tvType.setText("" + type);
        tvType.setTextSize(20f);

        tvValue.setText("" + value);
        tvValue.setTextSize(20f);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 5f);
        params.weight = 5f;

        tvDate.setLayoutParams(params);
        tvType.setLayoutParams(params);
        tvValue.setLayoutParams(params);

        params.weight = 1;
        btEdit.setLayoutParams(params);

        this.addView(tvDate);
        this.addView(tvType);
        this.addView(tvValue);
        this.addView(btEdit);
    }

    public int getID() {
        return ID;
    }

    public double getValue() {
        return value;
    }

    public Button getBtEdit() {
        return btEdit;
    }
}
