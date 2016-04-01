package com.frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.CustomerDao;
import com.model.Customer;

public class UpdateCustomer extends JFrame {
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
	 * 表Frame里的元素
	 */
	
	private CustomerDao customerdao;
	private Customer customer;
	
	private JLabel CustomerNameJLabel;
	private JLabel CustomerGenderJLabel;
	private JLabel CustomerAgeJLabel;
	private JLabel CustomerPhoneJLabel;
	private JLabel CustomerJobJLabel;
	private JLabel CustomerAddressJLabel;
	private JLabel CustomerIDJLabel;
	
	private	JTextField CustomerNameJTextField;
	private JComboBox CustomerGenderJComboBox;
	private JTextField CustomerAgeJTextField;
	private JTextField CustomerPhoneJTextField;
	private JComboBox CustomerJobJComboBox;
	private JTextField CustomerAddressJTextField;
	private JTextField CustomerIDJTextField;
	
	private JButton CreateJButton;
	private JButton ReSteJButton;
	
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
					UpdateCustomer updatecustomer = new UpdateCustomer();
					updatecustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * 創建視窗里的組件
	 */
	
	public UpdateCustomer() {
		setTitle("修改顧客資料");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 視窗的樣式
		setBounds(100, 0, 800, 730);						// 視窗的坐標和大小
		contentPane = new JPanel();							// 和scrollPane一樣、這是通用的容器、是放在JPanel里
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));	// 容器的邊界
		setContentPane(contentPane);						// Set容器進去
		contentPane.setLayout(null);						// 沒有Layout樣式、自己打座標
		
		JLayeredPane panel = new JLayeredPane();			// 就是JPanel、把contentPane加進來、不寫注解了
		panel.setBounds(0, 0, 800, 730);
		panel.setLayout(null);
		JLabel background1 = new JLabel(new ImageIcon("image/NormalBG.jpg"));
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
		
		/*
		 * 表Frame里里的組件
		 */
        
		// 姓名
		CustomerNameJLabel = new JLabel("姓名：");
		CustomerNameJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		CustomerNameJLabel.setForeground(Color.BLACK);
		CustomerNameJLabel.setBounds(250, 70, 300, 100);
		panel.add(CustomerNameJLabel);	
		panel.setLayer(CustomerNameJLabel, new Integer(200));
		
		// 姓名
		CustomerNameJTextField = new JTextField();
		CustomerNameJTextField.setBounds(310, 108, 290, 25);
        panel.add(CustomerNameJTextField);
        panel.setLayer(CustomerNameJTextField, new Integer(200));
        CustomerNameJTextField.setColumns(20);
        
		// 姓別
        CustomerGenderJLabel = new JLabel("姓別：");
        CustomerGenderJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        CustomerGenderJLabel.setForeground(Color.BLACK);
        CustomerGenderJLabel.setBounds(250, 120, 300, 100);
		panel.add(CustomerGenderJLabel);	
		panel.setLayer(CustomerGenderJLabel, new Integer(200));
		
		// 姓別
        String[] sex = new String[] { "男", "女", "萌偽娘", "帥妹子" };
        CustomerGenderJComboBox = new JComboBox(sex);
        CustomerGenderJComboBox.setBounds(310, 158, 290, 25);
        panel.add(CustomerGenderJComboBox);
        panel.setLayer(CustomerGenderJComboBox, new Integer(200));
       
		// 年齡
        CustomerAgeJLabel = new JLabel("年齡：");
        CustomerAgeJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        CustomerAgeJLabel.setForeground(Color.BLACK);
        CustomerAgeJLabel.setBounds(250, 170, 300, 100);
		panel.add(CustomerAgeJLabel);	
		panel.setLayer(CustomerAgeJLabel, new Integer(200));
		
		// 年齡
		CustomerAgeJTextField = new JTextField();
		CustomerAgeJTextField.setBounds(310, 208, 290, 25);
        panel.add(CustomerAgeJTextField);
        panel.setLayer(CustomerAgeJTextField, new Integer(200));
        CustomerAgeJTextField.setColumns(20);
        
		// 電話
        CustomerPhoneJLabel = new JLabel("電話：");
        CustomerPhoneJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        CustomerPhoneJLabel.setForeground(Color.BLACK);
        CustomerPhoneJLabel.setBounds(250, 220, 300, 100);
		panel.add(CustomerPhoneJLabel);	
		panel.setLayer(CustomerPhoneJLabel, new Integer(200));
		
		// 電話
		CustomerPhoneJTextField = new JTextField();
		CustomerPhoneJTextField.setBounds(310, 258, 290, 25);
        panel.add(CustomerPhoneJTextField);
        panel.setLayer(CustomerPhoneJTextField, new Integer(200));
        CustomerPhoneJTextField.setColumns(20);
        
		// 職業
        CustomerJobJLabel = new JLabel("職業：");
        CustomerJobJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        CustomerJobJLabel.setForeground(Color.BLACK);
        CustomerJobJLabel.setBounds(250, 270, 300, 100);
		panel.add(CustomerJobJLabel);	
		panel.setLayer(CustomerJobJLabel, new Integer(200));
		
		// 職業
        String[] job = new String[] { "建築與工程行業", "商業與金融行業", "IT與計算機行業", "藝術與設計行業", "醫療與衛生行業", "教育與教學行業", "科學與研究行業", "行政與司法行業", "食品與營養行業", "農林及其它行業" };
        CustomerJobJComboBox = new JComboBox(job);
        CustomerJobJComboBox.setBounds(310, 308, 290, 25);
        panel.add(CustomerJobJComboBox);
        panel.setLayer(CustomerJobJComboBox, new Integer(200));
        
		// 住址
        CustomerAddressJLabel = new JLabel("住址：");
        CustomerAddressJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        CustomerAddressJLabel.setForeground(Color.BLACK);
        CustomerAddressJLabel.setBounds(250, 320, 300, 100);
		panel.add(CustomerAddressJLabel);	
		panel.setLayer(CustomerAddressJLabel, new Integer(200));
		
		// 住址
		CustomerAddressJTextField = new JTextField();
		CustomerAddressJTextField.setBounds(310, 358, 290, 75);
        panel.add(CustomerAddressJTextField);
        panel.setLayer(CustomerAddressJTextField, new Integer(200));
        CustomerAddressJTextField.setColumns(20);
        
        CreateJButton = new JButton(new ImageIcon("image/Create.PNG"));
        CreateJButton.setBounds(310, 450, 133, 42);
        CreateJButton.setBorderPainted(false);
        panel.add(CreateJButton);
        panel.setLayer(CreateJButton, new Integer(300));
        
        ReSteJButton = new JButton(new ImageIcon("image/ReSet.PNG"));
        ReSteJButton.setBounds(470, 450, 133, 42);
        ReSteJButton.setBorderPainted(false);
        panel.add(ReSteJButton);
        panel.setLayer(ReSteJButton, new Integer(300));
        
        //Update Action
        CreateJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
        		// 點餐Menu
            	do_insertButton_UpdateCustomer();
            }
        });
	}
	
	protected void do_insertButton_UpdateCustomer() {
		// TODO Auto-generated method stub
		customerdao = new CustomerDao();
		customer = new Customer();
		
		if ( CustomerNameJTextField.getText() != null && !CustomerNameJTextField.getText().equals("") 
				&& CustomerGenderJComboBox.getSelectedItem().toString() != null && !CustomerGenderJComboBox.getSelectedItem().toString().equals("") 
				&& CustomerAgeJTextField.getText() != null && !CustomerAgeJTextField.getText().equals("") 
				&& CustomerPhoneJTextField.getText() != null && !CustomerPhoneJTextField.getText().equals("") 
				&& CustomerJobJComboBox.getSelectedItem().toString() != null && !CustomerJobJComboBox.getSelectedItem().toString().equals("") 
				&& CustomerAddressJTextField.getText() != null && !CustomerAddressJTextField.getText().equals("") ) {
			customer.setCustomerName(CustomerNameJTextField.getText());
			customer.setCustomerGender(CustomerGenderJComboBox.getSelectedItem().toString());
			customer.setCustomerAge(Integer.parseInt(CustomerAgeJTextField.getText()));
			customer.setCustomerPhone(CustomerPhoneJTextField.getText());
			customer.setCustomerJob(CustomerJobJComboBox.getSelectedItem().toString());
			customer.setCustomerAddress(CustomerAddressJTextField.getText());
			int i = customerdao.UpdateCustomer(customer);
			if ( i == 1 ) {
	            JOptionPane.showMessageDialog(getContentPane(), "成功更新顧客資料！", "", JOptionPane.WARNING_MESSAGE);
	            return;
			} else {
	            JOptionPane.showMessageDialog(getContentPane(), "更新顧客資料失敗，請輸入正確的顧客姓名！", "", JOptionPane.WARNING_MESSAGE);
	            return;
			}
		} else {
            JOptionPane.showMessageDialog(getContentPane(), "請輸入完整資料！", "", JOptionPane.WARNING_MESSAGE);
            return;
		}
	}

	public void CloseFrame(){
	    super.dispose();
	}
}
