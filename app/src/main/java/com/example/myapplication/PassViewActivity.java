package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PassViewActivity extends AppCompatActivity {


    EditText editFname, editLname, editNumber, editDob, editMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_view);
        setTitle("Pass view & Edit");

        //get id in activity
        TextView show = findViewById(R.id.passViewId);
        Button edit = findViewById(R.id.editPass);
        Button done = findViewById(R.id.doneView);

        //get key from PassActivity.java
        Intent intent = getIntent();

        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String dob = intent.getStringExtra("dob");
        String mail = intent.getStringExtra("mail");
        String number = intent.getStringExtra("number");


        show.setText("Name: " + firstName + " " + lastName + "\n\nDate of Birth: " + dob + "\n\nMail ID: " + mail + "\n\nNumber: " + number);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPopUp();
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PassViewActivity.this, PassActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });

    }

    public void startPopUp(){

        //get key from PassActivity.java
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String dob = intent.getStringExtra("dob");
        String mail = intent.getStringExtra("mail");
        String number = intent.getStringExtra("number");


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PassViewActivity.this);
        LayoutInflater inflater = PassViewActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout. activity_pass_edit_dialog, null);
        dialogBuilder.setView(dialogView);

        editFname = (EditText) dialogView.findViewById(R.id.fName);
        editFname.setText(firstName);
        editLname = (EditText) dialogView.findViewById(R.id.lName);
        editLname.setText(lastName);
        editNumber = (EditText) dialogView.findViewById(R.id.number);
        editNumber.setText(number);
        editDob = (EditText) dialogView.findViewById(R.id.dob);
        editDob.setText(dob);
        editMail = (EditText) dialogView.findViewById(R.id.mail);
        editMail.setText(mail);

        dialogBuilder.setCancelable(false).setPositiveButton("Update & Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new CustomListener(alertDialog));
    }

    // class for popup field validations
    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;

        public CustomListener(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            //get value to validate
            String firstName = editFname.getText().toString().trim();
            String lastName = editLname.getText().toString().trim();
            String number = editNumber.getText().toString().trim();
            String mail = editMail.getText().toString().trim();
            String dob = editDob.getText().toString();

            if (validateFirstName(firstName) && validateLastName(lastName) && validateDob(dob) && validateNumber(number) && validateMail(mail) ) {
                Toast.makeText(getApplicationContext(), "Registered success!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                Intent i = new Intent(PassViewActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finishAffinity();

            }
        }
    }

    //firstName validation
    private boolean validateFirstName(String valFname) {
        if (valFname.length() == 0) {
            editFname.requestFocus();
            editFname.setError("First Name cannot be empty");
            return false;
        }else if(!valFname.matches("[a-zA-Z]+")){
            editFname.requestFocus();
            editFname.setError("* allowed alphabetical characters only");
            return false;
        }
        return true;
    }

    //lastName validation
    private boolean validateLastName(String valLname) {
        if (valLname.length() == 0) {
            editLname.requestFocus();
            editLname.setError("Last Name cannot be empty");
            return false;
        }else if(!valLname.matches("[a-zA-Z]+")){
            editLname.requestFocus();
            editLname.setError("* allowed alphabetical characters only");
            return false;
        }
        return true;
    }

    //number validation
    private boolean validateNumber(String valNumber) {
        if (valNumber.length()==0){
            editNumber.requestFocus();
            editNumber.setError("* Required phone number");
            return false;
        }else if (valNumber.length()>10){
            editNumber.requestFocus();
            editNumber.setError("* Allowed 10 digits number only");
            return false;
        }else if (valNumber.length()<10 ){
            editNumber.requestFocus();
            editNumber.setError("* Required 10 digits number");
            return false;
        } else if(!valNumber.matches("[0-9]+")){
            editNumber.requestFocus();
            editNumber.setError("* allowed numbers only");
            return false;
        }
        return true;
    }

    //number validation
    private boolean validateMail(String valMail) {
        //email pattern
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (valMail.length()==0){
            editMail.requestFocus();
            editMail.setError("* Required mail address");
            return false;
        }else if(!valMail.matches(emailPattern)){
            editMail.requestFocus();
            editMail.setError("* Enter an valid mail address (yourmail@id.com)");
            return false;
        }
        return true;
    }

    //number validation
    private boolean validateDob(String valDob) {
        //Regex pattern for date format
        String datePattern = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";

        if (valDob.length()==0){
            editDob.requestFocus();
            editDob.setError("* Required Date of Birth");
            return false;
        }else if(!valDob.matches(datePattern)){
            editDob.requestFocus();
            editDob.setError("* Enter an valid date format(DD/MM/YYY)");
            return false;
        }
        return true;
    }
}
