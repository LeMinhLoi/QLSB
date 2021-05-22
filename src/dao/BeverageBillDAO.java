package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.BeverageBill;
import model.Bill;

public class BeverageBillDAO {
	public static BeverageBill insertBeverageBill(BeverageBill beverageBill) {
		if(Check(beverageBill))
		{
			PreparedStatement ps = null;
			if (ConnectDatabase.open()) {
	            try {
	            	ps = ConnectDatabase.cnn.prepareStatement("insert into beveragebill values (?,?,?)");
	                ps.setString(1, String.valueOf(beverageBill.getIdBeveBill()));
	                ps.setString(2, String.valueOf(beverageBill.getIdBeve()));
	                ps.setString(3, String.valueOf(beverageBill.getMountBeve()));
	                int row = ps.executeUpdate();
	                if (row < 1) beverageBill = null;
	            } catch (SQLException ex) {
	                System.out.println("Insert beveragebill fail!" + ex);
	                beverageBill = null;
	            } finally {
	            	ConnectDatabase.close(ps);
	            }
	        }
		} else updateBeverageBill(beverageBill);
		return beverageBill;
	}
	
	public static void deleteBeverageBill(int idBeveBill, int idBeve) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.open()) {
				ps = ConnectDatabase.cnn.prepareStatement("delete from beveragebill "
						+ "where id_beverage_Bill = ? and idBeverage = ?");
				ps.setString(1, String.valueOf(idBeveBill));
				ps.setString(2, String.valueOf(idBeve));
				ps.executeUpdate();
				ConnectDatabase.close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
	
	public static BeverageBill updateBeverageBill(BeverageBill beverageBill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update beveragebill "
                		+ "set mount = ?"
                		+ "where id_beverage_Bill = ? and idBeverage = ?");
                ps.setString(1, String.valueOf(beverageBill.getMountBeve()));
                ps.setString(2, String.valueOf(beverageBill.getIdBeveBill()));
                ps.setString(3, String.valueOf(beverageBill.getIdBeve()));
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
	public static List<BeverageBill> getAllBeveBill(){
		BeverageBill BeverageBill = null;
		List<BeverageBill> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from beveragebill");
        		rs = ps.executeQuery();
        		list = new ArrayList<>();
        		while(rs.next()) {
        			BeverageBill = new BeverageBill(rs.getInt(1),rs.getInt(2),rs.getInt(3));
        			list.add(BeverageBill);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get Bill fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}

	public static boolean Check(BeverageBill b)
	{
		for(BeverageBill i : getAllBeveBill())
			if(b.getIdBeve() == i.getIdBeve() && b.getIdBeveBill() == i.getIdBeveBill())
			{
				b.setMountBeve(b.getMountBeve() + i.getMountBeve());
				return false;
			}
		return true;
	}
	
	public static void main(String[] args) {
		BeverageBill beverage = new BeverageBill(1,13,11);
		BeverageBillDAO beverageBillDAO = new BeverageBillDAO();
		//beverageBillDAO.updateBeverageBill(beverage);
//		List<Beverage> list = beverageDAO.getAllBeverage();
//		for(Beverage item : list) {
//			System.out.println(item.toString());
//		}
	}
}