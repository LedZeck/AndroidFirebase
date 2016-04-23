package com.example.eric.crowdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView textViewCondition;
    Button buttonSunny;
    Button buttonFoggy;
    Button buttonSend;
    Button buttonClear;
    EditText editTextWhatever;
    Firebase reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textViewCondition = (TextView) findViewById(R.id.textViewCondition);
        buttonSunny = (Button) findViewById(R.id.buttonSunny);
        buttonFoggy = (Button) findViewById(R.id.buttonFoggy);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        editTextWhatever = (EditText) findViewById(R.id.editTextWhatever);

        reference = new Firebase("https://crowdwheater.firebaseio.com/condition");// INCLUDE THE APPLICATION FIREBASE REPO

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textViewCondition.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        buttonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.setValue("Sunny");
            }
        });

        buttonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.setValue("Foggy");
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anything = editTextWhatever.getText().toString();
                reference.setValue(anything);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.setValue("");
            }
        });


    }
}
