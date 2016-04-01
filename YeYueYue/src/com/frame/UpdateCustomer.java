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
	private JPanel contentPane;						// ҕ��
	JScrollPane scrollPane = new JScrollPane();		// �Ԅ�ʽ����Ԫ�ط��MScrollPane�ȡ��ٰ�ScrollPane���MJPanelҕ��
	
	/*
	 * 	����̶���Ԫ��
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
	 * ��Frame���Ԫ��
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
	 * ���öྀ�̿��ƽM�����䌍�������M�ȳ��ķ���
	 * �\�г���
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
	 * ����ҕ����ĽM��
	 */
	
	public UpdateCustomer() {
		setTitle("�޸���Y��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// ҕ���Ę�ʽ
		setBounds(100, 0, 800, 730);						// ҕ�������˺ʹ�С
		contentPane = new JPanel();							// ��scrollPaneһ�ӡ��@��ͨ�õ��������Ƿ���JPanel��
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));	// ������߅��
		setContentPane(contentPane);						// Set�����Mȥ
		contentPane.setLayout(null);						// �]��Layout��ʽ���Լ�������
		
		JLayeredPane panel = new JLayeredPane();			// ����JPanel����contentPane���M������ע����
		panel.setBounds(0, 0, 800, 730);
		panel.setLayout(null);
		JLabel background1 = new JLabel(new ImageIcon("image/NormalBG.jpg"));
		background1.setBounds(0, -15, 800, 730);
		panel.add(background1);
		contentPane.add(panel);

		// �T����
		StaffNameTextField = new JLabel("���ǻ�");
		StaffNameTextField.setFont(new Font("����", Font.PLAIN, 15));
		StaffNameTextField.setForeground(Color.white);
		StaffNameTextField.setBounds(680, -13, 300, 100);
		panel.add(StaffNameTextField);	
		panel.setLayer(StaffNameTextField, new Integer(200));
		
		// �ǳ�
		LogOutJButton= new JButton("�ǳ�");
		LogOutJButton.setFont(new Font("����", Font.PLAIN, 15));
		LogOutJButton.setForeground(Color.WHITE);
		LogOutJButton.setBounds(550, 23, 100, 30);
		LogOutJButton.setBorder(null);
		LogOutJButton.setContentAreaFilled(false);
		panel.add(LogOutJButton);	
		panel.setLayer(LogOutJButton, new Integer(200));
		
		// �����͑��Y��
		InsertCustomerJButton= new JButton("�����͑��Y��");
		InsertCustomerJButton.setFont(new Font("����", Font.PLAIN, 15));
		InsertCustomerJButton.setForeground(Color.WHITE);
		InsertCustomerJButton.setBounds(10, 100, 130, 30);
		InsertCustomerJButton.setBorder(null);
		InsertCustomerJButton.setContentAreaFilled(false);
		panel.add(InsertCustomerJButton);	
		panel.setLayer(InsertCustomerJButton, new Integer(200));
		
		// �޸Ŀ͑��Y��
		UpdateCustomerJButton= new JButton("�޸Ŀ͑��Y��");
		UpdateCustomerJButton.setFont(new Font("����", Font.PLAIN, 15));
		UpdateCustomerJButton.setForeground(Color.WHITE);
		UpdateCustomerJButton.setBounds(10, 160, 130, 30);
		UpdateCustomerJButton.setBorder(null);
		UpdateCustomerJButton.setContentAreaFilled(false);
		panel.add(UpdateCustomerJButton);	
		panel.setLayer(UpdateCustomerJButton, new Integer(200));
		
		// �c��
		OrderJButton= new JButton("�c��");
		OrderJButton.setFont(new Font("����", Font.PLAIN, 15));
		OrderJButton.setForeground(Color.WHITE);
		OrderJButton.setBounds(10, 220, 130, 30);
		OrderJButton.setBorder(null);
		OrderJButton.setContentAreaFilled(false);
		panel.add(OrderJButton);	
		panel.setLayer(OrderJButton, new Integer(200));
		
		// �z���c�͠�B
		CheckOrderJButton= new JButton("�z���c�͠�B");
		CheckOrderJButton.setFont(new Font("����", Font.PLAIN, 15));
		CheckOrderJButton.setForeground(Color.WHITE);
		CheckOrderJButton.setBounds(10, 280, 130, 30);
		CheckOrderJButton.setBorder(null);
		CheckOrderJButton.setContentAreaFilled(false);
		panel.add(CheckOrderJButton);	
		panel.setLayer(CheckOrderJButton, new Integer(200));
		
		// �Y��
		CheckJButton= new JButton("�Y��");
		CheckJButton.setFont(new Font("����", Font.PLAIN, 15));
		CheckJButton.setForeground(Color.WHITE);
		CheckJButton.setBounds(10, 340, 130, 30);
		CheckJButton.setBorder(null);
		CheckJButton.setContentAreaFilled(false);
		panel.add(CheckJButton);	
		panel.setLayer(CheckJButton, new Integer(200));
		
		// �z��ͼo�
		CheckCustomer= new JButton("�z��ͼo�");
		CheckCustomer.setFont(new Font("����", Font.PLAIN, 15));
		CheckCustomer.setForeground(Color.WHITE);
		CheckCustomer.setBounds(10, 400, 130, 30);
		CheckCustomer.setBorder(null);
		CheckCustomer.setContentAreaFilled(false);
		panel.add(CheckCustomer);	
		panel.setLayer(CheckCustomer, new Integer(200));
		
		// �z��N�ۿ��~
		CheckSelling= new JButton("�z��N�ۿ��~");
		CheckSelling.setFont(new Font("���� ", Font.PLAIN, 15));
		CheckSelling.setForeground(Color.WHITE);
		CheckSelling.setBounds(10, 460, 130, 30);
		CheckSelling.setBorder(null);
		CheckSelling.setContentAreaFilled(false);
		panel.add(CheckSelling);	
		panel.setLayer(CheckSelling, new Integer(200));
		
		// �ǳ�
		LogOutJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	CloseFrame();
            }
        });
		
		// �����͑��Y��
		InsertCustomerJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	InsertCustomerFrame insertcustomerframe = new InsertCustomerFrame();
            	insertcustomerframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// �޸Ŀ͑��Y��
		UpdateCustomerJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	UpdateCustomer updatecustomer = new UpdateCustomer();
            	updatecustomer.setVisible(true);
            	CloseFrame();
            }
        });
		
		// �c��
		OrderJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				OrderFrame orderframe = new OrderFrame();
				orderframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// �z���c�͠�B
		CheckOrderJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckOrderFrame checkorderframe = new CheckOrderFrame();
				checkorderframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// �Y��
		CheckJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckFrame checkframe = new CheckFrame();
				checkframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// �z��ͼo�
		CheckCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckCustomerFrame checkcustomerframe = new CheckCustomerFrame();
				checkcustomerframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// �z��N�ۿ��~
		CheckSelling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				CheckSellingFrame checksellingframe = new CheckSellingFrame();
				checksellingframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		// �ǳ�
		LogOutJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame();
				loginframe.setVisible(true);
            	CloseFrame();
            }
        });
		
		/*
		 * ��Frame����ĽM��
		 */
        
		// ����
		CustomerNameJLabel = new JLabel("������");
		CustomerNameJLabel.setFont(new Font("����", Font.PLAIN, 15));
		CustomerNameJLabel.setForeground(Color.BLACK);
		CustomerNameJLabel.setBounds(250, 70, 300, 100);
		panel.add(CustomerNameJLabel);	
		panel.setLayer(CustomerNameJLabel, new Integer(200));
		
		// ����
		CustomerNameJTextField = new JTextField();
		CustomerNameJTextField.setBounds(310, 108, 290, 25);
        panel.add(CustomerNameJTextField);
        panel.setLayer(CustomerNameJTextField, new Integer(200));
        CustomerNameJTextField.setColumns(20);
        
		// �Մe
        CustomerGenderJLabel = new JLabel("�Մe��");
        CustomerGenderJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerGenderJLabel.setForeground(Color.BLACK);
        CustomerGenderJLabel.setBounds(250, 120, 300, 100);
		panel.add(CustomerGenderJLabel);	
		panel.setLayer(CustomerGenderJLabel, new Integer(200));
		
		// �Մe
        String[] sex = new String[] { "��", "Ů", "�Ȃ���", "������" };
        CustomerGenderJComboBox = new JComboBox(sex);
        CustomerGenderJComboBox.setBounds(310, 158, 290, 25);
        panel.add(CustomerGenderJComboBox);
        panel.setLayer(CustomerGenderJComboBox, new Integer(200));
       
		// ���g
        CustomerAgeJLabel = new JLabel("���g��");
        CustomerAgeJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerAgeJLabel.setForeground(Color.BLACK);
        CustomerAgeJLabel.setBounds(250, 170, 300, 100);
		panel.add(CustomerAgeJLabel);	
		panel.setLayer(CustomerAgeJLabel, new Integer(200));
		
		// ���g
		CustomerAgeJTextField = new JTextField();
		CustomerAgeJTextField.setBounds(310, 208, 290, 25);
        panel.add(CustomerAgeJTextField);
        panel.setLayer(CustomerAgeJTextField, new Integer(200));
        CustomerAgeJTextField.setColumns(20);
        
		// �Ԓ
        CustomerPhoneJLabel = new JLabel("�Ԓ��");
        CustomerPhoneJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerPhoneJLabel.setForeground(Color.BLACK);
        CustomerPhoneJLabel.setBounds(250, 220, 300, 100);
		panel.add(CustomerPhoneJLabel);	
		panel.setLayer(CustomerPhoneJLabel, new Integer(200));
		
		// �Ԓ
		CustomerPhoneJTextField = new JTextField();
		CustomerPhoneJTextField.setBounds(310, 258, 290, 25);
        panel.add(CustomerPhoneJTextField);
        panel.setLayer(CustomerPhoneJTextField, new Integer(200));
        CustomerPhoneJTextField.setColumns(20);
        
		// �I
        CustomerJobJLabel = new JLabel("�I��");
        CustomerJobJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerJobJLabel.setForeground(Color.BLACK);
        CustomerJobJLabel.setBounds(250, 270, 300, 100);
		panel.add(CustomerJobJLabel);	
		panel.setLayer(CustomerJobJLabel, new Integer(200));
		
		// �I
        String[] job = new String[] { "���B�c�����ИI", "�̘I�c�����ИI", "IT�cӋ��C�ИI", "ˇ�g�c�OӋ�ИI", "�t���c�l���ИI", "�����c�̌W�ИI", "�ƌW�c�о��ИI", "�����c˾���ИI", "ʳƷ�c�I�B�ИI", "�r�ּ������ИI" };
        CustomerJobJComboBox = new JComboBox(job);
        CustomerJobJComboBox.setBounds(310, 308, 290, 25);
        panel.add(CustomerJobJComboBox);
        panel.setLayer(CustomerJobJComboBox, new Integer(200));
        
		// סַ
        CustomerAddressJLabel = new JLabel("סַ��");
        CustomerAddressJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerAddressJLabel.setForeground(Color.BLACK);
        CustomerAddressJLabel.setBounds(250, 320, 300, 100);
		panel.add(CustomerAddressJLabel);	
		panel.setLayer(CustomerAddressJLabel, new Integer(200));
		
		// סַ
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
        		// �c��Menu
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
	            JOptionPane.showMessageDialog(getContentPane(), "�ɹ�������Y�ϣ�", "", JOptionPane.WARNING_MESSAGE);
	            return;
			} else {
	            JOptionPane.showMessageDialog(getContentPane(), "������Y��ʧ����Ոݔ�����_���������", "", JOptionPane.WARNING_MESSAGE);
	            return;
			}
		} else {
            JOptionPane.showMessageDialog(getContentPane(), "Ոݔ�������Y�ϣ�", "", JOptionPane.WARNING_MESSAGE);
            return;
		}
	}

	public void CloseFrame(){
	    super.dispose();
	}
}
