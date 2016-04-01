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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.model.Staff;
import com.dao.StaffDao;
import com.frame.LoginFrame;

import static java.lang.System.*;

public class MainFrame {
	public static void main(String[] args) {
		LoginFrame loginframe = new LoginFrame();
		loginframe.setVisible(true);
	}
}