package com.example.feediee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText phoneNumber, password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        phoneNumber = (EditText) findViewById(R.id.login_number_input);
//        password = (EditText) findViewById(R.id.login_password_input);
//        login = (Button) findViewById(R.id.login_btn);
    }
}
