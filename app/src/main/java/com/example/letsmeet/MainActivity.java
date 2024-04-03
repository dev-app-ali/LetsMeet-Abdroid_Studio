package com.example.letsmeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button goLiveBtn;
    TextInputEditText liveIdInpout, nameInput;
    String liveID,name,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goLiveBtn = findViewById(R.id.go_live_btn);
        liveIdInpout = findViewById(R.id.live_id_input);
        nameInput = findViewById( R.id.name_input);



        liveIdInpout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                liveID = liveIdInpout.getText().toString();
                if (liveID.length() ==0){
                    goLiveBtn.setText("Start new Live");

                }
                else {
                    goLiveBtn.setText("Join Live");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        goLiveBtn.setOnClickListener((v) -> {
        name= nameInput.getText().toString();
        if(name.isEmpty()){
            nameInput.setError("Name is required");
            nameInput.requestFocus();
            return;

        }

        liveID = liveIdInpout.getText().toString();
        if( liveID.length()>0 && liveID.length()!=5){
            liveIdInpout.setError("Invalid Live ID");
            liveIdInpout.requestFocus();
            return;
        }
        startMeeting();

        });
    }


    void startMeeting(){

        Log.i("LOG","Start Meeting");

        boolean isHost = true;
        if (liveID.length()==5)
            isHost= false;
        else
            liveID = generateLiveID();
        userID = UUID.randomUUID().toString();
        Intent intent = new Intent(MainActivity.this , LiveActivity.class);
        intent.putExtra("user_id", userID);
        intent.putExtra("name", name);
        intent.putExtra("live_id",liveID);
        intent.putExtra("host", isHost);
        startActivity(intent);

    }
    String generateLiveID(){

        StringBuilder id = new StringBuilder();
        while ( id.length() !=5){
            int random = new Random().nextInt(10);
            id.append(random);

        }
        return id.toString();
    }
}