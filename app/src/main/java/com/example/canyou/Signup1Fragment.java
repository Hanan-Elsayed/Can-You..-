package com.example.canyou;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.Year;
import java.util.Calendar;


public class Signup1Fragment extends Fragment {

    public Signup1Fragment() {
        // Required empty public constructor
    }
TextInputLayout dateOfBirth;
    TextInputEditText dateOfBirthEt;
    DatePickerDialog.OnDateSetListener setListener;

    // TODO: Rename and change types and number of parameters
    public static Signup1Fragment newInstance(String param1, String param2) {
        Signup1Fragment fragment = new Signup1Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_signup1, container, false);
        dateOfBirth=view.findViewById(R.id.date_of_birth);
        dateOfBirthEt=view.findViewById(R.id.date_of_birthET);
        Calendar calendar =Calendar.getInstance();
        final int year =calendar.get(calendar.YEAR);
        final int month =calendar.get(calendar.MONTH);
        final int day =calendar.get(calendar.DAY_OF_MONTH);
        dateOfBirthEt.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         DatePickerDialog datePickerDialog = new DatePickerDialog(
                 getContext(), new DatePickerDialog.OnDateSetListener() {
             @Override
             public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                 dateOfBirthEt.setText(dayOfMonth+"/"+(month+1)+"/"+year);
             }
         },year,month,day);
         datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
         calendar.add(Calendar.DATE,-7);
         datePickerDialog.getDatePicker().setMinDate(Calendar.MILLISECOND);
         datePickerDialog.show();

     }
 });

        return view;
    }
}


