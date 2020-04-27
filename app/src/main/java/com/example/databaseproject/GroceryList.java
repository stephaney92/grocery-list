package com.example.databaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GroceryList extends AppCompatActivity {

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocerylist_activity);

        scrollView = findViewById(R.id.scrollView);
        Intent i = new Intent();

        //removes everything that is already in the scroll view
        scrollView.removeAllViewsInLayout();

        databaseManager dbm = new databaseManager(this);
        ArrayList<String> list = dbm.getItem(); //fills list with all the items in the database
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(list.size());

        //for each string title in our list
        for (String item:list ){
            TextView text = new TextView(this); //creating components of the view in the code
            text.setText(item);
            text.setTextSize(40);

            //makes button clickable
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                //tells the app what to do every time the button is clicked
                public void onClick(View view){
                    Intent i = new Intent(getApplicationContext(), ViewBoth.class); //launches view both activity
                    //takes item that was clicked on and stores into intent
                    i.putExtra("ITEM", ((TextView) view).getText().toString()); //casting value string
                    startActivity(i);
                }

            });
            grid.addView(text); //adds the food items to the view
        }
        scrollView.addView(grid); //adds the grid to the scrollview

    }

}
