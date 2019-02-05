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

public final class NhanSu extends JInternalFrame {

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
    public NhanSu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1400, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("DANH SÁCH NHÂN VIÊN");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setForeground(Color.DARK_GRAY);
        title.setBounds(450, 0, 250, 40);
        contentPane.add(title);

     
    }
}
