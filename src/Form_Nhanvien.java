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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
public class Form_Nhanvien {

    JFrame mainNV;
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
    DefaultTableModel defaultTableModel;
    DefaultMutableTreeNode root, ketoan, nhansu, kinhdoanh, hanhchinh;
    String user = "sa", pass = "minh123";
    String url, sql;
    Connection con;

    public Form_Nhanvien() {
       
        Nhanvien_GUI();
      
        jtree();
          showTable();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Form_Nhanvien window = new Form_Nhanvien();

                    window.mainNV.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    public void Nhanvien_GUI() {

        mainNV = new JFrame();
        mainNV.setBounds(0, 0, 1330, 700);
        mainNV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainNV.getContentPane().setLayout(null);
        mainNV.setLocationRelativeTo(null);

        panel1 = new JPanel();
        panel1.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel1.setBounds(280, 20, 982, 60);
        panel1.setLayout(null);
        mainNV.add(panel1);

        panel2 = new JPanel();
        panel2.setBorder(new TitledBorder(null, "Thông tin cá nhân", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel2.setBounds(280, 85, 800, 350);
        panel2.setLayout(null);
        mainNV.add(panel2);

        panel3 = new JPanel();
        panel3.setBorder(new TitledBorder(null, null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel3.setBounds(1100, 108, 160, 340);
        panel3.setLayout(null);
        mainNV.add(panel3);

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

        cbb_trinhdo = new JComboBox(new String[]{"Đại Học", "Cao Đẳng", "Trung Cấp"});
        cbb_trinhdo.setBounds(100, 310, 250, 25);
        cbb_trinhdo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_trinhdo);

        lbl_totnghiep = new JLabel("Tốt nghiệp loại");
        lbl_totnghiep.setBounds(400, 30, 100, 25);
        lbl_totnghiep.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_totnghiep);

        cbb_totnghiep = new JComboBox(new String[]{"Xuất Sắc", "Giỏi", "Khá", "Trung Bình"});
        cbb_totnghiep.setBounds(520, 30, 250, 25);
        cbb_totnghiep.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_totnghiep);

        lbl_chuyennganh = new JLabel("Chuyên ngành");
        lbl_chuyennganh.setBounds(400, 65, 100, 25);
        lbl_chuyennganh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_chuyennganh);

        cbb_chuyennganh = new JComboBox(new String[]{"Công nghệ thông tin", "Tài chính Kế toán", "Thiết bị di động", "Quản trị nhân lực"});
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

        cbb_mapb = new JComboBox(new String[]{"KINHDOANH", "KETOAN", "NHANSU", "HANHCHINH"});
        cbb_mapb.setBounds(520, 170, 250, 25);
        cbb_mapb.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_mapb);

        lbl_tenpb = new JLabel("Tên phòng ban");
        lbl_tenpb.setBounds(400, 205, 100, 25);
        lbl_tenpb.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_tenpb);

        cbb_tenpb = new JComboBox(new String[]{"Phòng kinh doanh", "Phòng kế toán", "Phòng nhân sự", "Phòng hành chính"});
        cbb_tenpb.setBounds(520, 205, 250, 25);
        cbb_tenpb.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_tenpb);

        lbl_chucvu = new JLabel("Chức vụ");
        lbl_chucvu.setBounds(400, 240, 100, 25);
        lbl_chucvu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_chucvu);

        cbb_chucvu = new JComboBox(new String[]{"Nhân viên", "Trường phòng"});
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

        cbb_loaihopdong = new JComboBox(new String[]{"Hợp đồng 1 năm", "Hợp đồng 3 năm", "Hợp đồng dài hạn"});
        cbb_loaihopdong.setBounds(520, 310, 250, 25);
        cbb_loaihopdong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_loaihopdong);

        btn_anh = new JButton("CHỌN ẢNH");
        btn_anh.setBounds(50, 30, 180, 230);
        mainNV.add(btn_anh);

        panel4 = new JPanel();
        panel4.setBorder(new TitledBorder(null, "Phòng ban", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel4.setBounds(50, 280, 180, 170);
        panel4.setLayout(null);
        mainNV.add(panel4);

        btn_lammoi = new JButton("Làm mới");
        btn_lammoi.setBounds(20, 30, 120, 30);
        btn_lammoi.setForeground(Color.blue);
        panel3.add(btn_lammoi);

        btn_them = new JButton("Thêm");
        btn_them.setBounds(20, 90, 120, 30);
        btn_them.setForeground(Color.blue);
        panel3.add(btn_them);

        btn_sua = new JButton("Cập nhật");
        btn_sua.setBounds(20, 150, 120, 30);
        btn_sua.setForeground(Color.blue);
        panel3.add(btn_sua);

        btn_xoa = new JButton("Xóa");
        btn_xoa.setBounds(20, 210, 120, 30);
        btn_xoa.setForeground(Color.blue);
        panel3.add(btn_xoa);

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
        mainNV.add(panel5);

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
        mainNV.getContentPane().add(sc);

        tbl_nhanvien = new JTable();

        defaultTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Họ và tên",
            "Ngày sinh", "Giới tính", "Địa chỉ", "Số ĐT", "Email", "Thẻ CC",
            "Trình độ", "Tốt nghiệp", "Chuyên ngành", "Ngoại ngữ", "Ngày vào làm",
            "Mã PB", "Tên PB", "Chức vụ", "M.s thuế", "Loại HĐ", "Ảnh"});
        tbl_nhanvien.setModel(defaultTableModel);
        sc.setViewportView(tbl_nhanvien);

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
       try{
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
    
}
