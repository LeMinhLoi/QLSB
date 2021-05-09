package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Beverage;
import model.CateYard;
import model.Price;
import model.Time;

public class BeverageDAO {
	public static Beverage insertBeverage(Beverage beverage) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("insert into beverage values (?,?,?,?,?,?)");
                ps.setString(1, String.valueOf(beverage.getIdBeverage()));
                ps.setString(2, beverage.getNameBeverage());
                ps.setString(3, String.valueOf(beverage.getMount()));
                ps.setString(4, String.valueOf(beverage.getOriginalPrice()));
                ps.setString(5, beverage.getMeasure());
                ps.setString(6, String.valueOf(beverage.getPrice()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    beverage = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert beverage fail!");
                beverage = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return beverage;
	}
	public static Beverage updateBeverage(Beverage beverage) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update beverage "
                		+ "set namebeverage = ?,"
                		+ "mount = ?,"
                		+ "originalPrice = ?,"
                		+ "measure = ?,"
                		+ "price = ?"
                		+ "where idBeverage = ? ");
                
                ps.setString(1, beverage.getNameBeverage());
                ps.setString(2, String.valueOf(beverage.getMount()));
                ps.setString(3, String.valueOf(beverage.getOriginalPrice()));
                ps.setString(4, beverage.getMeasure());
                ps.setString(5, String.valueOf(beverage.getPrice()));
                ps.setString(6, String.valueOf(beverage.getIdBeverage()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    beverage = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update beverage fail!" + ex.toString());
                beverage = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return beverage;
	}
	public static void deleteBeverage(int idBeverage) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.open()) {
				ps = ConnectDatabase.cnn.prepareStatement("delete from beverage where idBeverage = ?");
				ps.setString(1, String.valueOf(idBeverage));
				ps.executeUpdate();
				ConnectDatabase.close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
	public static List<Beverage> getAllBeverage(){
		Beverage beverage = null;
		List<Beverage> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from beverage");
        		rs = ps.executeQuery();
        		list = new ArrayList<Beverage>();
        		while(rs.next()) {
        			beverage = new Beverage(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6));
        			list.add(beverage);
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
        		ps = ConnectDatabase.cnn.prepareStatement("select AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'beverage' AND table_schema = 'qlsb'");
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
	public static void main(String[] args) {
		/*Beverage beverage = new Beverage(1,"Loi","c",123,124,10);
		//BeverageDAO beverageDAO = new BeverageDAO();
		Price price = new Price (1, 1, 1, 300);
		PriceDAO pricedao = new PriceDAO();
		PriceDAO.updatePrice(price);
//		beverageDAO.insertBeverage(beverage);
//		beverageDAO.updateBeverage(beverage);
		//beverageDAO.deleteBeverage(1);
//		List<Beverage> list = beverageDAO.getAllBeverage();
//		for(Beverage item : list) {
//			System.out.println(item.toString());
//		}
		List<Price> list = pricedao.getAllPrice();
		for(Price item : list) {
			System.out.print(item.toString());
		}
		System.out.println(pricedao);*/
		CateYard cateyard = new CateYard(1, "San 8");
		CateYardDAO cateYardDAO = new CateYardDAO();
		CateYardDAO.updateCateYard(cateyard);
		List<CateYard> list = cateYardDAO.getAllCateYard();
		for (CateYard item : list) {
			System.out.print(item.toString());
		}
		System.out.println(cateYardDAO);
	}
}
