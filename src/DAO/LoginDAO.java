package DAO;

import entities.Config;
import entities.TaiKhoan;
import java.sql.SQLException;
import java.util.List;

public interface LoginDAO {

    public boolean checkLogin(String username, String password);

    public boolean checkDataBase(String url, String username, String password);

    public Config getConfig();

    public boolean checkConfig();

    public List<TaiKhoan> getListTaiKhoan(String MaNhanVien);

    public boolean saveTaiKhoan(TaiKhoan taiKhoan);
    
    public boolean checkTaiKhoan(TaiKhoan taiKhoan);

    public boolean xoaTaiKhoan(TaiKhoan taiKhoan);
}
