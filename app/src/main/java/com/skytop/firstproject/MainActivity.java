package com.skytop.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button_reg;
    private Button button_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_reg=(Button)findViewById(R.id.button);
        button_login=(Button)findViewById(R.id.button2);
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //run the register activity
                //Toast.makeText(getApplicationContext(),"button clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //run the register activity
                //Toast.makeText(getApplicationContext(),"button clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
    }
}
