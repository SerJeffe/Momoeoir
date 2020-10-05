package com.example.momoeoir.ui.personalize;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.momoeoir.Conn;
import com.example.momoeoir.R;
import com.example.momoeoir.SetDecor;
import com.example.momoeoir.Start;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersonalizeFragment extends Fragment {
    ImageView bg,person;
    EditText name;
    Uri imguri;
    public static String ass=null;
    private static  final int PICK_IMAGE = 100;
    LinearLayout line;
    FloatingActionButton save,fab;
    private PersonalizeViewModel personalizeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personalizeViewModel =
                ViewModelProviders.of(this).get(PersonalizeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personalize, container, false);
        bg = root.findViewById(R.id.backImage);
        person = root.findViewById(R.id.perImage);
        name = root.findViewById(R.id.perName);
        final Conn con = new Conn(getActivity());
        line = root.findViewById(R.id.textLayout);
        save = root.findViewById(R.id.save);
        new Start().visibilityFAB("false");
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String getname = name.getText().toString();
                con.updateName(getname);
                BitmapDrawable drawable = (BitmapDrawable) person.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ass = saveToInternalStorage(bitmap);
                goBack();
            }
        });
        loadImageFromStorage("/data/user/0/com.example.momoeoir/files/myfolder");
        setEverything(new SetDecor(getActivity()).getDecor());
        person.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startGal();
            }
        });
        return root;
    }
    public void startGal()
    {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== getActivity().RESULT_OK && requestCode == PICK_IMAGE){
            imguri = data.getData();
            person.setImageURI(imguri);
        }
    }

    public void goBack(){
        Intent i = new Intent(getActivity(), Start.class);
        startActivity(i);
    }
    public void setEverything(String color){

        switch(color){
            case "pink":
                bg.setImageResource(R.drawable.pinkbg);
                line.setBackgroundResource(R.drawable.layout_pinkbg);
                name.setTextColor(this.getResources().getColor(R.color.textpink));
                name.setHintTextColor(this.getResources().getColor(R.color.textpink));
                break;
            case "dark":
                bg.setImageResource(R.drawable.lightdarkbg);
                line.setBackgroundResource(R.drawable.layout_darkbg);
                name.setTextColor(this.getResources().getColor(R.color.white));
                name.setHintTextColor(this.getResources().getColor(R.color.white));
                break;
        }
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getActivity());
        // path to /data/data/yourapp/app_data/imageDir
        File directory=new File(getActivity().getFilesDir(),"myfolder");
        directory.mkdir();
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            person.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
