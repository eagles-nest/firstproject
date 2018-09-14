package com.skytop.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Wrapper;

public class register extends AppCompatActivity {
    private Button button;
    private EditText user_email;
    private EditText user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_email=(EditText)findViewById(R.id.email);
        user_pass=(EditText)findViewById(R.id.pass);
        button=(Button)findViewById(R.id.button);
    }
    public void register(View view){
        String method="register";
        String pass=user_pass.getText().toString();
        String email=user_email.getText().toString();
        //prepare to call register user
        BackgroundTask backgroundTask = new BackgroundTask(this);
        //com.skytop.firstproject.Wrapper w = new com.skytop.firstproject.Wrapper(method, email, pass);
        //backgroundTask.execute(w);
        backgroundTask.execute(method, email, pass);
        //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
    }
}
