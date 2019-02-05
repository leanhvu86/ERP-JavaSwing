/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.LoggedRole;
import entities.NhanVien;
import entities.PhongBan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Win
 */
public class DaoTaoDAOImpl implements DaoTaoDAO {

    @Override
    public List<PhongBan> getListPhongBan() {
        List<PhongBan> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManager";

            Connection con = DriverManager.getConnection(url, "sa", "123456789");
            String sql = "select * from PhongBan where 1=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String maPhongBan = rs.getString("Maphongban");
                String tenPhongBan = rs.getString("TenPhongBan");
                String ghiChu = rs.getString("GhiChu");
                PhongBan e = new PhongBan(maPhongBan, tenPhongBan, ghiChu);
                list.add(e);
            }
            if (list.size() != 0) {
                return list;
            }
            ps.close();
            rs.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NhanVien> getListNhanVien(String maPhongBan) {
        List<NhanVien> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManager";

            Connection con = DriverManager.getConnection(url, "sa", "123456789");
            String sql = "select * from NhanVien where 1=1 and MaPhongBan=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maPhongBan);
            System.out.println(sql+"            select nè");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNhanVien");
                String tenNV = rs.getString("TenNhanVien");
                String ngaySinh = rs.getString("NgaySinh");
                String maPhongBan1 = rs.getString("MaPhongBan");
                String GioiTinh = rs.getString("GioiTinh");
                String DienThoai = rs.getString("DienThoai");
                String DiaChi = rs.getString("DiaChi");
                String Email = rs.getString("Email");
                int TheCanCuoc = rs.getInt("TheCanCuoc");
                String theCanCuoc = Integer.toString(TheCanCuoc);
                String ChuyenNganh = rs.getString("ChuyenNganh");
                String cVHoanThanh = rs.getString("CongViecHoanThanh");
                String kNLamViec = rs.getString("KyNangLamViec");
                String yThucLamViec = rs.getString("YThucLamViec");
                String thamGiaHD = rs.getString("ThamGiaHoatDong");
                String anhCaNhan = rs.getString("AnhCaNhan");
                String cVBanThan = rs.getString("AnhCaNhan");
                String ghiChu = rs.getString("GhiChu");
                String maNhomQuyen = rs.getString("MaNhomQuyen");
                String passWord = rs.getString("Password");
                NhanVien e = new NhanVien(maNV, tenNV, ngaySinh, maPhongBan1, GioiTinh, DienThoai, DiaChi, Email, theCanCuoc, ChuyenNganh, cVHoanThanh, kNLamViec, yThucLamViec, thamGiaHD, anhCaNhan, cVBanThan, ghiChu, maNhomQuyen, passWord);
                list.add(e);
            }
            if (list.size() != 0) {
                return list;
            }
            ps.close();
            rs.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

}
