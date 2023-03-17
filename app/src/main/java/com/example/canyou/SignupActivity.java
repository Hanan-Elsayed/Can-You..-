package com.example.canyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {
    private Button submitButton,loginButton;
    private int[] layouts;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
     //   initializeDropDownMenu();
        changeFragment(new  Signup1Fragment());
        viewPager = findViewById(R.id.viewPager);
        submitButton=findViewById(R.id.signup_submit_btn);
        loginButton=findViewById(R.id.login_Text_btn);
        layouts = new int[]{
                R.layout.fragment_signup1,
                R.layout.fragment_signup2,

        };
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {
                    Intent intent=new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
        //onClicks();


  /*  private void onClicks(){
        submitButton=findViewById(R.id.signup_submit_btn);
        loginButton=findViewById(R.id.login_Text_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

        }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }*/
    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();


    }
  /*  private void initializeDropDownMenu (){
         TextInputLayout textInputLayout;
         textInputLayout=findViewById(R.id.city_drop_down_menu);
         AutoCompleteTextView autoCompleteTextView;
         autoCompleteTextView=findViewById(R.id.cities);

    String [] cities=  {"Alexandria","Assiut", "Aswan" ,"Beheira","Bani Suef","Cairo","Daqahliya","Damietta","Fayyoum", "Gharbiya","Giza","Helwan","Ismailia","Kafr El Sheikh","Luxor","Marsa Matrouh", "Minya", "Monofiya","New Valley", "North Sinai","Port Said","Qalioubiya","Qena","Red Sea","Sharqiya","Sohag","South Sinai","Suez","Tanta"};
    ArrayAdapter<String> cityAdapter=new ArrayAdapter<>(SignupActivity.this, R.layout.items_list,cities);
    autoCompleteTextView.setAdapter(cityAdapter);
    }
*/
  private int getItem(int i) {
      return viewPager.getCurrentItem() + 1;
  }

}