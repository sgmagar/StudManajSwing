package com.javastud.studmproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;

import com.javastud.studmproj.*;
import com.javastud.studmproj.dao.UserDao;
import com.javastud.studmproj.model.User;
import javax.swing.JPasswordField;
public class LoginStudentLayout extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JButton btnSignIn;
	private JLabel lblStatus;
	private JPasswordField passwordField;
	
	private UserDao userDao = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginStudentLayout frame = new LoginStudentLayout();
					WindowManager.ui.put("LoginStudentLayout",frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginStudentLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(SystemColor.activeCaption);
		panelTop.setForeground(SystemColor.activeCaption);
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(SystemColor.activeCaption);
		contentPane.add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(SystemColor.activeCaption);
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		JPanel panelRight = new JPanel();
		panelRight.setBackground(SystemColor.activeCaption);
		contentPane.add(panelRight, BorderLayout.EAST);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 346, 111);
		panelCenter.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblUsername = new JLabel("Username");
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtUsername = new JTextField();
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		passwordField = new JPasswordField();
		panel.add(passwordField);
		
		lblStatus = new JLabel("Status :");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 132, 346, 14);
		panelCenter.add(lblStatus);
		
		panelCenter.add(getBtnSignIn());
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e ) {
				WindowManager.ui.clear();
				System.exit(0);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(267, 170, 89, 23);
		panelCenter.add(btnCancel);
		
		try{
			userDao = new UserDao();
		}catch(ClassNotFoundException| SQLException e){
			e.printStackTrace();
		}
	
	}
	
	private JButton getBtnSignIn(){
		if(btnSignIn == null){
			btnSignIn = new JButton("Sign In");
			btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnSignIn.setBounds(24, 170, 89, 23);
			btnSignIn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					User user = new User();
					user.setUsername(txtUsername.getText());
					user.setPassword(new String(passwordField.getPassword()));
					try{
					boolean validateSuccess = userDao.validateUser(user);
					if(validateSuccess){
						//Go to next student
						switchToStudMangScreen();
					}
					else{
						lblStatus.setText("Status:Either User or Password Invalid!!");
					}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
					
					
				}
			});
		
	}
		return btnSignIn;
}
	private void switchToStudMangScreen(){
		StudentManagement studMngWindow = new StudentManagement(txtUsername.getText());
		WindowManager.ui.put("StudentManagement", studMngWindow);
		studMngWindow.setVisible(true);
		
		LoginStudentLayout loginWindow = (LoginStudentLayout)WindowManager.ui.get("LoginStudentLayout");
		loginWindow.clearData();
		loginWindow.setVisible(false);
	}
	
	private void clearData(){
		passwordField.setText("");
		lblStatus.setText("Status: ");
	}
}
