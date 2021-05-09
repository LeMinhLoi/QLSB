package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.CateYard;
import model.Time;

public class CateYardDAO {
	
	public static CateYard updateCateYard(CateYard cateYard) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update category_yard "
                 		+ "set nameCategory_Yard = ? "
                		+ "where idCategory_Yard = ? ");
            	ps.setString(1, String.valueOf(cateYard.getNameCateYard()));
            	ps.setString(2, String.valueOf(cateYard.getIdCateYard()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    cateYard = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update CateYard fail!");
                cateYard = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return cateYard;	
	}
	public static List<CateYard> getAllCateYard(){
		CateYard cateYard = null;
		List<CateYard> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from price");
        		rs = ps.executeQuery();
        		list = new ArrayList<CateYard>();
        		while(rs.next()) {
        			cateYard = new CateYard(rs.getInt(1),rs.getString(2));
        			list.add(cateYard);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get CateYard fail!");
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
}
