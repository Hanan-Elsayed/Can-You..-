package com.example.canyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
     //   initializeDropDownMenu();
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
}