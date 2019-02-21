package View;

import Controller.DanhGiaMgr;
import Controller.DaoTaoMgr;
import entities.Danhgia;
import entities.LoggedRole;
import entities.NhanVien;
import entities.PhongBan;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public final class DanhGia extends JInternalFrame {

    private JButton btnSearch, btnNew, btnAdd, btnUpdate, btnDelete;

    private JTable jTableRight, jTableBottom;
    private JComboBox cmbPhongBan, cbQuy, cbYear;
    DefaultTableModel defaultTableModel, defaultTableModel1;
    final DefaultComboBoxModel Name = new DefaultComboBoxModel();
    private final DaoTaoMgr daoTaoMgr = new DaoTaoMgr();
    JTextField txtID, txtName;
    DanhGiaMgr danhGiaMgr = new DanhGiaMgr();
    JRadioButton rbtnTot_Cv, rbtnKha_Cv, rbtnTB_Cv, rbtnYeu_Cv, rbtnTot_KN, rbtnKha_KN, rbtnTB_KN, rbtnYeu_KN, rbtnTot_yt, rbtnKha_yt, rbtnTB_yt, rbtnYeu_yt, rbtnTot_HD, rbtnKha_HD, rbtnTB_HD, rbtnYeu_HD;
    JTextArea txtArea;
    private JPanel panel3;
    JSlider CVHT, KNLV, YTLV, TGHD;
    String img;
    JButton btnAnh;

    public DanhGia() {
        initViewDanhGia_btn();
        initViewDanhGia_table();
        initViewDanhGia();
        initViewDanhGia_txt();
        initTableDanhGia();
        setLayout(null);
        setSize(1400, 700);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Form Danh Gia");
        setResizable(true);
        setVisible(true);
    }

    private void initViewDanhGia_btn() {
        panel3 = new JPanel();
        panel3.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        panel3.setBounds(370, 20, 580, 400);
        panel3.setLayout(null);
        add(panel3);
        // TODO Auto-generated method stub
        ImageIcon icon = new ImageIcon("src\\image\\search.png");
        btnSearch = new JButton("Tìm Kiếm", icon);
        btnSearch.setBounds(380, 450, 125, 30);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String maNhanVien = txtID.getText();

                if (maNhanVien.equals("")) {
                    JOptionPane.showMessageDialog(TGHD, "Mời bạn chọn nhân viên muốn tìm danh sách đánh giá");

                    return;
                }
                String quyDanhGia = "";
                String namDanhGia = "";
                if (cbQuy.getSelectedIndex() != 0) {
                    quyDanhGia = cbQuy.getSelectedItem().toString();
                }

                if (cbYear.getSelectedIndex() != 0) {
                    namDanhGia = cbYear.getSelectedItem().toString();
                }
                System.out.println(maNhanVien + " manhanvien " + quyDanhGia + " quy danh gia " + namDanhGia);
                try {
                    List<Danhgia> list = danhGiaMgr.getListDanhGiaByFilter(maNhanVien, quyDanhGia, namDanhGia);
                    if (list != null) {
                        loadSlider(list);
                        loadTableDanhGia(list);
                        JOptionPane.showMessageDialog(cbQuy, "Thông tin đánh giá nhân viên như sau: ");
                    } else {
                        JOptionPane.showMessageDialog(cbQuy, "Chưa có bản ghi đánh giá nhân viên trong thời gian bạn tìm ");
                        defaultTableModel1.fireTableDataChanged();
                        defaultTableModel1.setRowCount(0);
                    }

                } catch (HeadlessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(cbQuy, "Không tồn tại lớp đào tạo có thông tin như trên ");
                }
            }
        });

        ImageIcon icon6 = new ImageIcon("src\\image\\them.png");
        btnNew = new JButton("Làm Mới", icon6);
        btnNew.setBounds(520, 450, 125, 30);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loadMoi();
            }
        });
        ImageIcon icon1 = new ImageIcon("src\\image\\save.png");
        btnAdd = new JButton("Thêm", icon1);
        btnAdd.setBounds(660, 450, 130, 30);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String maNV = LoggedRole.getUsername();
                String maPB = cmbPhongBan.getSelectedItem().toString();
                System.out.println(maNV +" phòng ban"+maPB);
                if (!LoggedRole.getLoggedRole().equals("SA")) {
                    if (danhGiaMgr.checkTruongPhong(maNV, maPB) == false) {
                        JOptionPane.showMessageDialog(cbQuy, "Bạn không thể đánh giá nhân viên phòng khác");
                        return;
                    }
                }

                if (txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(cbQuy, "Không để trống ID");
                    txtID.requestFocus();
                    return;
                } else if (txtName.getText().equals("")) {
                    JOptionPane.showMessageDialog(cbQuy, "Không để trống Họ tên");
                    txtName.requestFocus();
                    return;
                }
                String maNhanVien = txtID.getText();
                String hoten = txtName.getText();
                String CVHThanh = "";
                if (rbtnTot_Cv.isSelected() == true) {
                    CVHThanh = "Tốt";
                } else if (rbtnKha_Cv.isSelected() == true) {
                    CVHThanh = "Khá";
                } else if (rbtnTB_Cv.isSelected() == true) {
                    CVHThanh = "Trung Bình";
                } else {
                    CVHThanh = "Yếu";
                }
                String KnLviec = "";
                if (rbtnTot_KN.isSelected() == true) {
                    KnLviec = "Tốt";
                } else if (rbtnKha_KN.isSelected() == true) {
                    KnLviec = "Khá";
                } else if (rbtnTB_KN.isSelected() == true) {
                    KnLviec = "Trung Bình";
                } else {
                    KnLviec = "Yếu";
                }
                String YTLviec = "";
                if (rbtnTot_yt.isSelected() == true) {
                    YTLviec = "Tốt";
                } else if (rbtnKha_yt.isSelected() == true) {
                    YTLviec = "Khá";
                } else if (rbtnTB_yt.isSelected() == true) {
                    YTLviec = "Trung Bình";
                } else {
                    YTLviec = "Yếu";
                }
                String TgiaHD = "";
                if (rbtnTot_HD.isSelected() == true) {
                    TgiaHD = "Tốt";
                } else if (rbtnKha_HD.isSelected() == true) {
                    TgiaHD = "Khá";
                } else if (rbtnTB_HD.isSelected() == true) {
                    TgiaHD = "Trung Bình";
                } else {
                    TgiaHD = "Yếu";
                }
                String quy = cbQuy.getSelectedItem().toString();
                String nam = cbYear.getSelectedItem().toString();
                String ghichu = txtArea.getText();
                if(cbQuy.getSelectedIndex()==0||cbYear.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(cbQuy, "Vui lòng chọn quý và năm đánh giá nhân viên");
                    return;
                }
                Danhgia danhgia = new Danhgia(maNhanVien, hoten, CVHThanh, KnLviec, YTLviec, TgiaHD, quy, nam, ghichu);
                if (danhGiaMgr.checkDanhGia(danhgia) == false) {
                    danhGiaMgr.saveDanhGia(danhgia);
                    loadMoi();
                    JOptionPane.showMessageDialog(cbQuy, "Lưu đánh giá thành công");
                } else {
                    JOptionPane.showMessageDialog(cbQuy, "Lưu đánh giá thất bại\n\nNhân viên của bạn đã có 1 bản đánh giá vào thời gian này!!!\n\n Bạn muốn sửa đánh giá vui lòng tìm và chọn bản ghi");
                }
                initTableDanhGia();
            }
        });

        ImageIcon icon2 = new ImageIcon("src\\image\\edit.png");
        btnUpdate = new JButton("Sửa", icon2);
        btnUpdate.setBounds(800, 450, 130, 30);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String maNV = LoggedRole.getUsername();
                String maPB = cmbPhongBan.getSelectedItem().toString();
                if (!LoggedRole.getLoggedRole().equals("SA")) {
                    if (danhGiaMgr.checkTruongPhong(maNV, maPB) == false) {
                        JOptionPane.showMessageDialog(cbQuy, "Bạn không thể đánh giá nhân viên phòng khác");
                        return;
                    }
                }

                if (txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(cbQuy, "Không để trống ID");
                    txtID.requestFocus();
                    return;
                } else if (txtName.getText().equals("")) {
                    JOptionPane.showMessageDialog(cbQuy, "Không để trống Họ tên");
                    txtName.requestFocus();
                    return;
                }
                String maNhanVien = txtID.getText();
                String hoten = txtName.getText();
                String CVHThanh = "";
                if (rbtnTot_Cv.isSelected() == true) {
                    CVHThanh = "Tốt";
                } else if (rbtnKha_Cv.isSelected() == true) {
                    CVHThanh = "Khá";
                } else if (rbtnTB_Cv.isSelected() == true) {
                    CVHThanh = "Trung Bình";
                } else {
                    CVHThanh = "Yếu";
                }
                String KnLviec = "";
                if (rbtnTot_KN.isSelected() == true) {
                    KnLviec = "Tốt";
                } else if (rbtnKha_KN.isSelected() == true) {
                    KnLviec = "Khá";
                } else if (rbtnTB_KN.isSelected() == true) {
                    KnLviec = "Trung Bình";
                } else {
                    KnLviec = "Yếu";
                }
                String YTLviec = "";
                if (rbtnTot_yt.isSelected() == true) {
                    YTLviec = "Tốt";
                } else if (rbtnKha_yt.isSelected() == true) {
                    YTLviec = "Khá";
                } else if (rbtnTB_yt.isSelected() == true) {
                    YTLviec = "Trung Bình";
                } else {
                    YTLviec = "Yếu";
                }
                String TgiaHD = "";
                if (rbtnTot_HD.isSelected() == true) {
                    TgiaHD = "Tốt";
                } else if (rbtnKha_HD.isSelected() == true) {
                    TgiaHD = "Khá";
                } else if (rbtnTB_HD.isSelected() == true) {
                    TgiaHD = "Trung Bình";
                } else {
                    TgiaHD = "Yếu";
                }
                System.out.println(CVHThanh + "kỹ năng" + KnLviec + "ý thức làm việc" + YTLviec + "Tham gia" + TgiaHD);
                String quy = cbQuy.getSelectedItem().toString();
                String nam = cbYear.getSelectedItem().toString();
                String ghichu = txtArea.getText();
                Danhgia danhgia = new Danhgia(maNhanVien, hoten, CVHThanh, KnLviec, YTLviec, TgiaHD, quy, nam, ghichu);
                if (danhGiaMgr.saveDanhGia(danhgia)) {
                    JOptionPane.showMessageDialog(cbQuy, "Cập nhật đánh giá thành công");
                    loadMoi();
                } else {
                    JOptionPane.showMessageDialog(cbQuy, "Cập nhật đánh giá thất bại");
                }
                initTableDanhGia();
            }
        });

        ImageIcon icon3 = new ImageIcon("src\\image\\delete.png");
        btnDelete = new JButton("Xóa", icon3);
        btnDelete.setBounds(940, 450, 130, 30);

        add(btnAdd);
        add(btnNew);
        add(btnSearch);
        add(btnUpdate);
        CVHT = new JSlider();
        CVHT = new JSlider();
        CVHT.setPaintLabels(true);
//        CVHT.setPaintTicks(true);
        CVHT.setEnabled(false);
        CVHT.setMajorTickSpacing(25);

        KNLV = new JSlider();
        KNLV = new JSlider();
        KNLV.setPaintLabels(true);
//        KNLV.setPaintTicks(true);
        KNLV.setEnabled(false);
        KNLV.setMajorTickSpacing(25);

        YTLV = new JSlider();
        YTLV = new JSlider();
        YTLV.setPaintLabels(true);
//        YTLV.setPaintTicks(true);
        YTLV.setEnabled(false);
        YTLV.setMajorTickSpacing(25);

        TGHD = new JSlider();
        TGHD = new JSlider();
        TGHD.setPaintLabels(true);
//        TGHD.setPaintTicks(true);
        TGHD.setEnabled(false);
        TGHD.setMajorTickSpacing(25);

        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        labelTable.put(100, new JLabel("Tốt"));
        labelTable.put(75, new JLabel("Khá"));
        labelTable.put(50, new JLabel("TB"));
        labelTable.put(25, new JLabel("Yếu"));

        JLabel CVHT1 = new JLabel("CVHT");
        add(CVHT1);
        CVHT1.setBounds(20, 220, 50, 50);

        CVHT.setLabelTable(labelTable);
        add(CVHT);
        CVHT.setBounds(70, 230, 250, 50);
        JLabel KNLV1 = new JLabel("KNLV");
        add(KNLV1);
        KNLV1.setBounds(20, 290, 50, 50);
        KNLV.setLabelTable(labelTable);
        add(KNLV);
        KNLV.setBounds(70, 300, 250, 50);
        JLabel YTLV1 = new JLabel("YTLV");
        add(YTLV1);
        YTLV1.setBounds(20, 370, 50, 50);
        YTLV.setLabelTable(labelTable);
        add(YTLV);
        YTLV.setBounds(70, 380, 250, 50);
        JLabel TGHD1 = new JLabel("TGHD");
        add(TGHD1);
        TGHD1.setBounds(20, 450, 50, 50);
        TGHD.setLabelTable(labelTable);
        add(TGHD);
        TGHD.setBounds(70, 460, 250, 50);
    }

    private void initViewDanhGia_table() {
        // TODO Auto-generated method stub

        //*******************************************
        // Tạo bảng bên Phải
        // *********************************************
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(960, 100, 380, 380);
        add(scrollPane);

        defaultTableModel = new DefaultTableModel(new Object[][]{},
                new String[]{"STT", "Mã Nhân Viên", "Tên Nhân Viên", "Ảnh"});
        jTableRight = new JTable();
        jTableRight.setModel(defaultTableModel);
        scrollPane.setViewportView(jTableRight);

        //******************************************
        //Tạo Bảng Bên Duoi
        //***************************************************
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(10, 520, 1350, 200);
        add(scrollPane1);

        defaultTableModel1 = new DefaultTableModel(new Object[][]{},
                new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Công Việc Hoàn Thành", "Kỹ Năng làm việc", "Ý Thức Làm Việc", "Tham Gian Hoạt Động", "Quý Đánh Giá", "Năm Đánh Giá", "Ghi Chú"});
        jTableBottom = new JTable();
        jTableBottom.setModel(defaultTableModel1);
        scrollPane1.setViewportView(jTableBottom);

    }

    private void initTableDanhGia() {
        defaultTableModel1.fireTableDataChanged();
        defaultTableModel1 = (DefaultTableModel) jTableBottom.getModel();
        defaultTableModel1.setRowCount(0);
        try {
            List<Danhgia> list = danhGiaMgr.getListDanhGia("");
            if (!list.isEmpty()) {
                for (Danhgia E : list) {
                    defaultTableModel1.addRow(new Object[]{
                        E.getMaNhanVien(), E.getHoTen(), E.getCongViecHoanThanh(), E.getKyNangLamViec(), E.getyThucLamViec(), E.getThamGiaHoatDong(), E.getQuyDanhGia(), E.getNamDanhGia(), E.getGhiChu()
                    });
                }

            }
            jTableBottom.setModel(defaultTableModel1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jTableBottom.getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent lse
                    ) {
                        if (lse.getValueIsAdjusting()) {
                            return;
                        }
                        int index = jTableBottom.getSelectedRow();
                        if (index >= 0) {
                            String maNhanVien = jTableBottom.getValueAt(index, 0).toString();
                            String HoTen = jTableBottom.getValueAt(index, 1).toString();
                            txtID.setText(maNhanVien);
                            txtName.setText(HoTen);
                            String CVHThanh = jTableBottom.getValueAt(index, 2).toString();
                            setCVHThanhRatio(CVHThanh);
                            String kyNangLV = jTableBottom.getValueAt(index, 3).toString();
                            setkyNangLVRatio(kyNangLV);
                            String yThucHD = jTableBottom.getValueAt(index, 4).toString();
                            setyThucHDRatio(yThucHD);
                            String thamGiaHD = jTableBottom.getValueAt(index, 5).toString();
                            setthamGiaHDRatio(thamGiaHD);
                            cbQuy.setSelectedItem(jTableBottom.getValueAt(index, 6));
                            cbYear.setSelectedItem(jTableBottom.getValueAt(index, 7));
                        }
                    }
                }
                );

    }

    private void loadTableDanhGia(List<Danhgia> list) {
        defaultTableModel1.fireTableDataChanged();
        defaultTableModel1 = (DefaultTableModel) jTableBottom.getModel();
        defaultTableModel1.setRowCount(0);
        try {
            if (!list.isEmpty()) {
                for (Danhgia E : list) {
                    defaultTableModel1.addRow(new Object[]{
                        E.getMaNhanVien(), E.getHoTen(), E.getCongViecHoanThanh(), E.getKyNangLamViec(), E.getyThucLamViec(), E.getThamGiaHoatDong(), E.getQuyDanhGia(), E.getNamDanhGia(), E.getGhiChu()
                    });
                }

            }
            jTableBottom.setModel(defaultTableModel1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jTableBottom.getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent lse
                    ) {
                        if (lse.getValueIsAdjusting()) {
                            return;
                        }
                        int index = jTableBottom.getSelectedRow();
                        if (index >= 0) {
                            String maNhanVien = jTableBottom.getValueAt(index, 0).toString();
                            String HoTen = jTableBottom.getValueAt(index, 1).toString();
                            txtID.setText(maNhanVien);
                            txtName.setText(HoTen);
                            String CVHThanh = jTableBottom.getValueAt(index, 2).toString();
                            setCVHThanhRatio(CVHThanh);
                            String kyNangLV = jTableBottom.getValueAt(index, 3).toString();
                            setkyNangLVRatio(kyNangLV);
                            String yThucHD = jTableBottom.getValueAt(index, 4).toString();
                            setyThucHDRatio(yThucHD);
                            String thamGiaHD = jTableBottom.getValueAt(index, 5).toString();
                            setthamGiaHDRatio(thamGiaHD);
                            cbQuy.setSelectedItem(jTableBottom.getValueAt(index, 6));
                            cbYear.setSelectedItem(jTableBottom.getValueAt(index, 7));
                        }
                    }
                }
                );

    }

    private void initViewDanhGia_txt() {
        // TODO Auto-generated method stub

        txtID = new JTextField();
        txtID.setBounds(200, 20, 320, 20);
        txtID.setEditable(false);
        txtName = new JTextField();
        txtName.setBounds(200, 60, 320, 20);
        txtName.setEditable(false);
        // *******************************************************
        // Cong Việc Hoàn Thành
        // *******************************************************
        rbtnTot_Cv = new JRadioButton("Tốt");
        rbtnTot_Cv.setBounds(200, 100, 70, 20);
        rbtnTot_Cv.setSelected(true);
        rbtnKha_Cv = new JRadioButton("Khá");
        rbtnKha_Cv.setBounds(280, 100, 70, 20);

        rbtnTB_Cv = new JRadioButton("Trung Bình");
        rbtnTB_Cv.setBounds(360, 100, 100, 20);

        rbtnYeu_Cv = new JRadioButton("Yếu");
        rbtnYeu_Cv.setBounds(460, 100, 70, 20);

        ButtonGroup gb1 = new ButtonGroup();
        gb1.add(rbtnYeu_Cv);
        gb1.add(rbtnTB_Cv);
        gb1.add(rbtnKha_Cv);
        gb1.add(rbtnTot_Cv);

        // *****************************************************
        // Kỹ Năng Làm Việc
        // **************************************************8**
        rbtnTot_KN = new JRadioButton("Tốt");
        rbtnTot_KN.setBounds(200, 140, 70, 20);
        rbtnTot_KN.setSelected(true);
        rbtnKha_KN = new JRadioButton("Khá");
        rbtnKha_KN.setBounds(280, 140, 70, 20);

        rbtnTB_KN = new JRadioButton("Trung Bình");
        rbtnTB_KN.setBounds(360, 140, 100, 20);

        rbtnYeu_KN = new JRadioButton("Yếu");
        rbtnYeu_KN.setBounds(460, 140, 70, 20);

        ButtonGroup gb2 = new ButtonGroup();
        gb2.add(rbtnYeu_KN);
        gb2.add(rbtnKha_KN);
        gb2.add(rbtnTB_KN);
        gb2.add(rbtnTot_KN);

        // *****************************************************
        // Y thuc lam viec
        // *****************************************************
        rbtnTot_yt = new JRadioButton("Tốt");
        rbtnTot_yt.setBounds(200, 180, 70, 20);
        rbtnTot_yt.setSelected(true);
        rbtnKha_yt = new JRadioButton("Khá");
        rbtnKha_yt.setBounds(280, 180, 70, 20);

        rbtnTB_yt = new JRadioButton("Trung Bình");
        rbtnTB_yt.setBounds(360, 180, 100, 20);

        rbtnYeu_yt = new JRadioButton("Yếu");
        rbtnYeu_yt.setBounds(460, 180, 70, 20);

        ButtonGroup gb3 = new ButtonGroup();
        gb3.add(rbtnTot_yt);
        gb3.add(rbtnKha_yt);
        gb3.add(rbtnTB_yt);
        gb3.add(rbtnYeu_yt);
        // ****************************************************
        // Tham Gia Hoat Dong
        // ****************************************************
        rbtnTot_HD = new JRadioButton("Tốt");
        rbtnTot_HD.setBounds(200, 220, 70, 20);
        rbtnTot_HD.setSelected(true);
        rbtnKha_HD = new JRadioButton("Khá");
        rbtnKha_HD.setBounds(280, 220, 70, 20);

        rbtnTB_HD = new JRadioButton("Trung Bình");
        rbtnTB_HD.setBounds(360, 220, 100, 20);

        rbtnYeu_HD = new JRadioButton("Yếu");
        rbtnYeu_HD.setBounds(460, 220, 70, 20);

        ButtonGroup gb4 = new ButtonGroup();
        gb4.add(rbtnTot_HD);
        gb4.add(rbtnKha_HD);
        gb4.add(rbtnTB_HD);
        gb4.add(rbtnYeu_HD);
        // **************************************************
        // Thoi Gian Hoat Dong
        // ***************************************************

        cbQuy = new JComboBox(new String[]{"Chọn Quý", "Quý 1", "Quý 2", "Quý 3", "Quý 4"});
        cbQuy.setBounds(240, 260, 125, 20);

        cbYear = new JComboBox(new String[]{"Chọn năm", "2014", "2015", "2016", "2017", "2018", "2019"});
        cbYear.setBounds(400, 260, 125, 20);

        txtArea = new JTextArea(5, 5);
        txtArea.setBounds(200, 300, 320, 80);

        JScrollPane textArea = new JScrollPane(txtArea);
        textArea.setBounds(200, 300, 320, 80);
        textArea.setVisible(true);
        add(textArea);
        txtArea.setLineWrap(true);

        panel3.add(rbtnKha_Cv);
        panel3.add(rbtnKha_HD);
        panel3.add(rbtnKha_KN);
        panel3.add(rbtnTB_Cv);
        panel3.add(rbtnTB_HD);
        panel3.add(rbtnTB_KN);
        panel3.add(rbtnTot_Cv);
        panel3.add(rbtnTot_HD);
        panel3.add(rbtnTot_KN);
        panel3.add(rbtnYeu_Cv);
        panel3.add(rbtnYeu_HD);
        panel3.add(rbtnYeu_KN);
        panel3.add(rbtnKha_yt);
        panel3.add(rbtnTB_yt);
        panel3.add(rbtnTot_yt);
        panel3.add(rbtnYeu_yt);
        panel3.add(cbQuy);
        panel3.add(cbYear);
        panel3.add(txtArea);
        panel3.add(txtID);
        panel3.add(txtName);
        panel3.add(textArea);
    }

    private void initViewDanhGia() {
        // TODO Auto-generated method stub
        btnAnh = new JButton("Ảnh");
        btnAnh.setBounds(90, 10, 160, 200);

        JLabel lblId = new JLabel("Mã Nhân Viên");
        lblId.setBounds(50, 20, 150, 20);

        JLabel lblName = new JLabel("Tên Nhân Viên");
        lblName.setBounds(50, 60, 150, 20);

        JLabel lblCongViec = new JLabel("Công Việc Hoàn Thành");
        lblCongViec.setBounds(50, 100, 150, 20);

        JLabel lblKyNang = new JLabel("Kỹ Năng Làm Việc");
        lblKyNang.setBounds(50, 140, 150, 20);

        JLabel lblYThuc = new JLabel("Ý Thức Làm Việc");
        lblYThuc.setBounds(50, 180, 150, 20);

        JLabel lblThamGia = new JLabel("Tham Gia Hoạt Động");
        lblThamGia.setBounds(50, 220, 150, 20);

        JLabel lblTime = new JLabel("Thời Gian Đánh Giá");
        lblTime.setBounds(50, 260, 150, 20);

        JLabel lblGhiChu = new JLabel("Ghi Chú");
        lblGhiChu.setBounds(50, 300, 150, 20);

        fillCombobox();
        cmbPhongBan = new JComboBox(Name);
        cmbPhongBan.setBounds(1050, 50, 200, 30);
        cmbPhongBan.setSelectedIndex(0);
        cmbPhongBan.setBackground(Color.white);
        JScrollPane ListScrollPane = new JScrollPane(cmbPhongBan);
        add(cmbPhongBan);
        cmbPhongBan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {

                try {
                    String maPhongBan = cmbPhongBan.getSelectedItem().toString();
                    if (!maPhongBan.equals("Mã Phòng Ban")) {
                        initTable(maPhongBan);
                    } else {
                        defaultTableModel.fireTableDataChanged();
                        defaultTableModel.setRowCount(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        add(btnAnh);
        panel3.add(lblCongViec);
        panel3.add(lblGhiChu);
        panel3.add(lblId);
        panel3.add(lblKyNang);
        panel3.add(lblName);
        panel3.add(lblThamGia);
        panel3.add(lblTime);
        panel3.add(lblYThuc);

    }

    public static void main(String[] args) {
        new DanhGia();
    }

    public void fillCombobox() {
        List<PhongBan> list = daoTaoMgr.getListPhongBan();
        Name.addElement("Mã Phòng Ban");
        for (int i = 0; i < list.size(); i++) {

            PhongBan pb = list.get(i);
            Name.addElement(pb.getMaPhongBan());
        }
    }

    private void initTable(String maPhongBan) {
        defaultTableModel.fireTableDataChanged();
        defaultTableModel.setRowCount(0);

        try {
            System.out.println(maPhongBan);
            List<NhanVien> list = daoTaoMgr.getListNhanVien(maPhongBan);

            if (list.size() > 0) {
                for (NhanVien E : list) {
                    int i = 1;
                    defaultTableModel.addRow(new Object[]{i, E.getMaNhanVien(), E.getTenNhanVien(), E.getAnh()});
                    i++;
                }
                jTableRight.setModel(defaultTableModel);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        jTableRight.getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent lse
                    ) {
                        if (lse.getValueIsAdjusting()) {
                            return;
                        }
                        int index = jTableRight.getSelectedRow();
                        if (index >= 0) {
                            String maNhanVien = jTableRight.getValueAt(index, 1).toString();
                            String HoTen = jTableRight.getValueAt(index, 2).toString();
                            txtID.setText(maNhanVien);
                            txtName.setText(HoTen);
                            img = jTableRight.getValueAt(index, 3).toString();
                            System.out.println(img);
                            upImage(img);
                        }
                    }
                }
                );

    }

    public void setCVHThanhRatio(String diemDanhGia) {

        if (diemDanhGia.equals("Tốt")) {
            rbtnTot_Cv.setSelected(true);
            rbtnKha_Cv.setSelected(false);
            rbtnTB_Cv.setSelected(false);
            rbtnYeu_Cv.setSelected(false);
        } else if (diemDanhGia.equals("Khá")) {
            rbtnTot_Cv.setSelected(false);
            rbtnKha_Cv.setSelected(true);
            rbtnTB_Cv.setSelected(false);
            rbtnYeu_Cv.setSelected(false);
        } else if (diemDanhGia.equals("Trung Bình")) {
            rbtnTot_Cv.setSelected(false);
            rbtnKha_Cv.setSelected(false);
            rbtnTB_Cv.setSelected(true);
            rbtnYeu_Cv.setSelected(false);
        } else {
            rbtnTot_Cv.setSelected(false);
            rbtnKha_Cv.setSelected(false);
            rbtnTB_Cv.setSelected(false);
            rbtnYeu_Cv.setSelected(true);
        }

    }

    public void setkyNangLVRatio(String diemDanhGia) {

        if (diemDanhGia.equals("Tốt")) {
            rbtnTot_KN.setSelected(true);
            rbtnKha_KN.setSelected(false);
            rbtnTB_KN.setSelected(false);
            rbtnYeu_KN.setSelected(false);
        } else if (diemDanhGia.equals("Khá")) {
            rbtnTot_KN.setSelected(false);
            rbtnKha_KN.setSelected(true);
            rbtnTB_KN.setSelected(false);
            rbtnYeu_KN.setSelected(false);
        } else if (diemDanhGia.equals("Trung Bình")) {
            rbtnTot_KN.setSelected(false);
            rbtnKha_KN.setSelected(false);
            rbtnTB_KN.setSelected(true);
            rbtnYeu_KN.setSelected(false);
        } else {
            rbtnTot_KN.setSelected(false);
            rbtnKha_KN.setSelected(false);
            rbtnTB_KN.setSelected(false);
            rbtnYeu_KN.setSelected(true);
        }
    }

    public void setyThucHDRatio(String diemDanhGia) {

        if (diemDanhGia.equals("Tốt")) {
            rbtnTot_yt.setSelected(true);
            rbtnKha_yt.setSelected(false);
            rbtnTB_yt.setSelected(false);
            rbtnYeu_yt.setSelected(false);
        } else if (diemDanhGia.equals("Khá")) {
            rbtnKha_yt.setSelected(true);
            rbtnTot_yt.setSelected(false);

            rbtnTB_yt.setSelected(false);
            rbtnYeu_yt.setSelected(false);
        } else if (diemDanhGia.equals("Trung Bình")) {
            rbtnTB_yt.setSelected(true);
            rbtnKha_yt.setSelected(false);
            rbtnTot_yt.setSelected(false);

            rbtnYeu_yt.setSelected(false);
        } else {
            rbtnKha_yt.setSelected(false);
            rbtnTot_yt.setSelected(false);

            rbtnTB_yt.setSelected(false);
            rbtnYeu_yt.setSelected(true);
        }
    }

    public void setthamGiaHDRatio(String diemDanhGia) {

        if (diemDanhGia.equals("Tốt")) {
            rbtnTot_HD.setSelected(true);
            rbtnKha_HD.setSelected(false);
            rbtnTB_HD.setSelected(false);
            rbtnYeu_HD.setSelected(false);
        } else if (diemDanhGia.equals("Khá")) {
            rbtnTot_HD.setSelected(false);
            rbtnKha_HD.setSelected(true);
            rbtnTB_HD.setSelected(false);
            rbtnYeu_HD.setSelected(false);
        } else if (diemDanhGia.equals("Trung Bình")) {
            rbtnTot_HD.setSelected(false);
            rbtnKha_HD.setSelected(false);
            rbtnTB_HD.setSelected(true);
            rbtnYeu_HD.setSelected(false);
        } else {
            rbtnTot_HD.setSelected(false);
            rbtnKha_HD.setSelected(false);
            rbtnTB_HD.setSelected(false);
            rbtnYeu_HD.setSelected(true);
        }
    }

    public void upImage(String img) {
        System.out.println(img + " ten file là");
        ImageIcon anh = new ImageIcon("src\\image\\CV\\" + img);
        Image image = anh.getImage();
        ImageIcon anh1 = new ImageIcon(image.getScaledInstance(btnAnh.getWidth(), btnAnh.getHeight(), image.SCALE_SMOOTH));
        btnAnh.setIcon(anh1);
    }

    public void loadSlider(List<Danhgia> list) {
        int CVHT_gt = 0;
        int KNLV_gt = 0;
        int YTLV_gt = 0;
        int TGHD_gt = 0;
        for (int i = 0; i < list.size(); i++) {
            Danhgia danhgia = new Danhgia();
            danhgia = list.get(i);
            switch (danhgia.getCongViecHoanThanh()) {
                case "Tốt":
                    CVHT_gt += 100;
                    break;
                case "Khá":
                    CVHT_gt += 80;
                    break;
                case "Trung Bình":
                    CVHT_gt += 60;
                    break;
                case "Yếu":
                    CVHT_gt += 40;
                    break;
            }
            switch (danhgia.getKyNangLamViec()) {
                case "Tốt":
                    KNLV_gt += 100;
                    break;
                case "Khá":
                    KNLV_gt += 80;
                    break;
                case "Trung Bình":
                    KNLV_gt += 60;
                    break;
                case "Yếu":
                    KNLV_gt += 40;
                    break;
            }
            switch (danhgia.getyThucLamViec()) {
                case "Tốt":
                    YTLV_gt += 100;
                    break;
                case "Khá":
                    YTLV_gt += 80;
                    break;
                case "Trung Bình":
                    YTLV_gt += 60;
                    break;
                case "Yếu":
                    YTLV_gt += 40;
                    break;
            }
            switch (danhgia.getThamGiaHoatDong()) {
                case "Tốt":
                    TGHD_gt += 100;
                    break;
                case "Khá":
                    TGHD_gt += 80;
                    break;
                case "Trung Bình":
                    TGHD_gt += 60;
                    break;
                case "Yếu":
                    TGHD_gt += 40;
                    break;
            }
        }
        System.out.println(CVHT_gt / list.size() + " kỹ năng làm việc" + KNLV_gt / list.size() + " ý thức làm việc " + YTLV_gt / list.size() + " tham gia hoạt đọng" + TGHD_gt / list.size());
        CVHT.setValue(CVHT_gt / list.size());
        KNLV.setValue(KNLV_gt / list.size());
        YTLV.setValue(YTLV_gt / list.size());
        TGHD.setValue(TGHD_gt / list.size());
    }

    public void loadMoi() {
        cmbPhongBan.setSelectedIndex(0);
        txtID.setText("");
        txtName.setText("");
        defaultTableModel1.fireTableDataChanged();
        defaultTableModel1.setRowCount(0);
        cbQuy.setSelectedIndex(0);
        cbYear.setSelectedIndex(0);
    }
}
