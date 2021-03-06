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

import entity.Employee;
import service.CustomerService;
import service.EmployeeService;
import utility.SortColumnTable;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelEmployee extends JPanel {
	private JTextField textField;
	private JTable jtbEmployee;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnEdit;
	
	/**
	 * Create the panel.
	 */
	public JPanelEmployee() {
		
		initComponents();
		showEmployee();
		
		ButtonListener buttonListener = new ButtonListener();
		btnAdd.addActionListener(buttonListener);
		btnEdit.addActionListener(buttonListener);
		btnDelete.addActionListener(buttonListener);
		
		SortColumnTable columnTable = new SortColumnTable(jtbEmployee);
		
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
		
		
	}
	private void initComponents() {
		setLayout(null);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 73, 610, 357);
		add(scrollPane);
		
		jtbEmployee = new JTable();
		scrollPane.setViewportView(jtbEmployee);
		

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 203, 430);
		add(panel);
		panel.setLayout(null);
		
		btnDelete = new JButton("Xo??");
		btnDelete.setBackground(new Color(102, 204, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.setBounds(20, 208, 173, 45);
		panel.add(btnDelete);
		
		btnAdd = new JButton("Th??m");
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBackground(new Color(102, 204, 255));
		btnAdd.setBounds(20, 72, 173, 45);
		panel.add(btnAdd);
		
		btnEdit = new JButton("S???a/Xem");
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBackground(new Color(102, 204, 255));
		btnEdit.setBounds(20, 139, 173, 45);
		panel.add(btnEdit);
		
		textField = new JTextField();
		textField.setBounds(588, 27, 135, 20);
		add(textField);
		textField.setColumns(10);
		
		
		JLabel jlbSearch = new JLabel("T??m ki???m");
		jlbSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbSearch.setBounds(519, 30, 59, 14);
		add(jlbSearch);
		
		
	}
	JPanelEmployee getThis() {
		return this;
	}
	
	public void createFrame(boolean b) {
		if( b == true) {
			int getID = Integer.parseInt(jtbEmployee.getModel().getValueAt(jtbEmployee.getSelectedRow(),0).toString());
			Employee employee = EmployeeService.getInstance().checkIDEmployee(getID);
			JFrameAddvsEditEmployee addvsEditEmployee = new JFrameAddvsEditEmployee(employee,this);
			addvsEditEmployee.setVisible(true);
			System.out.println("???? t???o frame s???a");
		}else if(b == false){
			int nextID = EmployeeService.getInstance().getNextIdEmployee();
			JFrameAddvsEditEmployee addvsEditEmployee = new JFrameAddvsEditEmployee(nextID,this);
			addvsEditEmployee.setVisible(true);
			System.out.println("???? t???o frame th??m");
		}
	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("Xo??")) {//n???u nh???n v??o n??t xo?? th?? ...
				if(jtbEmployee.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getThis(), "B???n ch??a ch???n h??ng ????? xo??", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					int getID = Integer.parseInt(jtbEmployee.getModel().getValueAt(jtbEmployee.getSelectedRow(),0).toString());
					if(getID == EmployeeService.getInstance().getStoreUser().getIdCustomer()) {
						JOptionPane.showMessageDialog(getThis(), "B???n kh??ng th??? xo?? d??? li???u c???a ch??nh m??nh", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else {
						if(EmployeeService.getInstance().checkIDEmployee(getID).getRole() == 1) {
							JOptionPane.showMessageDialog(getThis(), "B???n kh??ng th??? xo?? d??? li???u c???a admin. N???u mu???n xo??, h??y s???a admin th??nh nh??n vi??n.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}else {
							EmployeeService.getInstance().deleteEmployee(getID);
							JOptionPane.showMessageDialog(getThis(),"Xo?? th??nh c??ng");
							showEmployee();
						}			
					}				
				}
			}else if(e.getActionCommand().equals("Th??m")) {
				createFrame(false);
			}else if(e.getActionCommand().equals("S???a/Xem")) {
				if(jtbEmployee.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getThis(), "B???n ch??a ch???n h??ng ????? s???a d??? li???u", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					createFrame(true);
				}
			}

		}
		
	}
	public void showEmployee() {
		Object[][] data = EmployeeService.getInstance().showEmployees();
		String col[] = {"ID","Name","Old","Gender","Address","Phone","IdentityNumber","Password","Role"};
		DefaultTableModel model = (DefaultTableModel) jtbEmployee.getModel();
        model.setDataVector(data, col);
        
	}
}
