
package com.br.les.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.br.les.povmt.R;

public class Login extends Activity {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginUser = (Button) findViewById(R.id.loginButton);
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainActivity.class);
                name = (EditText) findViewById(R.id.user_name);
                i.putExtra("NameUser", name.getText().toString());
                startActivity(i);

            }
        });

        Button createUser = (Button) findViewById(R.id.createButton);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, CreateUser.class);
                startActivity(i);
            }
        });

    }

}
