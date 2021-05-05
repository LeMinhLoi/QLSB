package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Beverage;
import model.Employee;

public class EmployeeDAO {
	
	public static Employee insertEmployee(Employee employee) {
		return null;
	}
	public static Employee updateEmployee(Employee employee) {
		return null;
	}
	public static void deleteEmployee(int idEmployee) {
	}
	public static List<Employee> getAllEmployees(){
		Employee employee = null;
		List<Employee> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from employee");
        		rs = ps.executeQuery();
        		list = new ArrayList<Employee>();
        		while(rs.next()) {
        			employee = new Employee(rs.getInt(1),rs.getString(2),rs.getString(6),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(7),rs.getString(8),rs.getInt(9));
        			list.add(employee);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get beverage fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
	public static int nextId() {
		return 0;
	}
	public static List<Employee> search(String name){
		List<Employee> list = null;
		return list;
	}
}
