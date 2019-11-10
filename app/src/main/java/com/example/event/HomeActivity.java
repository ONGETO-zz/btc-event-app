package com.example.event;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    TextView mTV;
    Button mBtn;
    Button button3;
    Button Urlopen;
    Button UrlOpen;
    Button btncontact;
    TextView textViews;

    Calendar c;
    DatePickerDialog dpd;

    private final int REQUEST_CODE=99;
    private static final int PICK_CONTACT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        textViews = (TextView) findViewById(R.id.textViews);

        Button btncontact=(Button)findViewById(R.id.btncontact);
        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor phone = getContentResolver().query(contactData, null, null, null, null);
                    if (phone.moveToFirst()) {
                        String contactNumberName = phone.getString(phone.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                                textViews.setText("Name:" + contactNumberName);
                            }
                        }

                    break;
                }

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

                Intent Getintent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.kenyabuzz.com/events/"));
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

                //Set event details

                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, "My House Party");
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Siwaka");
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "A Prayer Session");
                GregorianCalendar calDate = new GregorianCalendar(2012, 7, 15);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                startActivity(calIntent);


                //confirm the date
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
