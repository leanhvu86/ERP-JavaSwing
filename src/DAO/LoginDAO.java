package DAO;

import entities.Config;

public interface LoginDAO {

    public boolean checkLogin(String username, String password);

    public boolean checkDataBase(String url, String username, String password);
    
    public Config getConfig();
    public boolean checkConfig();
}
