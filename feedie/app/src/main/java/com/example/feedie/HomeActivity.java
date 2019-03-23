package com.example.feedie;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private Button submit;
    private EditText quantity;
    private EditText time;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///////////////// Drop Down/////////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(HomeActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.details));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        ////////////////Date Picker/////////////////////////
        mDisplayDate = (TextView) findViewById(R.id.CookedDateEdit);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal;
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        HomeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        submit = (Button) findViewById(R.id.submit_btn);
        quantity = (EditText) findViewById(R.id.quantity);
        time = (EditText) findViewById(R.id.CookedTimeEdit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qt = quantity.getText().toString();
                String dateValue = mDisplayDate.getText().toString();
                String timeValue = time.getText().toString();
                String type = mySpinner.getSelectedItem().toString();
                if (TextUtils.isEmpty(qt) || TextUtils.isEmpty(dateValue) || TextUtils.isEmpty(timeValue) || TextUtils.isEmpty(type)) {
                    Toast.makeText(HomeActivity.this, "Please fill all the fields...", Toast.LENGTH_SHORT).show();
                }
                else {
                    final String msg = "Hello.. we are from Feedie app. This is to inform you that "+type + " for " + qt + " children will be sent which is cooked on " + dateValue + " at " + timeValue + " O'clock";
                    Intent intent = new Intent(HomeActivity.this, OrphanList.class);
                    intent.putExtra("message", msg);
                    startActivity(intent);
                }
            }
        });
    }
}
