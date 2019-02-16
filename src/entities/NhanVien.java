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
public class NhanVien {
    private int id;
    private String maNhanVien,tenNhanVien,ngaySinh,maPhongBan,gioiTinh,dienThoai,diaChi,email,theCanCuoc,chuyenNganh,cVHoanThanh,kNLamViec,yThucLamViec,thamGiaHD,anhCaNhan,cVBanThan,ghiChu,maNhomQuyen,passWord;

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String anhCaNhan) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.anhCaNhan = anhCaNhan;
    }



    public NhanVien( String maNhanVien, String tenNhanVien, String ngaySinh, String maPhongBan, String gioiTinh, String dienThoai, String diaChi, String email, String theCanCuoc, String chuyenNganh, String cVHoanThanh, String kNLamViec, String yThucLamViec, String thamGiaHD, String anhCaNhan, String cVBanThan, String ghiChu, String maNhomQuyen) {
       
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.maPhongBan = maPhongBan;
        this.gioiTinh = gioiTinh;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.theCanCuoc = theCanCuoc;
        this.chuyenNganh = chuyenNganh;
        this.cVHoanThanh = cVHoanThanh;
        this.kNLamViec = kNLamViec;
        this.yThucLamViec = yThucLamViec;
        this.thamGiaHD = thamGiaHD;
        this.anhCaNhan = anhCaNhan;
        this.cVBanThan = cVBanThan;
        this.ghiChu = ghiChu;
        this.maNhomQuyen = maNhomQuyen;
    }

    public NhanVien( String maNhanVien, String tenNhanVien, String ngaySinh, String maPhongBan, String gioiTinh, String dienThoai, String diaChi, String email, String theCanCuoc, String chuyenNganh, String cVHoanThanh, String kNLamViec, String yThucLamViec, String thamGiaHD, String anhCaNhan, String cVBanThan, String ghiChu, String maNhomQuyen, String passWord) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.maPhongBan = maPhongBan;
        this.gioiTinh = gioiTinh;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.theCanCuoc = theCanCuoc;
        this.chuyenNganh = chuyenNganh;
        this.cVHoanThanh = cVHoanThanh;
        this.kNLamViec = kNLamViec;
        this.yThucLamViec = yThucLamViec;
        this.thamGiaHD = thamGiaHD;
        this.anhCaNhan = anhCaNhan;
        this.cVBanThan = cVBanThan;
        this.ghiChu = ghiChu;
        this.maNhomQuyen = maNhomQuyen;
        this.passWord = passWord;
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

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTheCanCuoc() {
        return theCanCuoc;
    }

    public void setTheCanCuoc(String theCanCuoc) {
        this.theCanCuoc = theCanCuoc;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String getcVHoanThanh() {
        return cVHoanThanh;
    }

    public void setcVHoanThanh(String cVHoanThanh) {
        this.cVHoanThanh = cVHoanThanh;
    }

    public String getkNLamViec() {
        return kNLamViec;
    }

    public void setkNLamViec(String kNLamViec) {
        this.kNLamViec = kNLamViec;
    }

    public String getyThucLamViec() {
        return yThucLamViec;
    }

    public void setyThucLamViec(String yThucLamViec) {
        this.yThucLamViec = yThucLamViec;
    }

    public String getThamGiaHD() {
        return thamGiaHD;
    }

    public void setThamGiaHD(String thamGiaHD) {
        this.thamGiaHD = thamGiaHD;
    }

    public String getAnhCaNhan() {
        return anhCaNhan;
    }

    public void setAnhCaNhan(String anhCaNhan) {
        this.anhCaNhan = anhCaNhan;
    }

    public String getcVBanThan() {
        return cVBanThan;
    }

    public void setcVBanThan(String cVBanThan) {
        this.cVBanThan = cVBanThan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(String maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
}
