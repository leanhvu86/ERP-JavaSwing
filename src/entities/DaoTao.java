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
public class DaoTao implements Serializable{
    private int id;
    private String maLop,tenLop,danhSach,maPhongBan,tuNgay,denNgay,ghiChu;

    public DaoTao() {
    }

    public DaoTao(int id, String maLop, String tenLop, String danhSach, String maPhongBan, String tuNgay, String denNgay, String ghiChu) {
        this.id = id;
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.danhSach = danhSach;
        this.maPhongBan = maPhongBan;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(String danhSach) {
        this.danhSach = danhSach;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    public String getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
