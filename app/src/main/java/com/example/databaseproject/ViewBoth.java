package com.example.databaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewBoth extends AppCompatActivity {

    private TextView itemView;
    private TextView categoryView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //connects this class to the layout view
        setContentView(R.layout.viewboth_activity);

        categoryView = findViewById(R.id.categoryView);
        itemView = findViewById(R.id.itemView);

        databaseManager dbm = new databaseManager(this);
        Intent i = getIntent();
        String item = i.getStringExtra("ITEM"); //gets item from intent in grocery list
        String[] entry = dbm.get(item);

        //sets the text from the database to the category and item view
        categoryView.setText(entry[0]);
        itemView.setText(entry[1]);

    }
    public void editPressed(View v){

        Intent i = new Intent(this, AddGroceries.class);
        //test if you are adding or editing, this i edits
        i.putExtra("ADD", false);
        startActivity(i);

    }
}
