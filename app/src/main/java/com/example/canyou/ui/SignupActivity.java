package com.example.canyou.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.databinding.ActivitySignupBinding;
import com.example.canyou.pojo.SignUpRequest;
import com.example.canyou.pojo.SignUpResponse;
import com.example.canyou.source.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.DatePicker;

import java.util.Calendar;

import android.app.DatePickerDialog;


public class SignupActivity extends AppCompatActivity {
   private ActivitySignupBinding binding;
    private PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        preferenceManager = new PreferenceManager(this);

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
                SignUpResponse signUpResponse=response.body();
                // Save user and token to shared preferences
                if (signUpResponse != null) {
                    preferenceManager.saveUser(signUpResponse.getUser());
                    preferenceManager.saveToken(signUpResponse.getToken());
                }
               changeActivity(ProfileImageActivity.class);
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
    String gender ;
public String getGenderValue(){
    int checkedId=binding.genderRadioGroup.getCheckedRadioButtonId();
    if (checkedId==R.id.male_radio_btn){

        gender ="male";

    }
    else if (checkedId==R.id.female_radio_btn)
    {
        gender ="female";
    }
    binding.genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (checkedId==R.id.male_radio_btn){

                gender ="male";

                }
                else if (checkedId==R.id.female_radio_btn)
                {
                    gender ="female";
                }
            }

    });
    return gender;
}
    //validation
private boolean validateName(){
String val =binding.nameTIL.getEditText().getText().toString();
if (val.isEmpty())
{
    binding.nameTIL.setError("Field cannot be empty");
    return false;
}else if (val.length()>30){
    binding.nameTIL.setError("name is too long");
    return false;
}
else {
    binding.nameTIL.setError(null);
    binding.nameTIL.setErrorEnabled(false);
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
    private boolean validatePhoneNumber(){
        String val =binding.phoneNumTIL.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        boolean flag=false;
        for (char ch: val.toCharArray()) {
            if (!Character.isDigit(ch)) {
                flag = true;
                break;
            }
        }
        if (val.isEmpty())
        {
            binding.phoneNumTIL.setError("Field cannot be empty");
            return false;
        }else if (!(val.length()==11)){
            binding.phoneNumTIL.setError("Phone number must be 11 digit");
            return false;
        }else if (flag) {
            binding.phoneNumTIL.setError("Enter digits only");
            flag=false;
            return false;}
        else if (!val.matches(noWhiteSpace)) {
            binding.phoneNumTIL.setError("White Spaces are not allowed");
            return false;
        }
        else {
            binding.phoneNumTIL.setError(null);
            binding.phoneNumTIL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateNationalId(){
        String val =binding.nationalIdTIL.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        boolean flag=false;
        for (char ch: val.toCharArray()) {
            if (!Character.isDigit(ch)) {
                flag = true;
                break;
            }
        }
        if (val.isEmpty())
        {
            binding.nationalIdTIL.setError("Field cannot be empty");
            return false;
        }else if (!(val.length()==14)){
            binding.nationalIdTIL.setError("National ID must be 14 digit");
            return false;
        } else if (flag) {
            binding.nationalIdTIL.setError("Enter digits only");
            flag=false;
            return false;}
        else if (!val.matches(noWhiteSpace)) {
            binding.nationalIdTIL.setError("White Spaces are not allowed");
            return false;
        }
        else {
            binding.nationalIdTIL.setError(null);
            binding.nationalIdTIL.setErrorEnabled(false);
            return true;
        }
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
                ".{7,}" +               //at least 5 characters
                "$";

        if (val.isEmpty()) {
            binding.passTIL.setError("Field cannot be empty");
            return false;
        }  else if (!val.matches(passwordVal)) {
            binding.passTIL.setError("password should be at least 7 characters");
            return false;
        } else {
            binding.passTIL.setError(null);
            binding.passTIL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateCity() {
        String val = binding.cityDropDownMenu.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.cityDropDownMenu.setError("Field cannot be empty");
            return false;
        } else {
            binding.cityDropDownMenu.setError(null);
            binding.cityDropDownMenu.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDateOfBirth() {
        String val = binding.dateOfBirth.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.dateOfBirth.setError("Field cannot be empty");
            return false;
        } else {
            binding.dateOfBirth.setError(null);
            binding.dateOfBirth.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateGender() {
        String val = getGenderValue();

        if (val==null) {
            binding.genderTIL.setError("Field cannot be empty");
            return false;
        } else {
            binding.genderTIL.setError(null);
            binding.genderTIL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateConfirmPassword(){
        String confirmPass =binding.confirmPassTIL.getEditText().getText().toString();
        String pass =binding.passTIL.getEditText().getText().toString();
        if (confirmPass.isEmpty())
        {
            binding.confirmPassTIL.setError("Field cannot be empty");
            return false;
        }else if(!(confirmPass.equals(pass))){

            binding.confirmPassTIL.setError("Those passwords didn’t match. Try again");
            return false;
        }else {
            binding.confirmPassTIL.setError(null);
            binding.confirmPassTIL.setErrorEnabled(false);
            return true;
        }
    }

  private void onClicks(){
   binding.signupSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation
                if(!validateName() |!validatePassword() | !validatePhoneNumber() |
                        !validateEmail() | !validateNationalId()| !validateDateOfBirth()|
                        !validateGender()| !validateConfirmPassword()| !validateCity())
                {
                    return;
                }else {
                SignUpRequest signUpRequest = new SignUpRequest();
                    signUpRequest.setFullName(binding.nameTIL.getEditText().getText().toString());
                    signUpRequest.setEmail(binding.emailTIL.getEditText().getText().toString());
                    signUpRequest.setNationalID(binding.nationalIdTIL.getEditText().getText().toString());
                    signUpRequest.setBirthDay(binding.dateOfBirth.getEditText().getText().toString());
                    signUpRequest.setPhoneNumber(binding.phoneNumTIL.getEditText().getText().toString());
                    signUpRequest.setCity(binding.nationalIdTIL.getEditText().getText().toString());
                    signUpRequest.setGender(getGenderValue());
                    signUpRequest.setPassword(binding.passTIL.getEditText().getText().toString());
                    signUpRequest.setConfirmPassword(binding.confirmPassTIL.getEditText().getText().toString());
                    registerUser(signUpRequest);

        }}
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
                binding.autoCompleteTxt.setText(city);

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
                        String dateString = String.format("%d-%02d-%02d",year, (month+1), dayOfMonth);

                        binding.dateOfBirthET.setText(dateString);
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