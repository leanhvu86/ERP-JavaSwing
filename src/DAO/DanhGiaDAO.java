/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Danhgia;
import java.util.List;

/**
 *
 * @author Win
 */
public interface DanhGiaDAO {

    /**
     *
     * @param maNhanVien
     * @return
     */
    public List<Danhgia> getListDanhGia(String maNhanVien);

    /**
     *
     * @param danhgia
     * @return
     */
    public boolean saveDanhGia(Danhgia danhgia);

    /**
     *
     * @param maNhanVien
     * @param Quy
     * @param Nam
     * @return
     */
    public boolean checkDanhGia(String maNhanVien, String Quy, String Nam);

    /**
     *
     * @param maNhanVien
     * @param quy
     * @param nam
     * @return
     */
    public List<Danhgia> getListDanhGiaByFilter(String maNhanVien, String quy, String nam);
    
    public boolean checkTruongPhong(String maNhanVien,String maPhongBan);
}
