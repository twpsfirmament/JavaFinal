package MongoDBProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Stock.*;
import http.http.*;
import http.http.用JTextPane接資料.OAOGUITest;

public class loginFrame extends JFrame {
    String account;
    String password;
    int balance;
    JButton checkBalance;
    JButton transfer;
    JButton changePassword;
    JButton search;
    JButton deleteButton;
    MongoDB m = new MongoDB();
    loginFrame()
    {
        super("登入畫面");
        add((new JLabel("歡迎使用")), BorderLayout.NORTH);
        JPanel mainFrame = new JPanel(new GridLayout(2,2));
        transfer = new JButton("轉帳");
        checkBalance = new JButton("查詢餘額");
        changePassword = new JButton("變更密碼");
        search = new JButton("股市功能");
        deleteButton = new JButton("刪除帳戶");
        mainFrame.add(transfer);
        mainFrame.add(checkBalance);
        mainFrame.add(changePassword);
        mainFrame.add(search);
        add(mainFrame,BorderLayout.CENTER);
        add(deleteButton,BorderLayout.SOUTH);
        handler h = new handler();
        checkBalance.addActionListener(h);
        changePassword.addActionListener(h);
        deleteButton.addActionListener(h);
        transfer.addActionListener(h);
        search.addActionListener(h);
    }
    private class handler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == checkBalance)
                JOptionPane.showMessageDialog(loginFrame.this,"餘額為"+ m.balance(account),"餘額",JOptionPane.PLAIN_MESSAGE);
            else if (event.getSource() == changePassword)
            {
              String first = JOptionPane.showInputDialog("請輸入欲更改的密碼");
              String second = JOptionPane.showInputDialog("請再輸入一次");
              if (first.equals(second))
              {
                  JOptionPane.showMessageDialog(loginFrame.this,"密碼更改成功","更改結果",JOptionPane.PLAIN_MESSAGE);
                  m.changePassword(account,first);
              }
              else
              {
                  JOptionPane.showMessageDialog(loginFrame.this,"兩次輸入密碼不同,請再次修改","更改結果",JOptionPane.PLAIN_MESSAGE);
              }
            }
            else if (event.getSource() == deleteButton)
            {
                m.deleteOne(account);
            }
            else if(event.getSource() == transfer)
            {
                transferFrame transferframe = new transferFrame();
                transferframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                transferframe.setVisible(true);
                transferframe.setSize(200,200);
                transferframe.setLocation(550,300);
            }
            else if (event.getSource() == search)
            {
                Gui_Main g = new Gui_Main();
                g.stockFunction();
                OAOGUITest o = new OAOGUITest();
                o.function();
            }
        }
    }
}

