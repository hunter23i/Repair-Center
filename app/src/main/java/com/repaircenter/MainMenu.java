package com.repaircenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    Button ajouter;
    Button clientlist;

    Button phoneliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ajouter = findViewById(R.id.ajouter);
        clientlist = findViewById(R.id.listclient);

        phoneliste = findViewById(R.id.phoneliste);

        phoneliste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentphoneliste();
            }
        });


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openajouterportable();
            }
        });
        clientlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openclientlist();
            }
        });
    }

    private void opentphoneliste() {
        Intent phliste = new Intent(this,Phone_list.class);
        startActivity(phliste);
    }


    private void openclientlist() {
        Intent client = new Intent(this,Client_list.class);
        startActivity(client);
    }

    private void openajouterportable() {
        Intent ajout = new Intent(this,Ajouter_portable.class);
        startActivity(ajout);
    }
}