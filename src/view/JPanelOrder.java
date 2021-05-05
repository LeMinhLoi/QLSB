package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class JPanelOrder extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelOrder() {
		setLayout(null);
		
		JLabel jlbDateChooser = new JLabel("Ng\u00E0y");
		jlbDateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbDateChooser.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbDateChooser.setBounds(55, 11, 46, 34);
		add(jlbDateChooser);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 11, 171, 34);
		add(dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("Gi\u1EDD thi \u0111\u1EA5u");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(292, 11, 125, 34);
		add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(432, 13, 165, 34);
		add(comboBox);
		
		JButton btnNewButton = new JButton("S\u00E2n 5A");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(55, 114, 147, 59);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("S\u00E2n 5B");
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setBounds(55, 194, 147, 59);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("S\u00E2n 5C");
		btnNewButton_2.setBackground(Color.ORANGE);
		btnNewButton_2.setBounds(55, 273, 147, 59);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("S\u00E2n 5D");
		btnNewButton_3.setBackground(Color.ORANGE);
		btnNewButton_3.setBounds(270, 114, 147, 59);
		add(btnNewButton_3);
		
		JButton btnSne = new JButton("S\u00E2n 5E");
		btnSne.setBackground(Color.ORANGE);
		btnSne.setBounds(270, 194, 147, 59);
		add(btnSne);
		
		JButton btnSnf = new JButton("S\u00E2n 5F");
		btnSnf.setBackground(Color.ORANGE);
		btnSnf.setBounds(270, 273, 147, 59);
		add(btnSnf);
		
		JButton btnNewButton_6 = new JButton("S\u00E2n 7A");
		btnNewButton_6.setBackground(Color.ORANGE);
		btnNewButton_6.setBounds(485, 114, 128, 218);
		add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("S\u00E2n 7B");
		btnNewButton_7.setBackground(Color.ORANGE);
		btnNewButton_7.setBounds(682, 114, 128, 218);
		add(btnNewButton_7);

	}
}
