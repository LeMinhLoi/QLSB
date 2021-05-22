package service;

import java.util.*;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import dao.BillDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.PriceDAO;
import dao.BillDAO;
import model.BeverageBill;
import model.Bill;
import model.Employee;
import model.Order;
import model.Price;
import model.Bill;

public class BillService {
	private static Bill Bill = null;
	private static BillDAO BillDAO;
	private List<Bill> listBill;
	
	public BillService() {
		BillDAO = new BillDAO();
	}
	
	public List<Bill> getAllBill() {
		return BillDAO.getAllBill();
	}
	
	public void DelByDate(String date1, String date2)
	{
		BillDAO.deleteBillByDate(date1, date2);
	}
	
	public Bill UpdateOrAdd(Bill Bill)
	{
		if(Check(Bill.getIdBill())) 
			return BillDAO.insertBill(Bill);
		else 
			return BillDAO.updateBill(Bill);
	}
	
	public Object[][]  Sort(int IDEmpl, int m)
	{
		List<Bill> l = BillDAO.getAllBill();
		Collections.sort(l,  new Comparator<Bill>() {
            @Override
            public int compare(Bill o1, Bill o2) {
            	switch(m)
            	{
            		case 1:
            			return o1.getCreateDate().toString().
            					compareTo(o2.getCreateDate().toString());
            		case 2:
            			return o1.getTotal() - o2.getTotal();
            		case 3:
            			return o1.getIdEmployee() - o2.getIdEmployee();
            		case 4:
            			return o1.getIdOrder() - o2.getIdOrder();
            		default:
            			return o1.getIdBill() - o2.getIdBill();
            	}
            }
        });
		return showBills(IDEmpl, l); 
	}
	
	public static Object[][] showBills(int IDEmpl, List<Bill> l){
		Object[][] result = new Object[l.size()][10];
		int dem = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			if(IDEmpl == 0 || IDEmpl == l.get(i).getIdEmployee())
			{
				result[dem][0] = dem + 1;
				result[dem][1] = l.get(i).getIdBill();
				result[dem][2] = l.get(i).getCreateDate().toString();
				result[dem][3] = l.get(i).getCreateTime().toString();
				result[dem][4] = String.valueOf(l.get(i).getTotal());
				result[dem][5] = String.valueOf(l.get(i).getIdEmployee());
				result[dem][6] = String.valueOf(l.get(i).getIdOrder());
				dem++;
			}
		}
		return result;
	}
	
	public Object[][] showBills(int IDEmpl){
		listBill = BillDAO.getAllBill();
		return showBills(IDEmpl, listBill);
	}
	
	public int NextID()
	{
		return BillDAO.nextId();
	}
	public void deleteBill(int id) {
		BillDAO.deleteBill(id);
	}
	public static List<Order> GetCBB()
	{
		List<Order> k = new ArrayList<>();
		for(Order i : OrderDAO.getAllOrder())
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
		return BillDAO.nextId();
	}
	public Bill checkID(int Id) {
		listBill = BillDAO.getAllBill();
		for(Bill item : listBill) {	
			if(item.getIdBill() == Id) 	return item;
		}
		return null;
	}
	
	public int ToTal(List<BeverageBill> l, int IDOrder)
	{
		int tien = BeverageBillService.TinhTien(l);
		for(Order i : OrderDAO.getAllOrder())
			if(i.getIdOrder() == IDOrder)
				for(Price k : PriceDAO.getAllPrice())
					if(k.getIdCateYard_Time() == i.getIdCateYard_Time()) 
						return tien + k.getPrice();
		return tien;
	}
	
	public Order GetSan(int ID)
	{
		for(Order i : OrderDAO.getAllOrder())
			if(i.getIdOrder() == ID)
				return i;
		return null;
	}
	
	public static boolean CheckCBB(int ID)
	{
		for(Bill item : BillDAO.getAllBill())
			if(item.getIdOrder() == ID) return false;
		return true;
	}
	
	public boolean Check(int ID)
	{
		listBill = BillDAO.getAllBill();
		for(Bill item : listBill)
			if(item.getIdBill() == ID) return false;
		return true;
	}

}