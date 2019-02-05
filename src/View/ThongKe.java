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

public final class ThongKe extends JInternalFrame {

    private JPanel contentPane;
    private JTextField txtMalop, txtTenLop, txtGhiChu, txtTuNgay, txtDenNgay;
    private JComboBox cmbPhongBan;
    private JTextArea areaDanhSach;
    private JTable listNhanVien, listLopDT;
    private JButton btnTimKiem, btnThem, btnSua, btnXoa, btnTuNgay, btnDenNgay;
    JDialog d;
    private JScrollPane textArea;
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

     

    }
}
