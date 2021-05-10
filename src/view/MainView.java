package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import model.CategoryPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SlideController;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class MainView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1086, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 231, 507);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panelOrder = new JPanel();
		panelOrder.setBackground(new Color(50, 205, 50));
		panelOrder.setBounds(10, 77, 211, 51);
		panel.add(panelOrder);
		panelOrder.setLayout(null);
		
		JLabel jlbOrder = new JLabel("Qu\u1EA3n l\u00FD \u0111\u1EB7t s\u00E2n");
		jlbOrder.setForeground(Color.WHITE);
		jlbOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbOrder.setHorizontalAlignment(SwingConstants.CENTER);
		jlbOrder.setBounds(51, 0, 160, 51);
		panelOrder.add(jlbOrder);
		
		JLabel jlbIconOrder = new JLabel("New label");
		jlbIconOrder.setIcon(new ImageIcon("C:\\Users\\lenth\\eclipse-workspace\\QLSB\\images\\icon-order.png"));
		jlbIconOrder.setBounds(0, 0, 51, 51);
		panelOrder.add(jlbIconOrder);
		
		JPanel panelBill = new JPanel();
		panelBill.setBackground(new Color(50, 205, 50));
		panelBill.setBounds(10, 139, 211, 51);
		panel.add(panelBill);
		panelBill.setLayout(null);
		
		JLabel jlbBill = new JLabel("Qu\u1EA3n l\u00FD ho\u00E1 \u0111\u01A1n");
		jlbBill.setForeground(Color.WHITE);
		jlbBill.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbBill.setBounds(51, 0, 160, 51);
		panelBill.add(jlbBill);
		
		JLabel jlbIconBill = new JLabel("New label");
		jlbIconBill.setIcon(new ImageIcon("C:\\Users\\lenth\\eclipse-workspace\\QLSB\\images\\icon-bill.png"));
		jlbIconBill.setBounds(0, 0, 51, 51);
		panelBill.add(jlbIconBill);
		
		JPanel panelBeverage = new JPanel();
		panelBeverage.setBackground(new Color(50, 205, 50));
		panelBeverage.setBounds(10, 201, 211, 51);
		panel.add(panelBeverage);
		panelBeverage.setLayout(null);
		
		JLabel jlbBeverage = new JLabel("Qu\u1EA3n l\u00FD m\u1EB7t h\u00E0ng");
		jlbBeverage.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbBeverage.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBeverage.setForeground(Color.WHITE);
		jlbBeverage.setBounds(51, 0, 160, 51);
		panelBeverage.add(jlbBeverage);
		
		JLabel jlbIconBeverage = new JLabel("New label");
		jlbIconBeverage.setIcon(new ImageIcon("C:\\Users\\lenth\\eclipse-workspace\\QLSB\\images\\icon-beverage.png"));
		jlbIconBeverage.setBounds(0, 0, 51, 51);
		panelBeverage.add(jlbIconBeverage);
		
		JPanel panelCustomer = new JPanel();
		panelCustomer.setBackground(new Color(50, 205, 50));
		panelCustomer.setBounds(10, 263, 211, 51);
		panel.add(panelCustomer);
		panelCustomer.setLayout(null);
		
		JLabel jlbCustomer = new JLabel("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		jlbCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCustomer.setForeground(Color.WHITE);
		jlbCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbCustomer.setBounds(51, 0, 160, 51);
		panelCustomer.add(jlbCustomer);
		
		JLabel jlbIconCustomer = new JLabel("New label");
		jlbIconCustomer.setIcon(new ImageIcon("C:\\Users\\lenth\\eclipse-workspace\\QLSB\\images\\icon-customer.png"));
		jlbIconCustomer.setBounds(0, 0, 51, 51);
		panelCustomer.add(jlbIconCustomer);
		
		JPanel panelEmployee = new JPanel();
		panelEmployee.setBackground(new Color(50, 205, 50));
		panelEmployee.setBounds(10, 325, 211, 51);
		panel.add(panelEmployee);
		panelEmployee.setLayout(null);
		
		JLabel jlbEmployee = new JLabel("Qu\u1EA3n l\u00FD nh\u00E2n vi\u00EAn");
		jlbEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		jlbEmployee.setForeground(Color.WHITE);
		jlbEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbEmployee.setBounds(51, 0, 160, 51);
		panelEmployee.add(jlbEmployee);
		
		JLabel jlbIconEmployee = new JLabel("New label");
		jlbIconEmployee.setIcon(new ImageIcon("C:\\Users\\lenth\\eclipse-workspace\\QLSB\\images\\icon-nhan-vien.png"));
		jlbIconEmployee.setBounds(0, 0, 51, 51);
		panelEmployee.add(jlbIconEmployee);
		
		JPanel panelYard = new JPanel();
		panelYard.setBackground(new Color(50, 205, 50));
		panelYard.setBounds(10, 387, 211, 51);
		panel.add(panelYard);
		panelYard.setLayout(null);
		
		JLabel jlbYard = new JLabel("Qu\u1EA3n l\u00FD s\u00E2n");
		jlbYard.setForeground(Color.WHITE);
		jlbYard.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbYard.setHorizontalAlignment(SwingConstants.CENTER);
		jlbYard.setBounds(51, 0, 160, 51);
		panelYard.add(jlbYard);
		
		JLabel JlbIconYard = new JLabel("New label");
		JlbIconYard.setIcon(new ImageIcon("C:\\Users\\lenth\\eclipse-workspace\\QLSB\\images\\icon-yard.jpg"));
		JlbIconYard.setBounds(0, 0, 51, 51);
		panelYard.add(JlbIconYard);
		
		JPanel panelSB = new JPanel();
		panelSB.setBackground(Color.RED);
		panelSB.setBounds(0, 0, 231, 66);
		panel.add(panelSB);
		panelSB.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("S\u00E2n b\u00F3ng TLTD");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 211, 44);
		panelSB.add(lblNewLabel);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(230, 0, 840, 66);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JPanel pnRoot = new JPanel();
		pnRoot.setBounds(230, 66, 840, 441);
		contentPane.add(pnRoot);
		
		
		
		
		List<CategoryPanel> list = new ArrayList<>();
		list.add(new CategoryPanel("Order",jlbOrder,panelOrder));
		list.add(new CategoryPanel("Employee",jlbEmployee,panelEmployee));
		list.add(new CategoryPanel("Customer",jlbCustomer,panelCustomer));
		SlideController sc = new SlideController(pnRoot);
		sc.setView(panelOrder, jlbOrder);
		sc.setEvent(list);
	}
}
