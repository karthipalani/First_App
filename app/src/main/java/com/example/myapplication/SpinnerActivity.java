package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        setTitle("Spinner & Toast");

        //Spinner element
        final Spinner spin = (Spinner) findViewById(R.id.spinner);
        Button next = (Button) findViewById(R.id.nextSpin);

        //Array list as string
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Male");
        categories.add("Female");

        //create adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        //attach adapter for dropdown
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching adapter to layout's spinner
        spin.setAdapter(dataAdapter);
        spin.setOnItemSelectedListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString().trim().equals("Select")) {
                    Toast.makeText(getApplicationContext(), "Please select the gender in the dropdown box", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(SpinnerActivity.this, RadioActivity.class);
                    startActivity(intent);
                    Toast.makeText(SpinnerActivity.this, "Select your qualifications", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final Spinner spin = (Spinner) findViewById(R.id.spinner);
        String select = "You have selected an\n";
        String gender = " gender";

        if (spin.getSelectedItem().toString().trim().equals("Male")) {
            String text = select + parent.getItemAtPosition(position).toString() + gender;
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }else if(spin.getSelectedItem().toString().trim().equals("Female")) {
            String text = select + parent.getItemAtPosition(position).toString() + gender;
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }
        /*String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }else if (id == R.id.action_spinner) {
            Intent intent = new Intent(this, SpinnerActivity.class);
            this.startActivity(intent);
        }else if (id == R.id.action_radio) {
            Intent intent = new Intent(this, RadioActivity.class);
            this.startActivity(intent);
        }else if (id == R.id.action_checkbox) {
            Intent intent = new Intent(this, CheckActivity.class);
            this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
