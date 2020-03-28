package com.example.qlsv;

import java.util.Date;

public class SinhVien {
    int id;
    String Ten;
    String namsinh;
    String Lop;
    String soThich;
    int gioitinh;

    public SinhVien(int id, String ten, String namsinh,  int gioitinh,String lop, String soThich) {
        this.id = id;
        this.Ten = ten;
        this.namsinh = namsinh;
        this.Lop = lop;
        this.soThich = soThich;
        this.gioitinh = gioitinh;
    }

    public SinhVien(String ten, String namsinh, String lop, int gioitinh, String soThich) {
        this.Ten = ten;
        this.namsinh = namsinh;
        this.Lop = lop;
        this.soThich = soThich;
        this.gioitinh = gioitinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        this.Ten = ten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        this.Lop = lop;
    }

    public String getSoThich() {
        return soThich;
    }

    public void setSoThich(String soThich) {
        this.soThich = soThich;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }
}