/*package com.example.momoeoir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    Button add;
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<CustomCardView> exampleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exampleList = new ArrayList<>();
        setAdapter();

        if(Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.darkshizzle));
        }

        add = findViewById(R.id.button);
        getDataDB();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryStart();
            }
        });
    }
    public void EntryStart()
    {
        Intent i = new Intent(this, DiaryEntry.class);
        startActivity(i);
    }
    public void EntryStart(String a,String b)
    {
        Intent i = new Intent(this, DiaryEntry.class);
        i.putExtra("title",a);
        i.putExtra("body",b);
        startActivity(i);
    }
    public void getDataDB()
    {
        Conn db = new Conn(this);
        Cursor cur = db.getData();
        if(cur.getCount()==0)
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        while(cur.moveToNext()){
            exampleList.add(new CustomCardView(cur.getString(1), cur.getString(2)));
        }
    }
    public void setAdapter(){
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RVAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            public void onItemClick(int position) {
                String a = exampleList.get(position).getText1();
                String b = exampleList.get(position).getText2();
                EntryStart(a,b);
            }
        });
    }
}
*/