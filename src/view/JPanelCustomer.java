package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import entity.Customer;
import service.CustomerService;
import utility.SortColumnTable;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelCustomer extends JPanel {
	
	private JTextField textField;
	private JTable jtbCustomer;
	private JScrollPane scrollPane;	
	private JButton btnAdd;
	private JButton btnEdit;

	public JPanelCustomer() {
		
		initComponents();
		showCustomer();
		
		SortColumnTable columnTable = new SortColumnTable(jtbCustomer);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textField.getText();
                if (text.trim().length() == 0) {
                	columnTable.getRowSorter().setRowFilter(null);
                } else {
                	columnTable.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textField.getText();
                if (text.trim().length() == 0) {
                	columnTable.getRowSorter().setRowFilter(null);
                } else {
                	columnTable.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
//		ButtonListener buttonListener = new ButtonListener();
//		btnAdd.addActionListener(buttonListener);
//		btnEdit.addActionListener(buttonListener);
//		btnDelete.addActionListener(buttonListener);
	}
	private void initComponents() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 205, 411);
		add(panel);
		panel.setLayout(null);
		

		
		JLabel jlbPhone = new JLabel("Phone");
		jlbPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbPhone.setBounds(519, 30, 59, 14);
		add(jlbPhone);
		
		textField = new JTextField();
		textField.setBounds(588, 27, 135, 20);
		add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 73, 610, 357);
		add(scrollPane);
		
		jtbCustomer = new JTable();
		scrollPane.setViewportView(jtbCustomer);
		
		btnEdit = new JButton("S???a");
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBackground(new Color(102, 204, 255));
		btnEdit.setBounds(20, 139, 173, 45);
		panel.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtbCustomer.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getThis(), "B???n ch??a ch???n h??ng ????? s???a", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					createFrame(true);
				}
			}
		});
		
		btnAdd = new JButton("Th??m");
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBackground(new Color(102, 204, 255));
		btnAdd.setBounds(20, 72, 173, 45);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFrame(false);
			}
		});

	}
	JPanelCustomer getThis() {
		return this;
	}
	public void showCustomer() {
		Object[][] data = CustomerService.getInstance().showCustomers();
		String col[] = {"ID Customer","Name","Phone"};
		DefaultTableModel model = (DefaultTableModel) jtbCustomer.getModel();
        model.setDataVector(data, col);
	}
	public void createFrame(boolean b) {
		if( b == true) {
			int getID = Integer.parseInt(jtbCustomer.getModel().getValueAt(jtbCustomer.getSelectedRow(),0).toString());
			Customer customer = CustomerService.getInstance().checkID(getID);
			JFrameAddvsEditCustomer addvsEditCustomer = new JFrameAddvsEditCustomer(customer,this);
			addvsEditCustomer.setVisible(true);
			System.out.println("???? t???o frame s???a");
		}else if(b == false){
			int nextID = CustomerService.getInstance().getNextIdCustomer();
			JFrameAddvsEditCustomer addvsEditCustomer = new JFrameAddvsEditCustomer(nextID,this);
			addvsEditCustomer.setVisible(true);
			System.out.println("???? t???o frame th??m");
		}
	}
//	private class ButtonListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			if(e.getActionCommand().equals("Xo??")) {//n???u nh???n v??o n??t xo?? th?? ...
//				int getID = Integer.parseInt(jtbCustomer_1.getModel().getValueAt(jtbCustomer_1.getSelectedRow(),0).toString());
//				customerService.deleteCustomer(getID);
//				showCustomer();
//				System.out.println("xoa");
//			}else if(e.getActionCommand().equals("Th??m")) {
//				createFrame(false);
//				System.out.println("them");
//			}else if(e.getActionCommand().equals("S\u1EEDa ")) {
//				createFrame(true);
//				System.out.println("sua");
//			}
//
//		}
//		
//	}
	
}