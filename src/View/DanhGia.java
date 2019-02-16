package View;

import Controller.DanhGiaMgr;
import Controller.DaoTaoMgr;
import entities.Danhgia;
import entities.LoggedRole;
import entities.NhanVien;
import entities.PhongBan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
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
        // TODO Auto-generated method stub
        ImageIcon icon = new ImageIcon("src\\image\\search.png");
        btnSearch = new JButton("Tìm Kiếm", icon);
        btnSearch.setBounds(30, 450, 125, 30);

        ImageIcon icon6 = new ImageIcon("src\\image\\them.png");
        btnNew = new JButton("Làm Mới", icon6);
        btnNew.setBounds(170, 450, 125, 30);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cmbPhongBan.setSelectedIndex(0);
                txtID.setText("");
                txtName.setText("");
            }
        });
        ImageIcon icon1 = new ImageIcon("src\\image\\save.png");
        btnAdd = new JButton("Thêm", icon1);
        btnAdd.setBounds(310, 450, 130, 30);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
                Danhgia danhgia = new Danhgia(maNhanVien, hoten, CVHThanh, KnLviec, YTLviec, TgiaHD, quy, nam, ghichu);
                if (danhGiaMgr.saveDanhGia(danhgia)) {
                    JOptionPane.showMessageDialog(cbQuy, "Lưu đánh giá thành công");
                    initTable("");
                } else {
                    JOptionPane.showMessageDialog(cbQuy, "Lưu đánh giá thất bại");
                }
            }
        });

        ImageIcon icon2 = new ImageIcon("src\\image\\edit.png");
        btnUpdate = new JButton("Sửa", icon2);
        btnUpdate.setBounds(450, 450, 130, 30);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
                Danhgia danhgia = new Danhgia(maNhanVien, hoten, CVHThanh, KnLviec, YTLviec, TgiaHD, quy, nam, ghichu);
                if (danhGiaMgr.saveDanhGia(danhgia)) {
                    JOptionPane.showMessageDialog(cbQuy, "Cập nhật đánh giá thành công");
                
                } else {
                    JOptionPane.showMessageDialog(cbQuy, "Cập nhật đánh giá thất bại");
                }
                 initTable("");
            }
        });

        ImageIcon icon3 = new ImageIcon("src\\image\\delete.png");
        btnDelete = new JButton("Xóa", icon3);
        btnDelete.setBounds(590, 450, 130, 30);

        add(btnAdd);
        add(btnDelete);
        add(btnNew);
        add(btnSearch);
        add(btnUpdate);

    }

    private void initViewDanhGia_table() {
        // TODO Auto-generated method stub

        //*******************************************
        // Tạo bảng bên Phải
        // *********************************************
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(750, 100, 300, 380);
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
        scrollPane1.setBounds(10, 520, 1300, 200);
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
            System.out.println(list.size() + " nè");
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
        txtID.setBounds(400, 100, 320, 20);
        txtID.setEditable(false);
        txtName = new JTextField();
        txtName.setBounds(400, 140, 320, 20);
        txtName.setEditable(false);
        // *******************************************************
        // Cong Việc Hoàn Thành
        // *******************************************************
        rbtnTot_Cv = new JRadioButton("Tốt");
        rbtnTot_Cv.setBounds(400, 170, 70, 20);
        rbtnTot_Cv.setSelected(true);
        rbtnKha_Cv = new JRadioButton("Khá");
        rbtnKha_Cv.setBounds(480, 170, 70, 20);

        rbtnTB_Cv = new JRadioButton("Trung Bình");
        rbtnTB_Cv.setBounds(560, 170, 100, 20);

        rbtnYeu_Cv = new JRadioButton("Yếu");
        rbtnYeu_Cv.setBounds(670, 170, 70, 20);

        ButtonGroup gb1 = new ButtonGroup();
        gb1.add(rbtnYeu_Cv);
        gb1.add(rbtnTB_Cv);
        gb1.add(rbtnKha_Cv);
        gb1.add(rbtnTot_Cv);

        // *****************************************************
        // Kỹ Năng Làm Việc
        // **************************************************8**
        rbtnTot_KN = new JRadioButton("Tốt");
        rbtnTot_KN.setBounds(400, 200, 70, 20);
        rbtnTot_KN.setSelected(true);
        rbtnKha_KN = new JRadioButton("Khá");
        rbtnKha_KN.setBounds(480, 200, 70, 20);

        rbtnTB_KN = new JRadioButton("Trung Bình");
        rbtnTB_KN.setBounds(560, 200, 100, 20);

        rbtnYeu_KN = new JRadioButton("Yếu");
        rbtnYeu_KN.setBounds(670, 200, 70, 20);

        ButtonGroup gb2 = new ButtonGroup();
        gb2.add(rbtnYeu_KN);
        gb2.add(rbtnKha_KN);
        gb2.add(rbtnTB_KN);
        gb2.add(rbtnTot_KN);

        // *****************************************************
        // Y thuc lam viec
        // *****************************************************
        rbtnTot_yt = new JRadioButton("Tốt");
        rbtnTot_yt.setBounds(400, 230, 70, 20);
        rbtnTot_yt.setSelected(true);
        rbtnKha_yt = new JRadioButton("Khá");
        rbtnKha_yt.setBounds(480, 230, 70, 20);

        rbtnTB_yt = new JRadioButton("Trung Bình");
        rbtnTB_yt.setBounds(560, 230, 100, 20);

        rbtnYeu_yt = new JRadioButton("Yếu");
        rbtnYeu_yt.setBounds(670, 230, 70, 20);

        // ****************************************************
        // Tham Gia Hoat Dong
        // ****************************************************
        rbtnTot_HD = new JRadioButton("Tốt");
        rbtnTot_HD.setBounds(400, 260, 70, 20);
        rbtnTot_HD.setSelected(true);
        rbtnKha_HD = new JRadioButton("Khá");
        rbtnKha_HD.setBounds(480, 260, 70, 20);

        rbtnTB_HD = new JRadioButton("Trung Bình");
        rbtnTB_HD.setBounds(560, 260, 100, 20);

        rbtnYeu_HD = new JRadioButton("Yếu");
        rbtnYeu_HD.setBounds(670, 260, 70, 20);
        // **************************************************
        // Thoi Gian Hoat Dong
        // ***************************************************

        cbQuy = new JComboBox(new String[]{"Quý 1", "Quý 2", "Quý 3", "Quý 4"});
        cbQuy.setBounds(400, 290, 75, 20);

        cbYear = new JComboBox(new String[]{"2014", "2015", "2016", "2017", "2018", "2019"});
        cbYear.setBounds(500, 290, 75, 20);

        txtArea = new JTextArea(5, 5);
        txtArea.setBounds(400, 320, 320, 100);

        add(rbtnKha_Cv);
        add(rbtnKha_HD);
        add(rbtnKha_KN);
        add(rbtnTB_Cv);
        add(rbtnTB_HD);
        add(rbtnTB_KN);
        add(rbtnTot_Cv);
        add(rbtnTot_HD);
        add(rbtnTot_KN);
        add(rbtnYeu_Cv);
        add(rbtnYeu_HD);
        add(rbtnYeu_KN);
        add(rbtnKha_yt);
        add(rbtnTB_yt);
        add(rbtnTot_yt);
        add(rbtnYeu_yt);
        add(cbQuy);
        add(cbYear);
        add(txtArea);
        add(txtID);
        add(txtName);
    }

    private void initViewDanhGia() {
        // TODO Auto-generated method stub
        JButton btnAnh = new JButton("Ảnh");
        btnAnh.setBounds(10, 100, 160, 300);

        JLabel lblTitle = new JLabel("ĐÁNH GIÁ NHÂN VIÊN");
        lblTitle.setBounds(400, 50, 300, 30);

        JLabel lblId = new JLabel("Mã Nhân Viên");
        lblId.setBounds(200, 100, 150, 20);

        JLabel lblName = new JLabel("Tên Nhân Viên");
        lblName.setBounds(200, 140, 150, 20);

        JLabel lblCongViec = new JLabel("Công Việc Hoàn Thành");
        lblCongViec.setBounds(200, 170, 150, 20);

        JLabel lblKyNang = new JLabel("Kỹ Năng Làm Việc");
        lblKyNang.setBounds(200, 200, 150, 20);

        JLabel lblYThuc = new JLabel("Ý Thức Làm Việc");
        lblYThuc.setBounds(200, 230, 150, 20);

        JLabel lblThamGia = new JLabel("Tham Gia Hoạt Động");
        lblThamGia.setBounds(200, 260, 150, 20);

        JLabel lblTime = new JLabel("Thời Gian Đánh Giá");
        lblTime.setBounds(200, 290, 150, 20);

        JLabel lblGhiChu = new JLabel("Ghi Chú");
        lblGhiChu.setBounds(200, 320, 150, 20);

        JLabel phongBan = new JLabel("MÃ PHÒNG BAN");
        phongBan.setBounds(50, 100, 150, 25);
        add(phongBan);
        fillCombobox();
        cmbPhongBan = new JComboBox(Name);
        cmbPhongBan.setBounds(850, 50, 200, 30);
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
        add(lblCongViec);
        add(lblGhiChu);
        add(lblId);
        add(lblKyNang);
        add(lblName);
        add(lblThamGia);
        add(lblTime);
        add(lblTitle);
        add(lblYThuc);

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
                    defaultTableModel.addRow(new Object[]{i, E.getMaNhanVien(), E.getTenNhanVien(), E.getAnhCaNhan()});
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
}
