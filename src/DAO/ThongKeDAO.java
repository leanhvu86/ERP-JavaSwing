/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.NhanVien;
import java.util.List;

/**
 *
 * @author Win
 */
public interface ThongKeDAO {
    public List<NhanVien> getListByFilter(String gioiTinh,String doTuoi,String hocVan,String chucVu);
    public void xuatExcel();
}
