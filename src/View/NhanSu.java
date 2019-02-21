package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.NhanVien;
import java.awt.BorderLayout;
import Controller.LoginMgr;
import entities.Config;
import Controller.LoginMgr;
import entities.Config;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Administrator
 */
public class NhanSu extends JInternalFrame {

    private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6;
    JButton btn_them, btn_sua, btn_xoa, btn_timkiem, btn_lammoi, btn_anh, btn_last, btn_first, btn_next, btn_prev;
    JLabel lbl_id, lbl_hoten, lbl_ngaysinh, lbl_gioitinh, lbl_noisinh, lbl_sdt, lbl_email, lbl_cmnd, lbl_trinhdo,
            lbl_totnghiep, lbl_chuyennganh, lbl_ngonngu, lbl_ngayvaolam, lbl_mapb, lbl_tenpb, lbl_chucvu,
            lbl_nguoiquanly, lbl_loaihopdong;
    JRadioButton ra_nam, ra_nu;
    JComboBox cbb_trinhdo, cbb_totnghiep, cbb_chuyennganh, cbb_mapb, cbb_tenpb, cbb_chucvu, cbb_loaihopdong,
            cb_timkiem_pb;
    JTextField txt_id, txt_hoten, txt_ngaysinh, txt_noisinh, txt_sdt, txt_email, txt_cmnd, txt_ngayvaolam,
            txt_nguoiquanli, txt_ngonngu, txt_timkiem_ht;
    JTable tbl_nhanvien;
    JTree tree;
    String[] trinhdo = {"Đại Học", "Cao Đẳng", "Trung Cấp"};
    String[] totnghieploai = {"Xuất Sắc", "Giỏi", "Khá", "Trung Bình"};
    String[] chuyennghanh = {"Công nghệ thông tin", "Tài chính Kế toán", "Thiết bị di động", "Quản trị nhân lực"};
    String[] maphongban = {"PGD001", "PHC002", "PKT004", "PTC003"};
    String[] tenphongban = {"Phòng kinh doanh", "Phòng kế toán", "Phòng nhân sự", "Phòng hành chính"};
    String[] chucvu = {"Nhân viên", "Trưởng phòng"};
    String[] loaihopdong = {"Hợp đồng 1 năm", "Hợp đồng 3 năm", "Hợp đồng dài hạn"};
    ArrayList<NhanVien> listNhanVien;
    DefaultTableModel defaultTableModel;

//    JTree tree1;
    private DefaultTreeModel moDefaultTreeNode;
    DefaultMutableTreeNode root, ketoan, nhansu, kinhdoanh, hanhchinh, a;

    String  img = null;
    int index = -1;
  
    String  sql;
    Connection con;
    LoginMgr loginMgr=new LoginMgr();
    Config config = loginMgr.getConnfig();
    private final String username = config.getUserName();
    private final String password = config.getPassword();
    private final String url =config.getUrl();



    public NhanSu() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      
            con = DriverManager.getConnection(url, "sa", "minh123");
        } catch (Exception e) {
        }

        listNhanVien = new ArrayList<>();
        Nhanvien_GUI();

        showTable();
        jtree();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NhanSu window = new NhanSu();

                    window.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    public void Nhanvien_GUI() {
        setBounds(0, 0, 1330, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panel1 = new JPanel();
        panel1.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel1.setBounds(280, 20, 982, 60);
        panel1.setLayout(null);
        add(panel1);

        panel2 = new JPanel();
        panel2.setBorder(new TitledBorder(null, "Thông tin cá nhân", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel2.setBounds(280, 85, 800, 350);
        panel2.setLayout(null);
        add(panel2);

        panel3 = new JPanel();
        panel3.setBorder(new TitledBorder(null, null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel3.setBounds(1100, 108, 160, 340);
        panel3.setLayout(null);
        add(panel3);

        lbl_id = new JLabel("ID");
        lbl_id.setBounds(20, 30, 100, 25);
        lbl_id.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_id);

        txt_id = new JTextField();
        txt_id.setBounds(100, 30, 250, 25);
        txt_id.setEnabled(false);
        panel2.add(txt_id);

        lbl_hoten = new JLabel("Họ và tên");
        lbl_hoten.setBounds(20, 65, 100, 25);
        lbl_hoten.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_hoten);

        txt_hoten = new JTextField();
        txt_hoten.setBounds(100, 65, 250, 25);
        panel2.add(txt_hoten);

        lbl_ngaysinh = new JLabel("Ngày sinh");
        lbl_ngaysinh.setBounds(20, 100, 100, 25);
        lbl_ngaysinh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_ngaysinh);

        txt_ngaysinh = new JTextField();
        txt_ngaysinh.setBounds(100, 100, 250, 25);
        panel2.add(txt_ngaysinh);

        lbl_gioitinh = new JLabel("Giới tính");
        lbl_gioitinh.setBounds(20, 135, 100, 25);
        lbl_gioitinh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_gioitinh);

        ra_nam = new JRadioButton("Nam");
        ra_nam.setBounds(100, 135, 70, 25);
        ra_nam.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(ra_nam);

        ra_nu = new JRadioButton("Nữ");
        ra_nu.setBounds(190, 135, 70, 25);
        ra_nu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(ra_nu);

        ButtonGroup bg = new ButtonGroup();
        bg.add(ra_nam);
        bg.add(ra_nu);

        lbl_noisinh = new JLabel("Địa chỉ");
        lbl_noisinh.setBounds(20, 170, 100, 25);
        lbl_noisinh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_noisinh);

        txt_noisinh = new JTextField();
        txt_noisinh.setBounds(100, 170, 250, 25);
        panel2.add(txt_noisinh);

        lbl_sdt = new JLabel("Số ĐT");
        lbl_sdt.setBounds(20, 205, 100, 25);
        lbl_sdt.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_sdt);

        txt_sdt = new JTextField();
        txt_sdt.setBounds(100, 205, 250, 25);
        panel2.add(txt_sdt);

        lbl_email = new JLabel("Email");
        lbl_email.setBounds(20, 240, 100, 25);
        lbl_email.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_email);

        txt_email = new JTextField();
        txt_email.setBounds(100, 240, 250, 25);
        panel2.add(txt_email);

//        lbl_cmnd = new JLabel("Thẻ CC");
//        lbl_cmnd.setBounds(20, 275, 100, 25);
//        lbl_cmnd.setFont(new Font("Tahoma", Font.BOLD, 13));
//        panel2.add(lbl_cmnd);
//        txt_cmnd = new JTextField();
//        txt_cmnd.setBounds(100, 275, 250, 25);
//        panel2.add(txt_cmnd);
        lbl_trinhdo = new JLabel("Trình độ");
        lbl_trinhdo.setBounds(400, 30, 100, 25);
        lbl_trinhdo.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_trinhdo);

        cbb_trinhdo = new JComboBox(trinhdo);
        cbb_trinhdo.setBounds(520, 30, 250, 25);
        cbb_trinhdo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_trinhdo);

        lbl_totnghiep = new JLabel("Tốt nghiệp loại");
        lbl_totnghiep.setBounds(400, 65, 100, 25);
        lbl_totnghiep.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_totnghiep);

        cbb_totnghiep = new JComboBox(totnghieploai);
        cbb_totnghiep.setBounds(520, 65, 250, 25);
        cbb_totnghiep.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_totnghiep);

        lbl_chuyennganh = new JLabel("Chuyên ngành");
        lbl_chuyennganh.setBounds(400, 100, 100, 25);
        lbl_chuyennganh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_chuyennganh);

        cbb_chuyennganh = new JComboBox(chuyennghanh);
        cbb_chuyennganh.setBounds(520, 100, 250, 25);
        cbb_chuyennganh.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_chuyennganh);

        lbl_ngonngu = new JLabel("Ngoại ngữ");
        lbl_ngonngu.setBounds(400, 135, 100, 25);
        lbl_ngonngu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_ngonngu);

        txt_ngonngu = new JTextField();
        txt_ngonngu.setBounds(520, 135, 250, 25);
        panel2.add(txt_ngonngu);

//        lbl_ngayvaolam = new JLabel("Ngày vào làm");
//        lbl_ngayvaolam.setBounds(400, 135, 100, 25);
//        lbl_ngayvaolam.setFont(new Font("Tahoma", Font.BOLD, 13));
//        panel2.add(lbl_ngayvaolam);
//        txt_ngayvaolam = new JTextField();
//        txt_ngayvaolam.setBounds(520, 135, 250, 25);
//        panel2.add(txt_ngayvaolam);
//        lbl_mapb = new JLabel("Mã phòng ban");
//        lbl_mapb.setBounds(400, 170, 100, 25);
//        lbl_mapb.setFont(new Font("Tahoma", Font.BOLD, 13));
//        panel2.add(lbl_mapb);
//        cbb_mapb = new JComboBox(maphongban);
//        cbb_mapb.setBounds(520, 170, 250, 25);
//        cbb_mapb.setFont(new Font("Tahoma", Font.PLAIN, 13));
//        panel2.add(cbb_mapb);
        lbl_tenpb = new JLabel("Tên phòng ban");
        lbl_tenpb.setBounds(400, 170, 100, 25);
        lbl_tenpb.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_tenpb);

        cbb_tenpb = new JComboBox(tenphongban);
        cbb_tenpb.setBounds(520, 170, 250, 25);
        cbb_tenpb.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_tenpb);

        lbl_chucvu = new JLabel("Chức vụ");
        lbl_chucvu.setBounds(400, 205, 100, 25);
        lbl_chucvu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_chucvu);

        cbb_chucvu = new JComboBox(chucvu);
        cbb_chucvu.setBounds(520, 205, 250, 25);
        cbb_chucvu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_chucvu);

//        lbl_nguoiquanly = new JLabel("Mã số thuế");
//        lbl_nguoiquanly.setBounds(400, 275, 100, 25);
//        lbl_nguoiquanly.setFont(new Font("Tahoma", Font.BOLD, 13));
//        panel2.add(lbl_nguoiquanly);
//        txt_nguoiquanli = new JTextField();
//        txt_nguoiquanli.setBounds(520, 275, 250, 25);
//        panel2.add(txt_nguoiquanli);
        lbl_loaihopdong = new JLabel("Loại H.Đồng");
        lbl_loaihopdong.setBounds(400, 240, 100, 25);
        lbl_loaihopdong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_loaihopdong);

        cbb_loaihopdong = new JComboBox(loaihopdong);
        cbb_loaihopdong.setBounds(520, 240, 250, 25);
        cbb_loaihopdong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_loaihopdong);

        btn_anh = new JButton("CHỌN ẢNH");
        btn_anh.setBounds(50, 30, 180, 230);
        add(btn_anh);
        btn_anh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());
                    // lấy tên file
                    img = selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().lastIndexOf("\\") + 1);
                    // lấy đường dẫn thư mục trong máy tính để copy file từ đây
                    String path = selectedFile.getParent();
                    // đường dẫn copy file từ folder nào với tên file là img
                    Path copy_from_1 = Paths.get(path, img);
                    // đường dẫn copy file đến folder, tên file
                    Path copy_to_1 = Paths.get("src\\image\\CV\\", copy_from_1
                            .getFileName().toString());
                    System.out.println(img);
                    System.out.println(path);
                    try {
                        Files.copy(copy_from_1, copy_to_1, REPLACE_EXISTING, COPY_ATTRIBUTES,
                                NOFOLLOW_LINKS);
                    } catch (IOException e1) {
                        System.err.println(e1);
                    }
                    // nhớ load ảnh sau khi thay đổi tên file nhé.
                    upImage(img);
                } else {
                    JOptionPane.showMessageDialog(null, "Hãy chọn ảnh ứng viên");
                }
            }
        });

        panel4 = new JPanel();

        panel4.setBounds(50, 280, 180, 170);
        add(panel4);

        btn_lammoi = new JButton("Làm mới");
        btn_lammoi.setBounds(20, 30, 120, 30);
        btn_lammoi.setForeground(Color.blue);
        panel3.add(btn_lammoi);
        btn_lammoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel.setRowCount(0);
                defaultTableModel.fireTableDataChanged();
                lammoi();
                showTable();
                JOptionPane.showMessageDialog(null, "Làm Mới Dữ Liệu Thành Công");
            }
        });


//        btn_them = new JButton("Thêm");
//        btn_them.setBounds(20, 90, 120, 30);
//        btn_them.setForeground(Color.blue);
//        panel3.add(btn_them);
//        btn_them.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                try {
//                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                    url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManager";
//                    con = DriverManager.getConnection(url, user, pass);
//                    String sql1 = "insert into NhanVien values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
//                    PreparedStatement pp = con.prepareStatement(sql1);
//                    pp.setString(1, txt_id.getText());
//                    pp.setString(2, txt_hoten.getText());
//                    pp.setString(3, txt_ngaysinh.getText());
//                    String gt = null;
//                    if (ra_nam.isSelected()) {
//                        gt = "Nam";
//                    } else if (ra_nu.isSelected()) {
//                        gt = "Nữ";
//                    }
//                    pp.setString(4, gt);
//                    pp.setString(5, txt_noisinh.getText());
//                    pp.setString(6, txt_sdt.getText());
//                    pp.setString(7, txt_email.getText());
//                    pp.setString(8, "");
//                    pp.setString(9, cbb_trinhdo.getSelectedItem().toString());
//                    pp.setString(10, cbb_totnghiep.getSelectedItem().toString());
//                    pp.setString(11, cbb_chuyennganh.getSelectedItem().toString());
//                    pp.setString(12, txt_ngonngu.getText());
//                    pp.setString(13, "");
//                    pp.setString(14, "");
//                    pp.setString(15, cbb_tenpb.getSelectedItem().toString());
//                    pp.setString(16, cbb_chucvu.getSelectedItem().toString());
//                    pp.setString(17, "");
//                    pp.setString(18, cbb_loaihopdong.getSelectedItem().toString());
//                    pp.setString(19, img);
//
//                    pp.execute();
//                    con.close();
//                    pp.close();
//                    JOptionPane.showMessageDialog(null, "Đã Thêm Mới Thành Công Nhân Viên " + txt_hoten.getText());
//                    defaultTableModel.setRowCount(0);
//                    defaultTableModel.fireTableDataChanged();
//                    showTable();
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Lỗi ở" + ex);
//                    System.out.println(ex);
//                }
//
//            }
//        });

//        btn_them = new JButton("Thêm");
//        btn_them.setBounds(20, 90, 120, 30);
//        btn_them.setForeground(Color.blue);
//        panel3.add(btn_them);
//        btn_them.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                kiemtra();
//                try {
//                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                    con = DriverManager.getConnection(url, username, password);
//
//                    String sql1 = "insert into NhanVien values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//                    PreparedStatement pp = con.prepareStatement(sql1);
//
//                    pp.setString(1, txt_id.getText());
//                    System.out.println("1");
//                    pp.setString(2, txt_hoten.getText());
//                    System.out.println("1");
//                    pp.setString(3, txt_ngaysinh.getText());
//                    System.out.println("1");
//                    String gt = null;
//                    if (ra_nam.isSelected()) {
//                        gt = "Nam";
//                    } else if (ra_nu.isSelected()) {
//                        gt = "Nữ";
//                    }
//                    pp.setString(4, gt);
//                    System.out.println("1");
//                    pp.setString(5, txt_noisinh.getText());
//                    System.out.println("1");
//                    pp.setString(6, txt_sdt.getText());
//                    System.out.println("1");
//                    pp.setString(7, txt_email.getText());
//                    System.out.println("1");
//                    pp.setString(8, txt_cmnd.getText());
//                    System.out.println("1");
//                    String trinhdo = null;
//                    if (cbb_trinhdo.getSelectedIndex() == 1) {
//                        trinhdo = "Đại Học";
//                    } else if (cbb_trinhdo.getSelectedIndex() == 2) {
//                        trinhdo = "Cao Đẳng";
//                    } else if (cbb_trinhdo.getSelectedIndex() == 3) {
//                        trinhdo = "Trung Cấp";
//                    }
//                    pp.setString(9, trinhdo);
//                    System.out.println("1");
//                    String totnghieploai = null;
//                    if (cbb_totnghiep.getSelectedIndex() == 1) {
//                        totnghieploai = "Xuất Sắc";
//                    } else if (cbb_totnghiep.getSelectedIndex() == 2) {
//                        totnghieploai = "Giỏi";
//                    } else if (cbb_totnghiep.getSelectedIndex() == 3) {
//                        totnghieploai = "Khá";
//                    } else if (cbb_totnghiep.getSelectedIndex() == 4) {
//                        totnghieploai = "Trung Bình";
//                    }
//                    pp.setString(10, totnghieploai);
//                    System.out.println("1");
//                    String chuyennghanh = null;
//                    if (cbb_chuyennganh.getSelectedIndex() == 1) {
//                        chuyennghanh = "Công Nghệ Thông Tin";
//                    } else if (cbb_chuyennganh.getSelectedIndex() == 2) {
//                        chuyennghanh = "Tài Chính Kế Toán";
//                    } else if (cbb_chuyennganh.getSelectedIndex() == 3) {
//                        chuyennghanh = "Thiết Bị Di Động";
//                    } else if (cbb_chuyennganh.getSelectedIndex() == 4) {
//                        chuyennghanh = "Quản Trị Nhân Lực";
//                    }
//                    pp.setString(11, chuyennghanh);
//                    System.out.println("1");
//                    pp.setString(12, txt_ngonngu.getText());
//                    System.out.println("1");
//                    pp.setString(13, txt_ngayvaolam.getText());
//                    System.out.println("1");
//                    String maphongban = null;
//                    if (cbb_mapb.getSelectedIndex() == 1) {
//                        maphongban = "KINHDOANH";
//                    } else if (cbb_mapb.getSelectedIndex() == 2) {
//                        maphongban = "KETOAN";
//                    } else if (cbb_mapb.getSelectedIndex() == 3) {
//                        maphongban = "NHANSU";
//                    } else if (cbb_mapb.getSelectedIndex() == 4) {
//                        maphongban = "HANHCHINH";
//                    }
//                    pp.setString(14, maphongban);
//                    System.out.println("1");
//                    String tenphongban = null;
//                    if (cbb_tenpb.getSelectedIndex() == 1) {
//                        tenphongban = "Phòng kinh doanh";
//                    } else if (cbb_tenpb.getSelectedIndex() == 2) {
//                        tenphongban = "Phòng Kế Toán";
//                    } else if (cbb_tenpb.getSelectedIndex() == 3) {
//                        tenphongban = "Phòng Nhân Sự";
//                    } else if (cbb_tenpb.getSelectedIndex() == 4) {
//                        tenphongban = "Phòng Hành Chính";
//                    }
//                    pp.setString(15, tenphongban);
//                    System.out.println("1");
//                    String chucvu = null;
//                    if (cbb_chucvu.getSelectedIndex() == 1) {
//                        chucvu = "Nhân Viên";
//                    } else if (cbb_chucvu.getSelectedIndex() == 2) {
//                        chucvu = "Trưởng Phòng";
//                    }
//                    pp.setString(16, chucvu);
//                    System.out.println("1");
//                    pp.setString(17, txt_nguoiquanli.getText());
//                    System.out.println("1");
//                    String loaihopdong = null;
//                    if (cbb_loaihopdong.getSelectedIndex() == 1) {
//                        loaihopdong = "Hợp đồng 1 năm";
//                    } else if (cbb_loaihopdong.getSelectedIndex() == 2) {
//                        loaihopdong = "Hợp Đồng 3 năm";
//                    } else if (cbb_loaihopdong.getSelectedIndex() == 3) {
//                        loaihopdong = "Hợp Đồng Dài Hạn";
//                    }
//                    pp.setString(18, loaihopdong);
//                    pp.setString(19, img);
//                    System.out.println("1");
//                    pp.execute();
//                    con.close();
//                    pp.close();
//                    String id = txt_id.getText();
//                    String hoten = txt_hoten.getText();
//                    String ngaysinh = txt_ngaysinh.getText();
//                    String noisinh = txt_noisinh.getText();
//                    String sdt = txt_sdt.getText();
//                    String email = txt_email.getText();
//                    String cmnd = txt_cmnd.getText();
//                    String ngonngu = txt_ngonngu.getText();
//                    String ngayvaolam = txt_ngayvaolam.getText();
//                    String mathue = txt_nguoiquanli.getName();
//                    Vector ve = new Vector();
//                    ve.add(id);
//                    ve.add(hoten);
//                    ve.add(ngaysinh);
//                    ve.add(gt);
//                    ve.add(noisinh);
//                    ve.add(sdt);
//                    ve.add(email);
//                    ve.add(cmnd);
//                    ve.add(trinhdo);
//                    ve.add(totnghieploai);
//                    ve.add(chuyennghanh);
//                    ve.add(ngonngu);
//                    ve.add(ngayvaolam);
//                    ve.add(maphongban);
//                    ve.add(tenphongban);
//                    ve.add(chucvu);
//                    ve.add(mathue);
//                    ve.add(loaihopdong);
//                    defaultTableModel.addRow(ve);
//
//                    JOptionPane.showMessageDialog(null, "Đã Thêm Mới Thành Công Nhân Viên " + txt_hoten.getText());
//
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Lỗi ở" + ex);
//                }
//
//            }
//        });


        btn_sua = new JButton("Cập nhật");
        btn_sua.setBounds(20, 90, 120, 30);
        btn_sua.setForeground(Color.blue);
        panel3.add(btn_sua);
        btn_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    update();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Lỗi  ở đâu này" + e2);
                    // TODO: handle exception
                }

            }
        });

        btn_xoa = new JButton("Xóa");
        btn_xoa.setBounds(20, 150, 120, 30);
        btn_xoa.setForeground(Color.blue);
        panel3.add(btn_xoa);
        btn_xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(txt_id.getText());
                lammoi();
                defaultTableModel.setRowCount(0);
                defaultTableModel.fireTableDataChanged();
                showTable();
                JOptionPane.showMessageDialog(null, "Xoa Thanh Cong");
            }
        });

        btn_timkiem = new JButton("Tìm kiếm");
        btn_timkiem.setBounds(840, 18, 120, 30);
        btn_timkiem.setForeground(Color.blue);
        panel1.add(btn_timkiem);
        btn_timkiem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel.fireTableDataChanged();
                defaultTableModel.setRowCount(0);
                search();
            }
        });

        JLabel lbl_timkiem_ht = new JLabel("Họ và Tên");
        lbl_timkiem_ht.setBounds(50, 18, 100, 25);
        panel1.add(lbl_timkiem_ht);

        txt_timkiem_ht = new JTextField();
        txt_timkiem_ht.setBounds(180, 18, 200, 25);
        panel1.add(txt_timkiem_ht);

        JLabel lbl_timkiem_pb = new JLabel("Phòng Ban");
        lbl_timkiem_pb.setBounds(420, 18, 100, 25);
        panel1.add(lbl_timkiem_pb);

        cb_timkiem_pb = new JComboBox(tenphongban);
        cb_timkiem_pb.setBounds(550, 18, 200, 25);
        panel1.add(cb_timkiem_pb);

        panel5 = new JPanel();
        panel5.setBorder(new TitledBorder(null, null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel5.setBounds(280, 445, 798, 50);
        panel5.setLayout(null);
        add(panel5);

        btn_first = new JButton("First");
        btn_first.setBounds(150, 10, 80, 30);
        panel5.add(btn_first);
        btn_first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first();
            }
        });

        btn_prev = new JButton("Prev");
        btn_prev.setBounds(270, 10, 80, 30);
        panel5.add(btn_prev);
        btn_prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prev();
            }
        });

        btn_next = new JButton("Next");
        btn_next.setBounds(390, 10, 80, 30);
        panel5.add(btn_next);
        btn_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        btn_last = new JButton("Last");
        btn_last.setBounds(510, 10, 80, 30);
        panel5.add(btn_last);
        btn_last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                last();
            }
        });
        JScrollPane sc = new JScrollPane();
        sc.setBounds(20, 510, 1280, 140);
        getContentPane().add(sc);

        tbl_nhanvien = new JTable();

        defaultTableModel = new DefaultTableModel(new Object[][]{},
                new String[]{"ID", "Họ và tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Số ĐT", "Email",
                    "Trình độ", "Tốt nghiệp", "Chuyên ngành", "Ngoại ngữ", "Tên PB",
                    "Chức vụ", "Loại HĐ", "Ảnh"});
        tbl_nhanvien.setModel(defaultTableModel);
        sc.setViewportView(tbl_nhanvien);
        tbl_nhanvien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                int indexx = tbl_nhanvien.getSelectedRow();
                if (indexx >= 0) {
                    txt_id.setText(tbl_nhanvien.getValueAt(indexx, 0).toString());
                    txt_hoten.setText(tbl_nhanvien.getValueAt(indexx, 1).toString());
                    txt_ngaysinh.setText(tbl_nhanvien.getValueAt(indexx, 2).toString());
                    if (tbl_nhanvien.getValueAt(indexx, 3).toString().equals("Nam")) {
                        ra_nam.setSelected(true);
                    } else {
                        ra_nu.setSelected(true);
                    }
                    txt_noisinh.setText(tbl_nhanvien.getValueAt(indexx, 4).toString());
                    txt_sdt.setText(tbl_nhanvien.getValueAt(indexx, 5).toString());
                    txt_email.setText(tbl_nhanvien.getValueAt(indexx, 6).toString());
//                    txt_cmnd.setText(tbl_nhanvien.getValueAt(indexx, 7).toString());
                    cbb_trinhdo.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 7).toString());
                    cbb_totnghiep.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 8).toString());
                    cbb_chuyennganh.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 9).toString());
                    txt_ngonngu.setText(tbl_nhanvien.getValueAt(indexx, 10).toString());
//                    txt_ngayvaolam.setText(tbl_nhanvien.getValueAt(indexx, 12).toString());
//                    cbb_mapb.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 13).toString());
                    cbb_tenpb.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 11).toString());
                    cbb_chucvu.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 12).toString());
//                    txt_nguoiquanli.setText(tbl_nhanvien.getValueAt(indexx, 16).toString());
                    cbb_loaihopdong.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 13).toString());
                    img = tbl_nhanvien.getValueAt(indexx, 14).toString();
                    upImage(img);

                }
            }
        });

    }

    public void jtree() {

        panel4.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel4.setLayout(new BorderLayout(0, 0));

        panel = new JPanel();
        panel4.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        final ArrayList<String> sample = new ArrayList<String>();

        sample.add("Phòng kinh doanh");
        sample.add("Phòng kế toán");
        sample.add("Phòng nhân sự");
        sample.add("Phòng hành chính");
        sample.add("Tên Phòng Ban");

        DefaultMutableTreeNode obj = new DefaultMutableTreeNode("Tên Phòng Ban") {
            {

                DefaultMutableTreeNode node_2;

                node_2 = new DefaultMutableTreeNode(sample.get(0));
                node_2 = new DefaultMutableTreeNode(sample.get(1));
                node_2 = new DefaultMutableTreeNode(sample.get(2));
                node_2 = new DefaultMutableTreeNode(sample.get(3));
                node_2 = new DefaultMutableTreeNode(sample.get(4));
                for (int i = 0; i < sample.size(); i++) {
                    node_2.add(new DefaultMutableTreeNode(sample.get(i)));
                }

                add(node_2);
            }
        };
        tree = new JTree();
        moDefaultTreeNode = new DefaultTreeModel(obj);
        tree.setModel(moDefaultTreeNode);
        tree.addTreeSelectionListener(listener());
        panel.add(tree);
    }

    TreeSelectionListener listener() {
        TreeSelectionListener objTreeListener = new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) // Nothing is selected.
                {
                    return;
                }
                Object nodeInfo = node.getUserObject();
                if (node.isLeaf()) {
                    if (nodeInfo.toString().equals("Phòng kinh doanh")) {
                        defaultTableModel.fireTableDataChanged();
                        defaultTableModel.setRowCount(0);
                        sql = "select * from NhanVien where TenPhongBan=N'Phòng kinh doanh'";
                        jtree_showTable();
                    } else if (nodeInfo.toString().equals("Phòng kế toán")) {
                        defaultTableModel.fireTableDataChanged();
                        defaultTableModel.setRowCount(0);
                        sql = "select * from NhanVien where TenPhongBan=N'Phòng kế toán'";
                        jtree_showTable();
                    } else if (nodeInfo.toString().equals("Phòng nhân sự")) {
                        defaultTableModel.fireTableDataChanged();
                        defaultTableModel.setRowCount(0);
                        sql = "select * from NhanVien where TenPhongBan=N'Phòng nhân sự'";
                        jtree_showTable();
                    } else if (nodeInfo.toString().equals("Phòng hành chính")) {
                        defaultTableModel.fireTableDataChanged();
                        defaultTableModel.setRowCount(0);
                        sql = "select * from NhanVien where TenPhongBan=N'Phòng hành chính'";
                        jtree_showTable();
                    }
//                    System.out.println("THE ROOT NODE IS: " + node.getParent().toString() + " CHILD NODE IS: " + nodeInfo.toString());
                } else {
                    return;
                }
            }
        };
        return objTreeListener;
    }

    // tạo tree gốc
//        root = new DefaultMutableTreeNode("Tên Phòng Ban");
//        // tạo tree con
//        kinhdoanh = new DefaultMutableTreeNode("Kinh Doanh");
//        
//        ketoan = new DefaultMutableTreeNode("Kế Toán ");
//        nhansu = new DefaultMutableTreeNode("Nhân Sự  ");
//        hanhchinh = new DefaultMutableTreeNode("Hành Chính ");
//        moDefaultTreeNode = new DefaultTreeModel(root);
//        tree = new JTree();
//        tree.setModel(moDefaultTreeNode);
//        root.add(kinhdoanh);
//        root.add(ketoan);
//        root.add(nhansu);
//        root.add(hanhchinh);
//        tree = new JTree(root);
//        tree.setSize(180, 170);
//        tree.setLocation(0, 0);
//        panel4.add(tree);
//        tree.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
//                if (tree.getSelectionCount()==1) {
//                    System.out.println("111111111111111");
//                    try {
//                        sql = "select * from NhanVien where TenPhongBan=N'Phòng kế toán'";
//                        Statement st = con.createStatement();
//                        ResultSet ss = st.executeQuery(sql);
//                        while (ss.next()) {
//                             if (ss.getString(15).equals("Phòng kế toán")) {
//                            System.out.println(sql);
//                            NhanVien nv = new NhanVien();
//                            nv.setManv(ss.getString(1));
//                            nv.setManv(ss.getString(2));
//                            nv.setManv(ss.getString(3));
//                            nv.setManv(ss.getString(4));
//                            nv.setManv(ss.getString(5));
//                            nv.setManv(ss.getString(6));
//                            nv.setManv(ss.getString(7));
////                                nv.setManv(ss.getString(8));
//                            nv.setManv(ss.getString(9));
//                            nv.setManv(ss.getString(10));
//                            nv.setManv(ss.getString(11));
//                            nv.setManv(ss.getString(12));
////                                nv.setManv(ss.getString(13));
////                                nv.setManv(ss.getString(14));
//                            nv.setManv(ss.getString(15));
//                            nv.setManv(ss.getString(16));
////                                nv.setManv(ss.getString(17));
//                            nv.setManv(ss.getString(18));
//                            nv.setManv(ss.getString(19));
//                            defaultTableModel.fireTableDataChanged();
//                            defaultTableModel.setRowCount(0);
//                            listNhanVien.add(nv);
//                           
//                                for (NhanVien nhanVien : listNhanVien) {
//                                    defaultTableModel.addRow(new Object[]{nhanVien.getManv(), nhanVien.getHoten(), nhanVien.getNgaysinh(), nhanVien.getGioitinh(), nhanVien.getDiachi(), nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getTrinhdo(), nhanVien.getTotnghiep(), nhanVien.getChuyennghanh(),
//                                        nhanVien.getNgonngu(), nhanVien.getTenpb(), nhanVien.getChucvu(), nhanVien.getLoaihd(), nhanVien.getAnh()});
//                                    break;
//                                }
//                            }
//                        }
//                    } catch (Exception ed) {
//                        System.out.println(ed);
//                    }
//                } else if (tree.getSelectionCount() == 2) {
//                    try {
//                        
//                        sql = "select * from NhanVien where TenPhongBan=N'Phòng kế toán'";
//                        Statement st = con.createStatement();
//                        ResultSet ss = st.executeQuery(sql);
//                        while (ss.next()) {
//                            if (ss.getString(15).equals("Phòng kế toán")) {
//                                NhanVien nv = new NhanVien();
//                                nv.setManv(ss.getString(1));
//                                nv.setManv(ss.getString(2));
//                                nv.setManv(ss.getString(3));
//                                nv.setManv(ss.getString(4));
//                                nv.setManv(ss.getString(5));
//                                nv.setManv(ss.getString(6));
//                                nv.setManv(ss.getString(7));
////                                nv.setManv(ss.getString(8));
//                                nv.setManv(ss.getString(9));
//                                nv.setManv(ss.getString(10));
//                                nv.setManv(ss.getString(11));
//                                nv.setManv(ss.getString(12));
////                                nv.setManv(ss.getString(13));
////                                nv.setManv(ss.getString(14));
//                                nv.setManv(ss.getString(15));
//                                nv.setManv(ss.getString(16));
////                                nv.setManv(ss.getString(17));
//                                nv.setManv(ss.getString(18));
//                                nv.setManv(ss.getString(19));
//                                defaultTableModel.fireTableDataChanged();
//                                defaultTableModel.setRowCount(0);
//                                listNhanVien.add(nv);
//                                for (NhanVien nhanVien : listNhanVien) {
//                                    defaultTableModel.addRow(new Object[]{nhanVien.getManv(), nhanVien.getHoten(), nhanVien.getNgaysinh(), nhanVien.getGioitinh(), nhanVien.getDiachi(), nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getTrinhdo(), nhanVien.getTotnghiep(), nhanVien.getChuyennghanh(),
//                                        nhanVien.getNgonngu(), nhanVien.getTenpb(), nhanVien.getChucvu(), nhanVien.getLoaihd(), nhanVien.getAnh()});
//                                    break;
//                                }
//                            }
//                        }
//                    } catch (Exception ed) {
//                    }
//                } else if (tree.getSelectionCount() == 3) {
//                    try {
//                        
//                        sql = "select * from NhanVien where TenPhongBan=N'Phòng nhân sự'";
//                        Statement st = con.createStatement();
//                        ResultSet ss = st.executeQuery(sql);
//                        while (ss.next()) {
//                            if (ss.getString(15).equals("Phòng nhân sự")) {
//                                NhanVien nv = new NhanVien();
//                                nv.setManv(ss.getString(1));
//                                nv.setManv(ss.getString(2));
//                                nv.setManv(ss.getString(3));
//                                nv.setManv(ss.getString(4));
//                                nv.setManv(ss.getString(5));
//                                nv.setManv(ss.getString(6));
//                                nv.setManv(ss.getString(7));
////                                nv.setManv(ss.getString(8));
//                                nv.setManv(ss.getString(9));
//                                nv.setManv(ss.getString(10));
//                                nv.setManv(ss.getString(11));
//                                nv.setManv(ss.getString(12));
////                                nv.setManv(ss.getString(13));
////                                nv.setManv(ss.getString(14));
//                                nv.setManv(ss.getString(15));
//                                nv.setManv(ss.getString(16));
////                                nv.setManv(ss.getString(17));
//                                nv.setManv(ss.getString(18));
//                                nv.setManv(ss.getString(19));
//                                defaultTableModel.fireTableDataChanged();
//                                defaultTableModel.setRowCount(0);
//                                listNhanVien.add(nv);
//                                for (NhanVien nhanVien : listNhanVien) {
//                                    defaultTableModel.addRow(new Object[]{nhanVien.getManv(), nhanVien.getHoten(), nhanVien.getNgaysinh(), nhanVien.getGioitinh(), nhanVien.getDiachi(), nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getTrinhdo(), nhanVien.getTotnghiep(), nhanVien.getChuyennghanh(),
//                                        nhanVien.getNgonngu(), nhanVien.getTenpb(), nhanVien.getChucvu(), nhanVien.getLoaihd(), nhanVien.getAnh()});
//                                    break;
//                                }
//                            }
//                        }
//                    } catch (Exception ed) {
//                    }
//                } else if (tree.getSelectionCount() == 4) {
//                    try {
//                        
//                        sql = "select * from NhanVien where TenPhongBan=N'Phòng hành chính'";
//                        Statement st = con.createStatement();
//                        ResultSet ss = st.executeQuery(sql);
//                        while (ss.next()) {
//                            if (ss.getString(15).equals("Phòng hành chính")) {
//                                NhanVien nv = new NhanVien();
//                                nv.setManv(ss.getString(1));
//                                nv.setManv(ss.getString(2));
//                                nv.setManv(ss.getString(3));
//                                nv.setManv(ss.getString(4));
//                                nv.setManv(ss.getString(5));
//                                nv.setManv(ss.getString(6));
//                                nv.setManv(ss.getString(7));
////                                nv.setManv(ss.getString(8));
//                                nv.setManv(ss.getString(9));
//                                nv.setManv(ss.getString(10));
//                                nv.setManv(ss.getString(11));
//                                nv.setManv(ss.getString(12));
////                                nv.setManv(ss.getString(13));
////                                nv.setManv(ss.getString(14));
//                                nv.setManv(ss.getString(15));
//                                nv.setManv(ss.getString(16));
////                                nv.setManv(ss.getString(17));
//                                nv.setManv(ss.getString(18));
//                                nv.setManv(ss.getString(19));
//                                defaultTableModel.fireTableDataChanged();
//                                defaultTableModel.setRowCount(0);
//                                listNhanVien.add(nv);
//                                for (NhanVien nhanVien : listNhanVien) {
//                                    defaultTableModel.addRow(new Object[]{nhanVien.getManv(), nhanVien.getHoten(), nhanVien.getNgaysinh(), nhanVien.getGioitinh(), nhanVien.getDiachi(), nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getTrinhdo(), nhanVien.getTotnghiep(), nhanVien.getChuyennghanh(),
//                                        nhanVien.getNgonngu(), nhanVien.getTenpb(), nhanVien.getChucvu(), nhanVien.getLoaihd(), nhanVien.getAnh()});
//                                    break;
//                                }
//                            }
//                        }
//                    } catch (Exception ed) {
//                    }
//                }
//            }
//            
//        });
    public void showTable() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);

            System.out.println("Chuc Mung Ban Da Ket Noi Den Toi");
            sql = "select * from NhanVien where Status=1";
            Statement st = con.createStatement();
            ResultSet ss = st.executeQuery(sql);
            while (ss.next()) {
                String id = ss.getString(1);
                String ht = ss.getString(2);
                String ngaysinh = ss.getString(3);
                String gt = ss.getString(4);
                String diachi = ss.getString(5);
                String sdt = ss.getString(6);
                String email = ss.getString(7);
                String cmnd = ss.getString(8);
                String trinhdo1 = ss.getString(9);
                String totnghiep = ss.getString(10);
                String chuyennganh = ss.getString(11);
                String ngonngu = ss.getString(12);
                String ngyavl = ss.getString(13);
                String mpb = ss.getString(14);
                String tenpb = ss.getString(15);
                String chucvu1 = ss.getString(16);
                String mathue = ss.getString(17);
                String loaihd = ss.getString(18);
                String anh = ss.getString(19);

                NhanVien e = new NhanVien(id, ht, ngaysinh, gt, diachi, sdt, email, cmnd, trinhdo1, totnghiep, chuyennganh, ngonngu, ngyavl, mathue, tenpb, chucvu1, mathue, loaihd, anh);
                listNhanVien.add(e);
                for (NhanVien nhanVien : listNhanVien) {
                    defaultTableModel.addRow(new Object[]{id, ht, ngaysinh, gt, diachi, sdt, email, trinhdo1, totnghiep, chuyennganh,
                        ngonngu, tenpb, chucvu1, loaihd, anh});
                    break;
                }

            }
            System.out.println(listNhanVien.size());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi ở " + e);
        }
    }

    int count = 0;

    public void search() {
        try {
            if (txt_timkiem_ht.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Tên Mà Bạn Muốn Tìm Kiếm");
            } else {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       
                    con = DriverManager.getConnection(url, username, password);

                    sql = "select * from NhanVien where HoTen = N'" + txt_timkiem_ht.getText() + "' and TenPhongBan=N'" + cb_timkiem_pb.getSelectedItem() + "'";

                    Statement st = con.createStatement();
                    System.out.println(sql);
                    ResultSet ss = st.executeQuery(sql);

                    while (ss.next()) {
                        if (txt_timkiem_ht.getText().equals(ss.getString(2)) && cb_timkiem_pb.getSelectedItem().equals(ss.getString(15))) {

                            String id = ss.getString(1);
                            String ht = ss.getString(2);
                            String ngaysinh = ss.getString(3);
                            String gt = ss.getString(4);
                            String diachi = ss.getString(5);
                            String sdt = ss.getString(6);
                            String email = ss.getString(7);
                            String cmnd = ss.getString(8);
                            String trinhdo1 = ss.getString(9);
                            String totnghiep = ss.getString(10);
                            String chuyennganh = ss.getString(11);
                            String ngonngu = ss.getString(12);
                            String ngyavl = ss.getString(13);
                            String mpb = ss.getString(14);
                            String tenpb = ss.getString(15);
                            String chucvu1 = ss.getString(16);
                            String mathue = ss.getString(17);
                            String loaihd = ss.getString(18);
                            String anh = ss.getString(19);

//                            NhanVien e = new NhanVien(id, ht, ngaysinh, ngaysinh, gt, diachi, email, trinhdo1, totnghiep, chuyennganh, ngonngu, tenpb, chucvu1, loaihd, anh);
//                            listNhanVien.add(e);
                            System.out.println(listNhanVien.size());
                            for (NhanVien nhanVien : listNhanVien) {
                                defaultTableModel.addRow(new Object[]{id, ht, ngaysinh, gt, diachi, sdt, email, trinhdo1, totnghiep, chuyennganh,
                                    ngonngu, tenpb, chucvu1, loaihd, anh});
                                break;
                            }
                            count++;
                        }

                    }
                    if (count == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên có tên : "
                                + txt_timkiem_ht.getText() + "\n Mời Bạn Nhập Lại Tên");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Đã Tìm Thành Công Nhân Viên Có Tên: " + txt_timkiem_ht.getText());
                    }
                } catch (Exception e21) {
                    System.out.println("Lỗi e21" + e21);
                }
            }
        } catch (Exception e22) {
            System.out.println("Lỗi e22" + e22);
        }
        // TODO Auto-generated method stub

    }

    public boolean update() {
        defaultTableModel.fireTableDataChanged();
        defaultTableModel.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
            sql = "update NhanVien set HoTen=?,NgaySinh=?,GioiTinh=?,DiaChi=?,SoDienThoai=?,Email=?,TrinhDo=?,"
                    + "LoaiTotNghiep=?,ChuyenNganh=?,NgoaiNgu=?,TenPhongBan=?,ChucVu=?,LoaiHD=?,Anh=?,Status=1 where MaNhanVien =?";
            PreparedStatement pp = con.prepareStatement(sql);

            pp.setString(1, txt_hoten.getText());

            pp.setString(2, txt_ngaysinh.getText());

            con = DriverManager.getConnection(url, username, password);
            sql = "update NhanVien set HoTen=?,NgaySinh=?,GioiTinh=?,DiaChi=?,SoDienThoai=?,Email=?,TheCC=?,TrinhDo=?,"
                    + "LoaiTotNghiep=?,ChuyenNganh=?,NgoaiNgu=?,NgayVaoLam=?,MaPhongBan=?,TenPhongBan=?,ChucVu=?,MaThue=?,LoaiHD=?,Anh=? where MaNhanVien =?";
            PreparedStatement pe = con.prepareStatement(sql);

            System.out.println("1");
            pe.setString(1, txt_hoten.getText());
            System.out.println("1");
            pe.setString(2, txt_ngaysinh.getText());
            System.out.println("1");

            String gt = null;
            if (ra_nam.isSelected()) {
                gt = "Nam";
            } else if (ra_nu.isSelected()) {
                gt = "Nữ";
            }
            pp.setString(3, gt);

            pp.setString(4, txt_noisinh.getText());

            pp.setString(5, txt_sdt.getText());

            pp.setString(6, txt_email.getText());

//            pp.setString(7, txt_cmnd.getText());
            pp.setString(7, cbb_trinhdo.getSelectedItem().toString());
            pp.setString(8, cbb_totnghiep.getSelectedItem().toString());

            pp.setString(9, cbb_chuyennganh.getSelectedItem().toString());

            pp.setString(10, txt_ngonngu.getText());

//            pp.setString(12, txt_ngayvaolam.getText());
//
//            pp.setString(13, cbb_mapb.getSelectedItem().toString());
            pp.setString(11, cbb_tenpb.getSelectedItem().toString());

            pp.setString(12, cbb_chucvu.getSelectedItem().toString());

//            pp.setString(16, txt_nguoiquanli.getText());
            pp.setString(13, cbb_loaihopdong.getSelectedItem().toString());
            pp.setString(14, img);
            pp.setString(15, txt_id.getText());
            pp.executeUpdate();
            showTable();
            JOptionPane.showMessageDialog(null, "Update Thành Cong");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi ở " + e);
        }
        return false;
    }

    public void delete(String maNhanVien) {
        int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa nhân viên này?", "Thong báo",
                JOptionPane.YES_NO_OPTION);
        defaultTableModel.fireTableDataChanged();
        defaultTableModel.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection(url, username, password);
            sql = "update NhanVien set HoTen=?,NgaySinh=?,GioiTinh=?,DiaChi=?,SoDienThoai=?,Email=?,TrinhDo=?,"
                    + "LoaiTotNghiep=?,ChuyenNganh=?,NgoaiNgu=?,TenPhongBan=?,ChucVu=?,LoaiHD=?,Anh=?,Status=0 where MaNhanVien =?";

            con = DriverManager.getConnection(url, username, password);
            PreparedStatement pp = con.prepareStatement(sql);

            pp.setString(1, txt_hoten.getText());

            pp.setString(2, txt_ngaysinh.getText());

            String gt = null;
            if (ra_nam.isSelected()) {
                gt = "Nam";
            } else if (ra_nu.isSelected()) {
                gt = "Nữ";
            }
            pp.setString(3, gt);

            pp.setString(4, txt_noisinh.getText());

            pp.setString(5, txt_sdt.getText());

            pp.setString(6, txt_email.getText());

//            pp.setString(7, txt_cmnd.getText());
            pp.setString(7, cbb_trinhdo.getSelectedItem().toString());
            pp.setString(8, cbb_totnghiep.getSelectedItem().toString());

            pp.setString(9, cbb_chuyennganh.getSelectedItem().toString());

            pp.setString(10, txt_ngonngu.getText());

//            pp.setString(12, txt_ngayvaolam.getText());
//
//            pp.setString(13, cbb_mapb.getSelectedItem().toString());
            pp.setString(11, cbb_tenpb.getSelectedItem().toString());

            pp.setString(12, cbb_chucvu.getSelectedItem().toString());

//            pp.setString(16, txt_nguoiquanli.getText());
            pp.setString(13, cbb_loaihopdong.getSelectedItem().toString());
            pp.setString(14, img);
            
            pp.setString(15, txt_id.getText());
            pp.executeUpdate();
            showTable();
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi ở " + e);
        }
       
    }

    public void lammoi() {
        txt_id.setText("");
        txt_id.setEnabled(true);
        txt_email.setText("");
        txt_hoten.setText("");
        txt_ngaysinh.setText("");
        txt_ngonngu.setText("");
        txt_noisinh.setText("");
        txt_sdt.setText("");
        cbb_chucvu.setSelectedIndex(0);
        cbb_chuyennganh.setSelectedIndex(0);
        cbb_loaihopdong.setSelectedIndex(0);
        cbb_tenpb.setSelectedIndex(0);
        cbb_totnghiep.setSelectedIndex(0);
        cbb_trinhdo.setSelectedIndex(0);
        upImage(null);
        ra_nam.setSelected(false);
        ra_nu.setSelected(false);
    }

    public boolean kiemtra() {
        if (txt_cmnd.getText().isEmpty() && txt_email.getText().isEmpty() && txt_hoten.getText().isEmpty()
                && txt_id.getText().isEmpty() && txt_ngaysinh.getText().isEmpty() && txt_ngayvaolam.getText().isEmpty()
                && txt_ngonngu.getText().isEmpty() && txt_nguoiquanli.getText().isEmpty()
                && txt_noisinh.getText().isEmpty() && txt_sdt.getText().isEmpty() && !ra_nam.isSelected()
                && !ra_nu.isSelected()) {
            JOptionPane.showMessageDialog(null, "Không để trống thông tin nhân viên");
        } else if (txt_cmnd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để  trông Thẻ CC");
        } else if (txt_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trống Email");
        } else if (txt_hoten.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trông họ tên");
        } else if (txt_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trống id");
        } else if (txt_ngaysinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trống ngày sinh");
        } else if (txt_ngayvaolam.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trống ngày vào làm");
        } else if (txt_ngonngu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trống ngôn ngữ");
        } else if (txt_nguoiquanli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Khong để trống mã thuế");
        } else if (txt_noisinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trống Nơi Sinh");
        } else if (txt_sdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không để trống sdt");
        } else if (!txt_cmnd.getText().matches("//d{12}")) {
            JOptionPane.showMessageDialog(null, "Lỗi Định dạng căn cước");
        } else if (!txt_email.getText().matches(
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(null, "Lỗi định dạng mail");
        } else if (!txt_ngaysinh.getText().matches("\\d{2}+\\-+\\d{2}+\\-+\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày");
        } else if (!txt_ngayvaolam.getText().matches("\\d{2}+\\-+\\d{2}+\\-+\\d{4}")) {
            JOptionPane.showMessageDialog(null, "LỖI định dạng ngày vào làm");
        } else if (!txt_sdt.getText().matches("0\\d{12}")) {
            JOptionPane.showMessageDialog(null, "LỖi định dạng SDT");

        }
        return true;

    }

    public void upImage(String img) {
        ImageIcon anh = new ImageIcon("src\\image\\CV\\" + img);
        Image image = anh.getImage();
        ImageIcon anh1 = new ImageIcon(image.getScaledInstance(btn_anh.getWidth(), btn_anh.getHeight(), image.SCALE_SMOOTH));
        btn_anh.setIcon(anh1);
    }

    public void first() {
        if (listNhanVien.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu");
        } else {
            index = 0;
            Display();
            JOptionPane.showMessageDialog(null, "Bạn đang ở đầu danh sách");
        }
    }

    public void last() {
        if (listNhanVien.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu");
        } else {

            index = listNhanVien.size() - 1;
            Display();
            JOptionPane.showMessageDialog(null, "Bạn Ðang ở cuối danh sách");
        }
    }

    public void next() {
        try {

            if (listNhanVien.size() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu");
            } else {
                index++;
                if (index >= listNhanVien.size()) {
                    JOptionPane.showMessageDialog(null, "Ðang ở cuối danh sách");
                    return;
                }
                Display();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ðang ở cuối danh sách");
            return;
        }
    }

    public void prev() {
        if (listNhanVien.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu");
        } else {
            index--;
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "Bạn đang ở đầu danh sách");
                return;
            }
            Display();
        }
    }

    public void Display() {
        NhanVien s = listNhanVien.get(index);
        txt_id.setText(s.getMaNhanVien());
        txt_hoten.setText(s.getTenNhanVien());
        txt_ngaysinh.setText(s.getNgaysinh());
//        if (tbl_nhanvien.getValueAt(indexx, 3).toString().equals("Nam")) {
//            ra_nam.setSelected(true);
//        } else {
//            ra_nu.setSelected(true);
//        }
        String gt = s.getGioitinh();
        if (ra_nam.isSelected()) {
            gt = "Nam";
        } else {
            gt = "Nữ";
        }
        txt_noisinh.setText(s.getDiachi());
        txt_sdt.setText(s.getSdt());
        txt_email.setText(s.getEmail());
//                    txt_cmnd.setText(tbl_nhanvien.getValueAt(indexx, 7).toString());
        cbb_trinhdo.setSelectedItem(s.getTrinhdo());
        cbb_totnghiep.setSelectedItem(s.getTotnghiep());
        cbb_chuyennganh.setSelectedItem(s.getChuyennghanh());
        txt_ngonngu.setText(s.getNgonngu());
//                    txt_ngayvaolam.setText(tbl_nhanvien.getValueAt(indexx, 12).toString());
//                    cbb_mapb.setSelectedItem(tbl_nhanvien.getValueAt(indexx, 13).toString());
        cbb_tenpb.setSelectedItem(s.getTenpb());
        cbb_chucvu.setSelectedItem(s.getChucvu());
//                    txt_nguoiquanli.setText(tbl_nhanvien.getValueAt(indexx, 16).toString());
        cbb_loaihopdong.setSelectedItem(s.getLoaihd());
        upImage(img);
        tbl_nhanvien.setRowSelectionInterval(index, index);
    }

    public void jtree_showTable() {
        try {
            Statement st = con.createStatement();
            ResultSet ss = st.executeQuery(sql);
            while (ss.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(ss.getString(1));
                nv.setTenNhanVien(ss.getString(2));
                nv.setNgaysinh(ss.getString(3));
                nv.setGioitinh(ss.getString(4));
                nv.setDiachi(ss.getString(5));
                nv.setSdt(ss.getString(6));
                nv.setEmail(ss.getString(7));
//                                nv.setManv(ss.getString(8));
                nv.setTrinhdo(ss.getString(9));
                nv.setTotnghiep(ss.getString(10));
                nv.setChuyennghanh(ss.getString(11));
                nv.setNgonngu(ss.getString(12));
//                                nv.setManv(ss.getString(13));
//                                nv.setManv(ss.getString(14));
                nv.setTenpb(ss.getString(15));
                nv.setChucvu(ss.getString(16));
//                                nv.setManv(ss.getString(17));
                nv.setLoaihd(ss.getString(18));
                nv.setAnh(ss.getString(19));
                listNhanVien.add(nv);
                for (NhanVien nhanVien : listNhanVien) {
                    defaultTableModel.addRow(new Object[]{nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(), nhanVien.getNgaysinh(), nhanVien.getGioitinh(), nhanVien.getDiachi(), nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getTrinhdo(), nhanVien.getTotnghiep(), nhanVien.getChuyennghanh(),
                        nhanVien.getNgonngu(), nhanVien.getTenpb(), nhanVien.getChucvu(), nhanVien.getLoaihd(), nhanVien.getAnh()});
                    break;
                }
            }
        } catch (Exception ee) {

        }
    }
}

