package MongoDBProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class registerFrame extends JFrame{
    ArrayList<JPanel> list = new ArrayList<>();
    ArrayList<JLabel> labelList = new ArrayList<>();
    ArrayList<JTextField> textList = new ArrayList<>();
    JButton registerButton = new JButton("註冊");
    public registerFrame() {
        super("註冊畫面");
        this.setLayout(new GridLayout(8, 1));
        labelList.add(0,new JLabel("帳戶"));
        labelList.add(1, new JLabel("密碼"));
        labelList.add(2, new JLabel("餘額"));
        labelList.add(3, new JLabel("姓名"));
        labelList.add(4, new JLabel("性別"));
        labelList.add(5, new JLabel("手機"));
        labelList.add(6, new JLabel("電子信箱"));
        for (int i = 0; i < 7; i++) {
            list.add(new JPanel(new FlowLayout()));
            textList.add(new JTextField(10));
            list.get(i).add(labelList.get(i));
            list.get(i).add(textList.get(i));
            add(list.get(i));
        }
        add(registerButton, BorderLayout.SOUTH);
        Handler handler = new Handler();
        registerButton.addActionListener(handler);
    }
        private class Handler implements ActionListener {
                String account;
                String password;
                int balance;
                String name;
                String gender;
                String mobile;
                String email;
            @Override
                public void actionPerformed(ActionEvent e){
                if(e.getSource() == registerButton)
                {
                    this.account = textList.get(0).getText();
                    this.password = textList.get(1).getText();
                    this.balance = Integer.parseInt(textList.get(2).getText());
                    this.name = textList.get(3).getText();
                    this.gender = textList.get(4).getText();
                    this.mobile = textList.get(5).getText();
                    this.email = textList.get(6).getText();
                        MongoDB mongoDB = new MongoDB();
                        mongoDB.insert(this.account,this.password,this.balance,this.name,this.gender,this.mobile,this.email);
            }
        }
    }
}
