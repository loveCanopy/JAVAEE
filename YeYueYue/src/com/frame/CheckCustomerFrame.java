package com.frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.dao.BuyingDao;
import com.dao.StaffDao;
import com.dao.CustomerDao;
import com.model.Buying;
import com.model.Customer;
import com.model.CheckBuying;

public class CheckCustomerFrame extends JFrame {
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
	
	private BuyingDao buyingdao = new BuyingDao();
	private StaffDao staffdao = new StaffDao();
	private CustomerDao customerdao = new CustomerDao();
	
	private JLabel CustomerIDJLabel;
	private JLabel CustomerTableJLabel;
	
	private Color GERY = new Color(66, 66, 66);
	
	private JLabel CustomerNameJLabel;
	private JLabel CustomerGenderJLabel;
	private JLabel CustomerAgeJLabel;
	private JLabel CustomerPhoneJLabel;
	private JLabel CustomerJobJLabel;
	private JLabel CustomerAddressJLabel;
	
	private JLabel ShowCustomerNameJLabel;
	private JLabel ShowCustomerGenderJLabel;
	private JLabel ShowCustomerAgeJLabel;
	private JLabel ShowCustomerPhoneJLabel;
	private JLabel ShowCustomerJobJLabel;
	private JLabel ShowCustomerAddressJLabel;
	
	private	JTextField CustomerIDJTextField;
	private JComboBox CustomerTableJComboBox;
	
	private JScrollPane MenuJScrollPane;
	
	private JTable MenuJtable;
	private DefaultTableModel defaultModel;
	
	private int[] PayBuyingTotalAmount;
	private int FinalPayBuyingTotalAmount;
	
	private JButton ReadJButton;
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
					CheckCustomerFrame checkcustomerframe = new CheckCustomerFrame();
					checkcustomerframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * ����ҕ����ĽM��
	 */
	
	public CheckCustomerFrame() {
		setTitle("�z���Y��");
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
		
		// ;�̖
		CustomerIDJLabel = new JLabel("��̖��");
		CustomerIDJLabel.setFont(new Font("����", Font.PLAIN, 15));
		CustomerIDJLabel.setForeground(Color.BLACK);
		CustomerIDJLabel.setBounds(250, 70, 300, 100);
		panel.add(CustomerIDJLabel);	
		panel.setLayer(CustomerIDJLabel, new Integer(200));
		
		// ;�̖
		CustomerIDJTextField = new JTextField();
		CustomerIDJTextField.setBounds(310, 108, 290, 25);
        panel.add(CustomerIDJTextField);
        panel.setLayer(CustomerIDJTextField, new Integer(200));
        CustomerIDJTextField.setColumns(20);
        
        // ����
		CustomerNameJLabel = new JLabel("������");
		CustomerNameJLabel.setFont(new Font("����", Font.PLAIN, 15));
		CustomerNameJLabel.setForeground(Color.BLACK);
		CustomerNameJLabel.setBounds(250, 120, 300, 100);
		panel.add(CustomerNameJLabel);	
		panel.setLayer(CustomerNameJLabel, new Integer(200));
		
        // ����
		ShowCustomerNameJLabel = new JLabel("");
		ShowCustomerNameJLabel.setFont(new Font("����", Font.PLAIN, 15));
		ShowCustomerNameJLabel.setForeground(GERY);
		ShowCustomerNameJLabel.setBounds(300, 120, 300, 100);
		panel.add(ShowCustomerNameJLabel);	
		panel.setLayer(ShowCustomerNameJLabel, new Integer(200));
		
		// �Մe
        CustomerGenderJLabel = new JLabel("�Մe��");
        CustomerGenderJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerGenderJLabel.setForeground(Color.BLACK);
        CustomerGenderJLabel.setBounds(400, 120, 300, 100);
		panel.add(CustomerGenderJLabel);	
		panel.setLayer(CustomerGenderJLabel, new Integer(200));
		
		// �Մe
		ShowCustomerGenderJLabel = new JLabel("");
		ShowCustomerGenderJLabel.setFont(new Font("����", Font.PLAIN, 15));
		ShowCustomerGenderJLabel.setForeground(GERY);
		ShowCustomerGenderJLabel.setBounds(450, 120, 300, 100);
		panel.add(ShowCustomerGenderJLabel);	
		panel.setLayer(ShowCustomerGenderJLabel, new Integer(200));
		
		// ���g
        CustomerAgeJLabel = new JLabel("���g��");
        CustomerAgeJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerAgeJLabel.setForeground(Color.BLACK);
        CustomerAgeJLabel.setBounds(500, 120, 300, 100);
		panel.add(CustomerAgeJLabel);	
		panel.setLayer(CustomerAgeJLabel, new Integer(200));
		
		// ���g
		ShowCustomerAgeJLabel = new JLabel("");
		ShowCustomerAgeJLabel.setFont(new Font("����", Font.PLAIN, 15));
		ShowCustomerAgeJLabel.setForeground(GERY);
		ShowCustomerAgeJLabel.setBounds(550, 120, 300, 100);
		panel.add(ShowCustomerAgeJLabel);	
		panel.setLayer(ShowCustomerAgeJLabel, new Integer(200));
		
		// �Ԓ
        CustomerPhoneJLabel = new JLabel("�Ԓ��");
        CustomerPhoneJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerPhoneJLabel.setForeground(Color.BLACK);
        CustomerPhoneJLabel.setBounds(250, 170, 300, 100);
		panel.add(CustomerPhoneJLabel);	
		panel.setLayer(CustomerPhoneJLabel, new Integer(200));
		
		// �Ԓ
		ShowCustomerPhoneJLabel = new JLabel("");
		ShowCustomerPhoneJLabel.setFont(new Font("����", Font.PLAIN, 15));
		ShowCustomerPhoneJLabel.setForeground(GERY);
		ShowCustomerPhoneJLabel.setBounds(300, 170, 300, 100);
		panel.add(ShowCustomerPhoneJLabel);	
		panel.setLayer(ShowCustomerPhoneJLabel, new Integer(200));
		
		// �I
        CustomerJobJLabel = new JLabel("�I��");
        CustomerJobJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerJobJLabel.setForeground(Color.BLACK);
        CustomerJobJLabel.setBounds(400, 170, 300, 100);
		panel.add(CustomerJobJLabel);	
		panel.setLayer(CustomerJobJLabel, new Integer(200));
		
		// �I
		ShowCustomerJobJLabel = new JLabel("");
		ShowCustomerJobJLabel.setFont(new Font("����", Font.PLAIN, 15));
		ShowCustomerJobJLabel.setForeground(GERY);
		ShowCustomerJobJLabel.setBounds(450, 170, 300, 100);
		panel.add(ShowCustomerJobJLabel);	
		panel.setLayer(ShowCustomerJobJLabel, new Integer(200));
		
		// סַ
        CustomerAddressJLabel = new JLabel("סַ��");
        CustomerAddressJLabel.setFont(new Font("����", Font.PLAIN, 15));
        CustomerAddressJLabel.setForeground(Color.BLACK);
        CustomerAddressJLabel.setBounds(250, 220, 300, 100);
		panel.add(CustomerAddressJLabel);	
		panel.setLayer(CustomerAddressJLabel, new Integer(200));
		
		// סַ
		ShowCustomerAddressJLabel = new JLabel("");
		ShowCustomerAddressJLabel.setFont(new Font("����", Font.PLAIN, 15));
		ShowCustomerAddressJLabel.setForeground(GERY);
		ShowCustomerAddressJLabel.setBounds(300, 220, 300, 100);
		panel.add(ShowCustomerAddressJLabel);	
		panel.setLayer(ShowCustomerAddressJLabel, new Integer(200));
       
        // ScrollPanel
        MenuJScrollPane = new JScrollPane();
        MenuJScrollPane.setBounds(180, 305, 580, 300);
        MenuJScrollPane.setBackground(Color.WHITE);
		panel.add(MenuJScrollPane);
		panel.setLayer(MenuJScrollPane, new Integer(200));

        // ���
		ReadJButton = new JButton(new ImageIcon("image/Read.PNG"));
		ReadJButton.setBounds(310, 630, 133, 42);
		ReadJButton.setBorderPainted(false);
        panel.add(ReadJButton);
        panel.setLayer(ReadJButton, new Integer(300));
        
        // ���O
        ReSteJButton = new JButton(new ImageIcon("image/ReSet.PNG"));
        ReSteJButton.setBounds(470, 630, 133, 42);
        ReSteJButton.setBorderPainted(false);
        panel.add(ReSteJButton);
        panel.setLayer(ReSteJButton, new Integer(300));
        
        // Pay Action
        ReadJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
        		// �c��Menu
        		displaytable();
            }
        });
	}
	
	private void displaytable() {
		// TODO Auto-generated method stub
		MenuJtable = new JTable();
		MenuJtable.setAutoResizeMode(MenuJtable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		MenuJtable.setRowHeight(54);

		defaultModel = (DefaultTableModel) MenuJtable.getModel();
		defaultModel.setRowCount(0);
		defaultModel.setColumnIdentifiers(new Object[] { "��Ʒ��̖", "��Ʒ", "����", "����", "�r��" });
		
		MenuJtable.getTableHeader().setReorderingAllowed(false);
		MenuJtable.setModel(defaultModel);
		
		MenuJtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		MenuJtable.getColumnModel().getColumn(1).setPreferredWidth(130);
		MenuJtable.getColumnModel().getColumn(2).setPreferredWidth(50);
		MenuJtable.getColumnModel().getColumn(3).setPreferredWidth(50);
		MenuJtable.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		Buying buying = new Buying();
		Customer customer = new Customer();
		
		if ( CustomerIDJTextField.getText() != null && !CustomerIDJTextField.getText().equals("") ) {
			buying.setCustomerID(Integer.parseInt(CustomerIDJTextField.getText()));
		} else {
			JOptionPane.showMessageDialog(this, "Ոݔ��;�̖��", "",JOptionPane.WARNING_MESSAGE);
			return;
		}
		List Customerlist = customerdao.CustomerData(Buying.getCustomerID());
		List buyinglist = buyingdao.CheckOrder(Buying.getCustomerID());
		
		// Check the CustomerID
		
		if (buyinglist.size() == 0) {
			JOptionPane.showMessageDialog(this, ";�̖�e�`��Ո����ݔ�����_�ľ�̖��", "",JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			
			for ( int i = 0; i < Customerlist.size(); i++) {
				Customer cus = (Customer) Customerlist.get(i);
				ShowCustomerNameJLabel.setText(cus.getCustomerName());
				ShowCustomerGenderJLabel.setText(cus.getCustomerGender());
				ShowCustomerAgeJLabel.setText(Integer.toString(cus.getCustomerAge()));
				ShowCustomerPhoneJLabel.setText(cus.getCustomerPhone());
				ShowCustomerJobJLabel.setText(cus.getCustomerJob());
				ShowCustomerAddressJLabel.setText(cus.getCustomerAddress());
			}
			
			for (int i = 0; i < buyinglist.size(); i++) {
				CheckBuying buy = (CheckBuying) buyinglist.get(i);
				PayBuyingTotalAmount = new int[buyinglist.size()];
				PayBuyingTotalAmount[i] = CountPayBuyingTotalAmount(buy.getBuyingQuantity(), buy.getBuyingTotalAmount());
				FinalPayBuyingTotalAmount += PayBuyingTotalAmount[i];
				defaultModel.addRow(new Object[] { buy.getDessertID(), buy.getDessertName(), buy.getBuyingDate(), buy.getBuyingQuantity()+ "��", "$"+PayBuyingTotalAmount[i] });
			}
			defaultModel.addRow(new Object[] { null, null, null, null, "�����~��$"+FinalPayBuyingTotalAmount});
		}
		
		MenuJScrollPane.setViewportView(MenuJtable);
	}
	
	public int CountPayBuyingTotalAmount(int BuyingQuantity, int BuyingTotalAmount) {
		int PayingBuyingTotalAmount = BuyingQuantity * BuyingTotalAmount;
		return PayingBuyingTotalAmount;
	}
	
	public void CloseFrame(){
	    super.dispose();
	}
}
