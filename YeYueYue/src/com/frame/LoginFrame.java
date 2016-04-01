package com.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.StaffDao;
import com.model.Staff;

public class LoginFrame extends JFrame {
	private JPanel contentPane;						// 視窗
	JScrollPane scrollPane = new JScrollPane();		// 捲動式、把元素放進ScrollPane內、再把ScrollPane放進JPanel視窗
	private JTable table;							// 表格
	private DefaultTableModel defaultModel;			// 繼承自AbstractTableModel
	
	private JTextField StaffNameTextField;			// 聲明JTextField
	private JPasswordField StaffPassWordTextField;	// 聲明JTextField
	
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
					LoginFrame frame = new LoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * 創建視窗里的組件
	 */
	
	public LoginFrame() {
		setTitle("夜月玥甜品點餐售賣系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 視窗的樣式
		setBounds(100, 100, 400, 485);						// 視窗的坐標和大小
		contentPane = new JPanel();							// 和scrollPane一樣、這是通用的容器、是放在JPanel里
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));	// 容器的邊界
		setContentPane(contentPane);						// Set容器進去
		contentPane.setLayout(null);						// 沒有Layout樣式、自己打座標
		
		JLayeredPane panel = new JLayeredPane();			// 就是JPanel、把contentPane加進來、不寫注解了
		panel.setBounds(0, 0, 400, 485);
		panel.setLayout(null);
		JLabel background1 = new JLabel(new ImageIcon("image/LoginBG.jpg"));
		background1.setBounds(0, -20, 400, 485);
		panel.add(background1);
		contentPane.add(panel);

		JLabel messageLabel = new JLabel("職員編號： ");
		messageLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		messageLabel.setBounds(50, 170, 300, 100);
		panel.add(messageLabel);	
		panel.setLayer(messageLabel, new Integer(200));
		
		JLabel messageLabe2 = new JLabel("登入密碼： ");
		messageLabe2.setFont(new Font("宋体", Font.PLAIN, 15));
		messageLabe2.setBounds(50, 230, 300, 100);
		panel.add(messageLabe2);	
		panel.setLayer(messageLabe2, new Integer(200));
		
		StaffNameTextField = new JTextField();
		StaffNameTextField.setBounds(135, 210, 180, 25);
        panel.add(StaffNameTextField);
        panel.setLayer(StaffNameTextField, new Integer(200));
        StaffNameTextField.setColumns(10);
        
        
        StaffPassWordTextField = new JPasswordField();
        StaffPassWordTextField.setBounds(135, 270, 180, 25);
        panel.add(StaffPassWordTextField);
        panel.setLayer(StaffPassWordTextField, new Integer(200));
        StaffPassWordTextField.setColumns(10);
        
        JButton LoginButton = new JButton(new ImageIcon("image/Login_icon.jpg"));
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	do_loginButton_actionPerformed(arg0);
            }
        });
        LoginButton.setBounds(48, 350, 308, 57);
        LoginButton.setBorderPainted(false);
        panel.add(LoginButton);
        panel.setLayer(LoginButton, new Integer(300));
	}

	protected void do_loginButton_actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		StaffDao staffdao = new StaffDao();
		Staff staff = new Staff();
		
		staff.setgetStaffName(StaffNameTextField.getText());
		staff.setStaffPassWord(StaffPassWordTextField.getText());
		
		if(!(StaffNameTextField.getText().equals("")) &&
				!(StaffPassWordTextField.getText().equals(""))) {
			int i = staffdao.StaffLogin(staff.getStaffName(), staff.getStaffPassWord());
			
			if ( i == 1 ) {
				LoginMainFrame loginmainframe = new LoginMainFrame();
				CloseFrame();
				loginmainframe.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "職員編號或登陸密碼錯誤。", "",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
		} else {
			JOptionPane.showMessageDialog(this, "必須填寫職員編員和登陸密碼。", "",JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
	public void CloseFrame(){
	    super.dispose();
	}
}
