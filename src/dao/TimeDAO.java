package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Price;
import model.Time;

public class TimeDAO {
	
	public static Time updateTime(Time time) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update time "
                 		+ "set time = ? "
                		+ "where idTime = ? ");
            	ps.setString(1, String.valueOf(time.getTime()));
            	ps.setString(2, String.valueOf(time.getIdTime()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    time = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update Time fail!");
                time = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return time;	
	}
	public static List<Time> getAllTime(){
		Time time = null;
		List<Time> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from price");
        		rs = ps.executeQuery();
        		list = new ArrayList<Time>();
        		while(rs.next()) {
        			time = new Time(rs.getInt(1),rs.getString(2));
        			list.add(time);
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
