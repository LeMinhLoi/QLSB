package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import model.Beverage;
import model.Time;

public class TimeDAO {
	
	public static Time updateTime(Time time) {
		PreparedStatement ps = null;
		if (ConnectDatabase.open()) {
            try {
                ps = ConnectDatabase.cnn.prepareStatement("update time "
                		+ "set time = ?"
                		+ "where idTime = ? ");
                
                ps.setString(1, time.getTime());
                ps.setString(2, String.valueOf(time.getIdTime()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    time = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update time fail!" + ex.toString());
                time = null;
            } finally {
            	ConnectDatabase.close(ps);
            }
        }
        return time;
	}
	public static List<Time> getAllTime(){
		List<Time> list = null;
		Time time = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.open()) {
        	try {
        		ps = ConnectDatabase.cnn.prepareStatement("select * from time");
        		rs = ps.executeQuery();
        		list = new ArrayList<Time>();
        		while(rs.next()) {
        			time = new Time(rs.getInt(1),rs.getString(2));
        			list.add(time);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get time fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.close(ps, rs);
            }
        }
		return list;
	}
	public static void main(String[] args) {
		Time time = new Time(1, "20/12/2021");
		TimeDAO.updateTime(time);
	}
}