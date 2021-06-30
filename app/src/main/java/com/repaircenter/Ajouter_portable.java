package com.repaircenter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Ajouter_portable extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DBhelper DB;
    Button generator,save;
    EditText codebar,name,phonenumber,model,description,imei;
    String barcode;

    final String[] items = new String[]{"Repairing complet", "Repairing", "Canceled"};
    String status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_portable);
        generator = findViewById(R.id.generator);
        Spinner dropdown = findViewById(R.id.spinner1);
        codebar= findViewById(R.id.codebar);
        name= findViewById(R.id.name);
        phonenumber = findViewById(R.id.phoneNum);
        save = findViewById(R.id.save);
        model = findViewById(R.id.model);
        description = findViewById(R.id.descreption);
        imei = findViewById(R.id.imei);

        DB= new DBhelper(this);

//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterclient();
                ajouteportable();


            }
        });


        generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codegenerator();

            }
        });
}



    private void codegenerator() {
        Random nb = new Random();
        String code = String.format("%04d", nb.nextInt(10000));
        if (code.length() == 4) {
            codebar.setText(code);

            barcode = code;
        }
    }
    public void ajouterclient(){
        try {


        name.getText().toString();
        phonenumber.getText().toString();
        codebar.getText().toString();

        String nom = null;
        String phnbr = null;
        String bar = null;
        if (!name.equals(" ") && !phonenumber.equals(" ") && !codebar.equals(" ")) {
            if (name.length() > 50) {
                name.setError("The number is too long");
            } else if (phonenumber.length() > 10) {
                phonenumber.setError("The number is too long");

            } else {
                nom = name.getText().toString();
                phnbr = phonenumber.getText().toString();
                bar = codebar.getText().toString();
            }
        }

        String nameTXT = nom;
        String pnTXT = phnbr;
        barcode = bar;
        if (name.getText().toString().trim().equalsIgnoreCase("") && phonenumber.getText().toString().trim().equalsIgnoreCase("") && codebar.getText().toString()==" "){
            name.setError("Name must be entered");
            phonenumber.setError("Phone number  must be entered");
            codebar.setError("You must click on the code generator");

        }
        else {

            Boolean checkinsertdata = DB.insertuserdata(barcode,nameTXT, pnTXT);
            if (checkinsertdata)

                Toast.makeText(Ajouter_portable.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Ajouter_portable.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();
        }
        } catch (Exception e){
            e.getMessage();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position , long id) {

        switch (position) {
            case 0:
                status = "Repairing completed";
                break;
            case 1:
                status ="Repairing";
                break;
            case 2:
                status = "Canceled";
                break;

        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(Ajouter_portable.this,"Stat must be selected",Toast.LENGTH_SHORT).show();

    }
    public void ajouteportable(){
        try {
            imei.getText().toString();
            description.getText().toString();
            model.getText().toString();

            String ime = null;
            String desc = null;
            String mode = null;
            if (!imei.equals(" ") && !description.equals(" ") && !model.equals(" ")) {
                if (imei.length() > 15) {
                    imei.setError("The number is too long");
                } else if (model.length() > 50) {
                    model.setError("The number is too long");
                } else if (description.length() > 200) {
                    description.setError("The number is too long");
                } else {
                    ime = imei.getText().toString();
                    desc = description.getText().toString();
                    mode = model.getText().toString();
                }
            }


            String modelTXT = mode;
            String imeiTXT = ime;
            String descreptionTXT = desc;
            String statusTXT = status;
            if (imei.getText().toString().trim().equalsIgnoreCase("") && description.getText().toString().trim().equalsIgnoreCase("") && model.getText().toString().trim().equalsIgnoreCase("")) {
                imei.setError("IMEI must be entered");
                description.setError("Description  must be given");
                model.setError("The Model must be entered");
            } else {
                if (!statusTXT.isEmpty()) {
                    Boolean checkinsertdata = DB.insertphonedata(barcode, modelTXT, imeiTXT, descreptionTXT, statusTXT);

                    if (checkinsertdata) {
                        Toast.makeText(Ajouter_portable.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                        codebar.getText().clear();
                        name.getText().clear();
                        phonenumber.getText().clear();
                        model.getText().clear();
                        description .getText().clear();
                        imei.getText().clear();
                    } else
                        Toast.makeText(Ajouter_portable.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            e.getMessage();
        }

    }


}