/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginMgr;
import entities.Config;
import entities.Tuyendung;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class TuyenDung extends JInternalFrame {

    private JPanel panel1, panel2, panel3, panel4;
    private JLabel lbl_id, lbl_hoten, lbl_ngaysinh, lbl_gioitinh, lbl_noisinh, lbl_sdt, lbl_email, lbl_cmnd, lbl_trinhdo,
            lbl_totnghiep, lbl_vitri, lbl_chuyennganh, lbl_ngonngu, lbl_kinhnghiem, lbl_nam1, lbl_nam2, lbl_dot1, lbl_dot2, lbl_chuyennganh1;
    private JTextField txt_id, txt_hoten, txt_ngaysinh, txt_noisinh, txt_sdt, txt_email, txt_cmnd, txt_vitri, txt_ngonngu;
    private JComboBox cbb_trinhdo, cbb_totnghiep, cbb_chuyennganh, cbb_chuyennganh2, cbb_nam1, cbb_nam2, cbb_dot1, cbb_dot2;
    private JRadioButton ra_nam, ra_co;
    private JRadioButton ra_nu, ra_khong;
    private JTable tbl_tuyendung;
    private JButton btn_anh;
    private JButton btn_timkiem, btn_themmoi, btn_sua, btn_duyet, btn_xoa, btn_add;
    private JButton btn_last, btn_prev, btn_next, btn_first;

    ArrayList<Tuyendung> list_TD = new ArrayList<>();
    DefaultTableModel model_TD = new DefaultTableModel();
    int index = -1;
    private Connection con;
    String img = null;
    LoginMgr loginMgr = new LoginMgr();
    Config config = loginMgr.getConnfig();
    private final String username = config.getUserName();
    private final String password = config.getPassword();
    private final String url = config.getUrl();

    public TuyenDung() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TD_GUI();
        fillTable();
        list_TD = getListTD();
        loadDbToTable();
        radio_khong();
        radio_nam();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TuyenDung window = new TuyenDung();
                    window.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    public void upImage(String img) {
        ImageIcon anh = new ImageIcon("src\\image\\CV\\" + img);
        Image image = anh.getImage();
        ImageIcon anh1 = new ImageIcon(image.getScaledInstance(btn_anh.getWidth(), btn_anh.getHeight(), image.SCALE_SMOOTH));
        btn_anh.setIcon(anh1);
    }

    private boolean check() {
        // Check d? li?u textbox ID
        if (txt_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ID không được để trống.\nVui lòng nhập ID");
            txt_id.requestFocus();
            return false;
        } else if (!txt_id.getText().matches("NV\\d{3}")) {
            JOptionPane.showMessageDialog(null, "ID phải có định dạng [NVxxx].\nVui lòng nhập lại ID");
            txt_id.setText("");
            txt_id.requestFocus();
            return false;
        } else if (!txt_id.getText().matches("\\w+")) {
            JOptionPane.showMessageDialog(null, "ID phải có định dạng [NVxxx].\nVui lòng nhập lại ID");
            txt_id.setText("");
            txt_id.requestFocus();
            return false;
        }
        // Check d? li?u textbox H? và tên
        if (txt_hoten.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Họ và tên không được để trống.\nVui lòng nhập Họ và tên");
            txt_hoten.requestFocus();
            return false;

        }
        // Check d? li?u Ngày sinh
        if (txt_ngaysinh.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống.\nVui lòng nhập Ngày sinh");
            txt_ngaysinh.requestFocus();
            return false;
        } else if (!txt_ngaysinh.getText().matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Không đúng định dạng ngày, tháng, nam [dd/mm/yyyy].\nVui lòng nhập lại Ngày sinh");
            txt_ngaysinh.requestFocus();
            txt_ngaysinh.setText("");
            return false;
        }
        //Check d? li?u Ð?a ch?
        if (txt_noisinh.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ðịa chỉ không được để trống.\nVui lòng nhập địa chỉ");
            txt_noisinh.requestFocus();
            return false;
        }
        // Check d? li?u SÐT
        if (txt_sdt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống.\nVui lòng ập số ÐT");
            txt_sdt.requestFocus();
            return false;
        } else if (!(txt_sdt.getText()).matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(null, "Không đúng định dạng số ÐT [0xxxxxxxxx].\n Vui lòng nhập lại số ÐT");
            txt_sdt.setText("");
            txt_sdt.requestFocus();
            return false;
        }
        // Check d? li?u Email
        if (txt_email.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Email không được để trống.\nVui lòng nhập Email");
            txt_email.requestFocus();
            return false;
        } else if (!txt_email.getText().matches("\\w+@\\w+[.]\\w+([.]\\w+)?")) {
            JOptionPane.showMessageDialog(null, "Không dúng dịnh dạng Email.\nVui lòng nhập lại Email");
            txt_email.setText("");
            txt_email.requestFocus();
            return false;
        }
        // Check d? li?u Th? CC
        if (txt_cmnd.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Thẻ căn cuớc không đuợc bỏ trống.\nVui lòng nhập Thẻ CC");
            txt_cmnd.requestFocus();
            return false;
        } else if (!(txt_cmnd.getText()).matches("^\\d{12}$")) {
            JOptionPane.showMessageDialog(null, "Sai định dạng thẻ căn cước.\nThẻ căn cuớc phải có 12 số.\nVui lòng nhập lại Thẻ CC");
            txt_cmnd.setText("");
            txt_cmnd.requestFocus();
            return false;
        }
        // Check d? li?u V? trí UT
        if (txt_vitri.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vị trí ứng tuyển không được để trống.\nVui lòng nhập Vị trí ứng tuyển");
            txt_vitri.requestFocus();
            return false;

        }
        // Check d? li?u Ngo?i ng?
        if (txt_ngonngu.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ngọai ngữ không được để trống.\nVui lòng nhập Ngoại ngữ");
            txt_ngonngu.requestFocus();
            return false;

        }
        if (img == null) {
            JOptionPane.showMessageDialog(null, "Không để trống ảnh.\nClick vào chỗ ảnh để chọn ảnh");
            return false;
        }
        return true;
    }

    public void fillTable() {
        model_TD = (DefaultTableModel) tbl_tuyendung.getModel();
        model_TD.setRowCount(0);
        for (Tuyendung td : list_TD) {
            model_TD.addRow(new Object[]{td.getId(), td.getHoten(), td.getNgaysinh(), td.getGioitinh(), td.getNoisinh(), td.getSdt(),
                td.getEmail(), td.getCmnd(), td.getTrinhdo(), td.getTotnghiep(), td.getVitri(), td.getChuyennganh(),
                td.getNgonngu(), td.getKinhnghiem(), td.getNam(), td.getDot(), td.getAnh()});
        }
        tbl_tuyendung.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                txt_id.setEditable(false);
                int index = tbl_tuyendung.getSelectedRow();
                if (index >= 0) {
                    txt_id.setText(tbl_tuyendung.getValueAt(index, 0).toString());
                    txt_hoten.setText(tbl_tuyendung.getValueAt(index, 1).toString());
                    txt_ngaysinh.setText(tbl_tuyendung.getValueAt(index, 2).toString());
                    String gt = tbl_tuyendung.getValueAt(index, 3).toString();
                    if (gt.equals("Nam")) {
                        radio_nam();
                    } else {
                        radio_nu();
                    }
                    txt_noisinh.setText(tbl_tuyendung.getValueAt(index, 4).toString());
                    txt_sdt.setText(tbl_tuyendung.getValueAt(index, 5).toString());
                    txt_email.setText(tbl_tuyendung.getValueAt(index, 6).toString());
                    txt_cmnd.setText(tbl_tuyendung.getValueAt(index, 7).toString());
                    cbb_trinhdo.setSelectedItem(tbl_tuyendung.getValueAt(index, 8).toString());
                    cbb_totnghiep.setSelectedItem(tbl_tuyendung.getValueAt(index, 9).toString());
                    txt_vitri.setText(tbl_tuyendung.getValueAt(index, 10).toString());
                    cbb_chuyennganh.setSelectedItem(tbl_tuyendung.getValueAt(index, 11).toString());
                    txt_ngonngu.setText(tbl_tuyendung.getValueAt(index, 12).toString());
                    String kn = tbl_tuyendung.getValueAt(index, 13).toString();
                    if (kn.equals("Có")) {
                        radio_co();
                    } else {
                        radio_khong();
                    }

                    cbb_nam2.setSelectedItem(tbl_tuyendung.getValueAt(index, 14).toString());
                    cbb_dot2.setSelectedItem(tbl_tuyendung.getValueAt(index, 15).toString());
                    img = tbl_tuyendung.getValueAt(index, 16).toString();
                    upImage(img);
                }
            }
        });
    }

    public void radio_nam() {
        ra_nam.setSelected(true);
        ra_nu.setSelected(false);
    }

    public void radio_nu() {
        ra_nu.setSelected(true);
        ra_nam.setSelected(false);
    }

    public void radio_co() {
        ra_co.setSelected(true);
        ra_khong.setSelected(false);
    }

    public void radio_khong() {
        ra_khong.setSelected(true);
        ra_co.setSelected(false);
    }

    public void loadDbToTable() {
        try {
            model_TD.setRowCount(0);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Tuyendung");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString("MaUngVien"));
                row.add(rs.getString("hoten"));
                row.add(rs.getString("ngaysinh"));
                row.add(rs.getString("gioitinh"));
                row.add(rs.getString("noisinh"));
                row.add(rs.getString("sdt"));
                row.add(rs.getString("email"));
                row.add(rs.getString("cmnd"));
                row.add(rs.getString("trinhdo"));
                row.add(rs.getString("totnghiep"));
                row.add(rs.getString("vitri"));
                row.add(rs.getString("chuyennganh"));
                row.add(rs.getString("ngonngu"));
                row.add(rs.getString("kinhnghiem"));
                row.add(rs.getString("nam"));
                row.add(rs.getString("dot"));
                row.add(rs.getString("anh"));
                model_TD.addRow(row);
            }
            tbl_tuyendung.setModel(model_TD);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Tuyendung> getListTD() {
        try {
            String sql = "Select * from Tuyendung";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tuyendung td = new Tuyendung();
                td.setId(rs.getString("MaUngVien"));
                td.setHoten(rs.getString("hoten"));
                td.setNgaysinh(rs.getString("ngaysinh"));
                td.setGioitinh(rs.getString("gioitinh"));
                td.setNoisinh(rs.getString("noisinh"));
                td.setSdt(rs.getString("sdt"));
                td.setEmail(rs.getString("email"));
                td.setCmnd(rs.getString("cmnd"));
                td.setTrinhdo(rs.getString("trinhdo"));
                td.setTotnghiep(rs.getString("totnghiep"));
                td.setVitri(rs.getString("vitri"));
                td.setChuyennganh(rs.getString("chuyennganh"));
                td.setNgonngu(rs.getString("ngonngu"));
                td.setKinhnghiem(rs.getString("kinhnghiem"));
                td.setNam(rs.getString("nam"));
                td.setDot(rs.getString("dot"));
                td.setAnh(rs.getString("anh"));
                list_TD.add(td);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list_TD;
    }

    private void clear() {
        String img = null;
        list_TD = getListTD();
        loadDbToTable();
        txt_id.setEditable(true);
        txt_id.setText("");
        txt_hoten.setText("");
        txt_ngaysinh.setText("");
        radio_nam();
        txt_noisinh.setText("");
        txt_sdt.setText("");
        txt_email.setText("");
        txt_cmnd.setText("");
        txt_vitri.setText("");
        txt_ngonngu.setText("");
        radio_khong();
        btn_anh.setIcon(null);
    }

    private boolean delete() {
        try {
            index = tbl_tuyendung.getSelectedRow();
            String sql = "delete from Tuyendung where MaUngVien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tbl_tuyendung.getValueAt(index, 0).toString());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void save() {

        if (check()) {

            try {
                Tuyendung td = new Tuyendung();
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(url, username, password);
                String sql = "insert into Tuyendung values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, txt_id.getText());
                st.setString(2, txt_hoten.getText());
                st.setString(3, txt_ngaysinh.getText());

                String gt;
                if (ra_nam.isSelected()) {
                    gt = "Nam";
                } else {
                    gt = "Nữ";
                }
                st.setString(4, gt);
                st.setString(5, txt_noisinh.getText());
                st.setString(6, txt_sdt.getText());
                st.setString(7, txt_email.getText());
                st.setString(8, txt_cmnd.getText());
                st.setString(9, (String) cbb_trinhdo.getSelectedItem());
                st.setString(10, (String) cbb_totnghiep.getSelectedItem());
                st.setString(11, txt_vitri.getText());
                st.setString(12, (String) cbb_chuyennganh.getSelectedItem());
                st.setString(13, txt_ngonngu.getText());
                String kn;
                if (ra_co.isSelected()) {
                    kn = "Có";
                } else {
                    kn = "Không";
                }
                st.setString(14, kn);
                st.setString(15, (String) cbb_nam2.getSelectedItem());
                st.setString(16, (String) cbb_dot2.getSelectedItem());
                st.setString(17, img);

                st.executeUpdate();

                list_TD = getListTD();
                JOptionPane.showMessageDialog(null, "Ðã thêm ứng viên thành công");
                fillTable();
                loadDbToTable();
                clear();
                upImage(img);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ID đã tồn tại. Vui lòng nhập lại ID");
                txt_id.requestFocus();

                e.printStackTrace();
            }
        }
    }

    private void capnhat() {
        if (txt_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Chọn ứng viên cần cập nhật");
            return;
        } else {
            if (check()) {
                try {
                    Tuyendung td = new Tuyendung();
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(url, username, password);
                    String sql = "update Tuyendung set hoten=?,ngaysinh=?,gioitinh=?,noisinh=?,sdt=?,email=?,cmnd=?,trinhdo=?, "
                            + "totnghiep=?, vitri=?,chuyennganh=?,ngonngu=?,"
                            + "kinhnghiem=?, nam=?, dot=?, anh=? where MaUngVien =?";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, txt_hoten.getText());
                    st.setString(2, txt_ngaysinh.getText());
                    String gt;
                    if (ra_nam.isSelected()) {
                        gt = "Nam";
                    } else {
                        gt = "N?";
                    }
                    st.setString(3, gt);
                    st.setString(4, txt_noisinh.getText());
                    st.setString(5, txt_sdt.getText());
                    st.setString(6, txt_email.getText());
                    st.setString(7, txt_cmnd.getText());
                    st.setString(8, (String) cbb_trinhdo.getSelectedItem());
                    st.setString(9, (String) cbb_totnghiep.getSelectedItem());
                    st.setString(10, txt_vitri.getText());
                    st.setString(11, (String) cbb_chuyennganh.getSelectedItem());
                    st.setString(12, txt_ngonngu.getText());
                    String kn;
                    if (ra_co.isSelected()) {
                        kn = "Có";
                    } else {
                        kn = "Không";
                    }
                    st.setString(13, kn);
                    st.setString(14, (String) cbb_nam2.getSelectedItem());
                    st.setString(15, (String) cbb_dot2.getSelectedItem());
                    st.setString(16, img);
                    st.setString(17, txt_id.getText());
                    st.executeUpdate();
                    list_TD = getListTD();
                    JOptionPane.showMessageDialog(null, "Ðã cập nhật thành công");
                    fillTable();
                    loadDbToTable();
                    upImage(img);
                } catch (Exception e) {
                    System.out.println(e);

                }

            }

        }
    }

    public void TD_GUI() {
        setBounds(0, 0, 1350, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panel1 = new JPanel();
        panel1.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel1.setBounds(280, 20, 982, 70);
        panel1.setLayout(null);
        add(panel1);

        lbl_nam1 = new JLabel("Nam");
        lbl_nam1.setBounds(40, 30, 50, 25);
        lbl_nam1.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel1.add(lbl_nam1);

        cbb_nam1 = new JComboBox(new String[]{"2019", "2018", "2017", "2016", "2015"});
        cbb_nam1.setBounds(80, 30, 70, 25);
        cbb_nam1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel1.add(cbb_nam1);

        lbl_dot1 = new JLabel("Ðợt xét tuyển");
        lbl_dot1.setBounds(170, 30, 150, 25);
        lbl_dot1.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel1.add(lbl_dot1);

        cbb_dot1 = new JComboBox(new String[]{"Ðợt I", "Ðợt II", "Ðợt III", "Ðợt IV"});
        cbb_dot1.setBounds(280, 30, 70, 25);
        cbb_dot1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel1.add(cbb_dot1);

        cbb_chuyennganh2 = new JComboBox(new String[]{"Công nghề thông tin", "Tài chính Kế toán", "Thiết bị di động", "Quản trị nhân lực"});
        cbb_chuyennganh2.setBounds(520, 30, 250, 25);
        cbb_chuyennganh2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel1.add(cbb_chuyennganh2);

        lbl_chuyennganh1 = new JLabel("Chuyên ngành");
        lbl_chuyennganh1.setBounds(400, 30, 130, 25);
        lbl_chuyennganh1.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel1.add(lbl_chuyennganh1);

        panel2 = new JPanel();
        panel2.setBorder(new TitledBorder(null, "Thông tin cá nhân", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel2.setBounds(280, 100, 800, 290);
        panel2.setLayout(null);
        add(panel2);

        panel3 = new JPanel();
        panel3.setBorder(new TitledBorder(null, null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel3.setBounds(1100, 108, 160, 280);
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
        ra_nam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                radio_nam();
            }
        });

        ra_nu = new JRadioButton("Nữ");
        ra_nu.setBounds(190, 135, 70, 25);
        ra_nu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(ra_nu);
        ra_nu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                radio_nu();
            }
        });

        lbl_noisinh = new JLabel("Ðịa chỉ");
        lbl_noisinh.setBounds(20, 170, 100, 25);
        lbl_noisinh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_noisinh);

        txt_noisinh = new JTextField();
        txt_noisinh.setBounds(100, 170, 250, 25);
        panel2.add(txt_noisinh);

        lbl_sdt = new JLabel("Số ÐT");
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
        lbl_cmnd.setBounds(400, 30, 100, 25);
        lbl_cmnd.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_cmnd);

        txt_cmnd = new JTextField();
        txt_cmnd.setBounds(520, 30, 250, 25);
        panel2.add(txt_cmnd);

        lbl_trinhdo = new JLabel("Trình độ");
        lbl_trinhdo.setBounds(400, 65, 100, 25);
        lbl_trinhdo.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_trinhdo);

        cbb_trinhdo = new JComboBox(new String[]{"Ðại Học", "Cao Dẳng", "Trung Cấp"});
        cbb_trinhdo.setBounds(520, 65, 250, 25);
        cbb_trinhdo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_trinhdo);

        lbl_totnghiep = new JLabel("Tốt nghiệp loại");
        lbl_totnghiep.setBounds(400, 100, 100, 25);
        lbl_totnghiep.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_totnghiep);

        cbb_totnghiep = new JComboBox(new String[]{"Xuất Sắc", "Giỏi", "Khá", "Trung Bình"});
        cbb_totnghiep.setBounds(520, 100, 250, 25);
        cbb_totnghiep.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_totnghiep);

        lbl_vitri = new JLabel("Vị trí UT");
        lbl_vitri.setBounds(400, 135, 100, 25);
        lbl_vitri.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_vitri);

        txt_vitri = new JTextField();
        txt_vitri.setBounds(520, 135, 250, 25);
        panel2.add(txt_vitri);

        lbl_chuyennganh = new JLabel("Chuyên ngành");
        lbl_chuyennganh.setBounds(400, 170, 100, 25);
        lbl_chuyennganh.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_chuyennganh);

        cbb_chuyennganh = new JComboBox(new String[]{"Công nghệ thông tin", "Tài chính Kế toán", "Thiết bị di dộng", "Quản trị nhân lực"});
        cbb_chuyennganh.setBounds(520, 170, 250, 25);
        cbb_chuyennganh.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(cbb_chuyennganh);

        lbl_ngonngu = new JLabel("Ngoọai ngữ");
        lbl_ngonngu.setBounds(400, 205, 100, 25);
        lbl_ngonngu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_ngonngu);

        txt_ngonngu = new JTextField();
        txt_ngonngu.setBounds(520, 205, 250, 25);
        panel2.add(txt_ngonngu);

        lbl_kinhnghiem = new JLabel("Kinh nghiệm");
        lbl_kinhnghiem.setBounds(400, 240, 100, 25);
        lbl_kinhnghiem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel2.add(lbl_kinhnghiem);

        ra_co = new JRadioButton("Có");
        ra_co.setBounds(520, 240, 50, 25);
        ra_co.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(ra_co);
        ra_co.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                radio_co();
            }
        });

        ra_khong = new JRadioButton("Không");
        ra_khong.setBounds(600, 240, 70, 25);
        ra_khong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel2.add(ra_khong);
        ra_khong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                radio_khong();
            }
        });

        ImageIcon moi = new ImageIcon("src//image//them.png");
        btn_themmoi = new JButton("Làm mới", moi);
        btn_themmoi.setBounds(20, 20, 120, 30);
        btn_themmoi.setForeground(Color.blue);
        panel3.add(btn_themmoi);
        btn_themmoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clear();
                JOptionPane.showMessageDialog(null, "Bạn có thể thêm ứng viên");
            }
        });

        ImageIcon add = new ImageIcon("src//image//save.png");
        btn_add = new JButton("Thêm", add);
        btn_add.setBounds(20, 70, 120, 30);
        btn_add.setForeground(Color.blue);
        panel3.add(btn_add);
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                save();
            }
        });

        ImageIcon sua = new ImageIcon("src//image//edit.png");
        btn_sua = new JButton("Cập nhật", sua);
        btn_sua.setBounds(20, 120, 120, 30);
        btn_sua.setForeground(Color.blue);
        panel3.add(btn_sua);
        btn_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                capnhat();
            }
        });

        ImageIcon xoa = new ImageIcon("src//image//delete.png");
        btn_xoa = new JButton("Xóa", xoa);
        btn_xoa.setBounds(20, 170, 120, 30);
        btn_xoa.setForeground(Color.blue);
        panel3.add(btn_xoa);
        btn_xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                index = tbl_tuyendung.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn ứng viền cần xóa");
                    return;
                } else {
                    int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        delete();
                        list_TD.remove(index);
                        fillTable();
                        loadDbToTable();
                        clear();
                        JOptionPane.showMessageDialog(null, "Ðã xóa thành công");
                    } else if (chon == JOptionPane.NO_OPTION) {
                        return;
                    } else {

                    }
                }

            }
        });

        ImageIcon duyet = new ImageIcon("src//image//duyet.png");
        btn_duyet = new JButton("Duyệt", duyet);
        btn_duyet.setBounds(20, 220, 120, 30);
        btn_duyet.setForeground(Color.blue);
        panel3.add(btn_duyet);
        btn_duyet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                duyet();
            }
        });

        ImageIcon timkiem = new ImageIcon("src//image//search.png");
        btn_timkiem = new JButton("Tìm kiếm", timkiem);
        btn_timkiem.setBounds(840, 25, 120, 30);
        btn_timkiem.setForeground(Color.blue);
        panel1.add(btn_timkiem);

        ImageIcon first = new ImageIcon("src//image//first.png");
        btn_first = new JButton(first);
        btn_first.setBounds(400, 400, 50, 30);
        add(btn_first);
        btn_first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                first();
            }
        });

        ImageIcon prev = new ImageIcon("src//image//prev.png");
        btn_prev = new JButton(prev);
        btn_prev.setBounds(530, 400, 50, 30);
        add(btn_prev);
        btn_prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                prev();
            }
        });

        ImageIcon next = new ImageIcon("src//image//next.png");
        btn_next = new JButton(next);
        btn_next.setBounds(660, 400, 50, 30);
        add(btn_next);
        btn_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                next();
            }
        });

        ImageIcon last = new ImageIcon("src//image//last.png");
        btn_last = new JButton(last);
        btn_last.setBounds(790, 400, 50, 30);
        add(btn_last);
        btn_last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                last();
            }
        });

        JScrollPane sc = new JScrollPane();
        sc.setBounds(20, 450, 1300, 150);
        getContentPane().add(sc);

        tbl_tuyendung = new JTable();
        tbl_tuyendung.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Họ và tên",
            "Ngày sinh", "Giớii tính", "Ðịa chỉ", "Số ÐT", "Email", "Thẻ CC",
            "Trình độ", "Tốt nghiệp", "Vị trí UT", "Chuyên ngành", "Ngôn ngữ", "Kinh nghiệm", "Năm", "Ðợt XT", "Ảnh"}));
        sc.setViewportView(tbl_tuyendung);

        btn_anh = new JButton("Chọn ảnh");
        btn_anh.setBounds(50, 30, 180, 230);
        add(btn_anh);
        btn_anh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
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
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    // nhớ load ảnh sau khi thay đổi tên file nhé.
                    upImage(img);
                } else {
                    JOptionPane.showMessageDialog(null, "Hãy chọn ảnh ứng viên");
                }
            }

        });

        panel4 = new JPanel();
        panel4.setBorder(new TitledBorder(null, "Thời gian xét tuyển", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        panel4.setBounds(50, 280, 180, 110);
        panel4.setLayout(null);
        add(panel4);

        lbl_nam2 = new JLabel("Nam");
        lbl_nam2.setBounds(20, 35, 50, 25);
        lbl_nam2.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel4.add(lbl_nam2);

        cbb_nam2 = new JComboBox(new String[]{"2019", "2018", "2017", "2016", "2015"});
        cbb_nam2.setBounds(80, 35, 70, 25);
        cbb_nam2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel4.add(cbb_nam2);

        lbl_dot2 = new JLabel("Ðợt XT");
        lbl_dot2.setBounds(20, 70, 150, 25);
        lbl_dot2.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel4.add(lbl_dot2);

        cbb_dot2 = new JComboBox(new String[]{"Ðợt I", "Ðợt II", "Ðợt III", "Ðợt IV"});
        cbb_dot2.setBounds(80, 70, 70, 25);
        cbb_dot2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel4.add(cbb_dot2);
    }

    public void first() {
        if (list_TD.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu");
        } else {

            index = 0;
            Display();
            JOptionPane.showMessageDialog(null, "Bạn đang ở đầu danh sách");
        }
    }

    public void prev() {
        if (list_TD.size() == 0) {
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

    public void next() {
        try {

            if (list_TD.size() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu");
            } else {
                index++;
                if (index >= list_TD.size()) {
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

    public void last() {
        if (list_TD.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu");
        } else {

            index = list_TD.size() - 1;
            Display();
            JOptionPane.showMessageDialog(null, "Bạn Ðang ở cuối danh sách");
        }
    }

//    private void Timkiem() {
//                
//                if (txt_timkiem.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null,"Nh?p mã sinh viên c?n tìm");
//                    return;
//                } else {
//                    int count = 0;
//                    for (Student sv : list_sv) {
//                        if (txt_timkiem.getText().equalsIgnoreCase(sv.getMasv())) {
//                            txt_masv.setText(sv.getMasv());
//                            txt_hoten.setText(sv.getHoten());
//                            txt_email.setText(sv.getEmail());
//                            txt_sdt.setText(sv.getSodt());
//                            ta_diachi.setText(sv.getDiachi());
//                            if (sv.isGioitinh() == true) {
//                                ra_nam.setSelected(true);
//                            } else {
//                                ra_nu.setSelected(true);
//                            }
//                            count++;
//                            JOptionPane.showMessageDialog(txt_masv, "Ðã tìm th?y mã sinh viên.\nThông tin nhu sau");
//                        }
//                    }
//                    if (count == 0) {
//                        JOptionPane.showMessageDialog(null,"Mã sinh viên không t?n t?i");
//                    }
//
//                }
//    }
//  
    public void Display() {
        Tuyendung td = list_TD.get(index);
        txt_id.setText(td.getId());
        txt_hoten.setText(td.getHoten());
        txt_ngaysinh.setText(td.getNgaysinh());
        String gt = td.getGioitinh();
        if (ra_nam.isSelected()) {
            gt = "Nam";
        } else {
            gt = "Nữ";
        }
        txt_noisinh.setText(td.getNoisinh());
        txt_sdt.setText(td.getSdt());
        txt_email.setText(td.getEmail());
        txt_cmnd.setText(td.getCmnd());
        cbb_trinhdo.setSelectedItem(td.getTrinhdo());
        cbb_totnghiep.setSelectedItem(td.getTotnghiep());
        txt_vitri.setText(td.getVitri());
        cbb_chuyennganh.setSelectedItem(td.getChuyennganh());
        txt_ngonngu.setText(td.getNgonngu());
        String kn = td.getKinhnghiem();
        if (ra_co.isSelected()) {
            gt = "Có";
        } else {
            gt = "Không";
        }
        cbb_nam2.setSelectedItem(td.getNam());
        cbb_dot2.setSelectedItem(td.getDot());
        tbl_tuyendung.setRowSelectionInterval(index, index);
    }

    public void duyet() {
//        try {
//            Tuyendung sv = new Tuyendung();
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Employee;user=sa;password=luong123");
//            
//            String sql2 = "insert into DIEM (masv,tienganh,tinhoc,gdtc) values \n"
//                    + "(?,?,?,?)";
//            PreparedStatement st2 = con.prepareStatement(sql2);
//            st2.setString(1, txt_masv.getText());
//            st2.setDouble(2, 0);
//            st2.setDouble(3, 0);
//            st2.setDouble(4, 0);
//            st2.executeUpdate();
//
//            JOptionPane.showMessageDialog(null, "Luu thành công");
//            fillTable();
//            list_sinhvien = getListStudent();
//            loadDbToTable();
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Không d? trùng mã sinh viên");
//            clear();
//            return;
//
//        }

    }
}
