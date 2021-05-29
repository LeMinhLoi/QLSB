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

import service.BeverageBillService;
import service.BillService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Button;
import com.toedter.calendar.JDayChooser;

import model.Order;

import java.awt.ScrollPane;

public class JPanelBill extends JPanel implements ActionListener{
	private JTable jtbBill;
	private JTextField txtIDEmp;
	private BillService BillService;
	private JDateChooser dateChooser;
	private static DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	private JButton btnDelbyDate;
	private JDateChooser dateChoDel1;
	private JDateChooser dateChoDel2;
	private BeverageBillService bilBeverageBillService;

	/**
	 * Create the panel.
	 */
	public JPanelBill() {
		initComponents();
		BillService = new BillService();
		bilBeverageBillService = new BeverageBillService();
		showBill(0, dformat.format(dateChooser.getDate()));
	}
	
	public String getDateChooser() {
		return dformat.format(dateChooser.getDate());
	}
	
	private void initComponents() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 205, 422);
		add(panel);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(10, 76, 185, 48);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(10, 135, 185, 48);
		btnEdit.addActionListener(this);
		panel.add(btnEdit);
		
		JButton btn = new JButton("Xóa");
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBounds(10, 194, 185, 48);
		btn.addActionListener(this);
		panel.add(btn);
		
		btnDelbyDate = new JButton("Xóa Theo Ngày");
		btnDelbyDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillService.DelByDate(dformat.format(dateChoDel1.getDate())
						, dformat.format(dateChoDel2.getDate()));
			}
		});
		btnDelbyDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelbyDate.setBounds(10, 253, 185, 48);
		panel.add(btnDelbyDate);
		
		JLabel lblBillDate = new JLabel("Bill Ngày");
		lblBillDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBillDate.setBounds(10, 11, 136, 27);
		panel.add(lblBillDate);
		
		dateChooser = new JDateChooser();
		dateChooser.getSpinner().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int i = (txtIDEmp.getText().length() < 1) ? 0 :
					Integer.parseInt(txtIDEmp.getText());
				showBill(i, dformat.format(dateChooser.getDate()));
			}
		});
		dateChooser.setBounds(10, 38, 185, 27);
		panel.add(dateChooser);
		
		JPanel pnlDel = new JPanel();
		pnlDel.setBounds(10, 312, 185, 99);
		panel.add(pnlDel);
		pnlDel.setLayout(null);
		
		dateChoDel1 = new JDateChooser();
		dateChoDel1.setBounds(10, 26, 134, 20);
		pnlDel.add(dateChoDel1);
		
		JLabel lblNewLabel = new JLabel("Từ ngày");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 134, 14);
		pnlDel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Đến ngày");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 48, 134, 14);
		pnlDel.add(lblNewLabel_1);
		
		dateChoDel2 = new JDateChooser();
		dateChoDel2.setBounds(10, 68, 134, 20);
		pnlDel.add(dateChoDel2);
		
		JButton btnShowAll = new JButton("ShowAll");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = (txtIDEmp.getText().length() < 1) ? 0 :
					Integer.parseInt(txtIDEmp.getText());
				showBill(i, "");
			}
		});
		btnShowAll.setBounds(106, 11, 89, 23);
		panel.add(btnShowAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(233, 75, 547, 312);
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
		
		txtIDEmp = new JTextField();
		txtIDEmp.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
		    	 if(txtIDEmp.getText().length() < 1)
						showBill(0, dformat.format(dateChooser.getDate()));
					else showBill(Integer.parseInt(txtIDEmp.getText()),
							dformat.format(dateChooser.getDate()));
			  }
			});
		txtIDEmp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIDEmp.setBounds(664, 44, 116, 20);
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
			int i = BillService.checkID(getID).getIdOrder();
			bilBeverageBillService.deleteBillBeveOfBill(
			bilBeverageBillService.getAllBeverageBill(getID));
			BillService.deleteBill(getID);
			BillService.DelOrder(i);
			showBill(0, "");
		}else if(e.getActionCommand().equals("Thêm")) {
			createFrame(111);
			//createFrame(BillService.NextID());
		}else if(e.getActionCommand().equals("Sửa")) {
			createFrame(Integer.parseInt(jtbBill.getModel().getValueAt(jtbBill.getSelectedRow(),1).toString()));
		}
	}
	public void showBill(int IDEmpl, String d) {
		Object[][] data = BillService.showBills(IDEmpl, d);
		String col[] = {"STT", "ID Bill", "Create Date", "Create Time", "Total", "ID Employee", "ID Order"};
		DefaultTableModel model = (DefaultTableModel) jtbBill.getModel();
        model.setDataVector(data, col);
	}
}
