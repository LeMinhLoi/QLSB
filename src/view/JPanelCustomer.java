package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import model.Employee;
import service.CustomerService;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelCustomer extends JPanel {
	private JTextField textField;
	private CustomerService customerService;
	private JTable jtbCustomer_1;
	private JScrollPane scrollPane;	
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnEdit;
	private JComboBox cbbSort;
	/**
	 * Create the panel.
	 */
	public JPanelCustomer() {
		
		initComponents();
		customerService = new CustomerService();
		showCustomer();
		
//		ButtonListener buttonListener = new ButtonListener();
//		btnAdd.addActionListener(buttonListener);
//		btnEdit.addActionListener(buttonListener);
//		btnDelete.addActionListener(buttonListener);
	}
	private void initComponents() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 205, 411);
		add(panel);
		panel.setLayout(null);
		
		JButton btnEdit = new JButton("S\u1EEDa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFrame(true);
				System.out.println("sua");
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(10, 37, 185, 48);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Xo\u00E1");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int getID = Integer.parseInt(jtbCustomer_1.getModel().getValueAt(jtbCustomer_1.getSelectedRow(),1).toString());
				customerService.deleteCustomer(getID);
				showCustomer();
				System.out.println("xoa");
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(10, 96, 185, 48);
		panel.add(btnDelete);
		
		JButton btnAdd = new JButton("Th\u00EAm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFrame(false);
				System.out.println("them");
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(10, 155, 185, 48);
		panel.add(btnAdd);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 214, 185, 48);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(564, 11, 46, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(625, 8, 96, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("T\u00ECm ki\u1EBFm");
		btnSearch.setBounds(724, 7, 83, 23);
		add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 70, 630, 327);
		add(scrollPane);
		
		jtbCustomer_1 = new JTable();
		scrollPane.setViewportView(jtbCustomer_1);

	}
	public void showCustomer() {
		Object[][] data = customerService.showCustomers();
		String col[] = {"STT","ID","Name","Phone"};
		DefaultTableModel model = (DefaultTableModel) jtbCustomer_1.getModel();
        model.setDataVector(data, col);
	}
//	private class ButtonListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			if(e.getActionCommand().equals("Xoá")) {//nếu nhấn vào nút xoá thì ...
//				int getID = Integer.parseInt(jtbCustomer_1.getModel().getValueAt(jtbCustomer_1.getSelectedRow(),0).toString());
//				customerService.deleteCustomer(getID);
//				showCustomer();
//				System.out.println("xoa");
//			}else if(e.getActionCommand().equals("Thêm")) {
//				createFrame(false);
//				System.out.println("them");
//			}else if(e.getActionCommand().equals("S\u1EEDa ")) {
//				createFrame(true);
//				System.out.println("sua");
//			}
//
//		}
//		
//	}
	public void createFrame(boolean b) {
		if( b == true) {
			int getID = Integer.parseInt(jtbCustomer_1.getModel().getValueAt(jtbCustomer_1.getSelectedRow(),1).toString());
			Customer customer = customerService.checkID(getID);
			JFrameAddvsEditCustomer addvsEditCustomer = new JFrameAddvsEditCustomer(customer,this);
			addvsEditCustomer.setVisible(true);
			System.out.println("Đã tạo frame sửa");
		}else if(b == false){
			int nextID = customerService.getNextIdCustomer();
			JFrameAddvsEditCustomer addvsEditCustomer = new JFrameAddvsEditCustomer(nextID,this);
			addvsEditCustomer.setVisible(true);
			System.out.println("Đã tạo frame thêm");
		}
	}
}
