package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editHoten, edtNS, edtLop;
    RadioButton rdNam, rdNu;
    CheckBox cbDocSach, cbTheThao, cbDuLich;
    Button btNhap, btNhapLai;
    Database database;
    Button imgDanhsasch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editHoten = findViewById(R.id.hoten);
        editHoten = findViewById(R.id.hoten);
        edtNS = findViewById(R.id.namsinh);
        edtLop = findViewById(R.id.lop);

        rdNam = findViewById(R.id.gioitinh_nam);
        rdNu = findViewById(R.id.gioitinh_nu);

        cbDocSach = findViewById(R.id.cbdocsach);
        cbTheThao = findViewById(R.id.cbthethao);
        cbDuLich = findViewById(R.id.cbdulic);

        btNhap = findViewById(R.id.nhaplieu);
        btNhapLai = findViewById(R.id.nhaplai);
        imgDanhsasch = findViewById(R.id.dsSinhVien);


        database = new Database(this);
        NhapLai();
        Nhap();
//        initUI();
        imgDanhsasch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DanhSach.class));
            }
        });

    }


    private void NhapLai() {
        btNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editHoten.setText("");
                edtLop.setText("");
                edtNS.setText("");
                rdNam.setChecked(true);
                rdNu.setChecked(false);
                cbDocSach.setChecked(false);
                cbDuLich.setChecked(false);
                cbTheThao.setChecked(false);
            }
        });
    }

    public void Nhap() {
        btNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soThich = "";
                int gTinh = 0;
                String hoTen = editHoten.getText().toString();
                String NamSinh = edtNS.getText().toString();
                String Lop = edtLop.getText().toString();


                if (rdNu.isChecked()) {
                    gTinh = 1;
                }
                if (cbTheThao.isChecked()) {
                    soThich += "," + cbTheThao.getText().toString();
                }
                if (cbDuLich.isChecked()) {
                    soThich += "," + cbDuLich.getText().toString();
                }
                if (cbDocSach.isChecked()) {
                    soThich += "," + cbDocSach.getText().toString();
                }
                SinhVien sv = new SinhVien(hoTen, NamSinh, Lop, gTinh, soThich);
                if (sv != null) {
                    database.ThemSinhVien(sv);
                    Toast.makeText(MainActivity.this, "Bạn Đã thêm thành công!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}