package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Price;

public class PriceDAO {
	
	public static Price updatePrice(Price price) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update price set "
                 		+ "price = ? "
                		+ "where idTime = ? "
                		+ "AND idTime_CateYard = ? "
                		+ "AND idCategory_Yard = ? ");
            	ps.setString(1, String.valueOf(price.getPrice()));
            	ps.setString(2, String.valueOf(price.getIdTime()));
            	ps.setString(3, String.valueOf(price.getIdCateYard_Time()));
            	ps.setString(4, String.valueOf(price.getIdCateYard()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    price = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update Price fail!");
                price = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return price;	
	}
	public static List<Price> getAllPrice(){
		Price price = null;
		List<Price> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from price");
        		rs = ps.executeQuery();
        		list = new ArrayList<Price>();
        		while(rs.next()) {
        			price = new Price(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
        			list.add(price);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get beverage fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
}
