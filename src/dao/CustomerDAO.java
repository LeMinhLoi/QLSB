package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Beverage;
import model.Customer;
import model.Employee;

public class CustomerDAO {

	public static Customer insertCustomer(Customer customer) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("insert into customer values (?,?,?)");
                ps.setString(1, String.valueOf(customer.getIdCustomer()));
                ps.setString(2, customer.getNameCustomer());
                ps.setString(3, customer.getPhoneCustomer());
                int row = ps.executeUpdate();
                if (row < 1) {
                    customer = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert customer fail!");
                customer = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return customer;
	}
	public static Customer updateCustomer(Customer customer) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update customer "
                		+ "set namecustomer = ?,"
                		+ "phone = ?"
                		+ "where idCustomer = ? ");

                ps.setString(1, customer.getNameCustomer());
                ps.setString(2, customer.getPhoneCustomer());
                ps.setString(3, String.valueOf(customer.getIdCustomer()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    customer = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update customer fail!" + ex.toString());
                customer = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return customer;
	}

	public static void deleteCustomer(int idCustomer) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.open()) {
				ps = ConnectDatabase.cnn.prepareStatement("delete from customer where idCustomer = ?");
				ps.setString(1, String.valueOf(idCustomer));
				ps.executeUpdate();
				ConnectDatabase.close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
	public static int nextId() {
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'customer' AND table_schema = 'qlsb'");
        		rs = ps.executeQuery();
        		while(rs.next()) {
        			value = rs.getInt(1);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get customer fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return value;
	}
	public static List<Customer> searchPhone(String phone){
		List<Customer> list = null;
		Customer customer = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from customer where phone = ? ");
        		ps.setString(1, phone);
        		rs = ps.executeQuery();
        		list = new ArrayList<Customer>();
        		while(rs.next()) {
        			customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3));
        			list.add(customer);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get customer by phone fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
	public static List<Customer> getAllCustomers(){
		Customer customer = null;
		List<Customer> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from customer");
        		rs = ps.executeQuery();
        		list = new ArrayList<Customer>();
        		while(rs.next()) {
        			customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3));
        			list.add(customer);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get customer fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
	public static void main(String[] args) {
		//CustomerDAO customerDAO = new CustomerDAO();
		//System.out.println(customerDAO.nextId());
		//Customer customer = new Customer(1, "Loi", "0336364692");
		CustomerDAO c = new CustomerDAO();
		List<Customer> list = c.searchPhone("0336364692");
		for(Customer i : list)
		{
			System.out.println(i.toString());
		}
	}
}