package com.skytop.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private Button button;
    private EditText user_email;
    private EditText user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_email=(EditText)findViewById(R.id.email);
        user_pass=(EditText)findViewById(R.id.pass);
        button=(Button)findViewById(R.id.button);
    }
    public void loginUser(View view){
        String method="login";
        String pass=user_pass.getText().toString();
        String email=user_email.getText().toString();
        //prepare to call register user
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, email, pass);
    }
    public void forgotPass(View view){
        //user forgot password
        Intent intent = new Intent(login.this,forgotPass.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "how can you forget your own password surely?",Toast.LENGTH_SHORT).show();
    }
}
