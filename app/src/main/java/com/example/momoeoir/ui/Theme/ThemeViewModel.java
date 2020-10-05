package com.example.momoeoir.ui.Theme;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThemeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ThemeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Coming Soon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}