package http.http.用label去接資料;

import http.http.用JTextPane接資料.OAO;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FontFrameV1 {
	



	private JFrame appFrame; // 定義為靜態變數以便main使用 並把這個視窗命名為 appFrame //我的主方案是 appFrame
	private JButton turnButton; 
	private JTextField inputTextBox1; 
	private JTextField cannotInputTextBox;
	private JLabel[][] label=new JLabel[11][5];
	private JRadioButton Group1TemperatureButton1;
	private JRadioButton Group1TemperatureButton2;

	private int fontSize = 20; 




	//實作appFrame
	public FontFrameV1() { // 構造器, 建立圖形介面
		appFrame = new JFrame("Font Test");
		appFrame.setLayout(new GridLayout(11,1));
		ActionListener listener = new MyEventListener();//建立一個名為listener的觸發事件(actionlistener)並觸發名為MyEventListner的FUNTION(下面實作)
		
		
		
		
		
		
		
		//定義元件 確定 開始轉換溫度
		turnButton = new JButton("水泥");// 
		turnButton.addActionListener(listener);//按鈕被出發會做 handler FUNTION
		//定義元件 可以輸入字
		
		
		
		//定義元件 顯示字
		label[0][0]=new JLabel("編號 名稱",SwingConstants.LEFT);
		label[0][1]=new JLabel("股價",SwingConstants.LEFT);
		label[0][2]=new JLabel("跌漲",SwingConstants.LEFT);
		label[0][3]=new JLabel("跌漲幅",SwingConstants.LEFT);
		label[0][4]=new JLabel("開盤價",SwingConstants.LEFT);
		//item
		for(int j=1;j<8;j++)
		{
			for(int i=0;i<5;i++)
			{
				label[j][i]=new JLabel(" ",SwingConstants.LEFT);
			}
		}
       //排序用 Panel
		JPanel buttonPanel1 = new JPanel();
		JPanel labelPanel0 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel0.setLayout(new GridLayout(1,5)); //排版模式
		JPanel labelPanel1 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel1.setLayout(new GridLayout(1,5)); //排版模式
		JPanel labelPanel2 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel2.setLayout(new GridLayout(1,5)); //排版模式
		JPanel labelPanel3 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel3.setLayout(new GridLayout(1,5)); //排版模式
		JPanel labelPanel4 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel4.setLayout(new GridLayout(1,5)); //排版模式
		JPanel labelPanel5 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel5.setLayout(new GridLayout(1,5)); //排版模式
		JPanel labelPanel6 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel6.setLayout(new GridLayout(1,5)); //排版模式
		JPanel labelPanel7 = new JPanel(); // used to get proper layout // 該面板用來顯示文字元件 (排版)
		labelPanel7.setLayout(new GridLayout(1,5)); //排版模式
		
		// 新增到面板
		buttonPanel1.add(turnButton);
		for(int i=0;i<5;i++)
		{
			labelPanel0.add(label[0][i]);
			labelPanel1.add(label[1][i]);
			labelPanel2.add(label[2][i]);
			labelPanel3.add(label[3][i]);
			labelPanel4.add(label[4][i]);
			labelPanel5.add(label[5][i]);
			labelPanel6.add(label[6][i]);
			labelPanel7.add(label[7][i]);
		}


		//加入
		appFrame.add(buttonPanel1); // add buttons at LEFT.
		
		appFrame.add(labelPanel0); // add buttons at LEFT.
		appFrame.add(labelPanel1); // add buttons at LEFT.
		appFrame.add(labelPanel2); // add buttons at LEFT.
		appFrame.add(labelPanel3); // add buttons at LEFT.
		appFrame.add(labelPanel4); // add buttons at LEFT.
		appFrame.add(labelPanel5); // add buttons at LEFT.
		appFrame.add(labelPanel6); // add buttons at LEFT.
		appFrame.add(labelPanel7); // add buttons at LEFT.
		appFrame.setVisible(true); // display Frame

		
		

	}

	private class MyEventListener implements ActionListener //多事件觸發 (下面有另一種 單事件觸發)
	{
		
		public void actionPerformed(ActionEvent OAOAOAOAOAO) //多事件觸發監控
		{

				String buttonName = OAOAOAOAOAO.getActionCommand();//獲得被觸發按鈕名稱
				if (buttonName.equals("水泥"))
				{
					http.http.用JTextPane接資料.OAO OAO1=new OAO();
					OAO1.checkWebsiteHTTP("http://jsjustweb.jihsun.com.tw/z/ze/zeg/zeg_23.djhtm");
					OAO1.Parser(OAO1.getAllData());
					System.out.printf("\n編號 名稱\t股價\t跌漲\t跌漲幅\t開盤價\n");
					for(int i=0; i<OAO1.getItemCounter();i++)
					{
						System.out.println(
						OAO1.getNameList().get(i).toString()+"\t"+
						OAO1.getCostList().get(i).toString()+"\t"+
						OAO1.getUpDownList().get(i).toString()+"\t"+
						OAO1.getUpDownPercentList().get(i).toString()+"\t"+
						OAO1.getOpenPriceList().get(i).toString()
						);
						//label[0]="編號 名稱 股價 漲跌 漲跌幅度 開盤價"
						label[i+1][0].setText(OAO1.getNameList().get(i).toString());
						label[i+1][1].setText(OAO1.getCostList().get(i).toString());
						label[i+1][2].setText(OAO1.getUpDownList().get(i).toString());
						label[i+1][3].setText(OAO1.getUpDownPercentList().get(i).toString());
						label[i+1][4].setText(OAO1.getOpenPriceList().get(i).toString());
						
						if(Double.parseDouble(label[i+1][2].getText())<0)
						{
							label[i+1][2].setForeground(Color.GREEN);//變色
							label[i+1][3].setForeground(Color.GREEN);//變色
						}
						if(Double.parseDouble(label[i+1][2].getText())>0)
						{
							label[i+1][2].setForeground(Color.red);//變色
							label[i+1][3].setForeground(Color.red);//變色
						}
						
					
						
					}
				}

		}	
	}

	public JFrame getFrame() 
	{
		
		return appFrame;
	}
}
