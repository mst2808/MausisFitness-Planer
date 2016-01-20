package com.example.marcus.mausisfitness_planer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HinzufuegenGewichtActivity extends Activity {

    private Intent intent;
    private Button buttonSendFormular;
    private EditText etGewicht;

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

                intent.putExtra(GewichtActivity.TAG_GEWICHT, etGewicht.getText().toString());
                intent.putExtra(GewichtActivity.TAG_DATE, System.currentTimeMillis());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

}
