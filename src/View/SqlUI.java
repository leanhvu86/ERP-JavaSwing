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
import entities.Config;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlUI extends JInternalFrame {

    JTextField txtUser, txtServerName, txtPort, txtInstanceName, txtPass;
    JLabel errMsg;
    ArrayList<Config> list= new ArrayList<>();
    public LoginMgr loginMgr = new LoginMgr();

    public SqlUI() {
        initView();
        setSize(580, 480);
        setTitle("Kết nối Database");
        setLocation(200, 200);
        setLayout(null);
        setResizable(true);
        setVisible(true);
    }

    private void initView() {

        JPanel jPanel = new JPanel();
        jPanel.setBounds(20, 20, 500, 340);
        jPanel.setLayout(null);
        jPanel.setBorder(new TitledBorder(new LineBorder(new Color(123123)), "Kết nối Database", TitledBorder.CENTER,
                TitledBorder.TOP, null, new Color(123123)));

        JLabel lbl = new JLabel("Server name");
        lbl.setBounds(150, 20, 100, 30);
        JLabel lblPort = new JLabel("Cổng Port:");
        lblPort.setBounds(150, 70, 100, 30);
        JLabel lblInstance = new JLabel("Instance name:");
        lblInstance.setBounds(150, 120, 100, 30);
        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(150, 170, 100, 30);
        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(150, 220, 100, 30);
        jPanel.add(lbl);
        jPanel.add(lblPort);
        jPanel.add(lblInstance);
        jPanel.add(lblUser);
        jPanel.add(lblPass);
        txtServerName = new JTextField();
        txtServerName.setBounds(270, 20, 215, 30);

        txtPort = new JTextField();
        txtPort.setBounds(270, 70, 215, 30);

        txtInstanceName = new JTextField();
        txtInstanceName.setBounds(270, 120, 215, 30);

        txtUser = new JTextField();
        txtUser.setBounds(270, 170, 215, 30);

        txtPass = new JPasswordField();
        txtPass.setBounds(270, 220, 215, 30);
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke);
                String user = txtUser.getText();
                user = user.trim();
                String pass = txtPass.getText();
                String url = "jdbc:sqlserver://" + txtServerName.getText() + "\\" + "\\" + txtInstanceName.getText() + ":" + txtPort.getText() + ";databseName=EmployeeManager";
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (check()) {

                        Config config = new Config();
                        config.setUserName(user);
                        config.setPassword(pass);
                        config.setUrl(url);
                        if (loginMgr.checkDataBase(url, user, pass) == true) {
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
                270, 250, 215, 20);
        errMsg.setVisible(
                true);
        jPanel.add(errMsg);
        jPanel.add(txtInstanceName);
        jPanel.add(txtPort);
        jPanel.add(txtPass);

        jPanel.add(txtUser);
        jPanel.add(txtServerName);
        JButton btnLogin = new JButton("Kết nối");

        btnLogin.setBounds(
                270, 280, 100, 50);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String user = txtUser.getText();
                user = user.trim();
                String pass = txtPass.getText();
                String url = "jdbc:sqlserver://"+ txtServerName.getText()+ "\\" + "\\" + txtInstanceName.getText() + ":" + txtPort.getText() + ";databaseName=EmployeeManager";
                if (check()) {
                    Config sqlUI = new Config();
                    sqlUI.setUserName(user);
                    sqlUI.setPassword(pass);
                    sqlUI.setUrl(url);
                    if (loginMgr.checkDataBase(url, user, pass) == true) {
                        setVisible(false);
                        try {
                            saveFileConfig(user, pass, url);
                        } catch (IOException ex) {
                            Logger.getLogger(SqlUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "Kết nối Thành Công!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Kết nối Thất bại!! Vui lòng kiểm tra lại dữ liệu nhập");
                    }

                }
            }
        }
        );

        JButton btnCanel = new JButton("Thoát");

        btnCanel.setBounds(
                385, 280, 100, 50);
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
                new ImageIcon("src\\image\\iconfinder_f-shield_256_282458.png"));

        btnImage.setBounds(
                10, 50, 140, 200);

        jPanel.add(btnImage);

        add(jPanel);
    }

    public static void main(String[] args) {
        SqlUI login = new SqlUI();
    }

    public boolean check() {
        if (txtServerName.getText().equals("")) {
            errMsg.setText("Không để trống servername");
            txtServerName.requestFocus();
            return false;
        }if (txtPort.getText().equals("")) {
            errMsg.setText("Không để trống cổng port");
            txtPort.requestFocus();
            return false;
        }
        try {
            int port = Integer.parseInt(txtPort.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errMsg.setText("Port là kiểu số");
            txtPort.requestFocus();
            return false;
        }
        if (txtInstanceName.getText().equals("")) {
            errMsg.setText("Không để trống InstanceName");
            txtInstanceName.requestFocus();
            return false;
        }
        if (txtUser.getText().equals("")) {
            errMsg.setText("Không để trống username");
            txtUser.requestFocus();
            return false;
        }

        if (txtPass.getText().equals("")) {
            txtUser.requestFocus();
            errMsg.setText("Password không hợp lệ");
            return false;
        }
        errMsg.setText("");
        return true;

    }
        public void saveFileConfig(String username, String password, String url) throws IOException {

        try {
            FileOutputStream file = new FileOutputStream("src\\config.txt");
            try (ObjectOutputStream oos = new ObjectOutputStream(file)) {
                list.add(new Config(username, password, url));
                oos.writeObject(list);
                oos.close();
                System.out.println(list.size()+" config");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
