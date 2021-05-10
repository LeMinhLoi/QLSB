package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.Customer;
import model.Employee;
import service.CustomerService;
import service.EmployeeService;
//import view.JFrameAddvsEditEmployee.ButtonListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class JFrameAddvsEditCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfPhone;
	private CustomerService customerService;
	private JPanelCustomer jpnCustomer;
	private JButton btnOk;
	private JButton btnCancel;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrameAddvsEditCustomer frame = new JFrameAddvsEditCustomer();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public JFrameAddvsEditCustomer(Customer customer, JPanelCustomer jpnCustomer) {
		initComponents();
		tfID.setText(String.valueOf(customer.getIdCustomer()));
		tfName.setText(customer.getNameCustomer());
		tfPhone.setText(customer.getPhoneCustomer());
		
		this.jpnCustomer = jpnCustomer;
		
//		ButtonListener buttonListener = new ButtonListener();
//		btnOk.addActionListener(buttonListener);
//		btnCancel.addActionListener(buttonListener);
	}
	public JFrameAddvsEditCustomer(int idCustomer, JPanelCustomer jpnCustomer) {
		initComponents();
		tfID.setText(String.valueOf(idCustomer));
		
		this.jpnCustomer = jpnCustomer;
		
//		ButtonListener buttonListener = new ButtonListener();
//		btnOk.addActionListener(buttonListener);
//		btnCancel.addActionListener(buttonListener);
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(29, 24, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(29, 96, 45, 13);
		contentPane.add(lblName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Phone");
		lblNewLabel_1_1.setBounds(29, 176, 45, 13);
		contentPane.add(lblNewLabel_1_1);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(83, 21, 147, 19);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(83, 93, 147, 19);
		contentPane.add(tfName);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(83, 173, 147, 19);
		contentPane.add(tfPhone);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerService customerService = new CustomerService();
				if(customerService.checkID(Integer.parseInt(tfID.getText())) != null) {//kiểm tra xem có mã tồn tại chưa, nếu có thì thực hiện update
					Customer customer = new Customer();
					customer.setIdCustomer(Integer.parseInt(tfID.getText()));
					customer.setNameCustomer(tfName.getText());
					customer.setPhoneCustomer(tfPhone.getText());
					if(customerService.updateCustomer(customer) != null) {
						JOptionPane.showMessageDialog(get(), "Update successfully!","Alert",JOptionPane.CLOSED_OPTION);
						jpnCustomer.showCustomer();
					}else {
						System.out.println("Update fail!");
					}
				}else {//nếu mã chưa tồn tại thì tức là thêm
					Customer customer = new Customer();
					customer.setIdCustomer(Integer.parseInt(tfID.getText()));
					customer.setNameCustomer(tfName.getText());
					customer.setPhoneCustomer(tfPhone.getText());
					if(customerService.insertCustomer(customer) != null) {
						JOptionPane.showMessageDialog(get(), "Insert successfully!","Alert",JOptionPane.CLOSED_OPTION);
						jpnCustomer.showCustomer();
					}else {
						System.out.println("Insert fail!");
					}
				}
				closeFrame();//xong thì đóng, để làm gì :v
			}
		});
		btnOk.setBounds(116, 219, 85, 21);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		btnCancel.setBounds(276, 219, 85, 21);
		contentPane.add(btnCancel);
	}
	public void closeFrame() {
		this.dispose();
	}
	public JFrameAddvsEditCustomer get() {
		return this;
	}
//	private class ButtonListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			customerService = new CustomerService();
//			// TODO Auto-generated method stub
//			if(e.getActionCommand().equals("OK")) {//nếu nhấn vào nút okay thì ...
//				if(customerService.checkID(Integer.parseInt(tfID.getText())) != null) {//kiểm tra xem có mã tồn tại chưa, nếu có thì thực hiện update
//					Customer customer = new Customer();
//					customer.setIdCustomer(Integer.parseInt(tfID.getText()));
//					customer.setNameCustomer(tfName.getText());
//					customer.setPhoneCustomer(tfPhone.getText());
//					if(customerService.updateCustomer(customer) != null) {
//						JOptionPane.showMessageDialog(get(), "Update successfully!","Alert",JOptionPane.CLOSED_OPTION);
//						jpnCustomer.showCustomer();
//					}else {
//						System.out.println("Update fail!");
//					}
//				}else {//nếu mã chưa tồn tại thì tức là thêm
//					Customer customer = new Customer();
//					customer.setIdCustomer(Integer.parseInt(tfID.getText()));
//					customer.setNameCustomer(tfName.getText());
//					customer.setPhoneCustomer(tfPhone.getText());
//					if(customerService.insertCustomer(customer) != null) {
//						JOptionPane.showMessageDialog(get(), "Insert successfully!","Alert",JOptionPane.CLOSED_OPTION);
//						jpnCustomer.showCustomer();
//					}else {
//						System.out.println("Insert fail!");
//					}
//				}
//				closeFrame();//xong thì đóng, để làm gì :v
//			}else if(e.getActionCommand().equals("Cancel")) {//nếu bấm vào nút huỷ
//				closeFrame();
//			}
//		}
//		
//	}
	
}
