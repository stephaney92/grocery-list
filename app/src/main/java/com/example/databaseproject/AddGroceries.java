package com.example.databaseproject;

import android.content.Intent;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddGroceries extends AppCompatActivity{

    private EditText foodCategory;
    private EditText itemOnList;
    private Button addButton;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //name of the xml
        setContentView(R.layout.addgrocery_activity);

        foodCategory = findViewById(R.id.foodCategory);
        itemOnList = findViewById(R.id.itemOnList);
        addButton = findViewById(R.id.addButton);

        //the main page grabs this i and sends it to open
        Intent i = getIntent();
        add = i.getBooleanExtra("ADD", true);
        //if add is true, adding new item, else false then editing
        if (add){
            //changes add button text
            addButton.setText("ADD");
        } else {
            addButton.setText("EDIT");
            foodCategory.setText(i.getStringExtra("CATEGORY"));
            itemOnList.setText(i.getStringExtra("ITEM"));
        }

    }
    //adds category and item to database
    public void addPressed(View v){

        //grabs title & director from title & director box in addactivity
        String category = foodCategory.getText().toString();
        String item = itemOnList.getText().toString();

        //grabbing the database manager
        databaseManager dbm = new databaseManager(this);

        if(add) {
            //inserts into database
            dbm.insert(category, item);
        }else {
            dbm.updateByItem(category,item);
        }
        //closes activity page
        finish();

    }
}
