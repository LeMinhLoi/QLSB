package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import connect.ConnectDatabase;
import model.BeverageBill;
import model.Bill;

public class BillDAO {
	
	public static Bill insertBill(Bill bill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("insert into Bill values (?,?,?,?,?,?)");
                ps.setString(1, String.valueOf(bill.getIdBill()));
                ps.setString(2, String.valueOf(bill.getCreateDate()));
                ps.setString(3, String.valueOf(bill.getCreateTime()));
                ps.setString(4, String.valueOf(bill.getTotal()));
                ps.setString(5, String.valueOf(bill.getIdEmployee()));
                ps.setString(6, String.valueOf(bill.getIdOrder()));
                int row = ps.executeUpdate();
                if (row < 1) {
                	bill = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert Bill fail!");
                bill = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return bill;
	}
	public static Bill updateBill(Bill bill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update bill "
                		+ "set create_Date = ?,"
                		+ "create_Time = ?,"
                		+ "total = ?,"
                		+ "idEmployee = ?,"
                		+ "idOrdered = ?"
                		+ "where idBill = ? ");

                ps.setString(1, String.valueOf(bill.getCreateDate()));
                ps.setString(2, String.valueOf(bill.getCreateTime()));
                ps.setString(3, String.valueOf(bill.getTotal()));
                ps.setString(4, String.valueOf(bill.getIdEmployee()));
                ps.setString(5, String.valueOf(bill.getIdOrder()));                
                ps.setString(6, String.valueOf(bill.getIdBill()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    bill = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update beverage fail!" + ex.toString());
                bill = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return bill;
	}
	public static void deleteBill(int idBill) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.open()) {
				ps = ConnectDatabase.cnn.prepareStatement("delete from bill where idBill = ?");
				ps.setString(1, String.valueOf(idBill));
				ps.executeUpdate();
				ConnectDatabase.close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
	public static void deleteBillByDate(String date1) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.open()) {
				ps = ConnectDatabase.cnn.prepareStatement("delete from bill where create_Date = ?");
				ps.setString(1, date1);
				ps.executeUpdate();
				ConnectDatabase.close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
//	public static int nextId() {
//		int value = -1;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		if(ConnectDatabase.open()) {
//        	try {
//        		ps = ConnectDatabase.cnn.prepareStatement("select AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'bill' AND table_schema = 'qlsb'");
//        		rs = ps.executeQuery();
//        		while(rs.next()) {
//        			value = rs.getInt(1);
//        		}
//        	}catch (SQLException ex) {
//        		System.out.println("Get bill fail!");
//            } finally {
//            	ConnectDatabase.close(ps, rs);
//            }
//        }
//		return value;
//	}
	
	public static void main(String[] args) {
		Bill bill = new Bill(3, Date.valueOf("2021-05-13"),
				Time.valueOf("20:00:00"), 4, 3, 2);
		BillDAO billdao = new BillDAO();
		billdao.insertBill(bill);
		//billdao.updateBill(bill);
		//billdao.deleteBillByDate("13-7-2019");
//		beverageDAO.updateBeverage(beverage);
		//beverageDAO.deleteBeverage(1);
//		List<Beverage> list = beverageDAO.getAllBeverage();
//		for(Beverage item : list) {
//			System.out.println(item.toString());
//		}
		//System.out.println(billdao.nextId());
	}
}
