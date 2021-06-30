package com.repaircenter;

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

import com.repaircenter.adapters.ClientAdapter;

import java.util.ArrayList;

public class Client_list extends AppCompatActivity {
    SearchView searchclient;
    RecyclerView recyclerView;
    DBhelper db;
    ArrayList<String> barcode, name, phoneNumber;
    ClientAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Clients List");
        }

        db = new DBhelper(this);
        searchclient = findViewById(R.id.searchClient);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setFilterTouchesWhenObscured(true);

        barcode = new ArrayList<>();
        name = new ArrayList<>();
        phoneNumber = new ArrayList<>();

        desplaydata();

        myAdapter = new ClientAdapter(Client_list.this,this, barcode,name,phoneNumber);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Client_list.this));














        searchclient.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String qry)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {

                return true;
            }


        });
}















    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{ super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1){
                // refreching the activity to show the deffrence after deletion
                Intent intent = new Intent(this,Phone_list.class);
                startActivity(intent);
                finish();
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
            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    private void desplaydata() {
        Cursor cursor = db.getclientdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                barcode.add(cursor.getString(0));
                name.add(cursor.getString(1));
                phoneNumber.add(cursor.getString(2));
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
                DBhelper db = new DBhelper(Client_list.this);
              db.deleteAllData();
                // refreching the activity to show the deffrence after deletion
                Intent intent = new Intent(Client_list.this,Client_list.class);
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
