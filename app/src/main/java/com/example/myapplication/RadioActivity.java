package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RadioActivity extends AppCompatActivity {
    //declare radiogroup class
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    private CoordinatorLayout coordinatorLayout;
    int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        setTitle("Radio Button & Snakebar");
        addListenerOnButton();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.snakebarRadio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "You have selected " + radioButton.getText(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        btnDisplay = (Button) findViewById(R.id.nextRadio);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (radioGroup.getCheckedRadioButtonId()==-1){
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "You have to select the qualifications ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {

                    // get selected radio button from radioGroup
                    selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);

                    //Toast.makeText(RadioActivity.this, "You selected "+radioButton.getText(), Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RadioActivity.this);

                    String getSelected = "You selected - " + radioButton.getText();
                    String messageAlert = "\n\n Are you sure to continue ?";
                    alertDialogBuilder.setMessage(getSelected + messageAlert);
                    alertDialogBuilder.setPositiveButton("continue",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(RadioActivity.this, "Please agree our Terms and Conditions", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RadioActivity.this, CheckActivity.class);
                                    startActivity(intent);
                                }
                            });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            overridePendingTransition( 0, 0);
                            startActivity(getIntent());
                            overridePendingTransition( 0, 0);
                            Toast.makeText(RadioActivity.this, "Please select the qualifications", Toast.LENGTH_LONG).show();
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
