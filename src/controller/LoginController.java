package controller;

import model.Employee;
import service.EmployeeService;
import view.Login;

public class LoginController {
	
	public static Employee storeEmployee=null;
	private EmployeeService employeeService;
	private Login login;
	
	public LoginController(Login frame) {
		login = frame;
		employeeService = new EmployeeService();
	}
}
