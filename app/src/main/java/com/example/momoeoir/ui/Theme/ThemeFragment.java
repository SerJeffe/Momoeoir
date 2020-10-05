package com.example.momoeoir.ui.Theme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.momoeoir.R;
import com.example.momoeoir.SetDecor;
import com.example.momoeoir.Start;

import java.util.Set;

public class ThemeFragment extends Fragment {

    private ThemeViewModel themeViewModel;
    private ImageView pink,dark,bg;
    Activity act;
    SetDecor color;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        themeViewModel =
                ViewModelProviders.of(this).get(ThemeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_theme, container, false);
        act = getActivity();
        pink = root.findViewById(R.id.im1);
        dark = root.findViewById(R.id.im2);
        bg = root.findViewById(R.id.backim);
        new Start().visibilityFAB("false");
        setBG(new SetDecor(getActivity()).getDecor());
        pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = new SetDecor(act);
                color.setDecor("pink");
                startEntry();
            }
        });
        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = new SetDecor(act);
                color.setDecor("dark");
                startEntry();
            }
        });
        return root;


    }
    public void startEntry()
    {
        Intent i = new Intent(getActivity(), Start.class);
        startActivity(i);
    }
    public void setBG(String Color) {
        switch (Color) {
            case "pink":
                bg.setImageResource(R.drawable.pinkbg);
                break;
            case "dark":
                bg.setImageResource(R.drawable.lightdarkbg);
                break;
        }
    }
}
