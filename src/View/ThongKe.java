package View;

import Controller.ThongKeMgr;
import entities.NhanVien;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class ThongKe extends JInternalFrame {

    private JPanel contentPane;
    private JTextField txtMalop, txtTenLop, txtGhiChu, txtTuNgay, txtDenNgay;
    private JComboBox cmbPhongBan;
    private JTextArea areaDanhSach;
    private JTable listNhanVien, listLopDT;
    private JButton btnTimKiem, btnThem, btnSua, btnXoa, btnTuNgay, btnDenNgay;
    JDialog d;
    private JScrollPane textArea;
    private final ThongKeMgr thongKeMgr = new ThongKeMgr();
    final DefaultComboBoxModel Name = new DefaultComboBoxModel();
    String danhsach = "";
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Daotao frame = new Daotao();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public ThongKe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1400, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("THỐNG KÊ NHÂN VIÊN");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setForeground(Color.DARK_GRAY);
        title.setBounds(650, 0, 250, 40);
        contentPane.add(title);

        JLabel gioiTinh = new JLabel("GIỚI TÍNH");
        gioiTinh.setBounds(350, 85, 150, 25);
        contentPane.add(gioiTinh);

        final DefaultComboBoxModel GTinh = new DefaultComboBoxModel();

        GTinh.addElement("------------------ Không xác định -----------------");
        GTinh.addElement("         Nam        ");
        GTinh.addElement("        Nữ        ");
        GTinh.addElement("        Giới tính khác        ");
        final JComboBox Combo = new JComboBox(GTinh);
        Combo.setBounds(450, 85, 350, 25);
        Combo.setSelectedIndex(0);
        Combo.setBackground(Color.white);
        JScrollPane ListScrollPane = new JScrollPane(Combo);
        contentPane.add(Combo);

        JLabel doTuoi = new JLabel("ĐỘ TUỔI");
        doTuoi.setBounds(350, 135, 150, 25);
        contentPane.add(doTuoi);

        final DefaultComboBoxModel Tuoi = new DefaultComboBoxModel();

        Tuoi.addElement("------------------ Không xác định -----------------");
        Tuoi.addElement("Từ 18 đến 40 tuổi");
        Tuoi.addElement("Từ 40 đến 55 tuổi");
        Tuoi.addElement("Đã về hưu");
        final JComboBox Combo1 = new JComboBox(Tuoi);
        Combo1.setBounds(450, 135, 350, 25);
        Combo1.setSelectedIndex(0);
        Combo1.setBackground(Color.white);
        JScrollPane ListScrollPane1 = new JScrollPane(Combo1);
        contentPane.add(Combo1);

        JLabel hocVan = new JLabel("HỌC VẤN");
        hocVan.setBounds(350, 185, 150, 25);
        contentPane.add(hocVan);

        final DefaultComboBoxModel hocvan = new DefaultComboBoxModel();

        hocvan.addElement("------------------ Không xác định -----------------");
        hocvan.addElement("Cao đẳng");
        hocvan.addElement("Đại học");
        hocvan.addElement("Thạc sỹ");
        hocvan.addElement("Tiến sĩ");
        final JComboBox Combo2 = new JComboBox(hocvan);
        Combo2.setBounds(450, 185, 350, 25);
        Combo2.setSelectedIndex(0);
        Combo2.setBackground(Color.white);
        JScrollPane ListScrollPane2 = new JScrollPane(Combo2);
        contentPane.add(Combo2);

        JLabel chucVu = new JLabel("CHỨC VỤ");
        chucVu.setBounds(350, 235, 150, 25);
        contentPane.add(chucVu);

        final DefaultComboBoxModel chucvu = new DefaultComboBoxModel();

        chucvu.addElement("------------------ Không xác định -----------------");
        chucvu.addElement("Giám đốc");
        chucvu.addElement("Trưởng phòng");
        chucvu.addElement("Nhân viên");

        final JComboBox Combo3 = new JComboBox(chucvu);
        Combo3.setBounds(450, 235, 350, 25);
        Combo3.setSelectedIndex(0);
        Combo3.setBackground(Color.white);
        JScrollPane ListScrollPane3 = new JScrollPane(Combo3);
        contentPane.add(Combo3);

        ImageIcon icon = new ImageIcon("src\\image\\search.png");
        btnTimKiem = new JButton("TÌM KIẾM", icon);
        btnTimKiem.setForeground(Color.BLUE);
        btnTimKiem.setBounds(1000, 125, 150, 25);
        contentPane.add(btnTimKiem);

        ImageIcon icon1 = new ImageIcon("src\\image\\excel.png");
        btnThem = new JButton("IN RA EXCEL", icon1);
        btnThem.setForeground(Color.BLUE);
        btnThem.setBounds(1000, 205, 150, 25);
        contentPane.add(btnThem);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 300, 1300, 350);
        contentPane.add(scrollPane);
        listNhanVien = new JTable();
        listNhanVien.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "MÃ NHÂN VIÊN", "TÊN NHÂN VIÊN", "GIỚI TÍNH", "ĐỘ TUỔI", "HỌC VẤN", "CHỨC VỤ"
                }
        ));
        listNhanVien.setBounds(20, 300, 1300, 350);
        scrollPane.setViewportView(listNhanVien);

    }

    private void initTable(String gioiTinh, String doTuoi, String hocVan, String chucVu) {
        model.fireTableDataChanged();
        model.setRowCount(0);

        try {

            List<NhanVien> list = thongKeMgr.getListByFilter(gioiTinh, doTuoi, hocVan, chucVu);
            if (!list.isEmpty()) {
                list.forEach((E) -> {
                    model.addRow(new Object[]{E.getMaNhanVien(), E.getTenNhanVien(), true});
                    danhsach += E.getMaNhanVien() + ";";

                });
            }

            listNhanVien.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
