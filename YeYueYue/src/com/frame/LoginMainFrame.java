package com.frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class LoginMainFrame extends JFrame {
	private JPanel contentPane;						// 視窗
	JScrollPane scrollPane = new JScrollPane();		// 捲動式、把元素放進ScrollPane內、再把ScrollPane放進JPanel視窗
	
	/*
	 * 	版面固定的元素
	 */
	
	private JLabel StaffNameTextField;
	private JButton LogOutJButton;
	private JButton InsertCustomerJButton;
	private JButton UpdateCustomerJButton;
	private JButton OrderJButton;
	private JButton CheckOrderJButton;
	private JButton CheckJButton;
	private JButton CheckCustomer;
	private JButton CheckSelling;
	
	/*
	 * 利用多線程控制組件、其實就是先進先出的方法
	 * 運行程序
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					LoginMainFrame loginmainframe = new LoginMainFrame();
					loginmainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * 創建視窗里的組件
	 */
	
	public LoginMainFrame() {
		setTitle("夜月玥甜品點餐售賣系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 視窗的樣式
		setBounds(100, 0, 800, 730);						// 視窗的坐標和大小
		contentPane = new JPanel();							// 和scrollPane一樣、這是通用的容器、是放在JPanel里
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));	// 容器的邊界
		setContentPane(contentPane);						// Set容器進去
		contentPane.setLayout(null);						// 沒有Layout樣式、自己打座標
		
		JLayeredPane panel = new JLayeredPane();			// 就是JPanel、把contentPane加進來、不寫注解了
		panel.setBounds(0, 0, 800, 730);
		panel.setLayout(null);
		JLabel background1 = new JLabel(new ImageIcon("image/LoginMainFrame.jpg"));
		background1.setBounds(0, -15, 800, 730);
		panel.add(background1);
		contentPane.add(panel);
		
		// 職員姓名
		StaffNameTextField = new JLabel("匡匡骨花");
		StaffNameTextField.setFont(new Font("宋体", Font.PLAIN, 15));
		StaffNameTextField.setForeground(Color.white);
		StaffNameTextField.setBounds(680, -13, 300, 100);
		panel.add(StaffNameTextField);	
		panel.setLayer(StaffNameTextField, new Integer(200));
		
		// 登出
		LogOutJButton= new JButton("登出");
		LogOutJButton.setFont(new Font("宋体", Font.PLAIN, 15));
		LogOutJButton.setForeground(Color.WHITE);
		LogOutJButton.setBounds(550, 23, 100, 30);
		LogOutJButton.setBorder(null);
		LogOutJButton.setContentAreaFilled(false);
		panel.add(LogOutJButton);	
		panel.setLayer(LogOutJButton, new Integer(200));
		
		// 新增客戶資料
		InsertCustomerJButton= new JButton("新增客戶資料");
		InsertCustomerJButton.setFont(new Font("宋体", Font.PLAIN, 15));
		InsertCustomerJButton.setForeground(Color.WHITE);
		InsertCustomerJButton.setBounds(10, 100, 130, 30);
		InsertCustomerJButton.setBorder(null);
		InsertCustomerJButton.setContentAreaFilled(false);
		panel.add(InsertCustomerJButton);	
		panel.setLayer(InsertCustomerJButton, new Integer(200));
		
		// 修改客戶資料
		UpdateCustomerJButton= new JButton("修改客戶資料");
		UpdateCustomerJButton.setFont(new Font("宋体", Font.PLAIN, 15));
		UpdateCustomerJButton.setForeground(Color.WHITE);
		UpdateCustomerJButton.setBounds(10, 160, 130, 30);
		UpdateCustomerJButton.setBorder(null);
		UpdateCustomerJButton.setContentAreaFilled(false);
		panel.add(UpdateCustomerJButton);	
		panel.setLayer(UpdateCustomerJButton, new Integer(200));
		
		// 點餐
		OrderJButton= new JButton("點餐");
		OrderJButton.setFont(new Font("宋体", Font.PLAIN, 15));
		OrderJButton.setForeground(Color.WHITE);
		OrderJButton.setBounds(10, 220, 130, 30);
		OrderJButton.setBorder(null);
		OrderJButton.setContentAreaFilled(false);
		panel.add(OrderJButton);	
		panel.setLayer(OrderJButton, new Integer(200));
		
		// 檢查點餐狀態
		CheckOrderJButton= new JButton("檢查點餐狀態");
		CheckOrderJButton.setFont(new Font("宋体", Font.PLAIN, 15));
		CheckOrderJButton.setForeground(Color.WHITE);
		CheckOrderJButton.setBounds(10, 280, 130, 30);
		CheckOrderJButton.setBorder(null);
		CheckOrderJButton.setContentAreaFilled(false);
		panel.add(CheckOrderJButton);	
		panel.setLayer(CheckOrderJButton, new Integer(200));
		
		// 結帳
		CheckJButton= new JButton("結帳");
		CheckJButton.setFont(new Font("宋体", Font.PLAIN, 15));
		CheckJButton.setForeground(Color.WHITE);
		CheckJButton.setBounds(10, 340, 130, 30);
		CheckJButton.setBorder(null);
		CheckJButton.setContentAreaFilled(false);
		panel.add(CheckJButton);	
		panel.setLayer(CheckJButton, new Integer(200));
		
		// 檢查顧客紀錄
		CheckCustomer= new JButton("檢查顧客紀錄");
		CheckCustomer.setFont(new Font("宋体", Font.PLAIN, 15));
		CheckCustomer.setForeground(Color.WHITE);
		CheckCustomer.setBounds(10, 400, 130, 30);
		CheckCustomer.setBorder(null);
		CheckCustomer.setContentAreaFilled(false);
		panel.add(CheckCustomer);	
		panel.setLayer(CheckCustomer, new Integer(200));
		
		// 檢閱銷售總額
		CheckSelling= new JButton("檢閱銷售總額");
		CheckSelling.setFont(new Font("宋体 ", Font.PLAIN, 15));
		CheckSelling.setForeground(Color.WHITE);
		CheckSelling.setBounds(10, 460, 130, 30);
		CheckSelling.setBorder(null);
		CheckSelling.setContentAreaFilled(false);
		panel.add(CheckSelling);	
		panel.setLayer(CheckSelling, new Integer(200));
		
		// 登出
		LogOutJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	CloseFrame();
            }
        });
		
		// 新增客戶資料
		InsertCustomerJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	InsertCustomerFrame insertcustomerframe = new InsertCustomerFrame();
            	insertcustomerframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// 修改客戶資料
		UpdateCustomerJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	UpdateCustomer updatecustomer = new UpdateCustomer();
            	updatecustomer.setVisible(true);
            	CloseFrame();
            }
        });
		
		// 點餐
		OrderJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				OrderFrame orderframe = new OrderFrame();
				orderframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// 檢查點餐狀態
		CheckOrderJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckOrderFrame checkorderframe = new CheckOrderFrame();
				checkorderframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// 結帳
		CheckJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckFrame checkframe = new CheckFrame();
				checkframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// 檢查顧客紀錄
		CheckCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckCustomerFrame checkcustomerframe = new CheckCustomerFrame();
				checkcustomerframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// 檢閱銷售總額
		CheckSelling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckSellingFrame checksellingframe = new CheckSellingFrame();
				checksellingframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// 登出
		LogOutJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame();
				loginframe.setVisible(true);
            	CloseFrame();
            }
        });
	}
	
	public void CloseFrame(){
	    super.dispose();
	}
}
