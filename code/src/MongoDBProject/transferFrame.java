package MongoDBProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class transferFrame extends JFrame{
    String accountI;
    String accountO;
    int amount;
    MongoDB m = new MongoDB();
    JButton confirm = new JButton("確認轉出");
    JTextField accountName = new JTextField(10);
    JTextField amountName = new JTextField(10);
    JTextField outName = new JTextField(10);
    public transferFrame(){
    this.setLayout(new GridLayout(4,1));
    JPanel accountPanel = new JPanel();
    JPanel amountPanel = new JPanel();
    JPanel outPanel = new JPanel();
    accountPanel.add(new JLabel("轉入帳戶"));
    accountPanel.add(accountName);
    outPanel.add(new JLabel("轉出帳戶"));
    outPanel.add(outName);
    amountPanel.add(new JLabel("轉出金額"));
    amountPanel.add(amountName);
    add(accountPanel);
    add(amountPanel);
    add(outPanel);
    add(confirm);
    handler handler = new handler();
    confirm.addActionListener(handler);
    }
    private class handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == confirm)
            {
                accountI = accountName.getText();
                accountO = outName.getText();
                amount = Integer.parseInt(amountName.getText());
                m.transferIn(accountI,amount);
                m.transferOut(accountO,amount);
            }
        }
    }
}
