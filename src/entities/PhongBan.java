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
public class PhongBan {

    private String maPhongBan, tenPhongBan, ghiChu;

    public PhongBan() {
    }

    public PhongBan(String maPhongBan, String tenPhongBan, String ghiChu) {
        this.maPhongBan = maPhongBan;
        this.tenPhongBan = tenPhongBan;
        this.ghiChu = ghiChu;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
