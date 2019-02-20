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

    public boolean checkDanhGia(Danhgia danhgia) {

        try {
            return danhGiaDAOImpl.checkDanhGia(danhgia.getMaNhanVien(), danhgia.getQuyDanhGia(), danhgia.getNamDanhGia());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Danhgia> getListDanhGiaByFilter(String maNhanVien, String quy, String nam) {
        try {

            return danhGiaDAOImpl.getListDanhGiaByFilter(maNhanVien, quy, nam);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean checkTruongPhong(String maNhanVien,String maPhongBan) {

        try {
            return danhGiaDAOImpl.checkTruongPhong(maNhanVien,maPhongBan);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
