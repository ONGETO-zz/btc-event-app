package com.example.event;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;


public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    TextView mTV;
    Button mBtn;
    Button button3;
    Button Urlopen;
    Button UrlOpen;

    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        UrlOpen = (Button) findViewById(R.id.buttontweet);
        UrlOpen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Getintent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.twitter.com"));
                startActivity(Getintent);

            }
        });

        Urlopen = (Button) findViewById(R.id.buttonweb);
        Urlopen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Getintent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.kenyabuzz.com"));
                startActivity(Getintent);

            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), AnnounceActivity.class);
                startActivity(activity2Intent);
            }

        });

// DatePicker Dialog
        mTV = findViewById(R.id.textView);
        mBtn = findViewById(R.id.button1);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(HomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        mTV.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);

                    }
                }, day, month, year);
                dpd.show();

            }

        });




    }
}
