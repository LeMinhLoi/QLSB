	package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class JFrameAddvsEditEmployee extends JFrame {

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
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrameAddvsEditEmployee frame = new JFrameAddvsEditEmployee();
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
	public JFrameAddvsEditEmployee(Employee employee) {
		initComponents();
		tfID.setText(String.valueOf(employee.getIdCustomer()));
		tfAddress.setText(employee.getAddress());
		tfName.setText(employee.getNameCustomer());
		tfOld.setText(String.valueOf(employee.getOld()));
		tfPass.setText(employee.getPassword());
		tfPhone.setText(employee.getPhoneCustomer());
	}
	public JFrameAddvsEditEmployee(int idEmployee) {
		initComponents();
		tfID.setText(String.valueOf(idEmployee));
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 393);
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
		jlbPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbPass.setBounds(301, 116, 78, 14);
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
		rdAdmin.setBounds(301, 59, 78, 23);
		contentPane.add(rdAdmin);
		
		rdNhanvien = new JRadioButton("Nh\u00E2n vi\u00EAn");
		rdNhanvien.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNhanvien.setBounds(381, 59, 95, 23);
		contentPane.add(rdNhanvien);
		
		tfPass = new JTextField();
		tfPass.setBounds(389, 113, 118, 20);
		contentPane.add(tfPass);
		tfPass.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(172, 290, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(301, 290, 89, 23);
		contentPane.add(btnCancel);
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	public JTextField getTfID() {
		return tfID;
	}
	public JTextField getTfName() {
		return tfName;
	}
	public JTextField getTfOld() {
		return tfOld;
	}
	public JTextField getTfAddress() {
		return tfAddress;
	}
	public JTextField getTfPhone() {
		return tfPhone;
	}
	public JTextField getTfPass() {
		return tfPass;
	}
	public JRadioButton getRdNam() {
		return rdNam;
	}
	public JRadioButton getRdNu() {
		return rdNu;
	}
	public JRadioButton getRdAdmin() {
		return rdAdmin;
	}
	public JRadioButton getRdNhanvien() {
		return rdNhanvien;
	}
	
}
