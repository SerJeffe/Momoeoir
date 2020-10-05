package com.example.momoeoir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DiaryEntry extends AppCompatActivity {
    private static String mon[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static int cdate, cmonth, cyear;
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    Button back;
    FloatingActionButton add;
    EditText text, head;
    Context basecon;
    String a = null, b = null, setDate;
    DatePickerDialog dp;
    TextView t1, t2;
    Toolbar too;
    RelativeLayout r;
    View view;
    ImageView iv;
    static SetDecor bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        too = findViewById(R.id.toolbar);
        setSupportActionBar(too);
        getSupportActionBar().setTitle("Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        r = findViewById(R.id.rl1);
        t1 = findViewById(R.id.tv1);
        iv = findViewById(R.id.backgroundimg);
        text = findViewById(R.id.content);
        head = findViewById(R.id.title);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("d MMMM yyyy");
        date = dateFormat.format(calendar.getTime());
        t1.setText(date);
        basecon = getBaseContext();

        bg = new SetDecor(basecon);
        setBG(bg.getDecor());
        add = findViewById(R.id.save);




        final Conn dbcon = new Conn(basecon);

        Intent i = getIntent();
        a = i.getStringExtra("title");
        b = i.getStringExtra("body");
        if (a != null && b != null) {
            head.setText(a);
            text.setText(b);
        }

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp = new DatePickerDialog(DiaryEntry.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        setDate = dayOfMonth + " " + mon[monthOfYear] + " " + year;
                        t1.setText(setDate);
                    }
                }, cyear, cmonth, cdate);
                dp.getDatePicker().setMinDate(System.currentTimeMillis());
                dp.show();
            }
        });

        //Enter Data//
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTitle = head.getText().toString();
                String getData = text.getText().toString();
                String date = t1.getText().toString();
                //Toast.makeText(getApplicationContext(), ""+date, Toast.LENGTH_SHORT).show();
                boolean result = dbcon.insert(getTitle, getData, date);
                if (result == true) {
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    getBack();
                } else
                    Toast.makeText(getApplicationContext(), "Oops! Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });

        //Auto enter data when back key pressed//
        too.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTitle = head.getText().toString();
                String getData = text.getText().toString();
                String date = t1.getText().toString();
                //Toast.makeText(getApplicationContext(), ""+date, Toast.LENGTH_SHORT).show();
                boolean result = dbcon.insert(getTitle, getData, date);
                if (result == true) {
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    getBack();
                } else
                    Toast.makeText(getApplicationContext(), "Oops! Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // For Toolbar menu start//
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.emenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.font1:
                Typeface face = Typeface.createFromAsset(getAssets(), "aclonica.ttf");
                head.setTypeface(face);
                text.setTypeface(face);
                break;
            case R.id.font2:
                Typeface face2 = Typeface.createFromAsset(getAssets(), "allerta_stencil.ttf");
                head.setTypeface(face2);
                text.setTypeface(face2);
                break;
            case R.id.font3:
                Typeface face3 = Typeface.createFromAsset(getAssets(), "annie_use_your_telescope.ttf");
                head.setTypeface(face3);
                text.setTypeface(face3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Get to main activity i.e; Home Fragment//
    public void getBack() {
        Intent i = new Intent(this, Start.class);
        startActivity(i);
    }

    //set background and shit//
    public void setBG(String Color) {
        switch (Color)
        {
            case "pink":
                //background image//
                iv.setImageResource(R.drawable.pinkbg);
                //text bg//
                r.setBackgroundResource(R.drawable.layout_pinkbg);
                //toolbar//
                too.setBackgroundResource(R.color.pink);
                //status bar//
                if (Build.VERSION.SDK_INT >= 21) {
                    Window window = this.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(this.getResources().getColor(R.color.pink));
                }
                //textView bottom thingy//
                t1.setBackgroundResource(R.drawable.pinkunder);
                //text color// TextView
                text.setTextColor(this.getResources().getColor(R.color.textpink));
                text.setHintTextColor(this.getResources().getColor(R.color.textpink));
                head.setTextColor(this.getResources().getColor(R.color.textpink));
                head.setHintTextColor(this.getResources().getColor(R.color.textpink));
                t1.setTextColor(this.getResources().getColor(R.color.textpink));
                break;
            case "dark":
                //background image//imageview
                iv.setImageResource(R.drawable.lightdarkbg);
                //text bg//Relative Layout
                r.setBackgroundResource(R.drawable.layout_darkbg);
                //toolbar// Toolbar
                too.setBackgroundResource(R.color.dark);
                //status bar// Status Bar
                if (Build.VERSION.SDK_INT >= 21) {
                    Window window = this.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(this.getResources().getColor(R.color.dark));
                }
                //textView bottom thingy//
                t1.setBackgroundResource(R.drawable.darkunder);
                //text color// TextView
                text.setTextColor(this.getResources().getColor(R.color.white));
                text.setHintTextColor(this.getResources().getColor(R.color.white));
                head.setTextColor(this.getResources().getColor(R.color.white));
                head.setHintTextColor(this.getResources().getColor(R.color.white));
                t1.setTextColor(this.getResources().getColor(R.color.white));
                break;
        }
    }

}

