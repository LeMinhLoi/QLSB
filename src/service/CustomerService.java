package service;

import java.util.List;

import dao.CustomerDAO;
import model.Customer;
import model.Employee;

public class CustomerService {
	private static Customer customer = null;
	private CustomerDAO customerDAO;
	private List<Customer> listCustomer;
	
	public CustomerService() {
		customerDAO = new CustomerDAO();
	}
	public List<Customer> getAllCustomer(){
		return customerDAO.getAllCustomers();
	}
	public Customer updateCustomer(Customer customer) {
		return customerDAO.updateCustomer(customer);
	}
	public Customer insertCustomer(Customer customer) {
		return customerDAO.insertCustomer(customer);
	}
	public Object[][] showCustomers(){
		listCustomer = customerDAO.getAllCustomers();
		Object[][] result = new Object[listCustomer.size()][10];
		for(int i = 0 ; i < listCustomer.size() ; i++) {
			result[i][0] = i + 1;
			result[i][1] = listCustomer.get(i).getIdCustomer();
			result[i][2] = listCustomer.get(i).getNameCustomer().toString();
			result[i][3] = listCustomer.get(i).getPhoneCustomer().toString();
		}
		return result;
	}	
	public void deleteCustomer(int idCustomer) {
		customerDAO.deleteCustomer(idCustomer);
	}
	public int getNextIdCustomer() {
		return customerDAO.nextId();
	}
	public Customer checkID(int Id) {
		listCustomer = customerDAO.getAllCustomers();
		for(Customer item : listCustomer) {
			if(item.getIdCustomer() == Id) return item;
		}
		return null;
	}
	public static void main(String[] args) {
		
	}
}
