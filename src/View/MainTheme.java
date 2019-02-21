package View;

import Controller.LoginMgr;
import entities.LoggedRole;
import entities.Config;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public final class MainTheme extends JFrame implements Runnable {

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
    boolean DTao = false, TDung = false, DGia = false, NSu = false, TKe = false, HT = false, AD = false;
    LoginMgr loginMgr = new LoginMgr();
    Hethongcongty hethongcongty;
    JDesktopPane jDesktopPane;
    Admin admin;

    public MainTheme() {
        initView();
        setSize(1680, 800);
        setResizable(false);
        setTitle("QUẢN LÝ NHÂN SỰ");

        setLayout(null);
        setLocationRelativeTo(null);
        Thread thread = new Thread(this);
        thread.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        checkConfig();
    }

    private void initView() {

        jMenuBar = new JMenuBar();
        jMenuBar.setSize(50, 450);
        jDesktopPane = new JDesktopPane() {
            ImageIcon icon = new ImageIcon("src\\image\\nen.jpg");

            public void paintComponent(Graphics g) {

                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);

                super.paintComponent(g);
            }

        };

        setContentPane(jDesktopPane);
        menuTChu = new JMenuItem("Trang Chủ");
        menuTChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    NSu = false;
                    nhanSu.setVisible(NSu);
                } catch (Exception e) {
                }
                try {
                    DGia = false;
                    danhGia.setVisible(DGia);
                } catch (Exception e) {
                }
                try {
                    TDung = false;
                    tuyenDung.setVisible(TDung);
                } catch (Exception e) {
                }
                try {
                    TKe = false;
                    thongKe.setVisible(TKe);
                } catch (Exception e) {
                }
                try {
                    HT = false;
                    hethongcongty.setVisible(HT);
                } catch (Exception e) {
                }
                try {
                    DTao = false;
                    daotao.setVisible(DTao);
                } catch (Exception e) {
                }
            }
        });
        menuNSu = new JMenuItem("Nhân Sự");
        menuNSu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    DGia = false;
                    danhGia.setVisible(DGia);
                } catch (Exception e) {
                }
                try {
                    TDung = false;
                    tuyenDung.setVisible(TDung);
                } catch (Exception e) {
                }
                try {
                    TKe = false;
                    thongKe.setVisible(TKe);
                } catch (Exception e) {
                }
                try {
                    DTao = false;
                    daotao.setVisible(DTao);
                } catch (Exception e) {
                }
                try {
                    HT = false;
                    hethongcongty.setVisible(HT);
                } catch (Exception e) {
                }
                if (NSu == true) {
                    return;
                }
                nhanSu = new NhanSu();
                nhanSu.setLocation(0, 50);
                nhanSu.setVisible(true);
                jDesktopPane.add(nhanSu);
                NSu = true;
            }
        });
        menuDGia = new JMenuItem("Đánh Giá");
        menuDGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    NSu = false;
                    nhanSu.setVisible(NSu);
                } catch (Exception e) {
                }
                try {
                    TDung = false;
                    tuyenDung.setVisible(TDung);
                } catch (Exception e) {
                }
                try {
                    TKe = false;
                    thongKe.setVisible(TKe);
                } catch (Exception e) {
                }
                try {
                    HT = false;
                    hethongcongty.setVisible(HT);
                } catch (Exception e) {
                }
                try {
                    DTao = false;
                    daotao.setVisible(DTao);
                } catch (Exception e) {
                }
                if (DGia == true) {
                    return;
                }

                danhGia = new DanhGia();
                danhGia.setLocation(0, 50);
                danhGia.setVisible(true);
                jDesktopPane.add(danhGia);
                DGia = true;
            }
        });
        menuDtao = new JMenuItem("Đào Tạo");

        menuDtao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    NSu = false;
                    nhanSu.setVisible(NSu);
                } catch (Exception e) {
                }
                try {
                    DGia = false;
                    danhGia.setVisible(DGia);
                } catch (Exception e) {
                }
                try {
                    TDung = false;
                    tuyenDung.setVisible(TDung);
                } catch (Exception e) {
                }
                try {
                    HT = false;
                    hethongcongty.setVisible(HT);
                } catch (Exception e) {
                }
                try {
                    TKe = false;
                    thongKe.setVisible(TKe);
                } catch (Exception e) {
                }

                if (DTao == true) {
                    return;
                }
                daotao = new Daotao();
                daotao.setLocation(0, 50);
                daotao.setVisible(true);
                jDesktopPane.add(daotao);
                DTao = true;
            }
        });

        menuTDung = new JMenuItem("Tuyển Dụng");
        menuTDung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    NSu = false;
                    nhanSu.setVisible(NSu);
                } catch (Exception e) {
                }
                try {
                    DGia = false;
                    danhGia.setVisible(DGia);
                } catch (Exception e) {
                }
                try {
                    HT = false;
                    hethongcongty.setVisible(HT);
                } catch (Exception e) {
                }
                try {
                    TKe = false;
                    thongKe.setVisible(TKe);
                } catch (Exception e) {
                }
                try {
                    DTao = false;
                    daotao.setVisible(DTao);
                } catch (Exception e) {
                }

                if (TDung == true) {
                    return;
                }

                tuyenDung = new TuyenDung();
                tuyenDung.setLocation(0, 50);
                tuyenDung.setVisible(true);
                jDesktopPane.add(tuyenDung);
                TDung = true;
            }
        });
        menuTKe = new JMenuItem("Thống Kê");
        menuTKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    NSu = false;
                    nhanSu.setVisible(NSu);
                } catch (Exception e) {
                }
                try {
                    DGia = false;
                    danhGia.setVisible(DGia);
                } catch (Exception e) {
                }
                try {
                    TDung = false;
                    tuyenDung.setVisible(TDung);
                } catch (Exception e) {
                }
                try {
                    HT = false;
                    hethongcongty.setVisible(HT);
                } catch (Exception e) {
                }
                try {
                    DTao = false;
                    daotao.setVisible(DTao);
                } catch (Exception e) {
                }

                if (TKe == true) {
                    return;
                }

                thongKe = new ThongKe();
                thongKe.setLocation(0, 50);
                thongKe.setVisible(true);
                jDesktopPane.add(thongKe);
                TKe = true;
            }
        });
        lblTime = new JLabel();
        lblTime.setBounds(1280, 0, 150, 50);
        lblTime.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTime.setForeground(new java.awt.Color(243, 237, 237));
        lblTime.setBorder(null);

        setJMenuBar(jMenuBar);
        jMenuBar.add(menuTChu);
        jMenuBar.add(menuNSu);
        jMenuBar.add(menuDGia);
        jMenuBar.add(menuDtao);
        jMenuBar.add(menuTDung);
        jMenuBar.add(menuTKe);

        menuTChu.setEnabled(false);
        menuNSu.setEnabled(false);
        menuDGia.setEnabled(false);
        menuDtao.setEnabled(false);
        menuTDung.setEnabled(false);
        menuTKe.setEnabled(false);
        jMenuBar.setEnabled(true);
        jToolBar = new JToolBar();
        jToolBar.setLayout(null);
        jToolBar.setBounds(0, 0, 450, 70);

        btn1 = new JButton(new ImageIcon(
                "src\\image\\iconfinder_dialog-information_118939.png"));
        btn1.setBounds(10, 1, 70, 70);
        btn1.setEnabled(false);

        btn2 = new JButton(new ImageIcon("src\\image\\iconfinder_Find_27847.png"));
        btn2.setBounds(82, 1, 70, 70);
        btn2.setEnabled(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    AD = false;
                    admin.setVisible(AD);
                } catch (Exception e) {
                }
                if (HT == true) {
                    return;
                }
                hethongcongty = new Hethongcongty();
                hethongcongty.setLocation(0, 50);
                hethongcongty.setVisible(true);
                jDesktopPane.add(hethongcongty);
                HT = true;
            }
        });
        btn3 = new JButton(new ImageIcon(
                "src\\image\\iconfinder_Network Connection Manager_3537.png"));
        btn3.setBounds(153, 1, 70, 70);
        btn3.setEnabled(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    HT = false;
                    nhanSu.setVisible(NSu);
                } catch (Exception e) {
                }
                if (HT == true) {
                    return;
                }
                admin = new Admin();
                admin.setLocation(0, 50);
                admin.setVisible(true);
                jDesktopPane.add(admin);
            }
        });

        btn4 = new JButton(new ImageIcon("src\\image\\iconfinder_1-08_511566.png"));
        btn4.setBounds(224, 1, 70, 70);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });

        btn5 = new JButton(new ImageIcon("src\\image\\iconfinder_Login_73221.png"));
        btn5.setBounds(295, 1, 70, 70);
        btn5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
//
                Login login = new Login();
                login.setSize(550, 350);
                login.setLocation(200, 100);
                login.setVisible(true);
                jDesktopPane.add(login);
            }
        });
        JButton btn6 = new JButton(
                new ImageIcon("src\\image\\iconfinder_exit_3855614.png"));
        btn6.setBounds(366, 1, 70, 70);
        btn6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int resut = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thoát Không?", "Thông Báo",
                        JOptionPane.YES_NO_OPTION);
                if (resut == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    return;
                }
            }
        });

        add(jToolBar);
        jToolBar.add(btn1);
        jToolBar.add(btn2);
        jToolBar.add(btn3);
        jToolBar.add(btn4);
        jToolBar.add(btn5);
        jToolBar.add(btn6);
        add(lblTime);
    }

    public void showTheme() {
        btn1.setEnabled(true);
        btn2.setEnabled(true);

        btn4.setEnabled(false);
        btn5.setEnabled(false);
        if (!LoggedRole.getLoggedRole().equals("Admin")) {
            menuTChu.setEnabled(true);
            menuNSu.setEnabled(true);
            menuDGia.setEnabled(true);
            menuDtao.setEnabled(true);
            menuTKe.setEnabled(true);
        } else {
            btn3.setEnabled(true);
        }

        if (LoggedRole.getLoggedRole().equals("Supervisor") || LoggedRole.getLoggedRole().equals("User") || LoggedRole.getLoggedRole().equals("Admin")) {
            menuTDung.setEnabled(false);
        } else {
            menuTDung.setEnabled(true);
        }

    }

    public static void main(String[] args) {
        JWindow window = new JWindow();
        ImageIcon icon = new ImageIcon("src\\image\\load.gif");
        window.getContentPane().add(
                new JLabel("", icon, SwingConstants.CENTER));
        window.setBounds(500, 150, 400, 300);
        window.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        MainTheme view = new MainTheme();

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            Date now = new Date();
            SimpleDateFormat formater = new SimpleDateFormat();
            formater.applyPattern("hh:mm:ss aa");
            String time = formater.format(now);
            lblTime.setText(time);
            if (null == LoggedRole.getLoggedRole() || "".equals(LoggedRole.getLoggedRole())) {
            } else {
                showTheme();
            }
        }
    }

    public void checkConfig() {
        if (loginMgr.checkConfig()) {
            Config config = loginMgr.getConnfig();
            try {
                if (loginMgr.checkDataBase(config.getUrl(), config.getUserName(), config.getPassword()) == true) {
                    Login login = new Login();
                    login.setSize(550, 350);
                    login.setLocation(200, 100);
                    login.setVisible(true);
                    jDesktopPane.add(login);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainTheme.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                File file = new File("src\\config.txt");
                file.delete();
                System.out.println("Xóa file config lỗi" + file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            SqlUI sqlUI = new SqlUI();
            sqlUI.setSize(560, 420);
            sqlUI.setLocation(400, 200);
            sqlUI.setVisible(true);
            jDesktopPane.add(sqlUI);
        }
    }

}
