/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DaoTaoDAOImpl;
import entities.DaoTao;
import entities.NhanVien;
import entities.PhongBan;
import java.util.List;

/**
 *
 * @author Win
 */
public class DaoTaoMgr {
    
    public DaoTaoDAOImpl daoTaoDAOImpl = new DaoTaoDAOImpl();
    
    public List<PhongBan> getListPhongBan() {
        try {
            
            return daoTaoDAOImpl.getListPhongBan();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<DaoTao> getListDaoTao() {
        try {
            
            return daoTaoDAOImpl.getListDaoTao();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<DaoTao> getListDaoTao(String maLop, String maPhongBan, String tuNgay, String denNgay) {
        try {
            
            return daoTaoDAOImpl.getListDaoTaoByFilter(maLop, maPhongBan, tuNgay, denNgay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<NhanVien> getListNhanVien(String maPhongBan) {
        try {
            
            return daoTaoDAOImpl.getListNhanVien(maPhongBan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean saveDaoTao(DaoTao daoTao) {
        try {
            return daoTaoDAOImpl.saveDaoTao(daoTao);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteDaoTaoByID(String maLop) {
        try {
            return daoTaoDAOImpl.deleteDaoTaoByID(maLop);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean getDaoTaoById(String maLop) {
        try {
            return daoTaoDAOImpl.getDaoTaoById(maLop);
        } catch (Exception e) {
        }
        return false;
    }
}
