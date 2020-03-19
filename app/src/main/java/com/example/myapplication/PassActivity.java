package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        setTitle("Pass Value & Submit");

        final EditText editFname = findViewById(R.id.fName);
        final EditText editLname = findViewById(R.id.lName);
        final EditText editNumber = findViewById(R.id.number);
        final EditText editDob = findViewById(R.id.dob);
        final EditText editMail = findViewById(R.id.mail);
        final TextView result = findViewById(R.id.resultView);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get value to validate
                String firstName = editFname.getText().toString().trim();
                String lastName = editLname.getText().toString().trim();
                String number = editNumber.getText().toString().trim();
                String mail = editMail.getText().toString().trim();
                String dob = editDob.getText().toString();


                //email pattern
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String datePattern = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";

                // conditions for validation text
                if (firstName.length()==0){
                    editFname.requestFocus();
                    editFname.setError("* Required first name");
                }else if(!firstName.matches("[a-zA-Z]+")){
                    editFname.requestFocus();
                    editFname.setError("* allowed alphabetical characters only");
                }else if (lastName.length()==0){
                    editLname.requestFocus();
                    editLname.setError("* Required last name");
                }else if(!lastName.matches("[a-zA-Z]+")){
                    editLname.requestFocus();
                    editLname.setError("* allowed alphabetical characters only");
                }else if (dob.length()==0){
                    editDob.requestFocus();
                    editDob.setError("* Required Date of Birth");
                }else if(!dob.matches(datePattern)){
                    editDob.requestFocus();
                    editDob.setError("* Enter an valid date format(DD/MM/YYY)");
                }else if (number.length()==0){
                    editNumber.requestFocus();
                    editNumber.setError("* Required phone number");
                }else if (number.length()>10){
                    editNumber.requestFocus();
                    editNumber.setError("* Allowed 10 digits number only");
                }else if (number.length()<10 ){
                    editNumber.requestFocus();
                    editNumber.setError("* Required 10 digits number");
                } else if(!number.matches("[0-9]+")){
                    editNumber.requestFocus();
                    editNumber.setError("* allowed numbers only");
                }else if (mail.length()==0){
                    editMail.requestFocus();
                    editMail.setError("* Required mail address");
                }else if(!mail.matches(emailPattern)){
                    editMail.requestFocus();
                    editMail.setError("* Enter an valid mail address (yourmail@id.com)");
                }
                else {
                    //result.setText("Name: " + firstName + " " + lastName + "\n\nDate of Birth: " + dob + "\n\nMail ID: " + mail + "\n\nNumber: " + number );

                    // if all submit correctly, then proceed to next activity
                    Intent intent = new Intent(PassActivity.this, PassViewActivity.class);
                    intent.putExtra("firstName",firstName);
                    intent.putExtra("lastName",lastName);
                    intent.putExtra("dob",dob);
                    intent.putExtra("mail",mail);
                    intent.putExtra("number",number);
                    startActivity(intent);

                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pass, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home_fromPass) {
            Intent intent = new Intent(this, MainActivity.class);
            // set the new task and clear flags
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
