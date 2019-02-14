package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Controller.LoginMgr;
import entities.SqlUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class sqlUI extends JInternalFrame {

    JPasswordField txtPass;
    JTextField txtUser;
    JLabel errMsg;

    public LoginMgr loginMgr = new LoginMgr();

    public sqlUI() {
        initView();
        setSize(550, 350);
        setTitle("Kết nối Database");
        setLocation(200, 100);
        setLayout(null);
        setResizable(true);
        setVisible(true);
    }

    private void initView() {

        JPanel jPanel = new JPanel();
        jPanel.setBounds(20, 20, 500, 260);
        jPanel.setLayout(null);
        jPanel.setBorder(new TitledBorder(new LineBorder(new Color(123123)), "Kết nối Database", TitledBorder.CENTER,
                TitledBorder.TOP, null, new Color(123123)));

        JLabel lbl = new JLabel("Mời bạn nhập thông tin tài khoản đăng nhập SQL SERVER");
        lbl.setBounds(80, 25, 350, 30);
        JLabel lblUser = new JLabel("USERNAME:");
        lblUser.setBounds(50, 80, 100, 30);

        JLabel lblPass = new JLabel("PASSWORD");
        lblPass.setBounds(50, 140, 100, 30);
        jPanel.add(lbl);
        jPanel.add(lblUser);
        jPanel.add(lblPass);

        txtUser = new JTextField();
        txtUser.setBounds(170, 80, 215, 30);

        txtPass = new JPasswordField();
        txtPass.setBounds(170, 140, 215, 30);
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke);
                String user = txtUser.getText();
                String pass = txtPass.getText();

                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (check()) {

                        SqlUI sqlUI = new SqlUI();
                        sqlUI.setUserName(user);
                        sqlUI.setPassword(pass);
                        if (loginMgr.checkDataBase(user, pass) == true) {
                            setVisible(false);
                            JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công!!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Đăng Nhập Thất bại!! Vui lòng kiểm tra lại username và password");
                        }

                    }
                    //To change body of generated methods, choose Tools | Templates.
                }
            }
        }
        );

        errMsg = new JLabel();

        errMsg.setFont(
                new Font("Tahoma", Font.ITALIC, 12));
        errMsg.setBorder(
                null);
        errMsg.setForeground(Color.red);

        errMsg.setBounds(
                270, 175, 215, 20);
        errMsg.setVisible(
                true);
        jPanel.add(errMsg);

        jPanel.add(txtPass);

        jPanel.add(txtUser);

        JButton btnLogin = new JButton("Login");

        btnLogin.setBounds(
                270, 200, 100, 50);
        btnLogin.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String user = txtUser.getText();
                String pass = txtPass.getText();
                if (check()) {
                    SqlUI sqlUI = new SqlUI();
                    sqlUI.setUserName(user);
                    sqlUI.setPassword(pass);
                    if (loginMgr.checkDataBase(user, pass) == true) {
                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "Kết nối Thành Công!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Kết nối Thất bại!! Vui lòng kiểm tra lại username và password");
                    }

                }
            }
        }
        );

        JButton btnCanel = new JButton("Canel");

        btnCanel.setBounds(
                385, 200, 100, 50);
        btnCanel.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                // TODO Auto-generated method stub
                txtPass.setText("");
                txtUser.setText("");
                System.exit(0);
                return;

            }
        }
        );
        jPanel.add(btnCanel);

        jPanel.add(btnLogin);

        JLabel btnImage = new JLabel(
                new ImageIcon("C:\\Users\\dell\\Downloads\\ICon Androi\\iconfinder_f-shield_256_282458.png"));

        btnImage.setBounds(
                10, 50, 140, 200);

        jPanel.add(btnImage);

        add(jPanel);
    }

    public static void main(String[] args) {
        sqlUI login = new sqlUI();
    }

    public boolean check() {
        if (txtUser.getText().equals("")) {
            errMsg.setText("Không để trống họ tên");

            return false;
        }
        errMsg.setText("");
        if (txtPass.getText().equals("")) {

            errMsg.setText("Password không hợp lệ");
            return false;
        }
        errMsg.setText("");
        return true;

    }
}