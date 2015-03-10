package com.mursico.xpeppersaddress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditActivity extends ActionBarActivity implements View.OnClickListener{
    private EditText etName;
    private EditText etSurname;
    private EditText etPhone;
    private Button saveButton;
    private int position;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = (EditText) findViewById(R.id.name_edittext);
        etSurname = (EditText) findViewById(R.id.surname_edittext);
        etPhone = (EditText) findViewById(R.id.phone_edittext);
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setEnabled(true);
        saveButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
         if(extras != null) {
             id = extras.getString("ID");
             for( User us : HomeActivity.rubricaList ) {
                 if (us.getId().equals(extras.getString("ID"))) {
                     etName.setText(us.getName());
                     etSurname.setText(us.getSurname());
                     etPhone.setText(us.getPhone());
                 }

             }
         }
    }

    public void onClick(View v) {
        if(etSurname.getText().toString().equals("")) {
            new AlertDialog.Builder(this)
                    .setTitle("Validation error")
                    .setMessage("The surname field can't be empty")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(etName.getText().toString().equals("")) {
            new AlertDialog.Builder(this)
                    .setTitle("Validation error")
                    .setMessage("The name field can't be empty")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        String regexStr = "^[+][0-9]{1,} [0-9]{1,} [0-9]{6,}";

        if(etPhone.getText().toString().matches(regexStr)==false) {
            new AlertDialog.Builder(this)
                    .setTitle("Validation error")
                    .setMessage("The phone field is not in a correct format")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        for( User us : HomeActivity.rubricaList ) {
            if(us.getId().equals(id))
            {
                us.setName(etName.getText().toString());
                us.setSurname(etSurname.getText().toString());
                us.setPhone(etPhone.getText().toString());
            }
        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
