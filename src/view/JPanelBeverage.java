package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Beverage;
import service.BeverageService;
import service.EmployeeService;
import service.BeverageService;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JPanelBeverage extends JPanel implements ActionListener{
	
	private JTextField txtSearch;
	private JTable jtbBeverage;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnEdit;
	private JComboBox cbbSort;
	private JScrollPane scrollPane;
	private BeverageService BeverageService;
	
	public JPanelBeverage() {
		initComponents();
		BeverageService = new BeverageService();
		showBeverage("");
	}
	
	private void initComponents() {
		setLayout(null);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 73, 610, 357);
		add(scrollPane);
		
		jtbBeverage = new JTable();
		scrollPane.setViewportView(jtbBeverage);
		

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 203, 430);
		add(panel);
		panel.setLayout(null);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBackground(new Color(102, 204, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.setBounds(20, 208, 173, 45);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBackground(new Color(102, 204, 255));
		btnAdd.setBounds(20, 72, 173, 45);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		btnEdit = new JButton("Sửa");
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBackground(new Color(102, 204, 255));
		btnEdit.setBounds(20, 139, 173, 45);
		btnEdit.addActionListener(this);
		panel.add(btnEdit);
		
		cbbSort = new JComboBox();
		cbbSort.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String k = (txtSearch.getText().length() < 1) ? "" : txtSearch.getText();
				Object[][] data = BeverageService.Sort(k, cbbSort.getSelectedIndex());
				String col[] = {"STT", "ID", "Name", "Measure", "Origin Price", "Price", "Mount"};
				DefaultTableModel model = (DefaultTableModel) jtbBeverage.getModel();
		        model.setDataVector(data, col);
			}
		});
		cbbSort.setModel(new DefaultComboBoxModel(new String[] {"ID", "Số Lượng", "Giá Gốc", "Giá"}));
		cbbSort.setForeground(Color.DARK_GRAY);
		cbbSort.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbbSort.setBackground(new Color(102, 204, 255));
		cbbSort.setBounds(20, 277, 173, 33);
		panel.add(cbbSort);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(588, 27, 135, 20);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSearch.getText().length() < 1)
					showBeverage("");
				else showBeverage(txtSearch.getText());
			}
		});
		btnSearch.setBounds(741, 26, 89, 23);
		add(btnSearch);
		
		JLabel jlbSearch = new JLabel("Tìm kiếm");
		jlbSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbSearch.setBounds(519, 30, 59, 14);
		add(jlbSearch);
	}

		public void createFrame(int ID) 
		{
			
			JFrameAddvsEditBeverage addvsEditBeverage = new JFrameAddvsEditBeverage(ID ,this);
			addvsEditBeverage.setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("Xóa")) {
				int getID = Integer.parseInt(jtbBeverage.getModel().getValueAt(jtbBeverage.getSelectedRow(),1).toString());
				BeverageService.deleteBeverage(getID);
				showBeverage("");
			}else if(e.getActionCommand().equals("Thêm")) {
				createFrame(0);
			}else if(e.getActionCommand().equals("Sửa")) {
				createFrame(Integer.parseInt(jtbBeverage.getModel().getValueAt(jtbBeverage.getSelectedRow(),1).toString()));
			}

		}
		
	public void showBeverage(String name) {
		Object[][] data = BeverageService.showBeverages(name);
		String col[] = {"STT", "ID", "Name", "Measure", "Origin Price", "Price", "Mount"};
		DefaultTableModel model = (DefaultTableModel) jtbBeverage.getModel();
        model.setDataVector(data, col);
	}

}