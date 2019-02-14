/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.DaoTao;
import entities.NhanVien;
import entities.PhongBan;
import java.util.List;

/**
 *
 * @author Win
 */
public interface DaoTaoDAO {
    public List<PhongBan> getListPhongBan();
    public List<NhanVien> getListNhanVien(String maPhongBan);
    public boolean saveDaoTao(DaoTao daoTao);
    public boolean getDaoTaoById(String maLop);
    public List<DaoTao> getListDaoTao();
}
