package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Beverage;
import model.Yard;

public class YardDAO {

	public static Yard updateYard(Yard yard) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update yard "
                		+ "set idCategory_Yard = ?,"
                		+ "nameYard = ?,"
                		+ "status = ?"
                		+ "where idYard = ? ");
                
                ps.setString(1, String.valueOf(yard.getIdCateyard()));
                ps.setString(2, yard.getNameYard());
                ps.setString(3, String.valueOf(yard.getStatus()));
                ps.setString(4, String.valueOf(yard.getIdYard()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    yard = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update yard fail!" + ex.toString());
                yard = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return yard;
	}
	public static List<Yard> getAllYard(){
		List<Yard> list = null;
		Yard yard = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from yard");
        		rs = ps.executeQuery();
        		list = new ArrayList<Yard>();
        		while(rs.next()) {
        			yard = new Yard(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4));
        			list.add(yard);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get yard fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
	public static void main(String[] args) {
		Yard yard = new Yard(1,1,"5A",1);
		YardDAO.updateYard(yard);
	}
}
