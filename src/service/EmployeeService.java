package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class EmployeeService {
	
	private static Employee employee = null;
	private EmployeeDAO employeeDAO;
	private List<Employee> listEmployee;
	
	public EmployeeService() {
		employeeDAO = new EmployeeDAO();
	}
	public List<Employee> getAllEmployee() {
		return employeeDAO.getAllEmployees();
	}
	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}
	public Employee insertEmployee(Employee employee) {
		return employeeDAO.insertEmployee(employee);
	}
	public Object[][] showEmployees(){
		listEmployee = employeeDAO.getAllEmployees();
		Object[][] result = new Object[listEmployee.size()][10];
		for(int i = 0 ; i < listEmployee.size() ; i++) {
			result[i][0] = i + 1;
			result[i][1] = listEmployee.get(i).getIdCustomer();
			result[i][2] = listEmployee.get(i).getNameCustomer().toString();
			result[i][3] = listEmployee.get(i).getOld();
			if(listEmployee.get(i).getGender() == 1) {
				result[i][4] = "Nam";
			}else {
				result[i][4] = "Nữ";
			}
			result[i][5] = listEmployee.get(i).getAddress().toString();
			result[i][6] = listEmployee.get(i).getPhoneCustomer().toString();
			result[i][7] = listEmployee.get(i).getIdentityNumber().toString();
			result[i][8] = listEmployee.get(i).getPassword().toString();
			if(listEmployee.get(i).getRole() == 1) {
				result[i][9] = "Admin";
			}else {
				result[i][9] = "Nhân viên";
			}
		}
		return result;
	}
	public void deleteEmployee(int idEmmployee) {
		employeeDAO.deleteEmployee(idEmmployee);
	}
	public int getNextIdEmployee() {
		return employeeDAO.nextId();
	}
	public Employee checkID(int Id) {
		listEmployee = employeeDAO.getAllEmployees();
		for(Employee item : listEmployee) {
			if(item.getIdCustomer() == Id) return item;
		}
		return null;
	}
	public static void main(String[] args) {
		
	}
}
