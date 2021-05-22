	package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import controller.EmployeeController;
import model.Employee;
import service.EmployeeService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;

public class JFrameAddvsEditEmployee extends JFrame {
	private EmployeeService employeeService;
	private JPanelEmployee jpnEmployee;
	
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfOld;
	private JTextField tfAddress;
	private JTextField tfPhone;
	private JTextField tfPass;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private JRadioButton rdAdmin;
	private JRadioButton rdNhanvien;
	private JTextField tfIdentity;
	private JButton btnOk;
	private JButton btnCancel;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrameAddvsEditEmployee frame = new JFrameAddvsEditEmployee(1);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public JFrameAddvsEditEmployee(Employee employee, JPanelEmployee jpnEmployee) {
		initComponents();
		tfID.setText(String.valueOf(employee.getIdCustomer()));
		tfAddress.setText(employee.getAddress());
		tfName.setText(employee.getNameCustomer());
		tfOld.setText(String.valueOf(employee.getOld()));
		tfPass.setText(employee.getPassword());
		tfPhone.setText(employee.getPhoneCustomer());
		
		this.jpnEmployee = jpnEmployee;
		
		ButtonListener buttonListener = new ButtonListener();
		btnOk.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}
	public JFrameAddvsEditEmployee(int idEmployee, JPanelEmployee jpnEmployee) {
		initComponents();
		tfID.setText(String.valueOf(idEmployee));
		
		this.jpnEmployee = jpnEmployee;
		
		ButtonListener buttonListener = new ButtonListener();
		btnOk.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlbId = new JLabel("ID");
		jlbId.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbId.setHorizontalAlignment(SwingConstants.CENTER);
		jlbId.setBounds(29, 11, 59, 14);
		contentPane.add(jlbId);
		
		JLabel jlbName = new JLabel("Name");
		jlbName.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbName.setHorizontalAlignment(SwingConstants.CENTER);
		jlbName.setBounds(29, 61, 59, 14);
		contentPane.add(jlbName);
		
		JLabel jlbOld = new JLabel("Old");
		jlbOld.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbOld.setHorizontalAlignment(SwingConstants.CENTER);
		jlbOld.setBounds(29, 114, 59, 14);
		contentPane.add(jlbOld);
		
		JLabel jlbAdress = new JLabel("Address");
		jlbAdress.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbAdress.setHorizontalAlignment(SwingConstants.CENTER);
		jlbAdress.setBounds(29, 166, 59, 14);
		contentPane.add(jlbAdress);
		
		JLabel lblNewLabel_4 = new JLabel("Phone");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(29, 215, 59, 14);
		contentPane.add(lblNewLabel_4);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(98, 8, 118, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(98, 58, 118, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfOld = new JTextField();
		tfOld.setBounds(98, 111, 118, 20);
		contentPane.add(tfOld);
		tfOld.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(98, 163, 118, 20);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(98, 212, 118, 20);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel jlbPass = new JLabel("Password");
		jlbPass.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbPass.setBounds(301, 166, 121, 14);
		contentPane.add(jlbPass);
		
		rdNam = new JRadioButton("Nam");
		rdNam.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNam.setBounds(301, 7, 65, 23);
		contentPane.add(rdNam);
		
		rdNu = new JRadioButton("N\u1EEF");
		rdNu.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNu.setBounds(381, 9, 65, 23);
		contentPane.add(rdNu);
		
		rdAdmin = new JRadioButton("Admin");
		rdAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdAdmin.setBounds(301, 110, 78, 23);
		contentPane.add(rdAdmin);
		
		rdNhanvien = new JRadioButton("Nh\u00E2n vi\u00EAn");
		rdNhanvien.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNhanvien.setBounds(381, 110, 95, 23);
		contentPane.add(rdNhanvien);
		
		tfPass = new JTextField();
		tfPass.setBounds(432, 165, 134, 20);
		contentPane.add(tfPass);
		tfPass.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(172, 290, 89, 23);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(301, 290, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel jlbIdentity = new JLabel("Identity Number");
		jlbIdentity.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbIdentity.setBounds(301, 63, 134, 14);
		contentPane.add(jlbIdentity);
		
		tfIdentity = new JTextField();
		tfIdentity.setBounds(432, 60, 134, 20);
		contentPane.add(tfIdentity);
		tfIdentity.setColumns(10);
		
		
	}
	public void closeFrame() {//dùng để đóng frame
		this.dispose();
	}
	public JFrameAddvsEditEmployee get() {
		return this;
	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			employeeService = new EmployeeService();
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("OK")) {//nếu nhấn vào nút okay thì ...
				if(employeeService.checkID(Integer.parseInt(tfID.getText())) != null) {//kiểm tra xem có mã tồn tại chưa, nếu có thì thực hiện update
					Employee employee = new Employee();
					employee.setIdCustomer(Integer.parseInt(tfID.getText()));
					employee.setNameCustomer(tfName.getText());
					employee.setAddress(tfAddress.getText());
					employee.setOld(Integer.parseInt(tfOld.getText()));
					employee.setIdentityNumber(tfIdentity.getText());
					employee.setPassword(tfPass.getText());
					employee.setPhoneCustomer(tfPhone.getText());
					if(rdNam.isSelected() == true) employee.setGender(1);
					else employee.setGender(0);
					if(rdAdmin.isSelected() == true) employee.setRole(1);
					else employee.setGender(0);
					if(employeeService.updateEmployee(employee) != null) {
						JOptionPane.showMessageDialog(get(), "Update successfully!","Alert",JOptionPane.CLOSED_OPTION);
						jpnEmployee.showEmployee();
					}else {
						System.out.println("Update fail!");
					}
				}else {//nếu mã chưa tồn tại thì tức là thêm
					Employee employee = new Employee();
					employee.setIdCustomer(Integer.parseInt(tfID.getText()));
					employee.setNameCustomer(tfName.getText());
					employee.setAddress(tfAddress.getText());
					employee.setOld(Integer.parseInt(tfOld.getText()));
					employee.setIdentityNumber(tfIdentity.getText());
					employee.setPassword(tfPass.getText());
					employee.setPhoneCustomer(tfPhone.getText());
					if(rdNam.isSelected() == true) employee.setGender(1);
					else employee.setGender(0);
					if(rdAdmin.isSelected() == true) employee.setRole(1);
					else employee.setGender(0);
					if(employeeService.insertEmployee(employee) != null) {
						JOptionPane.showMessageDialog(get(), "Insert successfully!","Alert",JOptionPane.CLOSED_OPTION);
						jpnEmployee.showEmployee();
					}else {
						System.out.println("Insert fail!");
					}
				}
				closeFrame();//xong thì đóng, để làm gì :v
			}else if(e.getActionCommand().equals("Cancel")) {//nếu bấm vào nút huỷ
				closeFrame();
			}
		}
		
	}
	
}