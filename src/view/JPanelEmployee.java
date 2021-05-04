package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JPanelEmployee extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelEmployee() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(202, 0, 628, 430);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 203, 430);
		add(panel);

	}
}
