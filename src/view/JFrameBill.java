package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import com.toedter.components.JSpinField;

import dao.OrderDAO;
import model.Beverage;
import model.BeverageBill;
import model.Bill;
import model.Order;
import service.BeverageBillService;
import service.BeverageService;
import service.BillService;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.SpinnerNumberModel;

public class JFrameBill extends JFrame implements ActionListener{

	private JPanel contentPane;
	private static JTextField txtIDBill;
	private JTable table;
	private static JTextField txtTotal;
	private JPanelBill jPanelBill;
	private static BillService billService;
	private static JComboBox cbbIDOrder;
	private static JDateChooser dateChooser;
	private static BeverageBillService billBeverageService;
	private static BeverageService beverageService;
	private static int ID;
	private JTextField txtIDEmpl;
	private JButton btnExit;
	private JButton btnOK;
	private JPanel pnlAdd;
	private JButton btnAdd;
	private static JComboBox cbbBeve;
	private JButton btnOKAdd;
	private JButton btnExitAdd;
	private static List<BeverageBill> lBeveBill;
	private static List<BeverageBill> lBeveBillAdd;
	private static List<BeverageBill> lBeveBillDel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameBill frame = new JFrameBill(0, new JPanelBill());
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
	public JFrameBill(int iID, JPanelBill jPanelBill) {
		initComponents();
		billService = new BillService();
		this.jPanelBill = jPanelBill;
		billBeverageService = new BeverageBillService();
		beverageService = new BeverageService();
		ID = iID;
		lBeveBill = (billService.Check(ID)) ? new ArrayList<>()
				: billBeverageService.getAllBeverageBill(ID);
		lBeveBillAdd = new ArrayList<>();
		lBeveBillDel = new ArrayList<>();
		SetCBB();
		cbbIDOrder.setSelectedIndex(0);
		SetUp();
		showBeveBill(ID, null);
		pnlAdd.setVisible(false);
	}
	
	private static void SetCBB()
	{
		cbbIDOrder.addItem("");
		for(Order i : billService.GetCBB())
			cbbIDOrder.addItem(i);
		for(Beverage i : beverageService.getAllBeverage())
			cbbBeve.addItem(i);
	}
	
	private static void SetUp()
	{
		if(!billService.Check(ID))
		{
			Bill b = billService.checkID(ID);
			txtIDBill.setText(String.valueOf(ID));
			dateChooser.setDate(b.getCreateDate());
			if(b.getIdOrder() != 0)
			{
				cbbIDOrder.addItem(billService.GetSan(b.getIdOrder()));
				cbbIDOrder.setSelectedIndex(billService.GetCBB().size() + 1);
			}
			txtTotal.setText(String.valueOf(billService.ToTal(lBeveBill, b.getIdOrder())));
		}
		else txtIDBill.setText(String.valueOf(ID));
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID Bill");
		lblNewLabel.setBounds(10, 10, 45, 13);
		contentPane.add(lblNewLabel);
		
		txtIDBill = new JTextField();
		txtIDBill.setEnabled(false);
		txtIDBill.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIDBill.setBounds(83, 7, 113, 19);
		contentPane.add(txtIDBill);
		txtIDBill.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(83, 54, 113, 19);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("Create Date");
		lblNewLabel_1.setBounds(10, 58, 86, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID Order");
		lblNewLabel_2.setBounds(225, 10, 64, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ID Employee");
		lblNewLabel_3.setBounds(225, 54, 76, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Beverage Bill");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(22, 104, 113, 30);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 144, 517, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T\u00EAn m\u1EB7t h\u00E0ng", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Th\u00E0nh ti\u1EC1n"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_5 = new JLabel("Total");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(32, 399, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(100, 396, 142, 19);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		cbbIDOrder = new JComboBox();
		cbbIDOrder.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int temp =(cbbIDOrder.getSelectedIndex() == 0) ? 0 
						: ((Order)cbbIDOrder.getSelectedItem()).getIdOrder();
				txtTotal.setText(String.valueOf(billService.ToTal(lBeveBill, temp)));
			}
		});
		cbbIDOrder.setBounds(299, 5, 142, 22);
		contentPane.add(cbbIDOrder);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bill newb = (!billService.Check(ID)) ? billService.checkID(ID) : new Bill();
				newb.setIdBill(Integer.parseInt(txtIDBill.getText()));
				DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
				newb.setCreateDate(Date.valueOf(dformat.format(dateChooser.getDate())));
				if(billService.Check(ID)) newb.setCreateTime(Time.valueOf(LocalTime.now()));
				newb.setTotal(Integer.parseInt(txtTotal.getText()));
				newb.setIdEmployee(Integer.parseInt(txtIDEmpl.getText()));
				if(cbbIDOrder.getSelectedItem() == "") newb.setIdOrder(0);
				else newb.setIdOrder(((Order)cbbIDOrder.getSelectedItem()).getIdOrder());
				billService.UpdateOrAdd(newb);
				for(BeverageBill w : lBeveBillAdd)
					billBeverageService.Add(w);
				if(!lBeveBillDel.isEmpty())
					for(BeverageBill i : lBeveBillDel)
						billBeverageService.delet(i.getIdBeveBill(), i.getIdBeve());
				dispose();
				jPanelBill.showBill(0, jPanelBill.getDateChooser());
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOK.setBounds(225, 434, 89, 23);
		contentPane.add(btnOK);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(360, 434, 89, 23);
		contentPane.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBounds(559, 144, 113, 223);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(10, 11, 93, 35);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		JButton btnDel = new JButton("Xóa");
		btnDel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDel.setBounds(10, 57, 93, 35);
		btnDel.addActionListener(this);
		panel.add(btnDel);
		
		JLabel lblNewLabel_6 = new JLabel("Sắp Xếp Theo");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 103, 93, 35);
		panel.add(lblNewLabel_6);
		
		txtIDEmpl = new JTextField();
		txtIDEmpl.setBounds(299, 53, 142, 20);
		contentPane.add(txtIDEmpl);
		txtIDEmpl.setColumns(10);
		
		pnlAdd = new JPanel();
		pnlAdd.setBounds(471, 10, 220, 94);
		contentPane.add(pnlAdd);
		pnlAdd.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Thêm Mặt Hàng");
		lblNewLabel_7.setBounds(0, 0, 113, 19);
		pnlAdd.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cbbBeve = new JComboBox();
		cbbBeve.setBounds(10, 30, 143, 22);
		pnlAdd.add(cbbBeve);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(163, 31, 47, 20);
		pnlAdd.add(spinner);
		
		btnOKAdd = new JButton("OK");
		btnOKAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOKAdd.setBounds(20, 63, 79, 23);
		btnOKAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeverageBill b = new BeverageBill();
				b.setIdBeveBill(ID);
				b.setIdBeve(((Beverage)cbbBeve.getSelectedItem()).getIdBeverage());
				b.setMountBeve(Integer.valueOf(spinner.getValue().toString()));
				if(Integer.valueOf(spinner.getValue().toString()) > 0)	
					{
						BeverageBillService.AddList(b, lBeveBill);
						lBeveBillAdd.add(b);
					}
				cbbBeve.setSelectedIndex(0);
				spinner.setValue(0);
				showBeveBill(ID, lBeveBill);
			}
		});
		pnlAdd.add(btnOKAdd);
		
		btnExitAdd = new JButton("Exit");
		btnExitAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExitAdd.setBounds(120, 63, 79, 23);
		btnExitAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAdd.setVisible(false);
				}
		});
		pnlAdd.add(btnExitAdd);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Xóa")) {
			String name = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
			billBeverageService.deleteList(ID, billBeverageService.ReID(name), lBeveBill);
			if(!lBeveBillAdd.isEmpty())
				billBeverageService.deleteList(ID, billBeverageService.ReID(name), lBeveBillAdd);
			lBeveBillDel.add(billBeverageService.checkID(ID, billBeverageService.ReID(name)));
			showBeveBill(ID, lBeveBill);
		}else if(e.getActionCommand().equals("Thêm")) {
			pnlAdd.setVisible(true);;
		}
	}
	
	public void showBeveBill(int ID, List<BeverageBill> l) {
		Object[][] data = (l == null) ? billBeverageService.showBeverageBills(ID)
				: billBeverageService.showBeverageBills(ID, l);
		String col[] = {"STT", "Tên Mặt Hàng", "Số Lượng", "Đơn Giá", "Thành Tiền"};
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int temp =(cbbIDOrder.getSelectedIndex() == 0) ? 0 
				: ((Order)cbbIDOrder.getSelectedItem()).getIdOrder();
    	txtTotal.setText(String.valueOf(billService.ToTal(lBeveBill, temp)));
        model.setDataVector(data, col);
	}
}
