package com.repaircenter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Client_Details extends AppCompatActivity {
    EditText barcode_input , name_input, phone_Number_input ;
    Button update , delete;
    String barcode , name, phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        barcode_input = findViewById(R.id.barcode_input2);
        name_input = findViewById(R.id.name_input);
        phone_Number_input = findViewById(R.id.phonenumber_input);

        update = findViewById(R.id.update2);
        delete = findViewById(R.id.delete2);

        DBhelper db = new DBhelper(Client_Details.this);
        // First we call this
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name + " " +"details");
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // And only then we call this

                try {

                    barcode = String.valueOf(barcode_input.getText());
                    name = String.valueOf(name_input.getText());
                    phoneNumber = String.valueOf(phone_Number_input.getText());
                    db.updateClientData(barcode,name,phoneNumber);

                } catch (Exception e){
                    e.getMessage();
                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    private void getAndSetIntentData() {
        try {
            if (getIntent().hasExtra("barcode") && getIntent().hasExtra("name") &&
                    getIntent().hasExtra("phone number")) {
                // getting data from intent
                barcode = getIntent().getStringExtra("barcode");
                name = getIntent().getStringExtra("name");
                phoneNumber = getIntent().getStringExtra("phone number");

                // setting data
                barcode_input.setText(barcode);
                name_input.setText(name);
                phone_Number_input.setText(phoneNumber);


            } else {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            e.getMessage();
        }

    }
    void confirmDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete " + name + " ?");
            builder.setMessage("Are you sure you want to delete " + name + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBhelper db = new DBhelper(Client_Details.this);
                    db.deleteClient(barcode);
                    db.deletePhone(barcode);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }catch (Exception e){
            e.getMessage();
        }
    }
}