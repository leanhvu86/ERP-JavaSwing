/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Administrator
 */
public class Tuyendung {
    private String id,hoten, ngaysinh, gioitinh,noisinh,
            sdt, email, cmnd, trinhdo, totnghiep, vitri,
            chuyennganh, ngonngu, kinhnghiem,anh, nam, dot;

    public Tuyendung() {
    }

    public Tuyendung(String id, String hoten, String ngaysinh, String gioitinh, String noisinh, String sdt, String email, String cmnd, String trinhdo, String totnghiep, String vitri, String chuyennganh, String ngonngu, String kinhnghiem, String anh, String nam, String dot) {
        this.id = id;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.noisinh = noisinh;
        this.sdt = sdt;
        this.email = email;
        this.cmnd = cmnd;
        this.trinhdo = trinhdo;
        this.totnghiep = totnghiep;
        this.vitri = vitri;
        this.chuyennganh = chuyennganh;
        this.ngonngu = ngonngu;
        this.kinhnghiem = kinhnghiem;
        this.anh = anh;
        this.nam = nam;
        this.dot = dot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
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

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
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

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        this.chuyennganh = chuyennganh;
    }

    public String getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(String ngonngu) {
        this.ngonngu = ngonngu;
    }

    public String getKinhnghiem() {
        return kinhnghiem;
    }

    public void setKinhnghiem(String kinhnghiem) {
        this.kinhnghiem = kinhnghiem;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    
}
