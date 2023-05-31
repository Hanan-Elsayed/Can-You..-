package com.example.canyou.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.databinding.ActivityLoginBinding;
import com.example.canyou.pojo.LoginRequest;
import com.example.canyou.pojo.LoginResponse;
import com.example.canyou.source.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        preferenceManager = new PreferenceManager(this);

        onClicks();
    }
    public void changeActivity(Class activity) {
        Intent intent=new Intent(LoginActivity.this, activity);
        startActivity(intent);
        finish();
    }
    private boolean validatePassword() {
        String val = binding.passTIL.getEditText().getText().toString();


        if (val.isEmpty()) {
            binding.passTIL.setError("Field cannot be empty");
            return false;

        } else {
            binding.passTIL.setError(null);
            binding.passTIL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEmail() {
        String val = binding.emailTIL.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.emailTIL.setError("Field cannot be empty");
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
//validation
                if(!validatePassword() |
                        !validateEmail()  )
                {
                    return;
                }else {
                    LoginRequest loginRequest=new LoginRequest();
                    loginRequest.setEmail(binding.emailTIL.getEditText().getText().toString());

                    loginRequest.setPassword(binding.passTIL.getEditText().getText().toString());
                    loginUser(loginRequest);

                }
            }
        });
binding.signupTextBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      changeActivity(SignupActivity.class);
    }
});
}
    public void toastMessage(String message ){
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();

    }
    private void loginUser(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = RetrofitClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.out.println(response);
                if (response.isSuccessful()) {
                    String message = "Login successful... ";
                    toastMessage(message);
                    LoginResponse loginResponse = response.body();

                    // Save user and token to shared preferences
                    if (loginResponse != null) {
                        preferenceManager.saveUser(loginResponse.getUser());
                        preferenceManager.saveToken(loginResponse.getToken());
                    }

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                    binding.passTIL.setError(" ");
                    binding.emailTIL.setError(" ");
                   binding.errorTV.setText("Email OR/AND Password is not valid");
                    binding.errorTV.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message =t.getLocalizedMessage();
                toastMessage( message ) ;
            }
        });
    }

}