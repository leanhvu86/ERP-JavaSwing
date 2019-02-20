package Controller;

import DAO.LoginDAOImpl;
import DAO.LoginDAO;
import entities.Config;
import entities.TaiKhoan;
import java.sql.SQLException;
import java.util.List;

public class LoginMgr {

    public LoginDAOImpl loginDAOImpl = new LoginDAOImpl();

    public boolean checkLogin(String username, String password) {
        try {

            return loginDAOImpl.checkLogin(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean checkDataBase(String url, String username, String password) throws SQLException {
        try {

            return loginDAOImpl.checkDataBase(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean saveTaiKhoan(TaiKhoan taiKhoan) {
        try {

            return loginDAOImpl.saveTaiKhoan(taiKhoan);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean xoaTaiKhoan(TaiKhoan taiKhoan) {
        try {

            return loginDAOImpl.xoaTaiKhoan(taiKhoan);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public List<TaiKhoan> getListTaiKhoan(String MaNhanVien) {
        try {

            return loginDAOImpl.getListTaiKhoan(MaNhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Config getConnfig() {
        try {
            return loginDAOImpl.getConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean checkConfig() {
        try {
            return loginDAOImpl.checkConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
