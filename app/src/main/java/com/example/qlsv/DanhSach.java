package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DanhSach extends AppCompatActivity {

    SQLiteDatabase database;

    ListView listView;
    ArrayList<SinhVien> list;
    AdapterSinhVien adapter;
    private static final String DATABASE_NAME = "QUANLYSINHVIEN.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ds_sinhvien);
        addControl();
        readData();
    }

    private void addControl() {
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new AdapterSinhVien(this,R.layout.item_sinhvien,list);
        listView.setAdapter(adapter);

    }
    public void readData()
    {

        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM SINHVIEN",null);
        cursor.moveToFirst();
        Toast.makeText(this,cursor.getString(1),Toast.LENGTH_LONG).show();
        for (int i = 0 ; i < cursor.getCount() ; i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String namsinh = cursor.getString(2);
            String lop = cursor.getString(3);
            String sothich = cursor.getString(4);
            int gioitinh = cursor.getInt(5);
            list.add(new SinhVien(ten,lop,namsinh,gioitinh,sothich));

        }
        adapter.notifyDataSetChanged();
    }
}