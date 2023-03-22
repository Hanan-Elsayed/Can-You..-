package com.example.canyou.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.canyou.R;
import com.example.canyou.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
//    private Button submitButton,loginButton;
String [] cities=  {"Alexandria","Assiut", "Aswan" ,"Beheira","Bani Suef","Cairo","Daqahliya","Damietta","Fayyoum", "Gharbiya","Giza","Helwan","Ismailia","Kafr El Sheikh","Luxor","Marsa Matrouh", "Minya", "Monofiya","New Valley", "North Sinai","Port Said","Qalioubiya","Qena","Red Sea","Sharqiya","Sohag","South Sinai","Suez","Tanta"};
    ArrayAdapter<String> adapterCities;
   private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   onClicks();
         binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        initializeDropDownMenu();

    }



//  private void onClicks(){
//        submitButton=findViewById(R.id.signup_submit_btn);
//        loginButton=findViewById(R.id.login_Text_btn);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(SignupActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//        }
//        });
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
    private void initializeDropDownMenu(){
        adapterCities = new ArrayAdapter<String>(this,R.layout.list_city,cities);
        binding.autoCompleteTxt.setAdapter(adapterCities);
        binding.autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }

}