package service;

import java.util.List;

import dao.TimeDAO;
import model.Time;

public class TimeService {
	
	private TimeDAO timeDAO;

	public TimeService() {
		timeDAO = new TimeDAO();
	}
	
	public List<Time> getAllTime(){
		return timeDAO.getAllTime();
	}
}
