package View;

import Controller.DaoTaoMgr;
import Controller.LoginMgr;
import entities.NhanVien;
import entities.PhongBan;
import entities.TaiKhoan;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public final class Admin extends JInternalFrame {

    private JButton btnSearch, btnNew, btnAdd, btnUpdate, btnDelete;

    private JTable jTableRight, jTableBottom;
    private JComboBox cmbPhongBan, cmbMaNhomQuyen;
    DefaultTableModel defaultTableModel, defaultTableModel1;
    final DefaultComboBoxModel Name = new DefaultComboBoxModel();
    private final DaoTaoMgr daoTaoMgr = new DaoTaoMgr();
    JTextField txtID, txtPassword;
    LoginMgr loginMgr = new LoginMgr();
    private JPanel panel3;
    String img;
    JButton btnAnh;
    String[] maNhomQuyen = {"Chọn mã quyền", "Admin", "Excute ", "SA", "Supervisor", "User"};

    public Admin() {
        initViewDanhGia_btn();
        initViewDanhGia_table();
        initViewDanhGia();
        initViewDanhGia_txt();
        initTableNhanVien();
        setLayout(null);
        setSize(1400, 700);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lí hệ thống");
        setResizable(true);
        setVisible(true);
    }

    private void initViewDanhGia_btn() {
        panel3 = new JPanel();
        panel3.setBorder(new TitledBorder(null, "Quản lí tài khoản", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        panel3.setBounds(350, 120, 580, 250);
        panel3.setLayout(null);
        add(panel3);
        // TODO Auto-generated method stub
        ImageIcon icon = new ImageIcon("src\\image\\search.png");
        btnSearch = new JButton("Tìm Kiếm", icon);
        btnSearch.setBounds(180, 410, 125, 30);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String maNhanVien = txtID.getText();

                if (maNhanVien.equals("")) {
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn nhân viên muốn tìm Tài khoản");

                    return;
                }
                JOptionPane.showMessageDialog(null, "Thông tin tài khoản bạn tìm như sau");
                loadTableTaiKhoan(maNhanVien);
            }
        });

        ImageIcon icon6 = new ImageIcon("src\\image\\them.png");
        btnNew = new JButton("Làm Mới", icon6);
        btnNew.setBounds(330, 410, 125, 30);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loadMoi();
            }
        });
        ImageIcon icon1 = new ImageIcon("src\\image\\save.png");
        btnAdd = new JButton("Thêm", icon1);
        btnAdd.setBounds(480, 410, 130, 30);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Không để trống ID");
                    txtID.requestFocus();
                    return;
                } else if (txtPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Không để trống Password");
                    txtPassword.requestFocus();
                    return;
                } else if (cmbMaNhomQuyen.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn Mã nhóm quyền");
                    cmbMaNhomQuyen.requestFocus();
                    return;
                }
                String maNhanVien = txtID.getText();
                String pasword = txtPassword.getText();
                String NhomQuyen = cmbMaNhomQuyen.getSelectedItem().toString();
                TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, pasword, NhomQuyen);
                if (loginMgr.saveTaiKhoan(taiKhoan) == true) {
                    JOptionPane.showMessageDialog(null, "Lưu tài khoản thành công");
                    initTableNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu tài khoản thất bại");
                }
            }
        });

        ImageIcon icon2 = new ImageIcon("src\\image\\edit.png");
        btnUpdate = new JButton("Sửa", icon2);
        btnUpdate.setBounds(630, 410, 130, 30);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Không để trống ID");
                    txtID.requestFocus();
                    return;
                } else if (txtPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Không để trống Password");
                    txtPassword.requestFocus();
                    return;
                } else if (cmbMaNhomQuyen.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn Mã nhóm quyền");
                    cmbMaNhomQuyen.requestFocus();
                    return;
                }
                String maNhanVien = txtID.getText();
                String pasword = txtPassword.getText();
                String NhomQuyen = cmbMaNhomQuyen.getSelectedItem().toString();
                TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, pasword, NhomQuyen);
                if (loginMgr.saveTaiKhoan(taiKhoan) == true) {
                    JOptionPane.showMessageDialog(null, "Lưu tài khoản thành công");
                    initTableNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu tài khoản thất bại");
                }
            }
        });

        ImageIcon icon3 = new ImageIcon("src\\image\\delete.png");
        btnDelete = new JButton("Xóa", icon3);
        btnDelete.setBounds(780, 410, 130, 30);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Bạn muốn xóa lớp đào tạo?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (txtID.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản bạn muốn xóa");
                        return;
                    }

                    String maNhanVien = txtID.getText();
                    String pasword = txtPassword.getText();
                    String NhomQuyen = cmbMaNhomQuyen.getSelectedItem().toString();
                    TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, pasword, NhomQuyen);
                    if (loginMgr.xoaTaiKhoan(taiKhoan) == true) {
                        JOptionPane.showMessageDialog(txtID, "Xóa thành công");
                        loadMoi();
                    } else {
                        JOptionPane.showMessageDialog(txtID, "Xóa thất bại");
                    }
                }
                initTableNhanVien();
            }
        });
        add(btnDelete);
        add(btnAdd);
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
        scrollPane1.setBounds(10, 490, 1350, 200);
        add(scrollPane1);

        defaultTableModel1 = new DefaultTableModel(new Object[][]{},
                new String[]{"Mã Nhân Viên", "Password", "Mã Nhóm Quyền"});
        jTableBottom = new JTable();
        jTableBottom.setModel(defaultTableModel1);
        scrollPane1.setViewportView(jTableBottom);

    }

    private void initTableNhanVien() {
        defaultTableModel1.fireTableDataChanged();
        defaultTableModel1 = (DefaultTableModel) jTableBottom.getModel();
        defaultTableModel1.setRowCount(0);
        try {
            List<TaiKhoan> list = loginMgr.getListTaiKhoan("");
            if (!list.isEmpty()) {
                for (TaiKhoan E : list) {
                    defaultTableModel1.addRow(new Object[]{
                        E.getMaNhanVien(), E.getPassword(), E.getMaNhomQuyen()
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
                            String password = jTableBottom.getValueAt(index, 1).toString();
                            txtID.setText(maNhanVien);
                            txtPassword.setText(password);
                            cmbMaNhomQuyen.setSelectedItem(jTableBottom.getValueAt(index, 2).toString());
                        }
                    }
                }
                );

    }

    private void loadTableTaiKhoan(String maNhanVien) {
        defaultTableModel1.fireTableDataChanged();
        defaultTableModel1 = (DefaultTableModel) jTableBottom.getModel();
        defaultTableModel1.setRowCount(0);
        try {
            List<TaiKhoan> list = loginMgr.getListTaiKhoan(maNhanVien);
            if (!list.isEmpty()) {
                for (TaiKhoan E : list) {
                    defaultTableModel1.addRow(new Object[]{
                        E.getMaNhanVien(), E.getPassword(), E.getMaNhomQuyen()
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
                            String password = jTableBottom.getValueAt(index, 1).toString();
                            txtID.setText(maNhanVien);
                            txtPassword.setText(password);
                            cmbMaNhomQuyen.setSelectedItem(jTableBottom.getValueAt(index, 2).toString());
                        }
                    }
                }
                );

    }

    private void initViewDanhGia_txt() {
        // TODO Auto-generated method stub
        JLabel id = new JLabel("Mã Nhân Viên");
        id.setBounds(20, 50, 150, 20);
        txtID = new JTextField();
        txtID.setBounds(200, 50, 320, 20);
        txtID.setEditable(false);
        txtPassword = new JTextField();
        txtPassword.setBounds(200, 100, 320, 20);
        txtPassword.setEditable(true);

        JLabel Password = new JLabel("Password");
        Password.setBounds(20, 100, 150, 20);

        JLabel Ma = new JLabel("Mã nhóm Quyền");
        Ma.setBounds(20, 150, 150, 20);
        cmbMaNhomQuyen = new JComboBox(maNhomQuyen);
        cmbMaNhomQuyen.setBounds(200, 150, 150, 20);
        cmbMaNhomQuyen.setSelectedIndex(0);
        cmbMaNhomQuyen.setBackground(Color.white);
        JScrollPane ListScrollPane = new JScrollPane(cmbMaNhomQuyen);
        panel3.add(cmbMaNhomQuyen);
        panel3.add(txtID);
        panel3.add(txtPassword);
        panel3.add(id);
        panel3.add(Password);
        panel3.add(Ma);

    }

    private void initViewDanhGia() {
        // TODO Auto-generated method stub
        btnAnh = new JButton("Ảnh");
        btnAnh.setBounds(80, 160, 160, 200);

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
    }

    public static void main(String[] args) {
        new Admin();
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
                     
                            txtID.setText(maNhanVien);
            
                            img = jTableRight.getValueAt(index, 3).toString();
                            System.out.println(img);
                            upImage(img);
                        }
                    }
                }
                );

    }

    public void upImage(String img) {
        System.out.println(img + " ten file là");
        ImageIcon anh = new ImageIcon("src\\image\\CV\\" + img);
        Image image = anh.getImage();
        ImageIcon anh1 = new ImageIcon(image.getScaledInstance(btnAnh.getWidth(), btnAnh.getHeight(), image.SCALE_SMOOTH));
        btnAnh.setIcon(anh1);
    }

    public void loadMoi() {
        cmbPhongBan.setSelectedIndex(0);
        txtID.setText("");
        txtPassword.setText("");
        defaultTableModel1.fireTableDataChanged();
        defaultTableModel1.setRowCount(0);
    }

}
