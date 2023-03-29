package com.example.canyou.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.canyou.R;
import com.example.canyou.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        onClicks();
    }
    public void changeActivity(Class activity) {
        Intent intent=new Intent(LoginActivity.this, activity);
        startActivity(intent);
        finish();
    }
    private boolean validatePassword() {
        String val = binding.passTIL.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //             "(?=.*[@#$%^&+=])" +    //at least 1 special character
                //        "(?=\\S+$)" +           //no white spaces
                ".{5,}" +               //at least 5 characters
                "$";

        if (val.isEmpty()) {
            binding.passTIL.setError("Field cannot be empty");
            return false;
        }  else if (!val.matches(passwordVal)) {
            binding.passTIL.setError("Password is too weak");
            return false;
        } else {
            binding.passTIL.setError(null);
            binding.passTIL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEmail() {
        String val = binding.emailTIL.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            binding.emailTIL.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            binding.emailTIL.setError("Invalid email address");
            return false;
        }  else {
            binding.emailTIL.setError(null);
            binding.emailTIL.setErrorEnabled(false);
            return true;
        }
    }

    private void onClicks(){

        binding.submitLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
binding.signupTextBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      changeActivity(SignupActivity.class);
    }
});
}

}