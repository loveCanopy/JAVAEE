package com.frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.BuyingDao;
import com.model.Buying;
import com.dao.DessertDao;

public class ConfirmOrderFrame extends JDialog {
	
	/*
	 * 	����̶���Ԫ��
	 */
	
	private JPanel contentPane;
	
	private String ShowDessertName;
	
	private JLabel DessertNameJLabel;
	private JLabel ShowDessertNameJLabel;
	private JLabel BuyingQuantityJLabel;
	private JComboBox BuyingTotalAmountJComboBox;
	
	private JButton OrderJButton;
	private JButton ReSetJButton;
	
	private int PayingBuyingTotalAmount;
	
	private BuyingDao buyingdao = new BuyingDao();
	private Buying buying = new Buying();
	
	/*
	 * ���öྀ�̿��ƽM�����䌍�������M�ȳ��ķ���
	 * �\�г���
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmOrderFrame frame = new ConfirmOrderFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * ����ҕ����ĽM��
	 */
	
	public ConfirmOrderFrame() {
    	setModal(true);
		setResizable(false);
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
        setBounds(285, 245, 320, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("�_�J�c��");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 320, 380);
        contentPane.add(panel);
        panel.setLayout(null);
        
        // ��Ʒ���Q
        JLabel DessertNameJLabel = new JLabel("��Ʒ���Q��");
        DessertNameJLabel.setFont(new Font("����", Font.PLAIN, 15));
        DessertNameJLabel.setForeground(Color.BLACK);
        DessertNameJLabel.setBounds(40, 70, 100, 15);
        panel.add(DessertNameJLabel);
        
        // ��Ʒ���Q
        ShowDessertName = buying.getDessertName();
        JLabel ShowDessertNameJLabel = new JLabel(ShowDessertName);
        ShowDessertNameJLabel.setFont(new Font("����", Font.PLAIN, 15));
        ShowDessertNameJLabel.setForeground(Color.BLACK);
        ShowDessertNameJLabel.setBounds(125, 70, 300, 15);
        panel.add(ShowDessertNameJLabel);
        
        // ��Ʒ����
        JLabel BuyingQuantityJLabel = new JLabel("��Ʒ������");
        BuyingQuantityJLabel.setFont(new Font("����", Font.PLAIN, 15));
        BuyingQuantityJLabel.setForeground(Color.BLACK);
        BuyingQuantityJLabel.setBounds(40, 120, 100, 15);
        panel.add(BuyingQuantityJLabel);
        
        // ��Ʒ����
        String[] TotalAmount = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };
        BuyingTotalAmountJComboBox = new JComboBox(TotalAmount);
        BuyingTotalAmountJComboBox.setBounds(125, 115, 150, 25);
        panel.add(BuyingTotalAmountJComboBox);
        
        // Order
        OrderJButton = new JButton("�c��");
        OrderJButton.setBounds(40, 215, 110, 40);
        OrderJButton.setFont(new Font("����", Font.PLAIN, 15));
        OrderJButton.setForeground(Color.BLACK);
        panel.add(OrderJButton);
        
        // Reset
        ReSetJButton = new JButton("���O");
        ReSetJButton.setBounds(160, 215, 110, 40);
        ReSetJButton.setFont(new Font("����", Font.PLAIN, 15));
        ReSetJButton.setForeground(Color.BLACK);
        panel.add(ReSetJButton);
        
        // Order Action
        OrderJButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// �c�� Action
        		do_button_dessertorder();
        	}
        });
	}

	protected void do_button_dessertorder() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		
		buying.setBuyingQuantity(Integer.parseInt(BuyingTotalAmountJComboBox.getSelectedItem().toString()));
		buying.setBuyingState("�u����");
		buying.setBuyingDate(ourJavaDateObject);
		
		PayingBuyingTotalAmount = CountPayBuyingTotalAmount(buying.getDessertSellingPrice(), buying.getBuyingQuantity());
		buying.setBuyingTotalAmount(PayingBuyingTotalAmount);
		
		int i = buyingdao.AddOrder(buying);
		
		if ( i == 1 ) {
            JOptionPane.showMessageDialog(getContentPane(), "�c�ͳɹ���", "", JOptionPane.WARNING_MESSAGE);
            CloseFrame();
            return;
		} else {
            JOptionPane.showMessageDialog(getContentPane(), "�c��ʧ����Ո�����c�ͣ�", "", JOptionPane.WARNING_MESSAGE);
            CloseFrame();
            return;
		}
	}
	
	public int CountPayBuyingTotalAmount(int BuyingQuantity, int BuyingTotalAmount) {
		int PayingBuyingTotalAmount = BuyingQuantity * BuyingTotalAmount;
		return PayingBuyingTotalAmount;
	}
	
	public void CloseFrame(){
	    super.dispose();
	} 
}