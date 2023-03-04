package com.example.canyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
private Button submitButton;
private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onClicks();
    }
private void onClicks(){
        submitButton=findViewById(R.id.submit_login_btn);
        signupButton=findViewById(R.id.signup_Text_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
signupButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
        finish();
    }
});
}

}