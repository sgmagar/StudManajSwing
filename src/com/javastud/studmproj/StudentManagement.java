package com.javastud.studmproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.javastud.studmproj.dao.StudentDao;
import com.javastud.studmproj.model.Student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

public class StudentManagement extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
		
	private StudentDao studDao;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblDateOfBirth;
	private JTextField txtDateOfBirth;
	private JLabel lblSemester;
	private JTextField txtSemester;
	private JLabel lblFaculty;
	private JTextField txtFaculty;
	private JLabel lblRollNo;
	private JTextField txtRollNo;
	private JLabel lblCollege;
	private JTextField txtCollege;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JButton btnSave;
	private JTable studTable;
	private JScrollPane scrollPane;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagement frame = new StudentManagement();
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
	public StudentManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Student Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 787, 193);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblActiveuser = new JLabel("ActiveUser:");
		lblActiveuser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblActiveuser.setBounds(467, 32, 76, 14);
		panel_1.add(lblActiveuser);
		
		
		
		JButton btnLogOff = new JButton("");
		btnLogOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToLoginScreen();
			}
		});
		btnLogOff.setIcon(new ImageIcon("D:\\Java-sts\\StudMgmtProject\\src\\logout.png"));
		btnLogOff.setBounds(691, 15, 46, 42);
		panel_1.add(btnLogOff);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 133, 206, 43);
		panel_1.add(panel);
		panel.setBorder(new TitledBorder(null, "Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		panel.add(getRdbtnMale());
		panel.add(getRdbtnFemale());
		panel_1.add(getActiveUser());
		panel_1.add(getLblName());
		panel_1.add(getTxtName());
		panel_1.add(getLblDateOfBirth());
		panel_1.add(getTxtDateOfBirth());
		panel_1.add(getLblSemester());
		panel_1.add(getTxtSemester());
		panel_1.add(getLblFaculty());
		panel_1.add(getTxtFaculty());
		panel_1.add(getLblRollNo());
		panel_1.add(getTxtRollNo());
		panel_1.add(getLblCollege());
		panel_1.add(getTxtCollege());
		panel_1.add(getLblAddress());
		panel_1.add(getTxtAddress());
		panel_1.add(getBtnSave());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnExit());
		
		try {
			studDao = new StudentDao();
			showAllStudents();
		} catch (ClassNotFoundException | SQLException  e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private JLabel getActiveUser(){
		if(lblNewLabel == null){
			lblNewLabel = new JLabel("ActiveUserName");
			lblNewLabel.setForeground(new Color(50, 205, 50));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(553, 32, 109, 14);
		}
		return lblNewLabel;
	}
	
	
	 
	
	
	public StudentManagement(String activeUser){
		this();
		this.getActiveUser().setText(activeUser);
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name");
			lblName.setBounds(33, 74, 46, 14);
		}
		return lblName;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBounds(77, 68, 164, 20);
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JLabel getLblDateOfBirth() {
		if (lblDateOfBirth == null) {
			lblDateOfBirth = new JLabel("Date of Birth");
			lblDateOfBirth.setBounds(251, 71, 76, 14);
		}
		return lblDateOfBirth;
	}
	private JTextField getTxtDateOfBirth() {
		if (txtDateOfBirth == null) {
			txtDateOfBirth = new JTextField();
			txtDateOfBirth.setBounds(328, 68, 162, 20);
			txtDateOfBirth.setColumns(10);
		}
		return txtDateOfBirth;
	}
	private JLabel getLblSemester() {
		if (lblSemester == null) {
			lblSemester = new JLabel("Semester");
			lblSemester.setBounds(251, 102, 67, 14);
		}
		return lblSemester;
	}
	private JTextField getTxtSemester() {
		if (txtSemester == null) {
			txtSemester = new JTextField();
			txtSemester.setBounds(328, 99, 162, 20);
			txtSemester.setColumns(10);
		}
		return txtSemester;
	}
	private JLabel getLblFaculty() {
		if (lblFaculty == null) {
			lblFaculty = new JLabel("Faculty");
			lblFaculty.setBounds(33, 105, 46, 14);
		}
		return lblFaculty;
	}
	private JTextField getTxtFaculty() {
		if (txtFaculty == null) {
			txtFaculty = new JTextField();
			txtFaculty.setBounds(77, 99, 164, 20);
			txtFaculty.setColumns(10);
		}
		return txtFaculty;
	}
	private JLabel getLblRollNo() {
		if (lblRollNo == null) {
			lblRollNo = new JLabel("Roll No");
			lblRollNo.setBounds(524, 71, 46, 14);
		}
		return lblRollNo;
	}
	private JTextField getTxtRollNo() {
		if (txtRollNo == null) {
			txtRollNo = new JTextField();
			txtRollNo.setBounds(590, 68, 147, 20);
			txtRollNo.setColumns(10);
		}
		return txtRollNo;
	}
	private JLabel getLblCollege() {
		if (lblCollege == null) {
			lblCollege = new JLabel("College");
			lblCollege.setBounds(524, 105, 46, 14);
		}
		return lblCollege;
	}
	private JTextField getTxtCollege() {
		if (txtCollege == null) {
			txtCollege = new JTextField();
			txtCollege.setBounds(590, 99, 147, 20);
			txtCollege.setColumns(10);
		}
		return txtCollege;
	}
	private JLabel getLblAddress() {
		if (lblAddress == null) {
			lblAddress = new JLabel("Address");
			lblAddress.setBounds(245, 148, 61, 14);
		}
		return lblAddress;
	}
	private JTextField getTxtAddress() {
		if (txtAddress == null) {
			txtAddress = new JTextField();
			txtAddress.setBounds(306, 145, 184, 20);
			txtAddress.setColumns(10);
		}
		return txtAddress;
	}
	private JRadioButton getRdbtnMale() {
		if (rdbtnMale == null) {
			rdbtnMale = new JRadioButton("Male");
			rdbtnMale.setBounds(6, 13, 63, 23);
		}
		return rdbtnMale;
	}
	private JRadioButton getRdbtnFemale() {
		if (rdbtnFemale == null) {
			rdbtnFemale = new JRadioButton("Female");
			rdbtnFemale.setBounds(91, 13, 109, 23);
		}
		return rdbtnFemale;
	}
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Save");
			btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnSave.setBounds(648, 144, 89, 23);
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Student stud = getStudentFormData();
						studDao.saveStudent(stud);
						showAllStudents();
					} catch (ClassNotFoundException | SQLException | ParseException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			});
			
		}
		return btnSave;
	}
	
	private Student getStudentFormData() throws ParseException{
		Student stud = new Student();
		stud.setName(txtName.getText());
		stud.setBirthdate(new SimpleDateFormat("YYYY-MM-DD").parse(txtDateOfBirth.getText()));
		stud.setRollno(txtRollNo.getText());
		stud.setFaculty(txtFaculty.getText());
		stud.setSemester(txtSemester.getText());
		stud.setCollegename(txtCollege.getText());
		if(rdbtnMale.isSelected()){
			stud.setSex("Male");
		}else if (rdbtnFemale.isSelected()){
			stud.setSex("Female");
		}
		stud.setAddress(txtAddress.getText());
		
		return stud;
	}
	
	private void showAllStudents() throws ClassNotFoundException, SQLException{
		/*** Clear Table data ***/
		DefaultTableModel model = (DefaultTableModel) studTable.getModel();
		model.setRowCount(0);
		
		/** fetch all students record ***/
		studDao = new StudentDao();
		List<Student>studentList = studDao.getAllStudents();
		for(Student stud : studentList){
			System.out.println(stud);
			model.addRow(new Object[] {stud.getId(), stud.getName(), stud.getAddress(), getYYYYMMDD(stud.getBirthdate()), 
					stud.getRollno(), stud.getFaculty(),stud.getSemester(), stud.getCollegename(), stud.getSex()});
		}
	}
	
	private String getYYYYMMDD(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	private JTable getStudTable() {
		if (studTable == null) {
			studTable = new JTable();
			studTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Name", "Address", "Date of Birth", "Roll No", "Semester", "Faculty", "Gender", "College"
				}
			));
		}
		return studTable;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(21, 248, 685, 165);
			scrollPane.setViewportView(getStudTable());
		}
		return scrollPane;
	}
	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					WindowManager.ui.clear();
					System.exit(0);
				}
			});
			btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnExit.setForeground(new Color(255, 69, 0));
			btnExit.setBounds(647, 444, 89, 23);
		}
		return btnExit;
	}
	
	private void switchToLoginScreen(){
		LoginStudentLayout loginWindow = (LoginStudentLayout) WindowManager.ui.get("LoginStudentLayout");
		loginWindow.setVisible(true);
		
		StudentManagement studManagWindow = (StudentManagement) WindowManager.ui.get("StudentManagement");
		studManagWindow.dispose();
	}
}
