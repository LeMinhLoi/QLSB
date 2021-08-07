package service;

import java.util.*;

import dao.BillDAO;
import dao.OrderDAO;
import dao.PriceDAO;
import model.BeverageBill;
import model.Bill;
import model.Order;
import model.Price;

public class BillService {
	private List<Bill> listBill;
	
	private static BillService instance;
	private BillService() {
		
	}
	public static BillService getInstance() {
		if(instance == null) {
			instance = new BillService();
		}
		return instance;
	}
	
	public List<Bill> getAllBill() {
		return BillDAO.getInstance().getAllBill();
	}
	
	public void DelByDate(String date1, String date2)
	{
		BillDAO.getInstance().deleteBillByDate(date1, date2);
	}
	
	public Bill UpdateOrAdd(Bill Bill)
	{
		if(Check(Bill.getIdBill())) 
			return BillDAO.getInstance().insertBill(Bill);
		else 
			return BillDAO.getInstance().updateBill(Bill);
	}
	
	public static Object[][] showBills(int IDEmpl, List<Bill> l, String d){
		Object[][] result = new Object[l.size()][10];
		int dem = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			if((IDEmpl == 0 || IDEmpl == l.get(i).getIdEmployee())
					&& (l.get(i).getCreateDate().toString().equals(d) || d == ""))
			{
				result[dem][0] = dem + 1;
				result[dem][1] = l.get(i).getIdBill();
				result[dem][2] = l.get(i).getCreateDate().toString();
				result[dem][3] = l.get(i).getCreateTime().toString();
				result[dem][4] = String.valueOf(l.get(i).getTotal());
				result[dem][5] = String.valueOf(l.get(i).getIdEmployee());
				if(l.get(i).getIdOrder() == 0) {
					result[dem][6] = "NULL";
				}else {
					result[dem][6] = String.valueOf(l.get(i).getIdOrder());
				}	
				dem++;
			}
		}
		return result;
	}
	
	public Object[][] showBills(int IDEmpl, String d){
		listBill = BillDAO.getInstance().getAllBill();
		return showBills(IDEmpl, listBill, d);
	}
	
	public int NextID()
	{
		return BillDAO.getInstance().nextId();
	}
	public void deleteBill(int id) {
		BillDAO.getInstance().deleteBill(id);
	}
	public static List<Order> GetCBB()
	{
		List<Order> k = new ArrayList<>();
		for(Order i : OrderDAO.getInstance().getAllOrder())
			if(CheckCBB(i.getIdOrder()))
				k.add(i);
		return k;
	}
	public int VitriCBB(int ID)
	{
		for(int i = 0; i < GetCBB().size(); ++i)
			if(GetCBB().get(i).getIdOrder() == ID)
				return ++i;
		return 0;
				
	}
	public int getNextIdBill() {
		return BillDAO.getInstance().nextId();
	}
	public Bill checkID(int Id) {
		listBill = BillDAO.getInstance().getAllBill();
		for(Bill item : listBill) {	
			if(item.getIdBill() == Id) 	return item;
		}
		return null;
	}
	
	public int ToTal(List<BeverageBill> l, int IDOrder)
	{
		int tien = BeverageBillService.getInstance().TinhTien(l);
		for(Order i : OrderDAO.getInstance().getAllOrder())
			if(i.getIdOrder() == IDOrder)
				for(Price k : PriceDAO.getInstance().getAllPrice())
					if(k.getIdCateYard_Time() == i.getIdCateYard_Time()) 
						return tien + k.getPrice();
		return tien;
	}
	
	public Order GetSan(int ID)
	{
		for(Order i : OrderDAO.getInstance().getAllOrder())
			if(i.getIdOrder() == ID)
				return i;
		return null;
	}
	
	public static boolean CheckCBB(int ID)
	{
		for(Bill item : BillDAO.getInstance().getAllBill())
			if(item.getIdOrder() == ID) return false;
		return true;
	}
	
	public boolean Check(int ID)
	{
		listBill = BillDAO.getInstance().getAllBill();
		for(Bill item : listBill)
			if(item.getIdBill() == ID) return false;
		return true;
	}

	public void DelOrder(int IDOrder) {
		OrderDAO.getInstance().deleteOrder(IDOrder);
	}

}