package MongoDBProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Account extends JFrame {

    private JLabel titleLabel;
    private JPanel loginPanel;
    private JTextField account;
    private JPasswordField passwordField;
    private JButton forgetPassword;
    private JButton loginButton;
    private JButton register;
    public Account() {
        super("金融帳戶整合系統");
        titleLabel = new JLabel("帳戶整合系統");
        MongoDB mongoDB=new MongoDB();
        add(titleLabel, BorderLayout.NORTH);
        JPanel accountPanel = new JPanel(new FlowLayout());
        accountPanel.add(new JLabel("登入名稱"));
        account = new JTextField(15);
        accountPanel.add(account);
        loginPanel = new JPanel(new GridLayout(4,1));
        add(loginPanel,BorderLayout.CENTER);
        loginPanel.add(accountPanel);
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("登入密碼"));
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        loginPanel.add(passwordPanel);
        JPanel registerPanel = new JPanel(new FlowLayout());
        forgetPassword = new JButton("忘記密碼");
        registerPanel.add(forgetPassword);
        register = new JButton("註冊帳戶");
        registerPanel.add(register);
        loginButton = new JButton("登入");
        loginPanel.add(loginButton);
        add(registerPanel,BorderLayout.SOUTH);
        RegisterHandler registerHandler = new RegisterHandler();
        register.addActionListener(registerHandler);
        loginButton.addActionListener(registerHandler);
        forgetPassword.addActionListener(registerHandler);
    }
        private class RegisterHandler implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent registerEvent)
            {
                    if (registerEvent.getSource() == register)
                    {
                        registerFrame registerframe = new registerFrame();
                        registerframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        registerframe.setSize(350,350);
                        registerframe.setLocation(200,300);
                        registerframe.setVisible(true);
                    }
                    else if (registerEvent.getSource() == loginButton||registerEvent.getSource() == passwordField)
                    {

                        try {
                            String loginAccount = account.getText();
                            String loginPassword =new String(passwordField.getPassword());
                            MongoDB m = new MongoDB();
                            m.login(loginAccount,loginPassword);
                            loginFrame loginframe = new loginFrame();
                            loginframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            loginframe.setSize(350,350);
                            loginframe.setVisible(true);
                            loginframe.setLocation(550,300);
                            loginframe.account = loginAccount;
                            loginframe.password = loginPassword;
                            loginframe.balance = m.balance(loginAccount);
                        }
                        catch (Exception e)
                        {
                            JOptionPane.showMessageDialog(Account.this,"帳號密碼錯誤或未註冊","登入失敗",JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    else if(registerEvent.getSource() == forgetPassword)
                    {
                        String account = JOptionPane.showInputDialog("輸入要查詢之帳號");
                        MongoDB m = new MongoDB();
                        try {
                            JOptionPane.showMessageDialog(Account.this,"帳戶密碼為" + m.findPassword(account),"查詢結果",JOptionPane.PLAIN_MESSAGE);
                        }
                        catch (Exception e)
                        {
                            JOptionPane.showMessageDialog(Account.this,"無相關結果","查詢結果",JOptionPane.PLAIN_MESSAGE);
                        }
                    }
            }
    }
}

