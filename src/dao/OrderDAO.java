package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.BeverageBill;
import model.Beverage;
import model.Order; 

public class OrderDAO {
	
	public static Order insertOrder(Order order) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("insert into ordered values (?,?,?,?,?)");
                ps.setString(1, String.valueOf(order.getIdOrder()));
                ps.setString(2, String.valueOf(order.getIdCateYard_Time()));
                ps.setString(3, String.valueOf(order.getIdYard()));
                ps.setString(4, String.valueOf(order.getDate()));
                ps.setString(5, String.valueOf(order.getIdCustomer()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    order = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert order fail!");
                order = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
		return order;
	}
	
	public static void deleteOrder(int idOrder) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.open()) {
				ps = ConnectDatabase.cnn.prepareStatement("delete from ordered where idOrdered = ?");
				ps.setString(1, String.valueOf(idOrder));
				ps.executeUpdate();
				ConnectDatabase.close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
	
	public static List<Order> getOrderByDateTime(Date date,int idTime){
		Order order = null;
		List<Order> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
		if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from ordered");
        		rs = ps.executeQuery();
        		list = new ArrayList<Order>();
        		while(rs.next()) {
        			order = new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getInt(5));
        			if(order.getDate().compareTo(date) == 0 && order.getIdCateYard_Time() == idTime)
        				list.add(order);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get order fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
	public static void main(String[] args) {
		Order Order = new Order(5,1, 3,Date.valueOf("2019-07-13"),1);
		OrderDAO OrderDAO1 = new OrderDAO();
		//OrderDAO.insertOrder(Order);
		//OrderDAO1.deleteOrder(5);
		List<Order> list = OrderDAO1.getOrderByDateTime(Date.valueOf("2019-07-13"), 2);
//		OrderDAO.updateOrder(Order);
		//OrderDAO.deleteOrder(1);
//		List<Order> list = OrderDAO.getAllOrder();
		for(Order item : list) 
		System.out.println(item.toString());
	}

}
