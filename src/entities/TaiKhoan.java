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
public class TaiKhoan implements Serializable{
    String maNhanVien,password,maNhomQuyen;

    public TaiKhoan() {
    }

    public TaiKhoan(String maNhanVien, String password, String maNhomQuyen) {
        this.maNhanVien = maNhanVien;
        this.password = password;
        this.maNhomQuyen = maNhomQuyen;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(String maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }
   
    
    
}
