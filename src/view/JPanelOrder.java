package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import model.Button_Yard;
import model.Order;
import model.Time;
import service.OrderService;
import service.TimeService;
import java.sql.Date;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JPanelOrder extends JPanel {

	private JDateChooser dateChooser;
	private JComboBox cbbChooseTime;
	private JButton btnSan5A;
	private JButton btnSan5B;
	private JButton btnSan5C;
	private JButton btnSan5D;
	private JButton btnSan5E;
	private JButton btnSan5F;
	private JButton btnSan7A;
	private JButton btnSan7B;
	private JButton btnShow;
	
	private List<Button_Yard> listButtonYard = null;
	private List<Order> listOrder = null;
	private OrderService orderService = null;
	private TimeService timeService = null;
	private List<Time> listTime = null;
	
	public JPanelOrder() {
		initComponents();
		listButtonYard = new ArrayList<Button_Yard>();
		listButtonYard.add(new Button_Yard(1,btnSan5A));
		listButtonYard.add(new Button_Yard(2,btnSan5B));
		listButtonYard.add(new Button_Yard(3,btnSan5C));
		listButtonYard.add(new Button_Yard(4,btnSan5D));
		listButtonYard.add(new Button_Yard(5,btnSan5E));
		listButtonYard.add(new Button_Yard(6,btnSan5F));
		listButtonYard.add(new Button_Yard(7,btnSan7A));
		listButtonYard.add(new Button_Yard(8,btnSan7B));
		timeService = new TimeService();
		listTime = timeService.getAllTime();
		cbbChooseTime.addItem("Hãy chọn giờ");
		for(Time item : listTime) {
			cbbChooseTime.addItem(item);
		}
		orderService = new OrderService();
		
		ButtonListener buttonListener = new ButtonListener();
		btnShow.addActionListener(buttonListener);
	}
	private void initComponents() {
		setLayout(null);
		
		JLabel jlbDateChooser = new JLabel("Ng\u00E0y");
		jlbDateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbDateChooser.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbDateChooser.setBounds(55, 11, 46, 34);
		add(jlbDateChooser);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 11, 171, 34);
		add(dateChooser);
		
		JLabel jlbChooseTime = new JLabel("Gi\u1EDD thi \u0111\u1EA5u");
		jlbChooseTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbChooseTime.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbChooseTime.setBounds(292, 11, 125, 34);
		add(jlbChooseTime);
		
		cbbChooseTime = new JComboBox();
		cbbChooseTime.setBounds(432, 13, 165, 34);
		add(cbbChooseTime);
		
		btnSan5A = new JButton("S\u00E2n 5A");
		btnSan5A.setBackground(Color.ORANGE);
		btnSan5A.setBounds(55, 114, 147, 59);
		add(btnSan5A);
		
		btnSan5B = new JButton("S\u00E2n 5B");
		btnSan5B.setBackground(Color.ORANGE);
		btnSan5B.setBounds(55, 194, 147, 59);
		add(btnSan5B);
		
		btnSan5C = new JButton("S\u00E2n 5C");
		btnSan5C.setBackground(Color.ORANGE);
		btnSan5C.setBounds(55, 273, 147, 59);
		add(btnSan5C);
		
		btnSan5D = new JButton("S\u00E2n 5D");
		btnSan5D.setBackground(Color.ORANGE);
		btnSan5D.setBounds(270, 114, 147, 59);
		add(btnSan5D);
		
		btnSan5E = new JButton("S\u00E2n 5E");
		btnSan5E.setBackground(Color.ORANGE);
		btnSan5E.setBounds(270, 194, 147, 59);
		add(btnSan5E);
		
		btnSan5F = new JButton("S\u00E2n 5F");
		btnSan5F.setBackground(Color.ORANGE);
		btnSan5F.setBounds(270, 273, 147, 59);
		add(btnSan5F);
		
		btnSan7A = new JButton("S\u00E2n 7A");
		btnSan7A.setBackground(Color.ORANGE);
		btnSan7A.setBounds(485, 114, 128, 218);
		add(btnSan7A);
		
		btnSan7B = new JButton("S\u00E2n 7B");
		btnSan7B.setBackground(Color.ORANGE);
		btnSan7B.setBounds(682, 114, 128, 218);
		add(btnSan7B);
		
		btnShow = new JButton("Hi\u1EC3n th\u1ECB");
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnShow.setBounds(626, 11, 103, 34);
		add(btnShow);

	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("Hiển thị")) {
				System.out.println("Clicked button show");
				listOrder = orderService.getOrderByDateTime(dateChooser.getDate(), ((Time)cbbChooseTime.getSelectedItem()).getIdTime());
				for(int i = 0 ; i < listOrder.size() ; i++) {
					System.out.println(listOrder.get(i).toString());
				}
				System.out.println("pass");
				for(int i = 0 ; i < listButton.size() ; i++) {
					if()
				}
			}
		}
		
	}
}
