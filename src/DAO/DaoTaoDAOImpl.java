/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.LoginMgr;
import entities.DaoTao;
import entities.LoggedRole;
import entities.NhanVien;
import entities.PhongBan;
import entities.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Win
 */
public class DaoTaoDAOImpl implements DaoTaoDAO {

    LoginMgr loginMgr = new LoginMgr();
    Config config = loginMgr.getConnfig();
    private final String username = config.getUserName();
    private final String password = config.getPassword();
    private final String url = config.getUrl();

    @Override
    public List<PhongBan> getListPhongBan() {
        List<PhongBan> list = new ArrayList<>();
        System.out.println(username + "fdsfd" + password);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
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
        return list;
    }

    @Override
    public List<NhanVien> getListNhanVien(String maPhongBan) {
        List<NhanVien> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                String sql = "select * from NhanVien where status=1 and MaPhongBan=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maPhongBan);
                System.out.println(sql + "            select nè");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String maNV = rs.getString("MaNhanVien");
                    String tenNV = rs.getString("HoTen");
                    String anh = rs.getString("Anh");
                    NhanVien e = new NhanVien(maNV, tenNV, anh);
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
    public boolean saveDaoTao(DaoTao daoTao) {
        if (getDaoTaoById(daoTao.getMaLop()) == false) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                Connection con = DriverManager.getConnection(url, username, password);
                String sql = "insert into DaoTao values(?,?,?,?,?,?,?,1)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, daoTao.getMaLop());
                st.setString(2, daoTao.getTenLop());
                st.setString(3, daoTao.getDanhSach());
                st.setString(4, daoTao.getMaPhongBan());
                st.setString(5, daoTao.getTuNgay());
                st.setString(6, daoTao.getDenNgay());
                st.setString(7, daoTao.getGhiChu());
                st.executeUpdate();
                if (st.getUpdateCount() > 0) {
                    return true;
                }
                System.out.println(sql);
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
                String sql = "update DaoTao set TenLop=?,danhsach=?,maphongban=?,"
                        + "tungay=?,denngay=?,ghichu=?  where Malop = ?";
                PreparedStatement st = con.prepareStatement(sql);

                st.setString(1, daoTao.getTenLop());
                st.setString(2, daoTao.getDanhSach());
                st.setString(3, daoTao.getMaPhongBan());
                st.setString(4, daoTao.getTuNgay());
                st.setString(5, daoTao.getDenNgay());
                st.setString(6, daoTao.getGhiChu());
                st.setString(7, daoTao.getMaLop());
                st.executeUpdate();
                if (st.getUpdateCount() > 0) {
                    return true;
                }
                st.close();
                con.close();
                System.out.println(sql);
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean getDaoTaoById(String maLop) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from DaoTao where 1=1 and MaLop=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maLop);
            ResultSet rs = ps.executeQuery();
            boolean isExist = rs.next();
            System.out.println(isExist);
            if (isExist == true) {
                return true;
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<DaoTao> getListDaoTao() {
        List<DaoTao> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from DaoTao where status=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String MaLop = rs.getString("MaLop");
                String TenLop = rs.getString("TenLop");
                String danhSach = rs.getString("DanhSach");
                String MaPhongBan = rs.getString("MaPhongBan");
                String tuNgay = rs.getString("TuNgay");
                String denNgay = rs.getString("DenNgay");
                String ghiChu = rs.getString("GhiChu");
                DaoTao e = new DaoTao(0, MaLop, TenLop, danhSach, MaPhongBan, tuNgay, denNgay, ghiChu);
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
        return list;
    }

    @Override
    public boolean deleteDaoTaoByID(String maLop) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                String sql = "update daotao set status =0 where malop = ? ";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, maLop);
                st.executeUpdate();

                st.close();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<DaoTao> getListDaoTaoByFilter(String malop, String maPhongBan, String tuNgay, String denNgay) {
        List<DaoTao> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from DaoTao where status=1";
            if (malop != null && !malop.equals("")) {
                sql += " and malop = '" + malop+"'";
            }
            if (maPhongBan != null && !maPhongBan.equals("")) {
                sql += " and maphongban = '" + maPhongBan+"'";
            }
            if (tuNgay != null && !tuNgay.equals("")) {
                sql += "and tungay >= '" + tuNgay+"'";
            }
            if (denNgay != null && !denNgay.equals("")) {
                sql += "and denngay <= '" + denNgay+"'";
            }
            System.out.println(" câu truy vấn: " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String MaLop = rs.getString("MaLop");
                String TenLop = rs.getString("TenLop");
                String danhSach = rs.getString("DanhSach");
                String MaPhongBan = rs.getString("MaPhongBan");
                String tuNgay1 = rs.getString("TuNgay");
                String denNgay1 = rs.getString("DenNgay");
                String ghiChu = rs.getString("GhiChu");
                DaoTao e = new DaoTao(0, MaLop, TenLop, danhSach, MaPhongBan, tuNgay1, denNgay1, ghiChu);
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
        return list;
    }
}
