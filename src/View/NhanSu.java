package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Administrator
 */
public class NhanSu extends JFrame {

    JPanel panel1, panel2, panel3, panel4, panel5, panel6;
    JButton btn_them, btn_sua, btn_xoa, btn_timkiem, btn_lammoi, btn_anh,
            btn_last, btn_first, btn_next, btn_prev;
    JLabel lbl_id, lbl_hoten, lbl_ngaysinh, lbl_gioitinh, lbl_noisinh, lbl_sdt, lbl_email, lbl_cmnd, lbl_trinhdo,
            lbl_totnghiep, lbl_chuyennganh, lbl_ngonngu, lbl_ngayvaolam, lbl_mapb, lbl_tenpb, lbl_chucvu, lbl_nguoiquanly,
            lbl_loaihopdong;
    JRadioButton ra_nam, ra_nu;
    JComboBox cbb_trinhdo, cbb_totnghiep, cbb_chuyennganh, cbb_mapb, cbb_tenpb, cbb_chucvu, cbb_loaihopdong;
    JTextField txt_id, txt_hoten, txt_ngaysinh, txt_noisinh, txt_sdt, txt_email, txt_cmnd, txt_ngayvaolam,
            txt_nguoiquanli, txt_ngonngu;
    JTable tbl_nhanvien;
    JTree tree;
    String[] trinhdo = {"Đại Học", "Cao Đẳng", "Trung Cấp"};
    String[] totnghieploai = {"Xuất Sắc", "Giỏi", "Khá", "Trung Bình"};
    String[] chuyennghanh = {"Công nghệ thông tin", "Tài chính Kế toán", "Thiết bị di động", "Quản trị nhân lực"};
    String[] maphongban = {"KINHDOANH", "KETOAN", "NHANSU", "HANHCHINH"};
    String[] tenphongban = {"Phòng kinh doanh", "Phòng kế toán", "Phòng nhân sự", "Phòng hành chính"};
    String[] chucvu = {"Nhân viên", "Trưởng phòng"};
    String[] loaihopdong = {"Hợp đồng 1 năm", "Hợp đồng 3 năm", "Hợp đồng dài hạn"};
    DefaultTableModel defaultTableModel;
    DefaultMutableTreeNode root, ketoan, nhansu, kinhdoanh, hanhchinh;
    String user = "sa", pass = "minh123";
    String url, sql;
    Connection con;

    public NhanSu() {

        Nhanvien_GUI();

        jtree();
        showTable();
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

        lbl_cmnd = new JLabel("Thẻ CC");
        lbl_cmnd.setBounds(20, 275, 100, 25);
        lbl_cmnd.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_cmnd);

        txt_cmnd = new JTextField();
        txt_cmnd.setBounds(100, 275, 250, 25);
        panel2.add(txt_cmnd);

        lbl_trinhdo = new JLabel("Trình độ");
        lbl_trinhdo.setBounds(20, 310, 100, 25);
        lbl_trinhdo.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_trinhdo);

        cbb_trinhdo = new JComboBox(trinhdo);
        cbb_trinhdo.setBounds(100, 310, 250, 25);
        cbb_trinhdo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_trinhdo);

        lbl_totnghiep = new JLabel("Tốt nghiệp loại");
        lbl_totnghiep.setBounds(400, 30, 100, 25);
        lbl_totnghiep.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_totnghiep);

        cbb_totnghiep = new JComboBox(totnghieploai);
        cbb_totnghiep.setBounds(520, 30, 250, 25);
        cbb_totnghiep.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_totnghiep);

        lbl_chuyennganh = new JLabel("Chuyên ngành");
        lbl_chuyennganh.setBounds(400, 65, 100, 25);
        lbl_chuyennganh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_chuyennganh);

        cbb_chuyennganh = new JComboBox(chuyennghanh);
        cbb_chuyennganh.setBounds(520, 65, 250, 25);
        cbb_chuyennganh.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_chuyennganh);

        lbl_ngonngu = new JLabel("Ngoại ngữ");
        lbl_ngonngu.setBounds(400, 100, 100, 25);
        lbl_ngonngu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_ngonngu);

        txt_ngonngu = new JTextField();
        txt_ngonngu.setBounds(520, 100, 250, 25);
        panel2.add(txt_ngonngu);

        lbl_ngayvaolam = new JLabel("Ngày vào làm");
        lbl_ngayvaolam.setBounds(400, 135, 100, 25);
        lbl_ngayvaolam.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_ngayvaolam);

        txt_ngayvaolam = new JTextField();
        txt_ngayvaolam.setBounds(520, 135, 250, 25);
        panel2.add(txt_ngayvaolam);

        lbl_mapb = new JLabel("Mã phòng ban");
        lbl_mapb.setBounds(400, 170, 100, 25);
        lbl_mapb.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_mapb);

        cbb_mapb = new JComboBox(maphongban);
        cbb_mapb.setBounds(520, 170, 250, 25);
        cbb_mapb.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_mapb);

        lbl_tenpb = new JLabel("Tên phòng ban");
        lbl_tenpb.setBounds(400, 205, 100, 25);
        lbl_tenpb.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_tenpb);

        cbb_tenpb = new JComboBox(tenphongban);
        cbb_tenpb.setBounds(520, 205, 250, 25);
        cbb_tenpb.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_tenpb);

        lbl_chucvu = new JLabel("Chức vụ");
        lbl_chucvu.setBounds(400, 240, 100, 25);
        lbl_chucvu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_chucvu);

        cbb_chucvu = new JComboBox(chucvu);
        cbb_chucvu.setBounds(520, 240, 250, 25);
        cbb_chucvu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_chucvu);

        lbl_nguoiquanly = new JLabel("Mã số thuế");
        lbl_nguoiquanly.setBounds(400, 275, 100, 25);
        lbl_nguoiquanly.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_nguoiquanly);

        txt_nguoiquanli = new JTextField();
        txt_nguoiquanli.setBounds(520, 275, 250, 25);
        panel2.add(txt_nguoiquanli);

        lbl_loaihopdong = new JLabel("Loại H.Đồng");
        lbl_loaihopdong.setBounds(400, 310, 100, 25);
        lbl_loaihopdong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_loaihopdong);

        cbb_loaihopdong = new JComboBox(loaihopdong);
        cbb_loaihopdong.setBounds(520, 310, 250, 25);
        cbb_loaihopdong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_loaihopdong);

        btn_anh = new JButton("CHỌN ẢNH");
        btn_anh.setBounds(50, 30, 180, 230);
        add(btn_anh);

        panel4 = new JPanel();
        panel4.setBorder(new TitledBorder(null, "Phòng ban", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel4.setBounds(50, 280, 180, 170);
        panel4.setLayout(null);
        add(panel4);

        btn_lammoi = new JButton("Làm mới");
        btn_lammoi.setBounds(20, 30, 120, 30);
        btn_lammoi.setForeground(Color.blue);
        panel3.add(btn_lammoi);
        btn_lammoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lammoi();
            }
        });

        btn_them = new JButton("Thêm");
        btn_them.setBounds(20, 90, 120, 30);
        btn_them.setForeground(Color.blue);
        panel3.add(btn_them);
        btn_them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kiemtra() == true) {

                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        url = "jdbc:sqlserver://localhost:1433;databaseName=Employee1";
                        con = DriverManager.getConnection(url, user, pass);

                        String sql1 = "insert into NhanVien values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                        PreparedStatement pp = con.prepareStatement(sql1);

                        pp.setString(1, txt_id.getText());
                        System.out.println("1");
                        pp.setString(2, txt_hoten.getText());
                        System.out.println("1");
                        pp.setString(3, txt_ngaysinh.getText());
                        System.out.println("1");
                        String gt = null;
                        if (ra_nam.isSelected()) {
                            gt = "Nam";
                        } else if (ra_nu.isSelected()) {
                            gt = "Nữ";
                        }
                        pp.setString(4, gt);
                        System.out.println("1");
                        pp.setString(5, txt_noisinh.getText());
                        System.out.println("1");
                        pp.setString(6, txt_sdt.getText());
                        System.out.println("1");
                        pp.setString(7, txt_email.getText());
                        System.out.println("1");
                        pp.setString(8, txt_cmnd.getText());
                        System.out.println("1");
                        String trinhdo = null;
                        if (cbb_trinhdo.getSelectedIndex() == 1) {
                            trinhdo = "Đại Học";
                        } else if (cbb_trinhdo.getSelectedIndex() == 2) {
                            trinhdo = "Cao Đẳng";
                        } else if (cbb_trinhdo.getSelectedIndex() == 3) {
                            trinhdo = "Trung Cấp";
                        }
                        pp.setString(9, trinhdo);
                        System.out.println("1");
                        String totnghieploai = null;
                        if (cbb_totnghiep.getSelectedIndex() == 1) {
                            totnghieploai = "Xuất Sắc";
                        } else if (cbb_totnghiep.getSelectedIndex() == 2) {
                            totnghieploai = "Giỏi";
                        } else if (cbb_totnghiep.getSelectedIndex() == 3) {
                            totnghieploai = "Khá";
                        } else if (cbb_totnghiep.getSelectedIndex() == 4) {
                            totnghieploai = "Trung Bình";
                        }
                        pp.setString(10, totnghieploai);
                        System.out.println("1");
                        String chuyennghanh = null;
                        if (cbb_chuyennganh.getSelectedIndex() == 1) {
                            chuyennghanh = "Công Nghệ Thông Tin";
                        } else if (cbb_chuyennganh.getSelectedIndex() == 2) {
                            chuyennghanh = "Tài Chính Kế Toán";
                        } else if (cbb_chuyennganh.getSelectedIndex() == 3) {
                            chuyennghanh = "Thiết Bị Di Động";
                        } else if (cbb_chuyennganh.getSelectedIndex() == 4) {
                            chuyennghanh = "Quản Trị Nhân Lực";
                        }
                        pp.setString(11, chuyennghanh);
                        System.out.println("1");
                        pp.setString(12, txt_ngonngu.getText());
                        System.out.println("1");
                        pp.setString(13, txt_ngayvaolam.getText());
                        System.out.println("1");
                        String maphongban = null;
                        if (cbb_mapb.getSelectedIndex() == 1) {
                            maphongban = "KINHDOANH";
                        } else if (cbb_mapb.getSelectedIndex() == 2) {
                            maphongban = "KETOAN";
                        } else if (cbb_mapb.getSelectedIndex() == 3) {
                            maphongban = "NHANSU";
                        } else if (cbb_mapb.getSelectedIndex() == 4) {
                            maphongban = "HANHCHINH";
                        }
                        pp.setString(14, maphongban);
                        System.out.println("1");
                        String tenphongban = null;
                        if (cbb_tenpb.getSelectedIndex() == 1) {
                            tenphongban = "Phòng kinh doanh";
                        } else if (cbb_tenpb.getSelectedIndex() == 2) {
                            tenphongban = "Phòng Kế Toán";
                        } else if (cbb_tenpb.getSelectedIndex() == 3) {
                            tenphongban = "Phòng Nhân Sự";
                        } else if (cbb_tenpb.getSelectedIndex() == 4) {
                            tenphongban = "Phòng Hành Chính";
                        }
                        pp.setString(15, tenphongban);
                        System.out.println("1");
                        String chucvu = null;
                        if (cbb_chucvu.getSelectedIndex() == 1) {
                            chucvu = "Nhân Viên";
                        } else if (cbb_chucvu.getSelectedIndex() == 2) {
                            chucvu = "Trưởng Phòng";
                        }
                        pp.setString(16, chucvu);
                        System.out.println("1");
                        pp.setString(17, txt_nguoiquanli.getText());
                        System.out.println("1");
                        String loaihopdong = null;
                        if (cbb_loaihopdong.getSelectedIndex() == 1) {
                            loaihopdong = "Hợp đồng 1 năm";
                        } else if (cbb_loaihopdong.getSelectedIndex() == 2) {
                            loaihopdong = "Hợp Đồng 3 năm";
                        } else if (cbb_loaihopdong.getSelectedIndex() == 3) {
                            loaihopdong = "Hợp Đồng Dài Hạn";
                        }
                        pp.setString(18, loaihopdong);
                        System.out.println("1");
                        pp.execute();
                        con.close();
                        pp.close();
                        String id = txt_id.getText();
                        String hoten = txt_hoten.getText();
                        String ngaysinh = txt_ngaysinh.getText();
                        String noisinh = txt_noisinh.getText();
                        String sdt = txt_sdt.getText();
                        String email = txt_email.getText();
                        String cmnd = txt_cmnd.getText();
                        String ngonngu = txt_ngonngu.getText();
                        String ngayvaolam = txt_ngayvaolam.getText();
                        String mathue = txt_nguoiquanli.getName();
                        Vector ve = new Vector();
                        ve.add(id);
                        ve.add(hoten);
                        ve.add(ngaysinh);
                        ve.add(gt);
                        ve.add(noisinh);
                        ve.add(sdt);
                        ve.add(email);
                        ve.add(cmnd);
                        ve.add(trinhdo);
                        ve.add(totnghieploai);
                        ve.add(chuyennghanh);
                        ve.add(ngonngu);
                        ve.add(ngayvaolam);
                        ve.add(maphongban);
                        ve.add(tenphongban);
                        ve.add(chucvu);
                        ve.add(mathue);
                        ve.add(loaihopdong);
                        defaultTableModel.addRow(ve);

                        JOptionPane.showMessageDialog(null, "Đã Thêm Mới Thành Công Nhân Viên " + txt_hoten.getText());

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi ở" + ex);
                    }
                }
            }
        });

        btn_sua = new JButton("Cập nhật");
        btn_sua.setBounds(20, 150, 120, 30);
        btn_sua.setForeground(Color.blue);
        panel3.add(btn_sua);
        btn_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kiemtra() == true) {
                    update();
                    showTable();
                }
            }
        });

        btn_xoa = new JButton("Xóa");
        btn_xoa.setBounds(20, 210, 120, 30);
        btn_xoa.setForeground(Color.blue);
        panel3.add(btn_xoa);
        btn_xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
                showTable();
            }
        });

        btn_timkiem = new JButton("Tìm kiếm");
        btn_timkiem.setBounds(840, 18, 120, 30);
        btn_timkiem.setForeground(Color.blue);
        panel1.add(btn_timkiem);

        JLabel lbl_timkiem_ht = new JLabel("Họ và Tên");
        lbl_timkiem_ht.setBounds(100, 18, 100, 25);
        panel1.add(lbl_timkiem_ht);

        JTextField txt_timkiem_ht = new JTextField();
        txt_timkiem_ht.setBounds(250, 18, 400, 25);
        panel1.add(txt_timkiem_ht);

        panel5 = new JPanel();
        panel5.setBorder(new TitledBorder(null, null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel5.setBounds(280, 445, 798, 50);
        panel5.setLayout(null);
        add(panel5);

        btn_first = new JButton("First");
        btn_first.setBounds(150, 10, 80, 30);
        panel5.add(btn_first);

        btn_prev = new JButton("Prev");
        btn_prev.setBounds(270, 10, 80, 30);
        panel5.add(btn_prev);

        btn_next = new JButton("Next");
        btn_next.setBounds(390, 10, 80, 30);
        panel5.add(btn_next);

        btn_last = new JButton("Last");
        btn_last.setBounds(510, 10, 80, 30);
        panel5.add(btn_last);

        JScrollPane sc = new JScrollPane();
        sc.setBounds(20, 510, 1280, 140);
        getContentPane().add(sc);

        tbl_nhanvien = new JTable();

        defaultTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Họ và tên",
            "Ngày sinh", "Giới tính", "Địa chỉ", "Số ĐT", "Email", "Thẻ CC",
            "Trình độ", "Tốt nghiệp", "Chuyên ngành", "Ngoại ngữ", "Ngày vào làm",
            "Mã PB", "Tên PB", "Chức vụ", "M.s thuế", "Loại HĐ", "Ảnh"});
        tbl_nhanvien.setModel(defaultTableModel);
        sc.setViewportView(tbl_nhanvien);
        tbl_nhanvien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indexx = tbl_nhanvien.getSelectedRow();
                if (indexx >= 0) {

                    txt_id.setText(tbl_nhanvien.getValueAt(indexx, 0).toString());
                    txt_hoten.setText(tbl_nhanvien.getValueAt(indexx, 1).toString());
                    txt_ngaysinh.setText(tbl_nhanvien.getValueAt(indexx, 2).toString());
                    txt_noisinh.setText(tbl_nhanvien.getValueAt(indexx, 4).toString());
                    txt_sdt.setText(tbl_nhanvien.getValueAt(indexx, 5).toString());
                    txt_email.setText(tbl_nhanvien.getValueAt(indexx, 6).toString());
                    txt_cmnd.setText(tbl_nhanvien.getValueAt(indexx, 7).toString());
                    txt_ngonngu.setText(tbl_nhanvien.getValueAt(indexx, 11).toString());
                    txt_ngayvaolam.setText(tbl_nhanvien.getValueAt(indexx, 12).toString());
                    txt_nguoiquanli.setText(tbl_nhanvien.getValueAt(indexx, 16).toString());
                    if (tbl_nhanvien.getValueAt(indexx, 3).toString().equals("Nam")) {
                        ra_nam.setSelected(true);
                    } else {
                        ra_nu.setSelected(true);
                    }
                    if (tbl_nhanvien.getValueAt(indexx, 8).toString().equals("Đại Học")) {
                        cbb_trinhdo.setSelectedIndex(1);
                    } else if (tbl_nhanvien.getValueAt(indexx, 8).toString().equals("Cao Đẳng")) {
                        cbb_trinhdo.setSelectedIndex(2);
                    } else if (tbl_nhanvien.getValueAt(indexx, 8).toString().equals("Trung Cấp")) {
                        cbb_trinhdo.setSelectedIndex(3);
                    }
                    if (tbl_nhanvien.getValueAt(indexx, 9).toString().equals("Xuất Sắc")) {
                        cbb_totnghiep.setSelectedIndex(1);
                    } else if (tbl_nhanvien.getValueAt(indexx, 9).toString().equals("Giỏi")) {
                        cbb_totnghiep.setSelectedIndex(2);
                    } else if (tbl_nhanvien.getValueAt(indexx, 9).toString().equals("Khá")) {
                        cbb_totnghiep.setSelectedIndex(3);
                    } else if (tbl_nhanvien.getValueAt(indexx, 9).toString().equals("Trung Bình")) {
                        cbb_totnghiep.setSelectedIndex(4);
                    }
                    if (tbl_nhanvien.getValueAt(indexx, 10).toString().equals("Công nghệ thông tin")) {
                        cbb_chuyennganh.setSelectedIndex(1);
                    } else if (tbl_nhanvien.getValueAt(indexx, 10).toString().equals("Tài chính kế toán")) {
                        cbb_chuyennganh.setSelectedIndex(2);

                    } else if (tbl_nhanvien.getValueAt(indexx, 10).toString().equals("Thiết bị di động")) {
                        cbb_chuyennganh.setSelectedIndex(3);
                    } else if (tbl_nhanvien.getValueAt(indexx, 10).toString().equals("Quản lí nhân viên")) {
                        cbb_chuyennganh.setSelectedIndex(4);
                    }
                    if (tbl_nhanvien.getValueAt(indexx, 13).toString().equals("KINHDOANH")) {
                        cbb_mapb.setSelectedIndex(1);
                    } else if (tbl_nhanvien.getValueAt(indexx, 13).toString().equals("KETOAN")) {
                        cbb_mapb.setSelectedIndex(2);
                    } else if (tbl_nhanvien.getValueAt(indexx, 13).toString().equals("NHANSU")) {
                        cbb_mapb.setSelectedIndex(3);
                    } else if (tbl_nhanvien.getValueAt(indexx, 13).toString().equals("HANHCHINH")) {
                        cbb_mapb.setSelectedIndex(4);
                    }
                    if (tbl_nhanvien.getValueAt(indexx, 14).toString().equals("Phòng kinh doanh")) {
                        cbb_tenpb.setSelectedIndex(1);
                    } else if (tbl_nhanvien.getValueAt(indexx, 14).toString().equals("Phòng kế toán")) {
                        cbb_tenpb.setSelectedIndex(2);
                    } else if (tbl_nhanvien.getValueAt(indexx, 14).toString().equals("Phòng nhân sự")) {
                        cbb_tenpb.setSelectedIndex(3);
                    } else if (tbl_nhanvien.getValueAt(indexx, 14).toString().equals("Phòng hành chính")) {
                        cbb_tenpb.setSelectedIndex(4);
                    }
                    if (tbl_nhanvien.getValueAt(indexx, 15).toString().equals("Nhân viên")) {
                        cbb_chucvu.setSelectedIndex(1);
                    } else if (tbl_nhanvien.getValueAt(indexx, 15).toString().equals("Trưởng phòng")) {
                        cbb_chucvu.setSelectedIndex(2);
                    }
                    if (tbl_nhanvien.getValueAt(indexx, 17).toString().equals("Hợp đồng 1 năm")) {
                        cbb_loaihopdong.setSelectedIndex(1);
                    } else if (tbl_nhanvien.getValueAt(indexx, 17).toString().equals("Hợp đồng 3 năm")) {
                        cbb_loaihopdong.setSelectedIndex(2);
                    } else if (tbl_nhanvien.getValueAt(indexx, 17).toString().equals("Hợp đồng dài hạn")) {
                        cbb_loaihopdong.setSelectedIndex(3);
                    }
                }
            }

        });

    }

    public void jtree() {
        //tạo tree gốc
        root = new DefaultMutableTreeNode("Tên Phòng Ban");
        // tạo tree con
        kinhdoanh = new DefaultMutableTreeNode("Kinh Doanh");
        ketoan = new DefaultMutableTreeNode("Kế Toán ");
        nhansu = new DefaultMutableTreeNode("Nhân Sự  ");
        hanhchinh = new DefaultMutableTreeNode("Hành Chính ");
        //add tree con vào tree gốc
        root.add(kinhdoanh);
        root.add(ketoan);
        root.add(nhansu);
        root.add(hanhchinh);

        tree = new JTree(root);
        tree.setSize(180, 170);
        tree.setLocation(0, 0);
        panel4.add(tree);

    }

    public void showTable() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            url = "jdbc:sqlserver://localhost:1433;databaseName=Employee1";
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Chuc Mung Ban Da Ket Noi Den Toi");
            sql = "select * from NhanVien";
            Statement st = con.createStatement();
            ResultSet ss = st.executeQuery(sql);
            while (ss.next()) {
                Vector ve = new Vector();
                ve.add(ss.getString(1));
                ve.add(ss.getString(2));
                ve.add(ss.getString(3));
                ve.add(ss.getString(4));
                ve.add(ss.getString(5));
                ve.add(ss.getString(6));
                ve.add(ss.getString(7));
                ve.add(ss.getString(8));
                ve.add(ss.getString(9));
                ve.add(ss.getString(10));
                ve.add(ss.getString(11));
                ve.add(ss.getString(12));
                ve.add(ss.getString(13));
                ve.add(ss.getString(14));
                ve.add(ss.getString(15));
                ve.add(ss.getString(16));
                ve.add(ss.getString(17));
                ve.add(ss.getString(18));
                ve.add(ss.getString(19));
                defaultTableModel.addRow(ve);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, ketoan);
        }
    }

    public boolean update() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            url = "jdbc:sqlserver://localhost:1433;databaseName=Employee1";
            con = DriverManager.getConnection(url, user, pass);
            sql = "update NhanVien set HoTen=?,NgaySinh=?,GioiTinh=?,DiaChi=?,SoDienThoai=?,Email=?,TheCC=?,TrinhDo=?,"
                    + "LoaiTotNghiep=?,ChuyenNganh=?,NgoaiNgu=?,NgayVaoLam=?,MaPhongBan=?,TenPhongBan=?,ChucVu=?,MaThue=?,LoaiHD=? where MaNhanVien =?";
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
            pe.setString(3, gt);
            System.out.println("1");
            pe.setString(4, txt_noisinh.getText());
            System.out.println("1");
            pe.setString(5, txt_sdt.getText());
            System.out.println("1");
            pe.setString(6, txt_email.getText());
            System.out.println("1");
            pe.setString(7, txt_cmnd.getText());
            System.out.println("1");
            String trinhdo = null;
            if (cbb_trinhdo.getSelectedIndex() == 1) {
                trinhdo = "Đại Học";
            } else if (cbb_trinhdo.getSelectedIndex() == 2) {
                trinhdo = "Cao Đẳng";
            } else if (cbb_trinhdo.getSelectedIndex() == 3) {
                trinhdo = "Trung Cấp";
            }
            pe.setString(8, trinhdo);
            System.out.println("1");
            String totnghieploai = null;
            if (cbb_totnghiep.getSelectedIndex() == 1) {
                totnghieploai = "Xuất Sắc";
            } else if (cbb_totnghiep.getSelectedIndex() == 2) {
                totnghieploai = "Giỏi";
            } else if (cbb_totnghiep.getSelectedIndex() == 3) {
                totnghieploai = "Khá";
            } else if (cbb_totnghiep.getSelectedIndex() == 4) {
                totnghieploai = "Trung Bình";
            }
            pe.setString(9, totnghieploai);
            System.out.println("1");
            String chuyennghanh = null;
            if (cbb_chuyennganh.getSelectedIndex() == 1) {
                chuyennghanh = "Công Nghệ Thông Tin";
            } else if (cbb_chuyennganh.getSelectedIndex() == 2) {
                chuyennghanh = "Tài Chính Kế Toán";
            } else if (cbb_chuyennganh.getSelectedIndex() == 3) {
                chuyennghanh = "Thiết Bị Di Động";
            } else if (cbb_chuyennganh.getSelectedIndex() == 4) {
                chuyennghanh = "Quản Trị Nhân Lực";
            }
            pe.setString(10, chuyennghanh);
            System.out.println("1");
            pe.setString(11, txt_ngonngu.getText());
            System.out.println("1");
            pe.setString(12, txt_ngayvaolam.getText());
            System.out.println("1");
            String maphongban = null;
            if (cbb_mapb.getSelectedIndex() == 1) {
                maphongban = "KINHDOANH";
            } else if (cbb_mapb.getSelectedIndex() == 2) {
                maphongban = "KETOAN";
            } else if (cbb_mapb.getSelectedIndex() == 3) {
                maphongban = "NHANSU";
            } else if (cbb_mapb.getSelectedIndex() == 4) {
                maphongban = "HANHCHINH";
            }
            pe.setString(13, maphongban);
            System.out.println("1");
            String tenphongban = null;
            if (cbb_tenpb.getSelectedIndex() == 1) {
                tenphongban = "Phòng kinh doanh";
            } else if (cbb_tenpb.getSelectedIndex() == 2) {
                tenphongban = "Phòng Kế Toán";
            } else if (cbb_tenpb.getSelectedIndex() == 3) {
                tenphongban = "Phòng Nhân Sự";
            } else if (cbb_tenpb.getSelectedIndex() == 4) {
                tenphongban = "Phòng Hành Chính";
            }
            pe.setString(14, tenphongban);
            System.out.println("1");
            String chucvu = null;
            if (cbb_chucvu.getSelectedIndex() == 1) {
                chucvu = "Nhân Viên";
            } else if (cbb_chucvu.getSelectedIndex() == 2) {
                chucvu = "Trưởng Phòng";
            }
            pe.setString(15, chucvu);
            System.out.println("1");
            pe.setString(16, txt_nguoiquanli.getText());
            System.out.println("1");
            String loaihopdong = null;
            if (cbb_loaihopdong.getSelectedIndex() == 1) {
                loaihopdong = "Hợp đồng 1 năm";
            } else if (cbb_loaihopdong.getSelectedIndex() == 2) {
                loaihopdong = "Hợp Đồng 3 năm";
            } else if (cbb_loaihopdong.getSelectedIndex() == 3) {
                loaihopdong = "Hợp Đồng Dài Hạn";
            }
            pe.setString(17, loaihopdong);
            pe.setString(18, txt_id.getText());
            pe.executeUpdate();
            showTable();
            JOptionPane.showMessageDialog(null, "Update Thành Cong");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi ở " + e);
        }
        return false;
    }

    public void delete() {
        int result = JOptionPane.showConfirmDialog(null,
                "Bạn muốn xóa nhân viên này?",
                "Thong báo",
                JOptionPane.YES_NO_OPTION);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            url = "jdbc:sqlserver://localhost:1433;databaseName=Employee1";
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pp = con.prepareStatement(sql);
            String sql = "delete from NhanVien where MaNhanVien=?";
            pp.setString(1, txt_id.getText());
            pp.executeUpdate();
            lammoi();
            JOptionPane.showMessageDialog(null, "Xoa Thanh Cong");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi Ở " + e);
        }
    }

    public void lammoi() {
        txt_id.setText("");
        txt_cmnd.setText("");
        txt_email.setText("");
        txt_hoten.setText("");
        txt_ngaysinh.setText("");
        txt_ngayvaolam.setText("");
        txt_ngonngu.setText("");
        txt_nguoiquanli.setText("");
        txt_noisinh.setText("");
        txt_sdt.setText("");
        cbb_chucvu.setSelectedIndex(1);
        cbb_chuyennganh.setSelectedIndex(1);
        cbb_loaihopdong.setSelectedIndex(1);
        cbb_mapb.setSelectedIndex(1);
        cbb_tenpb.setSelectedIndex(1);
        cbb_totnghiep.setSelectedIndex(1);
        cbb_trinhdo.setSelectedIndex(1);
        ra_nam.setSelected(false);
        ra_nu.setSelected(false);
    }

    public boolean kiemtra() {
        if (txt_cmnd.getText().isEmpty() && txt_email.getText().isEmpty() && txt_hoten.getText().isEmpty() && txt_id.getText().isEmpty() && txt_ngaysinh.getText().isEmpty()
                && txt_ngayvaolam.getText().isEmpty() && txt_ngonngu.getText().isEmpty() && txt_nguoiquanli.getText().isEmpty() && txt_noisinh.getText().isEmpty() && txt_sdt.getText().isEmpty()
                && !ra_nam.isSelected() && !ra_nu.isSelected()) {
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

        } else if (!txt_email.getText().matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(null, "Lỗi định dạng mail");
        } else if (!txt_ngaysinh.getText().matches("//d{2}/////d{2}/////d{4}")) {
            JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày");
        } else if (!txt_ngayvaolam.getText().matches("//d{2}/////d{2}/////d{4}")) {
            JOptionPane.showMessageDialog(null, "LỖI định dạng ngày vào làm");
        } else if (!txt_sdt.getText().matches("0//d{12}")) {
            JOptionPane.showMessageDialog(null, "LỖi định dạng SDT");

        }
        return true;

    }

}
