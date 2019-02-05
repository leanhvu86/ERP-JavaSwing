/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ThongKeDAOImpl;
import entities.NhanVien;
import java.util.List;

/**
 *
 * @author Win
 */
public class ThongKeMgr {
    ThongKeDAOImpl thongKeDAOImpl=new ThongKeDAOImpl();
    public List<NhanVien> getListByFilter(String gioiTinh,String doTuoi,String hocVan,String chucVu) {
        try {

            return thongKeDAOImpl.getListByFilter(gioiTinh, doTuoi, hocVan, chucVu);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
