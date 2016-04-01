package com.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.dao.DessertDao;
import com.dao.StaffDao;
import com.model.Dessert;
import com.model.Buying;

public class OrderFrame extends JFrame {
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
	
	private DessertDao dessertdao = new DessertDao();
	private StaffDao staffdao = new StaffDao();
	
	private JLabel CustomerNameJLabel;
	private JLabel CustomerTableJLabel;
	
	private	JTextField CustomerNameJTextField;
	private JComboBox CustomerTableJComboBox;
	
	private JScrollPane MenuJScrollPane;
	
	private JTable MenuJtable;
	private DefaultTableModel defaultModel;
	
	private JButton DessertOrderJButton;
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
					OrderFrame orderframe = new OrderFrame();
					orderframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * 創建視窗里的組件
	 */
	
	public OrderFrame() {
		setTitle("點餐");
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
		
		// 顧客編號
		CustomerNameJLabel = new JLabel("編號：");
		CustomerNameJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		CustomerNameJLabel.setForeground(Color.BLACK);
		CustomerNameJLabel.setBounds(250, 70, 300, 100);
		panel.add(CustomerNameJLabel);	
		panel.setLayer(CustomerNameJLabel, new Integer(200));
		
		// 顧客編號
		CustomerNameJTextField = new JTextField();
		CustomerNameJTextField.setBounds(310, 108, 290, 25);
        panel.add(CustomerNameJTextField);
        panel.setLayer(CustomerNameJTextField, new Integer(200));
        CustomerNameJTextField.setColumns(20);
        
		// 檯號
        CustomerTableJLabel = new JLabel("檯號：");
        CustomerTableJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        CustomerTableJLabel.setForeground(Color.BLACK);
        CustomerTableJLabel.setBounds(250, 120, 300, 100);
		panel.add(CustomerTableJLabel);	
		panel.setLayer(CustomerTableJLabel, new Integer(200));
		
		// 檯號
        String[] sex = new String[] { "001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "014", "015", "016", "017", "018", "019", "020" };
        CustomerTableJComboBox = new JComboBox(sex);
        CustomerTableJComboBox.setBounds(310, 158, 290, 25);
        panel.add(CustomerTableJComboBox);
        panel.setLayer(CustomerTableJComboBox, new Integer(200));
        
        // ScrollPanel
        MenuJScrollPane = new JScrollPane();
        MenuJScrollPane.setBounds(180, 210, 580, 400);
        MenuJScrollPane.setBackground(Color.WHITE);
		panel.add(MenuJScrollPane);
		panel.setLayer(MenuJScrollPane, new Integer(200));
		
		// 點餐Menu
		selecttable();
		
        // 新增
		DessertOrderJButton = new JButton(new ImageIcon("image/Order.PNG"));
		DessertOrderJButton.setBounds(310, 630, 133, 42);
		DessertOrderJButton.setBorderPainted(false);
        panel.add(DessertOrderJButton);
        panel.setLayer(DessertOrderJButton, new Integer(300));
        
        // 重設
        ReSteJButton = new JButton(new ImageIcon("image/ReSet.PNG"));
        ReSteJButton.setBounds(470, 630, 133, 42);
        ReSteJButton.setBorderPainted(false);
        panel.add(ReSteJButton);
        panel.setLayer(ReSteJButton, new Integer(300));
        
        // Order Action
        DessertOrderJButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// 點餐 Action
        		do_button_dessertorder();
        	}
        });
	}
	
	/*
	 * 點餐的Menu
	 */
	
	private void selecttable () {
		// TODO Auto-generated method stub
		MenuJtable = new JTable();
		MenuJtable.setAutoResizeMode(MenuJtable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		MenuJtable.setRowHeight(54);

		defaultModel = (DefaultTableModel) MenuJtable.getModel();
		defaultModel.setRowCount(0);
		defaultModel.setColumnIdentifiers(new Object[] { "編號", "甜品", "描述", "價格", "貨量" });
		
		MenuJtable.getTableHeader().setReorderingAllowed(false);
		MenuJtable.setModel(defaultModel);
		
		MenuJtable.getColumnModel().getColumn(0).setPreferredWidth(10);
		MenuJtable.getColumnModel().getColumn(1).setPreferredWidth(130);
		MenuJtable.getColumnModel().getColumn(2).setPreferredWidth(250);
		MenuJtable.getColumnModel().getColumn(3).setPreferredWidth(20);
		MenuJtable.getColumnModel().getColumn(4).setPreferredWidth(20);
		
		List dessertlist = dessertdao.SelectMenu();
		for (int i = 0; i < dessertlist.size(); i++) {

			Dessert des = (Dessert) dessertlist.get(i);
			defaultModel.addRow(new Object[] { des.getDessertID(), des.getDessertName(), des.getDessertDescription(), des.getDessertSellingPrice(), des.getDessertStorage()+"件" });
		}
		MenuJScrollPane.setViewportView(MenuJtable);
		
	}
	
	protected void do_button_dessertorder() {
		// TODO Auto-generated method stub
		int row = MenuJtable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "請選擇甜品！", "",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (CustomerNameJTextField.getText()!= null && !CustomerNameJTextField.getText().equals("") ) {
			Buying buying = new Buying();
			buying.setCustomerID(Integer.parseInt(CustomerNameJTextField.getText()));
			buying.setDessertID(Integer.parseInt(MenuJtable.getValueAt(row, 0).toString()));
			buying.setDessertName(MenuJtable.getValueAt(row, 1).toString());
			buying.setDessertSellingPrice(Integer.parseInt(MenuJtable.getValueAt(row, 3).toString()));
		} else {
			JOptionPane.showMessageDialog(this, "請填寫顧客編號！", "",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ConfirmOrderFrame cofirmorderframe = new ConfirmOrderFrame();
		cofirmorderframe.setVisible(true);
	}

	public void CloseFrame(){
	    super.dispose();
	} 
	
}
