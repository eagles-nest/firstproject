package com.skytop.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotPass extends AppCompatActivity {
    protected Button button_forgotPass;
    protected EditText user_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        user_email=(EditText)findViewById(R.id.email);
    }
    public void recoverPass(View view){
        String method="recover";
        String email=user_email.getText().toString().trim();
        //recover the user password
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,email,"");
        //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
    }
}
