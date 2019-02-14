package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.LoggedRole;
import java.sql.SQLException;
import DAO.LoginDAO;
import entities.SqlUI;

public class LoginDAOImpl implements LoginDAO {

    SqlUI sqlUI = new SqlUI();
    private String username = sqlUI.getUserName();
    private String password = sqlUI.getPassword();

    @Override
    public boolean checkLogin(String user, String pass) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManager";

            Connection con = DriverManager.getConnection(url,username, password);
            String sql = "select maNhomquyen from nhanvien where MaNhanVien =? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user);
            ps.setString(2, pass);

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
    public boolean checkDataBase(String username, String password) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManager";

            Connection con = DriverManager.getConnection(url, username, password);
            if (!con.getMetaData().getDatabaseProductName().equals("")) {
                return true;
            }
            System.out.println(con.getMetaData().getDatabaseProductName());
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
