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
	private JPanel contentPane;						// ҕ��
	JScrollPane scrollPane = new JScrollPane();		// �Ԅ�ʽ����Ԫ�ط��MScrollPane�ȡ��ٰ�ScrollPane���MJPanelҕ��
	private JTable table;							// ���
	private DefaultTableModel defaultModel;			// �^����AbstractTableModel
	
	private JTextField StaffNameTextField;			// ��JTextField
	private JPasswordField StaffPassWordTextField;	// ��JTextField
	
	/*
	 * ���öྀ�̿��ƽM�����䌍�������M�ȳ��ķ���
	 * �\�г���
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
	 * ����ҕ����ĽM��
	 */
	
	public LoginFrame() {
		setTitle("ҹ�«h��Ʒ�c�����uϵ�y");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// ҕ���Ę�ʽ
		setBounds(100, 100, 400, 485);						// ҕ�������˺ʹ�С
		contentPane = new JPanel();							// ��scrollPaneһ�ӡ��@��ͨ�õ��������Ƿ���JPanel��
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));	// ������߅��
		setContentPane(contentPane);						// Set�����Mȥ
		contentPane.setLayout(null);						// �]��Layout��ʽ���Լ�������
		
		JLayeredPane panel = new JLayeredPane();			// ����JPanel����contentPane���M������ע����
		panel.setBounds(0, 0, 400, 485);
		panel.setLayout(null);
		JLabel background1 = new JLabel(new ImageIcon("image/LoginBG.jpg"));
		background1.setBounds(0, -20, 400, 485);
		panel.add(background1);
		contentPane.add(panel);

		JLabel messageLabel = new JLabel("�T��̖�� ");
		messageLabel.setFont(new Font("����", Font.PLAIN, 15));
		messageLabel.setBounds(50, 170, 300, 100);
		panel.add(messageLabel);	
		panel.setLayer(messageLabel, new Integer(200));
		
		JLabel messageLabe2 = new JLabel("�����ܴa�� ");
		messageLabe2.setFont(new Font("����", Font.PLAIN, 15));
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
				JOptionPane.showMessageDialog(this, "�T��̖�����ܴa�e�`��", "",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
		} else {
			JOptionPane.showMessageDialog(this, "�����T���T�͵���ܴa��", "",JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
	public void CloseFrame(){
	    super.dispose();
	}
}
