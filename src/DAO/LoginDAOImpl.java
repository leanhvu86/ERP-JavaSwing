package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.LoggedRole;
import java.sql.SQLException;
import DAO.LoginDAO;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean checkLogin(String username, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManager";

            Connection con = DriverManager.getConnection(url, "sa", "123456789");
            String sql = "select maNhomquyen from nhanvien where MaNhanVien =? and password=?";
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
    public boolean checkDataBase(String username, String password) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManager";

            Connection con = DriverManager.getConnection(url, username, password);
        if(!con.getMetaData().getDatabaseProductName().equals(""))return true;
            System.out.println(con.getMetaData().getDatabaseProductName());
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
