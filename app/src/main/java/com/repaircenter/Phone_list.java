package com.repaircenter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.repaircenter.adapters.PhoneAdapter;

import java.util.ArrayList;

public class Phone_list extends AppCompatActivity {
    SearchView seachph;
    RecyclerView recyclerView;
    DBhelper db;
    ArrayList<String> barcode, phonemodel, imei, description, status;
    PhoneAdapter myAdapter;


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
      ActionBar ab = getSupportActionBar();

        assert ab != null;
        ab.setTitle("Phones List");




        db = new DBhelper(this);
        seachph = findViewById(R.id.searchPhone);
        recyclerView = findViewById(R.id.recyclerView);

        barcode = new ArrayList<>();
        phonemodel = new ArrayList<>();
        imei = new ArrayList<>();
        description = new ArrayList<>();
        status = new ArrayList<>();

        desplaydata();

        myAdapter = new PhoneAdapter(Phone_list.this,this, barcode,phonemodel,imei,description,status);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

            recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Phone_list.this));








       seachph.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return true;
            }
        });

}
















        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            try{ super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1){
                recreate();
            }
            } catch(Exception e){
                e.getMessage();
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
            DBhelper db = new DBhelper(this);
            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    private void desplaydata () {
            Cursor cursor = db.getphonedata();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    barcode.add(cursor.getString(0));
                    phonemodel.add(cursor.getString(1));
                    imei.add(cursor.getString(2));
                    description.add(cursor.getString(3));
                    status.add(cursor.getString(4));


                }
            }
        }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Data Phones and Clients ?");
        builder.setMessage("Are you sure you want to delete ALL Data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBhelper db = new DBhelper(Phone_list.this);
                db.deleteAllData();
                // refreching the activity to show the deffrence after deletion
                Intent intent = new Intent(Phone_list.this,Phone_list.class);
                startActivity(intent);
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