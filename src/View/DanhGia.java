package View;

import Controller.DaoTaoMgr;
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

    DefaultTableModel defaultTableModel, defaultTableModel1;

    public DanhGia() {
        initViewDanhGia_btn();
        initViewDanhGia_table();
        initViewDanhGia();
        initViewDanhGia_txt();
        setLayout(null);
        setSize(1400, 1000);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Form Danh Gia");
        setResizable(true);
        setVisible(true);
    }

    private void initViewDanhGia_btn() {
        // TODO Auto-generated method stub
        btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBounds(110, 450, 100, 30);

        btnNew = new JButton("Làm Mới");
        btnNew.setBounds(230, 450, 100, 30);

        btnAdd = new JButton("Thêm");
        btnAdd.setBounds(350, 450, 100, 30);

        btnUpdate = new JButton("Sửa");
        btnUpdate.setBounds(470, 450, 100, 30);

        btnDelete = new JButton("Xóa");
        btnDelete.setBounds(580, 450, 100, 30);

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
        scrollPane.setBounds(750, 100, 220, 380);
        add(scrollPane);

        defaultTableModel = new DefaultTableModel(new Object[][]{},
                new String[]{"STT", "Mã Nhân Viên", "Tên Nhân Viên"});
        jTableRight = new JTable();
        jTableRight.setModel(defaultTableModel);
        scrollPane.setViewportView(jTableRight);

        //******************************************
        //Tạo Bảng Bên Duoi
        //***************************************************
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(10, 520, 960, 250);
        add(scrollPane1);

        defaultTableModel1 = new DefaultTableModel(new Object[][]{},
                new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Công Việc Hoàn Thành", "Kỹ Năng làm việc", "Ý Thức Làm Việc", "Tham Gian Hoạt Động", "Thời Gian Đánh Giá", "Ghi Chú"});
        jTableBottom = new JTable();
        jTableBottom.setModel(defaultTableModel1);
        scrollPane1.setViewportView(jTableBottom);

    }

    private void initViewDanhGia_txt() {
        // TODO Auto-generated method stub

        JTextField txtID = new JTextField();
        txtID.setBounds(400, 100, 320, 20);

        JTextField txtName = new JTextField();
        txtName.setBounds(400, 140, 320, 20);

        // *******************************************************
        // Cong Việc Hoàn Thành
        // *******************************************************
        JRadioButton rbtnTot_Cv = new JRadioButton("Tốt");
        rbtnTot_Cv.setBounds(400, 170, 70, 20);

        JRadioButton rbtnKha_Cv = new JRadioButton("Khá");
        rbtnKha_Cv.setBounds(480, 170, 70, 20);

        JRadioButton rbtnTB_Cv = new JRadioButton("Trung Bình");
        rbtnTB_Cv.setBounds(560, 170, 100, 20);

        JRadioButton rbtnYeu_Cv = new JRadioButton("Yếus");
        rbtnYeu_Cv.setBounds(670, 170, 70, 20);

        ButtonGroup gb1 = new ButtonGroup();
        gb1.add(rbtnYeu_Cv);
        gb1.add(rbtnTB_Cv);
        gb1.add(rbtnKha_Cv);
        gb1.add(rbtnTot_Cv);

        // *****************************************************
        // Kỹ Năng Làm Việc
        // **************************************************8**
        JRadioButton rbtnTot_KN = new JRadioButton("Tốt");
        rbtnTot_KN.setBounds(400, 200, 70, 20);

        JRadioButton rbtnKha_KN = new JRadioButton("Khá");
        rbtnKha_KN.setBounds(480, 200, 70, 20);

        JRadioButton rbtnTB_KN = new JRadioButton("Trung Bình");
        rbtnTB_KN.setBounds(560, 200, 100, 20);

        JRadioButton rbtnYeu_KN = new JRadioButton("Yếus");
        rbtnYeu_KN.setBounds(670, 200, 70, 20);

        ButtonGroup gb2 = new ButtonGroup();
        gb2.add(rbtnYeu_KN);
        gb2.add(rbtnKha_KN);
        gb2.add(rbtnTB_KN);
        gb2.add(rbtnTot_KN);

        // *****************************************************
        // Y thuc lam viec
        // *****************************************************
        JRadioButton rbtnTot_yt = new JRadioButton("Tốt");
        rbtnTot_yt.setBounds(400, 230, 70, 20);

        JRadioButton rbtnKha_yt = new JRadioButton("Khá");
        rbtnKha_yt.setBounds(480, 230, 70, 20);

        JRadioButton rbtnTB_yt = new JRadioButton("Trung Bình");
        rbtnTB_yt.setBounds(560, 230, 100, 20);

        JRadioButton rbtnYeu_yt = new JRadioButton("Yếus");
        rbtnYeu_yt.setBounds(670, 230, 70, 20);

        // ****************************************************
        // Tham Gia Hoat Dong
        // ****************************************************
        JRadioButton rbtnTot_HD = new JRadioButton("Tốt");
        rbtnTot_HD.setBounds(400, 260, 70, 20);

        JRadioButton rbtnKha_HD = new JRadioButton("Khá");
        rbtnKha_HD.setBounds(480, 260, 70, 20);

        JRadioButton rbtnTB_HD = new JRadioButton("Trung Bình");
        rbtnTB_HD.setBounds(560, 260, 100, 20);

        JRadioButton rbtnYeu_HD = new JRadioButton("Yếus");
        rbtnYeu_HD.setBounds(670, 260, 70, 20);
        // **************************************************
        // Thoi Gian Hoat Dong
        // ***************************************************

        JComboBox cbQuy = new JComboBox(new String[]{"Quý 1", "Quý 2", "Quý 3", "Quý 4"});
        cbQuy.setBounds(400, 290, 75, 20);

        JComboBox cbYear = new JComboBox(new String[]{"2014", "2015", "2016", "2017", "2018", "2019"});
        cbYear.setBounds(500, 290, 75, 20);

        JTextArea txtArea = new JTextArea(5, 5);
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

        JLabel lblTitle = new JLabel("Danh Sách Đánh Giá");
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
}
