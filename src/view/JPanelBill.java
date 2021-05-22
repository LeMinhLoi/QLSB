package view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.BillService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;

public class JPanelBill extends JPanel implements ActionListener{
	private JTable jtbBill;
	private JTextField txtIDEmp;
	private BillService BillService;

	/**
	 * Create the panel.
	 */
	public JPanelBill() {
		initComponents();
		BillService = new BillService();
		showBill(0);
	}
	
	private void initComponents() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 205, 411);
		add(panel);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(10, 37, 185, 48);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(10, 96, 185, 48);
		btnEdit.addActionListener(this);
		panel.add(btnEdit);
		
		JButton btn = new JButton("Xóa");
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBounds(10, 155, 185, 48);
		btn.addActionListener(this);
		panel.add(btn);
		
		JComboBox cbbSort = new JComboBox();
		cbbSort.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int k = (txtIDEmp.getText().length() < 1) ? 0 : Integer.parseInt(txtIDEmp.getText());
				Object[][] data = BillService.Sort(k, cbbSort.getSelectedIndex());
				String col[] = {"STT", "ID Bill", "Create Date", "Create Time", "Total", "ID Employee", "ID Order"};
				DefaultTableModel model = (DefaultTableModel) jtbBill.getModel();
		        model.setDataVector(data, col);
			}
		});
		cbbSort.setModel(new DefaultComboBoxModel(new String[] {"ID Bill", "Date Create", "Total", "ID Employee", "ID Order"}));
		cbbSort.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbbSort.setBounds(10, 250, 185, 48);
		panel.add(cbbSort);
		
		JButton btnDelbyDate = new JButton("Xóa Theo Ngày");
		btnDelbyDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelbyDate.setBounds(10, 309, 185, 48);
		panel.add(btnDelbyDate);
		
		JLabel lblNewLabel = new JLabel("    Sắp Xếp Theo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 214, 153, 25);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(233, 87, 547, 300);
		add(scrollPane);
		
		jtbBill = new JTable();
		jtbBill.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtbBill.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Bill", "Create Time", "Create Date", "Total", "ID Order", "ID Employee"
			}
		));
		scrollPane.setViewportView(jtbBill);
		
		JButton btnSearch = new JButton("Tìm Kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtIDEmp.getText().length() < 1)
					showBill(0);
				else showBill(Integer.parseInt(txtIDEmp.getText()));
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearch.setBounds(691, 53, 89, 23);
		add(btnSearch);
		
		txtIDEmp = new JTextField();
		txtIDEmp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIDEmp.setBounds(565, 56, 116, 20);
		add(txtIDEmp);
		txtIDEmp.setColumns(10);
	}
	
	public void createFrame(int ID) 
	{
		JFrameBill Frame = new JFrameBill(ID ,this);
		Frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Xóa")) {
			int getID = Integer.parseInt(jtbBill.getModel().getValueAt(jtbBill.getSelectedRow(),1).toString());
			BillService.deleteBill(getID);
			showBill(0);
		}else if(e.getActionCommand().equals("Thêm")) {
			createFrame(0222);
			//createFrame(BillService.NextID());
		}else if(e.getActionCommand().equals("Sửa")) {
			createFrame(Integer.parseInt(jtbBill.getModel().getValueAt(jtbBill.getSelectedRow(),1).toString()));
		}
	}
	public void showBill(int IDEmpl) {
		Object[][] data = BillService.showBills(IDEmpl);
		String col[] = {"STT", "ID Bill", "Create Date", "Create Time", "Total", "ID Employee", "ID Order"};
		DefaultTableModel model = (DefaultTableModel) jtbBill.getModel();
        model.setDataVector(data, col);
	}
}
