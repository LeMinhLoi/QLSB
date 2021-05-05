package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class JPanelCustomer extends JPanel {
	private JTable table;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public JPanelCustomer() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 205, 411);
		add(panel);
		panel.setLayout(null);
		
		JButton btnEdit = new JButton("S\u1EEDa");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(10, 37, 185, 48);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Xo\u00E1");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(10, 96, 185, 48);
		panel.add(btnDelete);
		
		JButton btnAdd = new JButton("Th\u00EAm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(10, 155, 185, 48);
		panel.add(btnAdd);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 214, 185, 48);
		panel.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 66, 568, 316);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(564, 11, 46, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(625, 8, 96, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("T\u00ECm ki\u1EBFm");
		btnNewButton.setBounds(724, 7, 83, 23);
		add(btnNewButton);

	}
}
