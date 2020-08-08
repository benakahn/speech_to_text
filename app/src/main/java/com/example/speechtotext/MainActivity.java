package com.example.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txvResult;
    EditText FirstNameEditText,LastNameEditText,CollegeNameEditText,AddressEditText;
    Button RegistrationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvResult = (EditText)findViewById(R.id.txvResult);
        RegistrationButton=(Button)findViewById(R.id.button);
        FirstNameEditText=(EditText)findViewById(R.id.editText1);
        LastNameEditText=(EditText)findViewById(R.id.editText2);
        CollegeNameEditText=(EditText)findViewById(R.id.editText3);
        AddressEditText=(EditText)findViewById(R.id.editText4);
        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String FName=FirstNameEditText.getText().toString();
                final String LName=LastNameEditText.getText().toString();
                final String CName=CollegeNameEditText.getText().toString();
                final String Address=AddressEditText.getText().toString();
                if(FName.length()==0)
                {
                    FirstNameEditText.requestFocus();
                    FirstNameEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!FName.matches("[a-zA-Z ]+"))
                {
                    FirstNameEditText.requestFocus();
                    FirstNameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(LName.length()==0)
                {
                    LastNameEditText.requestFocus();
                    LastNameEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!LName.matches("[a-zA-Z ]+"))
                {
                    LastNameEditText.requestFocus();
                    LastNameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(CName.length()==0)
                {
                    CollegeNameEditText.requestFocus();
                    CollegeNameEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!CName.matches("[a-zA-Z ]+"))
                {
                    CollegeNameEditText.requestFocus();
                    CollegeNameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(Address.length()==0)
                {
                    AddressEditText.requestFocus();
                    AddressEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else
                {
                    FirstNameEditText.getText().clear();
                    LastNameEditText.getText().clear();
                    CollegeNameEditText.getText().clear();
                    AddressEditText.getText().clear();
                    Toast.makeText(MainActivity.this,"Successfully Registered",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.buttom_navigation,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_info:
                Intent i= new Intent(MainActivity.this,HomeActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));
                }
                break;
        }
    }
}