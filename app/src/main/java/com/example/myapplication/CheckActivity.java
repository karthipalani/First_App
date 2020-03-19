package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        setTitle("Checkbox & Popup");

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        Button btnDisplay = (Button) findViewById(R.id.submitTc);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            private CoordinatorLayout CoordinatorLayout;

            //Run when button is clicked
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    String alert = "Thank you !\n\nYou're registered successfully...";

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CheckActivity.this);

                    alertDialogBuilder.setMessage(alert);
                    alertDialogBuilder.setPositiveButton("Okay",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Intent intent = new Intent(CheckActivity.this, MainActivity.class);
                                    finishAffinity();
                                    startActivity(intent);
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }else {
                    String alert = "Please accept the T&C to finish this registration";

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CheckActivity.this);

                    alertDialogBuilder.setMessage(alert);
                    alertDialogBuilder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    finish();
                                    overridePendingTransition( 0, 0);
                                    startActivity(getIntent());
                                    overridePendingTransition( 0, 0);
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });
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

