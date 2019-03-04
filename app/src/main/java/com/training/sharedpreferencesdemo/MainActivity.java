package com.training.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button saveButton;

    public static final String APP_SHARED_PREF = "APP_PREF";
    public static final String PREF_FIRST_NAME_KEY = "FIRST_NAME_KEY";
    public static final String PREF_LAST_NAME_KEY = "LAST_NAME_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameEditText = findViewById(R.id.firstname_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        saveButton = findViewById(R.id.save_button);

        // Get values from shared preference file if the exist
        final SharedPreferences sharedPreferences = getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE);
        String firstName = sharedPreferences.getString(PREF_FIRST_NAME_KEY, ""); // default to empty string
        String lastName = sharedPreferences.getString(PREF_LAST_NAME_KEY, "");

        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            firstNameEditText.setText(firstName);
            lastNameEditText.setText(lastName);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PREF_FIRST_NAME_KEY, firstName);
                    editor.putString(PREF_LAST_NAME_KEY, lastName);
                    editor.apply(); // favor apply() over commit. Apply will save to pref file asynchronously. commit() will block the calling thread and return true once successful, false otherwise
                    Toast.makeText(MainActivity.this, firstName + " saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please complete all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
