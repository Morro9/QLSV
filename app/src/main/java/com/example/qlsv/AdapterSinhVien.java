package com.example.qlsv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AdapterSinhVien extends BaseAdapter {
    Context mycontext;
    int myLayout;
    List<SinhVien> sinhVienList;

    public AdapterSinhVien(Context context, int layout, List<SinhVien> list) {
        mycontext = context;
        myLayout = layout;
        sinhVienList = list;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_sinhvien, null);

        TextView tvTen, tvSothich, tvNamSinh, tvGioiTinh, tvLop;
        ImageButton sua, xoa;
        tvTen = convertView.findViewById(R.id.tensv);
        tvGioiTinh = convertView.findViewById(R.id.gioitinh);
        tvLop = convertView.findViewById(R.id.lop);
        tvNamSinh = convertView.findViewById(R.id.namsinh);
        tvSothich = convertView.findViewById(R.id.sothich);

        sua = convertView.findViewById(R.id.sua);
        xoa = convertView.findViewById(R.id.xoa);

        final SinhVien sinhVien = sinhVienList.get(position);

        tvTen.setText(sinhVien.Ten);
        tvGioiTinh.setText(sinhVien.gioitinh);
        tvLop.setText(sinhVien.Lop);
        tvSothich.setText(sinhVien.soThich);
        tvNamSinh.setText(sinhVien.namsinh);

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mycontext, MainActivity.class);
                intent.putExtra("id", sinhVien.id);
                mycontext.startActivity(intent);
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mycontext);
                builder.setTitle("Xác nhận xoá");
                builder.setMessage("Bạn có chắc chắc xoá Sinh Viên này ? ");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(sinhVien.id);
                    }


                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        return v;
    }

    private void delete(int idSinhVien) {
        SQLiteDatabase database = Database.initDatabase((Activity) mycontext, "QUANLYSINHVIEN.db");
        database.delete("SINHVIEN", "ID = ? ", new String[]{idSinhVien + ""});
        Cursor cursor = database.rawQuery("SELECT * FROM SINHVIEN", null);
        while ((cursor.moveToNext())) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String namsinh = cursor.getString(2);
            String lop = cursor.getString(3);
            int gioitinh = cursor.getInt(4);
            String sothich = cursor.getString(5);

            sinhVienList.add(new SinhVien(id, ten, namsinh, gioitinh,lop , sothich));
        }
        notifyDataSetChanged();
    }
}