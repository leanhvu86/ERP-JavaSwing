package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.LoggedRole;
import java.sql.SQLException;
import DAO.LoginDAO;
import entities.Config;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAOImpl implements LoginDAO {

    ArrayList<Config> list = new ArrayList<>();

    @Override
    public boolean checkLogin(String username, String password) {
        Config config = getConfig();
        String url = config.getUrl();
        String user = config.getUserName();
        String pass = config.getPassword();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select maNhomquyen from taikhoan where MaNhanVien =? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet sq = ps.executeQuery();
            Boolean a = sq.next();
            String loggedRole;
            try {
                loggedRole = sq.getString("MaNhomQuyen");
                //gán quyền cho nhân viên 
                LoggedRole.setLoggedRole(loggedRole);
                System.out.println(LoggedRole.getLoggedRole());
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
            if (!con.getMetaData().getDatabaseProductName().equals("")) {
                return true;
            }
            System.out.println(con.getMetaData().getDatabaseProductName());

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
            System.out.println("đọc từ file" + list.size() + config.getPassword() + "fds " + config.getUserName() + " " + config.getUrl());
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
}
