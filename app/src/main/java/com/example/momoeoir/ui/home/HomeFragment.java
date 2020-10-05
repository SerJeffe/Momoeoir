package com.example.momoeoir.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momoeoir.Conn;
import com.example.momoeoir.CustomCardView;
import com.example.momoeoir.DiaryEntry;
import com.example.momoeoir.R;
import com.example.momoeoir.RVAdapter;
import com.example.momoeoir.SetDecor;
import com.example.momoeoir.Start;
import com.example.momoeoir.UpdateDelete;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    Button add;
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<CustomCardView> exampleList;
    View root,groot;
    RelativeLayout relative;
    ImageView imageview;
    SetDecor decor;
    Toolbar too;
    LinearLayout line;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        exampleList = new ArrayList<>();

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        relative = root.findViewById(R.id.relative1);
        imageview = root.findViewById(R.id.imageView1);
        too = root.findViewById(R.id.toolbar);
        decor = new SetDecor(getActivity());
        new Start().visibilityFAB("true");
        setBG(decor.getDecor());
        setAdapter();
        getDataDB();
        return root;
    }

    public void EntryStart() {
        Intent i = new Intent(getActivity(), DiaryEntry.class);
        startActivity(i);
    }

    public void EntryStart(String a, String b) {
        Intent i = new Intent(getActivity(), UpdateDelete.class);
        i.putExtra("title", a);
        i.putExtra("body", b);
        startActivity(i);
    }

    public void getDataDB() {
        Conn db = new Conn(getActivity());
        Cursor cur = db.getData();
        while (cur.moveToNext()) {
            exampleList.add(new CustomCardView(cur.getString(1), cur.getString(2),cur.getString(3)));
        }
    }

    public void setAdapter() {
        mRecyclerView = root.findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RVAdapter(exampleList,getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            public void onItemClick(int position) {
                String a = exampleList.get(position).getText1();
                String b = exampleList.get(position).getText2();
                EntryStart(a, b);
            }
        });
    }

    //set background and shit//
    public void setBG(String Color) {
        switch (Color)
        {
            case "pink":
                //background image//
                imageview.setImageResource(R.drawable.pinkbg);
                //status bar//
                if (Build.VERSION.SDK_INT >= 21) {
                    Window window = getActivity().getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(this.getResources().getColor(R.color.pink));
                }
                break;
            case "dark":
                //background image//imageview
                imageview.setImageResource(R.drawable.lightdarkbg);
                if (Build.VERSION.SDK_INT >= 21) {
                    Window window = getActivity().getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(this.getResources().getColor(R.color.dark));
                }
                break;
        }
    }
}
