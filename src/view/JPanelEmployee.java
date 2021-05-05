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

import controller.EmployeeController;
import service.EmployeeService;

import javax.swing.JTable;

public class JPanelEmployee extends JPanel {
	private JTextField textField;
	private JTable jtbEmployee;
	private EmployeeService employeeService;
	/**
	 * Create the panel.
	 */
	public JPanelEmployee() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 73, 610, 357);
		add(scrollPane);
		
		jtbEmployee = new JTable();
		scrollPane.setViewportView(jtbEmployee);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 203, 430);
		add(panel);
		panel.setLayout(null);
		
		JButton btnDelete = new JButton("Xo\u00E1");
		btnDelete.setBackground(new Color(102, 204, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.setBounds(20, 208, 173, 45);
		panel.add(btnDelete);
		
		JButton btnAdd = new JButton("Th\u00EAm");
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBackground(new Color(102, 204, 255));
		btnAdd.setBounds(20, 72, 173, 45);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("S\u1EEDa ");
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBackground(new Color(102, 204, 255));
		btnEdit.setBounds(20, 139, 173, 45);
		panel.add(btnEdit);
		
		JComboBox cbbSort = new JComboBox();
		cbbSort.setForeground(Color.DARK_GRAY);
		cbbSort.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbbSort.setBackground(new Color(102, 204, 255));
		cbbSort.setBounds(20, 277, 173, 33);
		panel.add(cbbSort);
		
		textField = new JTextField();
		textField.setBounds(588, 27, 135, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("T\u00ECm ki\u1EBFm");
		btnSearch.setBounds(741, 26, 89, 23);
		add(btnSearch);
		
		JLabel jlbSearch = new JLabel("T\u00ECm ki\u1EBFm");
		jlbSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbSearch.setBounds(519, 30, 59, 14);
		add(jlbSearch);

		employeeService = new EmployeeService();
		EmployeeController ec = new EmployeeController(this, employeeService);
	}
	public JTable getTable() {
		return jtbEmployee;
	}
}
