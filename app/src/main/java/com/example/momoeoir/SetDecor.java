package com.example.momoeoir;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

public class SetDecor {
    public static String setBackGround;
    Context c;

    public SetDecor(Context con)
    {
        c = con;
    }
    public void setDecor(String get) {
        setBackGround = get;
        Conn dbcon = new Conn(c);
        boolean result = dbcon.updateColor(setBackGround);
    }

    public String getDecor() {
        Conn db = new Conn(c);
        Cursor cur = db.getColor();
        while (cur.moveToNext()) {
            setBackGround = cur.getString(1);
        }
        return setBackGround;
    }
}
