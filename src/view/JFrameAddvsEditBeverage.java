package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BeverageBillDAO;
import dao.BeverageDAO;
import model.Beverage;
import service.BeverageService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameAddvsEditBeverage extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtMeasure;
	private JTextField txtOriginPrice;
	private JTextField txtPrice;
	private JTextField txtMount;
	private JPanelBeverage jpnBeverage;
	private BeverageService BeverageService;

	/**
	 * Create the frame.
	 */
	public JFrameAddvsEditBeverage(int ID, JPanelBeverage jpnBeverage) {
		initComponents();
		BeverageService = new BeverageService();
		this.jpnBeverage = jpnBeverage;
		SetUp(ID);
	}
	
	private void SetUp(int ID)
	{
		if(ID != 0)
		{
			Beverage b = BeverageService.checkID(ID);
			txtID.setText(String.valueOf(b.getIdBeverage()));
			txtName.setText(b.getNameBeverage().toString());
			txtMeasure.setText(b.getMeasure().toString());
			txtOriginPrice.setText(String.valueOf(b.getOriginalPrice()));
			txtPrice.setText(String.valueOf(b.getPrice()));
			txtMount.setText(String.valueOf(b.getMount()));
		}
		else txtID.setText(String.valueOf(BeverageDAO.nextId()));
	}
		
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblID.setBounds(10, 11, 51, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtID.setBounds(74, 9, 109, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(10, 38, 46, 16);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(74, 37, 109, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblMeasure = new JLabel("Đơn Vị");
		lblMeasure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMeasure.setBounds(10, 65, 51, 17);
		contentPane.add(lblMeasure);
		
		txtMeasure = new JTextField();
		txtMeasure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMeasure.setBounds(74, 63, 109, 20);
		contentPane.add(txtMeasure);
		txtMeasure.setColumns(10);
		
		JLabel lblOriginPrice = new JLabel("Giá Gốc");
		lblOriginPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOriginPrice.setBounds(235, 11, 51, 16);
		contentPane.add(lblOriginPrice);
		
		txtOriginPrice = new JTextField();
		txtOriginPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtOriginPrice.setBounds(296, 10, 109, 20);
		contentPane.add(txtOriginPrice);
		txtOriginPrice.setColumns(10);
		
		JLabel lblPrice = new JLabel("Giá");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrice.setBounds(235, 40, 51, 17);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPrice.setBounds(296, 37, 109, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblMount = new JLabel("Số Lượng");
		lblMount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMount.setBounds(235, 67, 63, 15);
		contentPane.add(lblMount);
		
		txtMount = new JTextField();
		txtMount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMount.setBounds(296, 64, 109, 20);
		contentPane.add(txtMount);
		txtMount.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Beverage b = new Beverage();
				b.setIdBeverage(Integer.parseInt(txtID.getText()));
				b.setNameBeverage(txtName.getText());
				b.setMeasure(txtMeasure.getText());
				b.setOriginalPrice(Integer.parseInt(txtOriginPrice.getText()));
				b.setPrice(Integer.parseInt(txtPrice.getText()));
				b.setMount(Integer.parseInt(txtMount.getText()));
				BeverageService.UpdateOrAdd(b);
				dispose();
				jpnBeverage.showBeverage("");
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOK.setBounds(94, 118, 89, 23);
		contentPane.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(235, 119, 89, 23);
		contentPane.add(btnCancel);
	}
}
