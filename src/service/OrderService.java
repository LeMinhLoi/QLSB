package service;

import java.util.Date;
import java.util.List;

import dao.OrderDAO;
import model.Order;

public class OrderService {

	private OrderDAO orderDAO;

	public OrderService() {
		orderDAO = new OrderDAO();
	}
	
	public Order insertOrder(Order order) {
		return orderDAO.insertOrder(order);
	}
	public void deleteOrder(int idOrder) {
		orderDAO.deleteOrder(idOrder);
	}
	public List<Order> getOrderByDateTime(Date date,int idTime){
		return orderDAO.getOrderByDateTime(date, idTime);
	}
	
}
