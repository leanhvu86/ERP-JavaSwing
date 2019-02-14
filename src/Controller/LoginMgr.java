package Controller;

import DAO.LoginDAOImpl;
import DAO.LoginDAO;

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

    public boolean checkDataBase(String username, String password) {
        try {

            return loginDAOImpl.checkDataBase(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
}
