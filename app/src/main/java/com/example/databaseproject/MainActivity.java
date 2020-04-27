package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void createPressed(View v){
        Intent i = new Intent(this, AddGroceries.class);
        //test if you are adding or editing, this i adds
        i.putExtra("ADD", true);
        startActivity(i);

    }
    public void viewListPressed(View v){
        //launches second screen ViewActivity screen
        //intent-activity pages talk to each other
        Intent i = new Intent(this,GroceryList.class);
        startActivity(i);
    }
}
