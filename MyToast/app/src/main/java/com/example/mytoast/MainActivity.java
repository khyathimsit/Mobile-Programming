package com.example.mytoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int count = 0;
    TextView showCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCount = (TextView) findViewById(R.id.show_count);
    }

    public void btn_cnt(View view) {
        count++;
        if (showCount !=null) {
            showCount.setText(Integer.toString(count));
        }
    }

    public void btn_toast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
