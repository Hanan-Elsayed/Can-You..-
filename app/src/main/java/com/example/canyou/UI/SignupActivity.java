package com.example.canyou.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.canyou.R;
import com.example.canyou.databinding.ActivitySignupBinding;
import com.example.canyou.pojo.SignUpRequest;
import com.example.canyou.pojo.SignUpResponse;
import com.example.canyou.source.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.DatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.time.Year;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class SignupActivity extends AppCompatActivity {
   private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        initializeDropDownMenu();
        datePicker();
        onClicks();

    }
    public void changeActivity(Class activity) {
                Intent intent=new Intent(SignupActivity.this, activity);
                startActivity(intent);
                finish();
            }
    public void toastMessage(String message ){
        Toast.makeText(SignupActivity.this,message,Toast.LENGTH_LONG).show();

    }
public void registerUser(SignUpRequest signUpRequest){
    Call<SignUpResponse> signUpResponseCall= RetrofitClient.getService().registerUser(signUpRequest);
    signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
        @Override
        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
            if(response.isSuccessful()){
                String message ="Register successful... ";
                toastMessage( message ) ;
                changeActivity(LoginActivity.class);
            }else {
                String message ="An error occurred please try again later... ";
                toastMessage( message ) ;
            }

        }

        @Override
        public void onFailure(Call<SignUpResponse> call, Throwable t) {
            String message =t.getLocalizedMessage();
            toastMessage( message ) ;

        }
    });
}


  private void onClicks(){
   binding.signupSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation
                if (TextUtils.isEmpty(binding.nameET.getText().toString())&&
                        TextUtils.isEmpty(binding.emailET.getText().toString())&&
                        TextUtils.isEmpty(binding.nationalIdET.getText().toString())&&
                        TextUtils.isEmpty(binding.phoneNumET.getText().toString())&&
                        TextUtils.isEmpty(binding.passET.getText().toString())&&
                        TextUtils.isEmpty(binding.confirmPassET.getText().toString()))
                {
                    String message ="All Fields is required... ";
                    toastMessage( message ) ;
                }else {
                    SignUpRequest signUpRequest = new SignUpRequest();
                    signUpRequest.setFullName(binding.nameET.getText().toString());
                    signUpRequest.setEmail(binding.emailET.getText().toString());
                    signUpRequest.setNationalID(binding.nationalIdET.getText().toString());
                    signUpRequest.setPhoneNumber(binding.phoneNumET.getText().toString());
                    signUpRequest.setPassword(binding.passET.getText().toString());
                    signUpRequest.setConfirmPassword(binding.confirmPassET.getText().toString());
                    registerUser(signUpRequest);
                }
        }
        });
        binding.loginTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(LoginActivity.class);

            }
        });
    }
    private void initializeDropDownMenu(){
        String [] cities=  {"Alexandria","Assiut", "Aswan" ,"Beheira","Bani Suef","Cairo","Daqahliya","Damietta","Fayyoum", "Gharbiya","Giza","Helwan","Ismailia","Kafr El Sheikh","Luxor","Marsa Matrouh", "Minya", "Monofiya","New Valley", "North Sinai","Port Said","Qalioubiya","Qena","Red Sea","Sharqiya","Sohag","South Sinai","Suez","Tanta"};
        ArrayAdapter<String> adapterCities;

        adapterCities = new ArrayAdapter<String>(this,R.layout.list_city,cities);
        binding.autoCompleteTxt.setAdapter(adapterCities);
        binding.autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();

            }
        });
    }
    private void datePicker(){
    DatePickerDialog.OnDateSetListener setListener;
        Calendar calendar =Calendar.getInstance();
        final int year =calendar.get(calendar.YEAR);
        final int month =calendar.get(calendar.MONTH);
        final int day =calendar.get(calendar.DAY_OF_MONTH);
        binding.dateOfBirthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        binding.dateOfBirthET.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                calendar.add(Calendar.DATE,-7);
                datePickerDialog.getDatePicker().setMinDate(Calendar.MILLISECOND);
                datePickerDialog.show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }

}