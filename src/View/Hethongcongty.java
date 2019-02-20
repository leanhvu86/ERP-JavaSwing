package View;

import Controller.LoginMgr;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public final class Hethongcongty extends JInternalFrame {

    private JButton btn1, btn2, btn3, btn4, btn5;
    private JMenuItem menuTChu, menuNSu, menuDGia, menuDtao, menuTDung, menuTKe, jMenu6, jMenu7, jMenu8;
    private JToolBar jToolBar;
    Daotao daotao;
    TuyenDung tuyenDung;
    NhanSu nhanSu;
    ThongKe thongKe;
    DanhGia danhGia;
    JMenuBar jMenuBar;
    private JLabel lblTime;
    boolean DTao = false, TDung = false, DGia = false, NSu = false, TKe = false;
    LoginMgr loginMgr = new LoginMgr();

    JDesktopPane jDesktopPane;

    public Hethongcongty() {
        initView();
        setBounds(0, 0, 1400, 650);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initView() {
        jDesktopPane = new JDesktopPane() {
            ImageIcon icon = new ImageIcon("src\\image\\CV\\sda.png");

            public void paintComponent(Graphics g) {

                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);

                super.paintComponent(g);
            }

        };

        setContentPane(jDesktopPane);
       
    }

    public static void main(String[] args) {
       
        Hethongcongty view = new Hethongcongty();
        view.setVisible(true);
    }
}
