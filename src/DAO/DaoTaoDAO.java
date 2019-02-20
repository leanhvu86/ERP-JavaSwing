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

    /**
     *
     * @return
     */
    public List<PhongBan> getListPhongBan();

    /**
     *
     * @param maPhongBan
     * @return
     */
    public List<NhanVien> getListNhanVien(String maPhongBan);

    /**
     *
     * @param daoTao
     * @return
     */
    public boolean saveDaoTao(DaoTao daoTao);

    /**
     *
     * @param maLop
     * @return
     */
    public boolean getDaoTaoById(String maLop);

    /**
     *
     * @return
     */
    public List<DaoTao> getListDaoTao();

    /**
     *
     * @param maLop
     * @return
     */
    public boolean deleteDaoTaoByID(String maLop);

    /**
     *
     * @param malop
     * @param maPhongBan
     * @param tuNgay
     * @param denNgay
     * @return
     */
    public List<DaoTao> getListDaoTaoByFilter(String malop, String maPhongBan, String tuNgay, String denNgay);
}
