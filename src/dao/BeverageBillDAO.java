package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import connect.ConnectDatabase;
import model.Beverage;
import model.BeverageBill;

public class BeverageBillDAO {
	public static BeverageBill insertBeverageBill(BeverageBill beverageBill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("insert into beverage_bill values (?,?,?)");
                ps.setString(1, String.valueOf(beverageBill.getIdBeveBill()));
                ps.setString(2, String.valueOf(beverageBill.getIdBeve()));
                ps.setString(3, String.valueOf(beverageBill.getMountBeve()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    beverageBill = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert beverage fail!");
                beverageBill = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
		return beverageBill;
	}
	public static BeverageBill updateBeverageBill(BeverageBill beverageBill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update beverage_bill "
                		+ "set idBeverage = ?,"
                		+ "mount = ?"
                		+ "where idBeverage = ? ");
                ps.setString(1, String.valueOf(beverageBill.getIdBeve()));
                ps.setString(2, String.valueOf(beverageBill.getMountBeve()));
                ps.setString(3, String.valueOf(beverageBill.getIdBeveBill()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    beverageBill = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update beverageBill fail!" + ex.toString());
                beverageBill = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return beverageBill;
	}
	
	public static void main(String[] args) {
		BeverageBill beverage = new BeverageBill(5,12,100);
		BeverageBillDAO beverageBillDAO = new BeverageBillDAO();
		//beverageBillDAO.insertBeverageBill(beverage);
		//beverageBillDAO.updateBeverageBill(beverage);
//		beverageDAO.updateBeverage(beverage);
		//beverageDAO.deleteBeverage(1);
//		List<Beverage> list = beverageDAO.getAllBeverage();
//		for(Beverage item : list) {
//			System.out.println(item.toString());
//		}
	}
}
