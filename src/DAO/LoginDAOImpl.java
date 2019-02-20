package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.LoggedRole;
import java.sql.SQLException;
import entities.Config;
import entities.PhongBan;
import entities.TaiKhoan;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    Config config = getConfig();
    String url = config.getUrl();
    String user = config.getUserName();
    String pass = config.getPassword();
    ArrayList<Config> list = new ArrayList<>();

    @Override
    public boolean checkLogin(String username, String password) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select maNhomquyen,MaNhanVien from taikhoan where MaNhanVien =? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet sq = ps.executeQuery();
            Boolean a = sq.next();
            String loggedRole;
            String maNhanVien;
            try {
                loggedRole = sq.getString("MaNhomQuyen");
                maNhanVien = sq.getString("MaNhanVien");
                //gán quyền cho nhân viên 
                LoggedRole.setLoggedRole(loggedRole);
                LoggedRole.setUsername(maNhanVien);
                System.out.println(LoggedRole.getLoggedRole() + LoggedRole.getUsername());
            } catch (SQLException e) {
            }

            ps.close();
            sq.close();
            con.close();
            if (a) {

                return true;
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkDataBase(String url, String username, String password) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println(con.getMetaData().getDatabaseProductName());
            if (!con.getMetaData().getDatabaseProductName().equals("")) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public Config getConfig() {
        Config config = new Config();
        try {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            fis = new FileInputStream("src\\config.txt");
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Config>) ois.readObject();
            config = list.get(0);
            System.out.println("đọc từ file" + list.size() + " " + config.getPassword() + "fds " + config.getUserName() + " " + config.getUrl());
            ois.close();
            fis.close();
        } catch (Exception e) {
        }
        return config;
    }

    @Override
    public boolean checkConfig() {
        Config config = new Config();
        try {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            fis = new FileInputStream("src\\config.txt");
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Config>) ois.readObject();
            config = list.get(0);
            if (checkDataBase(config.getUrl(), config.getUserName(), config.getPassword()) == false) {
                return false;
            }

            ois.close();
            fis.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<TaiKhoan> getListTaiKhoan(String maNhanVien) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from taikhoan where status = 1";
            if (maNhanVien != null && !maNhanVien.equals("")) {
                sql += " and MaNhanVien = " + maNhanVien;
            }
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String MaNhanVien = rs.getString("MaNhanVien");
                String Password = rs.getString("Password");
                String MaNhomQuyen = rs.getString("MaNhomQuyen");

                TaiKhoan e = new TaiKhoan(MaNhanVien, Password, MaNhomQuyen);

                list.add(e);
            }
            System.out.println(list.size());
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
    public boolean saveTaiKhoan(TaiKhoan taiKhoan) {
        if (checkTaiKhoan(taiKhoan) == false) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "insert into taikhoan values(?,?,?,1)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, taiKhoan.getMaNhanVien());
                st.setString(2, taiKhoan.getPassword());
                st.setString(3, taiKhoan.getMaNhomQuyen());
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

                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "update taikhoan set password=?,manhomquyen=? , status = 1"
                        + " where manhanvien = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, taiKhoan.getPassword());
                st.setString(2, taiKhoan.getMaNhomQuyen());
                st.setString(3, taiKhoan.getMaNhanVien());
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
    public boolean checkTaiKhoan(TaiKhoan taiKhoan) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select maNhomquyen from taikhoan where MaNhanVien =? ";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, taiKhoan.getMaNhanVien());

            ResultSet sq = ps.executeQuery();
            Boolean a = sq.next();
            ps.close();
            sq.close();
            con.close();
            if (a) {

                return true;
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean xoaTaiKhoan(TaiKhoan taiKhoan) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                String sql = "update taikhoan set status =0 where manhanvien = ? ";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, taiKhoan.getMaNhanVien());
                st.executeUpdate();

                st.close();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        }
    }

}
