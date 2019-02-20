/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.LoginMgr;
import entities.Danhgia;
import entities.Config;
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
public class DanhGiaDAOImpl implements DanhGiaDAO {

    LoginMgr loginMgr = new LoginMgr();
    Config config = loginMgr.getConnfig();
    private final String username = config.getUserName();
    private final String password = config.getPassword();
    private final String url = config.getUrl();

    @Override
    public List<Danhgia> getListDanhGia(String maNhanVien) {
        List<Danhgia> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                String sql = "select * from DanhGia where status=1 ";
                PreparedStatement ps = con.prepareStatement(sql);
                if (!maNhanVien.equals("")) {
                    sql += "and maNhanVien like ?";
                    ps.setString(1, maNhanVien);
                }
                System.out.println(sql + "            select nè");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String maNV = rs.getString("MaNhanVien");
                    String tenNV = rs.getString("HoTen");
                    String cvHoanThanh = rs.getString("congviechoanthanh");
                    String KyNangLamViec = rs.getString("KyNangLamViec");
                    String YThucLamViec = rs.getString("YThucLamViec");
                    String ThamGiaHoatDong = rs.getString("ThamGiaHoatDong");
                    String quyDanhGia = rs.getString("quyDanhGia");
                    String namDanhGia = rs.getString("namDanhGia");
                    String Ghichu = rs.getString("GhiChu");
                    Danhgia e = new Danhgia(maNV, tenNV, cvHoanThanh, KyNangLamViec, YThucLamViec, ThamGiaHoatDong, quyDanhGia, namDanhGia, Ghichu);
                    list.add(e);
                }
                if (list.size() != 0) {
                    return list;
                }
                ps.close();
                rs.close();
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean saveDanhGia(Danhgia danhgia) {

        if (checkDanhGia(danhgia.getMaNhanVien(), danhgia.getQuyDanhGia(), danhgia.getNamDanhGia()) == false) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                Connection con = DriverManager.getConnection(url, username, password);
                String sql = "insert into DanhGia values(?,?,?,?,?,?,?,?,?,1)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, danhgia.getMaNhanVien());
                st.setString(2, danhgia.getCongViecHoanThanh());
                st.setString(3, danhgia.getKyNangLamViec());
                st.setString(4, danhgia.getyThucLamViec());
                st.setString(5, danhgia.getThamGiaHoatDong());
                st.setString(6, danhgia.getQuyDanhGia());
                st.setString(7, danhgia.getNamDanhGia());
                st.setString(8, danhgia.getGhiChu());
                st.setString(9, danhgia.getHoTen());
                st.executeUpdate();
                if (st.getUpdateCount() > 0) {
                    return true;
                }
                st.close();
                con.close();

            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                Connection con = DriverManager.getConnection(url, username, password);
                String sql = "update DanhGia set Congviechoanthanh=?,Kynanglamviec=?,ythuclamviec=?,"
                        + "thamgiahoatdong=?,ghichu=?,hoten=?  where MaNhanVien = ? and quydanhgia=? and namdanhgia=?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, danhgia.getCongViecHoanThanh());
                st.setString(2, danhgia.getKyNangLamViec());
                st.setString(3, danhgia.getyThucLamViec());
                st.setString(4, danhgia.getThamGiaHoatDong());
                st.setString(5, danhgia.getGhiChu());
                st.setString(6, danhgia.getHoTen());
                st.setString(7, danhgia.getMaNhanVien());
                st.setString(8, danhgia.getQuyDanhGia());
                st.setString(9, danhgia.getNamDanhGia());
                st.executeUpdate();
                if (st.getUpdateCount() > 0) {
                    return true;
                }
                st.close();
                con.close();

            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }

        return false;
    }

    public boolean checkDanhGia(String maNhanVien, String Quy, String Nam) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                String sql = "select * from DanhGia where status=1 and maNhanVien = ? and quydanhgia = ?  and Namdanhgia = ?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, maNhanVien);

                ps.setString(2, Quy);

                ps.setString(3, Nam);

                System.out.println(sql + "            select nè");
                ResultSet rs = ps.executeQuery();
                if (rs.next() == true) {
                    return true;
                }
                ps.close();
                rs.close();
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public List<Danhgia> getListDanhGiaByFilter(String maNhanVien, String quy, String nam) {
        List<Danhgia> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                String sql = "select * from DanhGia where status=1 ";
                if (maNhanVien != null && !maNhanVien.equals("")) {
                    sql += " and maNhanVien = '" + maNhanVien + "'";
                }
                if (quy != null && !quy.equals("")) {
                    sql += " and quydanhgia = '" + quy + "'";
                }
                if (nam != null && !nam.equals("")) {
                    sql += "and Namdanhgia = '" + nam + "'";
                }
                System.out.println(sql + "            select nè");
                PreparedStatement ps = con.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String maNV = rs.getString("MaNhanVien");
                    String tenNV = rs.getString("HoTen");
                    String cvHoanThanh = rs.getString("congviechoanthanh");
                    String KyNangLamViec = rs.getString("KyNangLamViec");
                    String YThucLamViec = rs.getString("YThucLamViec");
                    String ThamGiaHoatDong = rs.getString("ThamGiaHoatDong");
                    String quyDanhGia = rs.getString("quyDanhGia");
                    String namDanhGia = rs.getString("namDanhGia");
                    String Ghichu = rs.getString("GhiChu");
                    Danhgia e = new Danhgia(maNV, tenNV, cvHoanThanh, KyNangLamViec, YThucLamViec, ThamGiaHoatDong, quyDanhGia, namDanhGia, Ghichu);
                    list.add(e);

                }
                System.out.println("list size" + list.size());
                if (list.size() != 0) {
                    return list;
                }
                ps.close();
                rs.close();
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkTruongPhong(String maNhanVien,String maPhongBan) {
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                String sql = "select * from NhanVien join TaiKhoan on NhanVien.MaNhanVien=TaiKhoan.MaNhanVien where ChucVu = N'Trưởng phòng' and NhanVien.MaNhanVien= ? and TaiKhoan.MaPhongBan =?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maNhanVien);
                ps.setString(2, maPhongBan);
                System.out.println(sql + "            select nè");
                ResultSet rs = ps.executeQuery();
                if (rs.next() == true) {
                    return true;
                }
                ps.close();
                rs.close();
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
            return false;
        }
        return false;
    }
}
