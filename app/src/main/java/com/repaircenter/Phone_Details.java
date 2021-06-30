package com.repaircenter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Phone_Details extends AppCompatActivity {
    EditText barcode_input , phonemodel_input, imei_input , description_input;
    RadioGroup mygroup;
    RadioButton repairing , reparingcomplet , canceled;
    Button update , delete;
    String barcode , phonemodel, imei , description , status;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);

        barcode_input = findViewById(R.id.barcode2);
        phonemodel_input = findViewById(R.id.phone_model2);
        imei_input = findViewById(R.id.imei2);
        description_input = findViewById(R.id.description2);



        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        mygroup = findViewById(R.id.radiogroup);

        repairing = findViewById(R.id.repairing);
        reparingcomplet = findViewById(R.id.repairing_completed);
        canceled = findViewById(R.id.canceled);

        DBhelper db = new DBhelper(Phone_Details.this);
        // First we call this
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(phonemodel + "details");
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // And only then we call this

                try {

                    barcode = String.valueOf(barcode_input.getText());
                    phonemodel = String.valueOf(phonemodel_input.getText());
                    imei = String.valueOf(imei_input.getText());
                    description = String.valueOf(description_input.getText());
                    if (repairing.isChecked()){
                        status = "Repairing";
                    } else  if (reparingcomplet.isChecked()){
                        status = "Repairing completed";
                    } else  if (canceled.isChecked()){
                        status = "Canceled";
                    }

                    db.updatePhoneData(barcode,phonemodel,imei,description,status);

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

        void getAndSetIntentData() {
            if (getIntent().hasExtra("barcode") && getIntent().hasExtra("phone model") &&
                    getIntent().hasExtra("imei") && getIntent().hasExtra("description") &&
                    getIntent().hasExtra("status")) {
                // getting data from intent
                barcode = getIntent().getStringExtra("barcode");
                phonemodel = getIntent().getStringExtra("phone model");
                imei = getIntent().getStringExtra("imei");
                description = getIntent().getStringExtra("description");
                status = getIntent().getStringExtra("status");
                // setting data
                barcode_input.setText(barcode);
                phonemodel_input.setText(phonemodel);
                imei_input.setText(imei);
                description_input.setText(description);
                if (status.equals("Repairing")){
                    repairing.setChecked(true);
                } else  if (status.equals("Repairing completed")){
                    reparingcomplet.setChecked(true);
                } else  if (status.equals("Canceled")){
                    canceled.setChecked(true);
                }


            } else {
                Toast.makeText(this,"No Data" , Toast.LENGTH_SHORT).show();
            }

        }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + phonemodel + " ?");
        builder.setMessage("Are you sure you want to delete " + phonemodel + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBhelper db = new DBhelper(Phone_Details.this);
                db.deletePhone(barcode);
                db.deleteClient(barcode);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}