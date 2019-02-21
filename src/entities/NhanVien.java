/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author Win
 */
public class NhanVien implements Serializable {

    private String maNhanVien, tenNhanVien, ngaysinh,gioitinh ,diachi, sdt, email, cmnd, trinhdo, totnghiep,
            chuyennghanh, ngonngu, ngayvaolam, maPhongBan, tenpb, chucvu, masothue, loaihd, anh;

    public NhanVien(String maNhanVien, String tenNhanVien, String ngaysinh, String gioitinh, String diachi, String sdt, String email, String cmnd, String trinhdo, String totnghiep, String chuyennghanh, String ngonngu, String ngayvaolam, String maPhongBan, String tenpb, String chucvu, String masothue, String loaihd, String anh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.cmnd = cmnd;
        this.trinhdo = trinhdo;
        this.totnghiep = totnghiep;
        this.chuyennghanh = chuyennghanh;
        this.ngonngu = ngonngu;
        this.ngayvaolam = ngayvaolam;
        this.maPhongBan = maPhongBan;
        this.tenpb = tenpb;
        this.chucvu = chucvu;
        this.masothue = masothue;
        this.loaihd = loaihd;
        this.anh = anh;
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String anh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.anh = anh;
    }

    
    public NhanVien() {
    }
//  private String maNhanVien,tenNhanVien,ngaySinh,maPhongBan,gioiTinh,dienThoai
//          ,diaChi,email,theCanCuoc,chuyenNganh,cVHoanThanh,kNLamViec,yThucLamViec
//          ,thamGiaHD,anhCaNhan,cVBanThan,ghiChu,maNhomQuyen,passWord;

    
    
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getTrinhdo() {
        return trinhdo;
    }

    public void setTrinhdo(String trinhdo) {
        this.trinhdo = trinhdo;
    }

    public String getTotnghiep() {
        return totnghiep;
    }

    public void setTotnghiep(String totnghiep) {
        this.totnghiep = totnghiep;
    }

    public String getChuyennghanh() {
        return chuyennghanh;
    }

    public void setChuyennghanh(String chuyennghanh) {
        this.chuyennghanh = chuyennghanh;
    }

    public String getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(String ngonngu) {
        this.ngonngu = ngonngu;
    }

    public String getNgayvaolam() {
        return ngayvaolam;
    }

    public void setNgayvaolam(String ngayvaolam) {
        this.ngayvaolam = ngayvaolam;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTenpb() {
        return tenpb;
    }

    public void setTenpb(String tenpb) {
        this.tenpb = tenpb;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getMasothue() {
        return masothue;
    }

    public void setMasothue(String masothue) {
        this.masothue = masothue;
    }

    public String getLoaihd() {
        return loaihd;
    }

    public void setLoaihd(String loaihd) {
        this.loaihd = loaihd;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

   
}