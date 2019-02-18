package Controller;

import DAO.LoginDAOImpl;
import DAO.LoginDAO;
import entities.Config;

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

    public boolean checkDataBase(String url, String username, String password) {
        try {

            return loginDAOImpl.checkDataBase(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

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
