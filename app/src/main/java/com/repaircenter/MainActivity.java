package com.repaircenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button demarer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demarer= findViewById(R.id.demarer);
        demarer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openmain();

            }
        });
    }
    private void openmain() {
        Intent intent;
        intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }
    }
