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
	 * 	版面固定的元素
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
	 * 利用多線程控制組件、其實就是先進先出的方法
	 * 運行程序
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
	 * 創建視窗里的組件
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
        setTitle("確認點餐");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 320, 380);
        contentPane.add(panel);
        panel.setLayout(null);
        
        // 甜品名稱
        JLabel DessertNameJLabel = new JLabel("甜品名稱：");
        DessertNameJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        DessertNameJLabel.setForeground(Color.BLACK);
        DessertNameJLabel.setBounds(40, 70, 100, 15);
        panel.add(DessertNameJLabel);
        
        // 甜品名稱
        ShowDessertName = buying.getDessertName();
        JLabel ShowDessertNameJLabel = new JLabel(ShowDessertName);
        ShowDessertNameJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        ShowDessertNameJLabel.setForeground(Color.BLACK);
        ShowDessertNameJLabel.setBounds(125, 70, 300, 15);
        panel.add(ShowDessertNameJLabel);
        
        // 甜品數量
        JLabel BuyingQuantityJLabel = new JLabel("甜品數量：");
        BuyingQuantityJLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        BuyingQuantityJLabel.setForeground(Color.BLACK);
        BuyingQuantityJLabel.setBounds(40, 120, 100, 15);
        panel.add(BuyingQuantityJLabel);
        
        // 甜品數量
        String[] TotalAmount = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };
        BuyingTotalAmountJComboBox = new JComboBox(TotalAmount);
        BuyingTotalAmountJComboBox.setBounds(125, 115, 150, 25);
        panel.add(BuyingTotalAmountJComboBox);
        
        // Order
        OrderJButton = new JButton("點餐");
        OrderJButton.setBounds(40, 215, 110, 40);
        OrderJButton.setFont(new Font("宋体", Font.PLAIN, 15));
        OrderJButton.setForeground(Color.BLACK);
        panel.add(OrderJButton);
        
        // Reset
        ReSetJButton = new JButton("重設");
        ReSetJButton.setBounds(160, 215, 110, 40);
        ReSetJButton.setFont(new Font("宋体", Font.PLAIN, 15));
        ReSetJButton.setForeground(Color.BLACK);
        panel.add(ReSetJButton);
        
        // Order Action
        OrderJButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// 點餐 Action
        		do_button_dessertorder();
        	}
        });
	}

	protected void do_button_dessertorder() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		
		buying.setBuyingQuantity(Integer.parseInt(BuyingTotalAmountJComboBox.getSelectedItem().toString()));
		buying.setBuyingState("製作中");
		buying.setBuyingDate(ourJavaDateObject);
		
		PayingBuyingTotalAmount = CountPayBuyingTotalAmount(buying.getDessertSellingPrice(), buying.getBuyingQuantity());
		buying.setBuyingTotalAmount(PayingBuyingTotalAmount);
		
		int i = buyingdao.AddOrder(buying);
		
		if ( i == 1 ) {
            JOptionPane.showMessageDialog(getContentPane(), "點餐成功！", "", JOptionPane.WARNING_MESSAGE);
            CloseFrame();
            return;
		} else {
            JOptionPane.showMessageDialog(getContentPane(), "點餐失敗，請重新點餐！", "", JOptionPane.WARNING_MESSAGE);
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