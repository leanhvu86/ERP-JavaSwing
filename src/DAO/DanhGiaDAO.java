/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Danhgia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Win
 */
public interface DanhGiaDAO {

    public List<Danhgia> getListDanhGia(String maNhanVien);
     public boolean saveDanhGia(Danhgia danhgia);
}
