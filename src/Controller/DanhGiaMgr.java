/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DanhGiaDAOImpl;
import entities.Danhgia;
import java.util.List;

/**
 *
 * @author Win
 */
public class DanhGiaMgr {

    public DanhGiaDAOImpl danhGiaDAOImpl = new DanhGiaDAOImpl();

    public List<Danhgia> getListDanhGia(String maNhanVien) {
        try {

            return danhGiaDAOImpl.getListDanhGia(maNhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean saveDanhGia(Danhgia danhgia) {
      
        try {
            return danhGiaDAOImpl.saveDanhGia(danhgia);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
