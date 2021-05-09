package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Employee;

public class EmployeeDAO {
	
	public static Employee insertEmployee(Employee employee) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("insert into employee values (?,?,?,?,?,?,?,?,?)");
                ps.setString(1, String.valueOf(employee.getIdCustomer()));
                ps.setString(2, employee.getNameCustomer());
                ps.setString(3, String.valueOf(employee.getOld()));
                ps.setString(4, employee.getAddress());
                ps.setString(5, String.valueOf(employee.getGender()));
                ps.setString(6, employee.getPhoneCustomer());
                ps.setString(7, employee.getIdentityNumber());
                ps.setString(8, String.valueOf(employee.getPassword()));
                ps.setString(9, String.valueOf(employee.getRole()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    employee = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert beverage fail!");
                employee = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return employee;
	}
	public static Employee updateEmployee(Employee employee) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update employee "
                		+ "set name = ?,"
                		+ "old = ?,"
                		+ "address = ?,"
                		+ "gender = ?,"
                		+ "phone = ?,"
                		+ "identity_Number = ?,"
                		+ "password = ?,"
                		+ "role = ?"
                		+ "where idEmployee = ? ");
                
                ps.setString(1, employee.getNameCustomer());
                ps.setString(2, String.valueOf(employee.getOld()));
                ps.setString(3, employee.getAddress());
                ps.setString(4, String.valueOf(employee.getGender()));
                ps.setString(5, employee.getPhoneCustomer());
                ps.setString(7, employee.getPassword());
                ps.setString(8, String.valueOf(employee.getRole()));
                ps.setString(9, String.valueOf(employee.getIdCustomer()));
                ps.setString(6, employee.getIdentityNumber());
                int row = ps.executeUpdate();
                if (row < 1) {
                    employee = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update enployee fail!" + ex.toString());
                employee = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return employee;
	}
	public static void deleteEmployee(int idEmployee) {
		PreparedStatement ps = null;
		try {
            if (ConnectDatabase.open()) {
                ps = ConnectDatabase.cnn.prepareStatement("delete from employee where idEmployee = ?");
                ps.setString(1, String.valueOf(idEmployee));
                ps.executeUpdate();
                ConnectDatabase.close();
            }
        } catch (SQLException e) {
            System.out.println("Delete employee fail!");
        }
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
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("SET @@SESSION.information_schema_stats_expiry = 0 ");
        		ps.executeQuery();
        		ps = ConnectDatabase.cnn.prepareStatement("select AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'employee' AND table_schema = 'qlsb'");
        		rs = ps.executeQuery();
        		while(rs.next()) {
        			value = rs.getInt(1);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get beverage fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return value;
	}
	public static List<Employee> search(String name){
		List<Employee> list = null;
		return list;
	}
	public static void main(String[] args) {
		EmployeeDAO eD  = new EmployeeDAO();
		int i = eD.nextId();
		System.out.println(i);
	}
}
