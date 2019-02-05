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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JInternalFrame {

    JPasswordField txtPass;
    JTextField txtUser;
    JLabel errMsg;

    public LoginMgr loginMgr = new LoginMgr();

    public Login() {
        initView();
        setSize(550, 350);
        setTitle("Login");
        setLocation(200, 100);
        setLayout(null);
        setResizable(true);
        setVisible(true);
    }

    private void initView() {

        JPanel jPanel = new JPanel();
        jPanel.setBounds(20, 20, 500, 260);
        jPanel.setLayout(null);
        jPanel.setBorder(new TitledBorder(new LineBorder(new Color(123123)), "Login", TitledBorder.CENTER,
                TitledBorder.TOP, null, new Color(123123)));

        JLabel lblUser = new JLabel("USERNAME:");
        lblUser.setBounds(150, 50, 100, 30);

        JLabel lblPass = new JLabel("PASSWORD");
        lblPass.setBounds(150, 110, 100, 30);

        jPanel.add(lblUser);
        jPanel.add(lblPass);

        txtUser = new JTextField();
        txtUser.setBounds(270, 50, 215, 30);
        
        txtPass = new JPasswordField();
        txtPass.setBounds(270, 110, 215, 30);
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke);
                String user = txtUser.getText();
                String pass = txtPass.getText();

                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (check()) {
                        if (loginMgr.checkLogin(user, pass)) {

                            setVisible(false);
                            JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công!!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Đăng Nhập Thất bại!!");
                            errMsg.setText("Sai username và password");
                        }
                    }
                    //To change body of generated methods, choose Tools | Templates.
                }
            }
        });

        errMsg = new JLabel();
        errMsg.setFont(new Font("Tahoma", Font.ITALIC, 12));
        errMsg.setBorder(null);
        errMsg.setForeground(Color.red);
        errMsg.setBounds(270, 165, 215, 20);
        errMsg.setVisible(true);
        jPanel.add(errMsg);
        jPanel.add(txtPass);
        jPanel.add(txtUser);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(270, 200, 100, 50);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUser.getText();
                String pass = txtPass.getText();
                if (check()) {

                    if (loginMgr.checkLogin(user, pass)) {

                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Đăng Nhập Thất bại!!");
                        errMsg.setText("Sai username và password");
                    }
                }
            }
        });

        JButton btnCanel = new JButton("Canel");
        btnCanel.setBounds(385, 200, 100, 50);
        btnCanel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                txtPass.setText("");
                txtUser.setText("");
                System.exit(0);
                return;

            }
        });
        jPanel.add(btnCanel);
        jPanel.add(btnLogin);

        JLabel btnImage = new JLabel(
                new ImageIcon("C:\\Users\\dell\\Downloads\\ICon Androi\\iconfinder_f-shield_256_282458.png"));
        btnImage.setBounds(10, 50, 140, 200);

        jPanel.add(btnImage);
        add(jPanel);
    }

    public static void main(String[] args) {
        Login login = new Login();
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
