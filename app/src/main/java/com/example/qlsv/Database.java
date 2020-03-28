package com.example.qlsv;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Database extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "QUANLYSINHVIEN.db";
    private static  final String TABLE_NAME = "SINHVIEN";
    private static  final String ID = "ID";
    private static  final String TEN = "TEN";
    private static  final String NAMSINH = "TUOI";
    private static  final String LOP = "LOP";
    private static  final String GIOITINH = "GT";
    private static  final String SOTHICH = "SOTHICH";
    public Database(@Nullable Context context) {
        super(context,DATABASE_NAME, null, 1
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE IF NOT EXISTS SV(ID INTEGER PRIMARY KEY AUTOINCREMENT,  TEN text, NAMSINH text, LOP text, GIOITINH integer, SOTHICH text )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void ThemSinhVien(SinhVien sinhvienmoi){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN,sinhvienmoi.getTen());
        values.put(NAMSINH,sinhvienmoi.getNamsinh());
        values.put(LOP,sinhvienmoi.getLop());
        values.put(GIOITINH,sinhvienmoi.getGioitinh());
        values.put(SOTHICH,sinhvienmoi.getSoThich());

        database.insert(TABLE_NAME ,null,values);
        database.close();
    }
    public static SQLiteDatabase initDatabase(Activity activity, String databaseName){
        try {
            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if(!f.exists()) {
                InputStream e = activity.getAssets().open(databaseName);
                File folder = new File(activity.getApplicationInfo().dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }
}