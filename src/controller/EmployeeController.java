package controller;

import javax.swing.table.DefaultTableModel;

import service.EmployeeService;
import view.JPanelEmployee;

public class EmployeeController {
	
	private JPanelEmployee jpnEmployee;
	private EmployeeService employeeService;
	
	public EmployeeController(JPanelEmployee jpnEmployee, EmployeeService employeeService) {
		this.jpnEmployee = jpnEmployee;
		this.employeeService = employeeService;
		showEmployee();
	}
	
	private void showEmployee() {
		Object[][] data = employeeService.showEmployees();
		String col[] = {"STT","ID","Name","Old","Gender","Address","Phone","IdentityNumber","Password","Role"};
		DefaultTableModel model = (DefaultTableModel) jpnEmployee.getTable().getModel();
        model.setDataVector(data, col);
	}
	
}
