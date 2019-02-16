/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Win
 */
public class Danhgia {

    private int id;
    private String maNhanVien,hoTen, congViecHoanThanh, kyNangLamViec, yThucLamViec, thamGiaHoatDong, quyDanhGia, namDanhGia, ghiChu;

    public Danhgia() {
    }

    public Danhgia(int id, String maNhanVien, String congViecHoanThanh, String kyNangLamViec, String yThucLamViec, String thamGiaHoatDong, String quyDanhGia, String namDanhGia, String ghiChu) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.congViecHoanThanh = congViecHoanThanh;
        this.kyNangLamViec = kyNangLamViec;
        this.yThucLamViec = yThucLamViec;
        this.thamGiaHoatDong = thamGiaHoatDong;
        this.quyDanhGia = quyDanhGia;
        this.namDanhGia = namDanhGia;
        this.ghiChu = ghiChu;
    }

    public Danhgia(String maNhanVien,String Hoten, String congViecHoanThanh, String kyNangLamViec, String yThucLamViec, String thamGiaHoatDong, String quyDanhGia, String namDanhGia, String ghiChu) {
        this.maNhanVien = maNhanVien;
        this.hoTen = Hoten;
        this.congViecHoanThanh = congViecHoanThanh;
        this.kyNangLamViec = kyNangLamViec;
        this.yThucLamViec = yThucLamViec;
        this.thamGiaHoatDong = thamGiaHoatDong;
        this.quyDanhGia = quyDanhGia;
        this.namDanhGia = namDanhGia;
        this.ghiChu = ghiChu;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getCongViecHoanThanh() {
        return congViecHoanThanh;
    }

    public void setCongViecHoanThanh(String congViecHoanThanh) {
        this.congViecHoanThanh = congViecHoanThanh;
    }

    public String getKyNangLamViec() {
        return kyNangLamViec;
    }

    public void setKyNangLamViec(String kyNangLamViec) {
        this.kyNangLamViec = kyNangLamViec;
    }

    public String getyThucLamViec() {
        return yThucLamViec;
    }

    public void setyThucLamViec(String yThucLamViec) {
        this.yThucLamViec = yThucLamViec;
    }

    public String getThamGiaHoatDong() {
        return thamGiaHoatDong;
    }

    public void setThamGiaHoatDong(String thamGiaHoatDong) {
        this.thamGiaHoatDong = thamGiaHoatDong;
    }

    public String getQuyDanhGia() {
        return quyDanhGia;
    }

    public void setQuyDanhGia(String quyDanhGia) {
        this.quyDanhGia = quyDanhGia;
    }

    public String getNamDanhGia() {
        return namDanhGia;
    }

    public void setNamDanhGia(String namDanhGia) {
        this.namDanhGia = namDanhGia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
