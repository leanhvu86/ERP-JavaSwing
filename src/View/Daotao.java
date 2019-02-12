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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

public final class Daotao extends JInternalFrame {

    private final JPanel contentPane;
    private JTextField txtMalop, txtTenLop, txtGhiChu, txtTuNgay, txtDenNgay;
    private JComboBox cmbPhongBan;
    private JTextArea areaDanhSach;
    private JTable listNhanVien, listLopDT;
    private JButton btnTimKiem, btnThem, btnSua, btnXoa, btnTuNgay, btnDenNgay;
    JDialog d;
    private final JScrollPane textArea;
    private final DaoTaoMgr daoTaoMgr = new DaoTaoMgr();
    final DefaultComboBoxModel Name = new DefaultComboBoxModel();
    String danhsach = "";
    MyTableModel model = new MyTableModel();

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
    public Daotao() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1400, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("DANH SÁCH LỚP ĐÀO TẠO");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setForeground(Color.DARK_GRAY);
        title.setBounds(450, 0, 250, 40);
        contentPane.add(title);

        JLabel maLop = new JLabel("MÃ LỚP");
        maLop.setBounds(50, 35, 150, 25);
        contentPane.add(maLop);

        txtMalop = new JTextField();
        txtMalop.setBounds(200, 35, 250, 25);
        contentPane.add(txtMalop);

        JLabel tenLop = new JLabel("TẾN LỚP");
        tenLop.setBounds(50, 85, 150, 25);
        contentPane.add(tenLop);

        txtTenLop = new JTextField();
        txtTenLop.setBounds(200, 85, 550, 25);
        contentPane.add(txtTenLop);

        JLabel phongBan = new JLabel("MÃ PHÒNG BAN");
        phongBan.setBounds(50, 135, 150, 25);
        contentPane.add(phongBan);
        fillCombobox();
        cmbPhongBan = new JComboBox(Name);
        cmbPhongBan.setBounds(200, 135, 250, 25);
        cmbPhongBan.setSelectedIndex(0);
        cmbPhongBan.setBackground(Color.white);
        JScrollPane ListScrollPane = new JScrollPane(cmbPhongBan);
        contentPane.add(cmbPhongBan);
        cmbPhongBan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {

                try {
                    String maPhongBan = cmbPhongBan.getSelectedItem().toString();
                    if (!maPhongBan.equals("Mã Phòng Ban")) {
                        initTable(maPhongBan);
                    } else {
                        model.fireTableDataChanged();
                        model.setRowCount(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        JLabel thoiGian = new JLabel("THỜI GIAN");
        thoiGian.setBounds(50, 185, 150, 25);
        contentPane.add(thoiGian);

        JLabel tuNgay = new JLabel("TỪ NGÀY");
        tuNgay.setBounds(200, 185, 150, 25);
        contentPane.add(tuNgay);

        txtTuNgay = new JTextField();
        txtTuNgay.setBounds(280, 185, 150, 25);
        contentPane.add(txtTuNgay);

        btnTuNgay = new JButton(new ImageIcon("src\\image\\datetimepicker.png"));
        btnTuNgay.setForeground(Color.BLUE);
        btnTuNgay.setBounds(430, 185, 25, 25);
        contentPane.add(btnTuNgay);
        btnTuNgay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DatePicker dp = new DatePicker();
                Point bP = btnTuNgay.getLocationOnScreen();
                dp.d.setLocation(bP.x, bP.y + btnTuNgay.getHeight());
                dp.d.setVisible(true);
                txtTuNgay.setText(dp.setPickedDate());

            }
        });

        JLabel denNgay = new JLabel("ĐẾN NGÀY");
        denNgay.setBounds(480, 185, 150, 25);
        contentPane.add(denNgay);

        txtDenNgay = new JTextField();
        txtDenNgay.setBounds(560, 185, 150, 25);
        contentPane.add(txtDenNgay);

        btnDenNgay = new JButton(new ImageIcon("src\\image\\datetimepicker.png"));
        btnDenNgay.setForeground(Color.BLUE);
        btnDenNgay.setBounds(710, 185, 25, 25);
        contentPane.add(btnDenNgay);
        btnDenNgay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DatePicker dp = new DatePicker();
                Point bP = btnDenNgay.getLocationOnScreen();
                dp.d.setLocation(bP.x, bP.y + btnDenNgay.getHeight());
                dp.d.setVisible(true);
                txtDenNgay.setText(dp.setPickedDate());

            }
        });

        JLabel danhSach = new JLabel("DANH SÁCH");
        danhSach.setBounds(50, 235, 150, 25);
        contentPane.add(danhSach);

        areaDanhSach = new JTextArea();
        areaDanhSach.setBounds(200, 235, 550, 100);
        contentPane.add(areaDanhSach);
        textArea = new JScrollPane(areaDanhSach);
        textArea.setBounds(200, 235, 550, 100);
        textArea.setVisible(true);
        contentPane.add(textArea);
        areaDanhSach.setEditable(false);
        areaDanhSach.setLineWrap(true);

        JLabel ghichu = new JLabel("GHI CHÚ");
        ghichu.setBounds(50, 355, 150, 25);
        contentPane.add(ghichu);

        txtGhiChu = new JTextField();
        txtGhiChu.setBounds(200, 355, 550, 25);
        contentPane.add(txtGhiChu);

        ImageIcon icon = new ImageIcon("src\\image\\search.png");
        btnTimKiem = new JButton("TÌM KIẾM", icon);
        btnTimKiem.setForeground(Color.BLUE);
        btnTimKiem.setBounds(100, 405, 150, 25);
        contentPane.add(btnTimKiem);

        ImageIcon icon1 = new ImageIcon("src\\image\\save.png");
        btnThem = new JButton("THÊM", icon1);
        btnThem.setForeground(Color.BLUE);
        btnThem.setBounds(300, 405, 150, 25);

        ImageIcon icon2 = new ImageIcon("src\\image\\edit.png");
        btnSua = new JButton("SỬA", icon2);
        btnSua.setForeground(Color.BLUE);
        btnSua.setBounds(500, 405, 150, 25);

        ImageIcon icon3 = new ImageIcon("src\\image\\delete.png");
        btnXoa = new JButton("XÓA", icon3);
        btnXoa.setForeground(Color.BLUE);
        btnXoa.setBounds(700, 405, 150, 25);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 450, 1350, 160);
        contentPane.add(scrollPane);

        listLopDT = new JTable();
        listLopDT.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "STT", "MÃ LỚP ĐÀO TẠO", "TÊN LỚP", "MÃ PHÒNG BAN", "TỪ NGÀY", "ĐẾN NGÀY", "DANH SÁCH", "GHI CHÚ"
                }
        ));
        listLopDT.setBounds(0, 450, 1350, 160);
        scrollPane.setViewportView(listLopDT);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(900, 55, 450, 350);
        contentPane.add(scrollPane1);

        listNhanVien = new JTable();
        listNhanVien.setModel(new MyTableModel());

        listNhanVien.setBounds(900, 55, 450, 350);
        scrollPane1.setViewportView(listNhanVien);
        String role = LoggedRole.getLoggedRole();
        if (role.equals("Admin") || role.equals("SA")) {
            contentPane.add(btnXoa);
            contentPane.add(btnThem);
            contentPane.add(btnSua);
        } else if (role.equals("Execute")) {
            contentPane.add(btnThem);
            contentPane.add(btnSua);
        }
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
        model.fireTableDataChanged();
        model.setRowCount(0);
        danhsach = "";
        try {

            List<NhanVien> list = daoTaoMgr.getListNhanVien(maPhongBan);
            if (!list.isEmpty()) {
                list.forEach((E) -> {
                    model.addRow(new Object[]{E.getMaNhanVien(), E.getTenNhanVien(), true});
                    danhsach += E.getMaNhanVien() + ";";
                    areaDanhSach.setText(danhsach);
                });
            }

            listNhanVien.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        listNhanVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (lse.getValueIsAdjusting()) {
                    return;
                }
                int index = listNhanVien.getSelectedRow();
                if (index >= 0) {
                    String maNhanVien = listNhanVien.getValueAt(index, 0).toString();
                    boolean daotao = (boolean) listNhanVien.getValueAt(index, 2);
                    System.out.println(maNhanVien + "dao tao" + daotao);
                    areaDanhSach.setText(getDanhSach(maNhanVien, daotao));

                }
            }
        });

    }

    public String getDanhSach(String maNhanVien, boolean daotao) {
        if (daotao == true) {
            if (!danhsach.contains(maNhanVien)) {
                danhsach += maNhanVien + ";";
            }
            System.out.println("get danh sach" + danhsach);
        } else {
            int index = danhsach.indexOf(maNhanVien);
            System.out.println(index);
            if (index > 0) {
                String b = danhsach;
                danhsach = danhsach.substring(0, index);
                b = b.substring(index + 6);
                danhsach = danhsach + b;
                System.out.println(danhsach + "danh sach");
            } else if (index == 0) {
                danhsach = danhsach.substring(6);
            }

            System.out.println("xoanv" + danhsach);
        }
        return danhsach;
    }

}
