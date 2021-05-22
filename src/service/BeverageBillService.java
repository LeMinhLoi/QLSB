package service;

import java.util.*;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import dao.BeverageBillDAO;
import model.Beverage;
import model.BeverageBill;
import dao.BeverageDAO;

public class BeverageBillService {
	private static BeverageBill BeverageBill = null;
	private static BeverageBillDAO BeverageBillDAO;
	private List<BeverageBill> listBeverageBill;
	
	public BeverageBillService() {
		BeverageBillDAO = new BeverageBillDAO();
	}
	
	public void delet(int idBeveBill, int idBeve)
	{
		BeverageBillDAO.deleteBeverageBill(idBeveBill, idBeve);
	}
	
	public List<BeverageBill> getAllBeverageBill(int ID) {
		List<BeverageBill> l = new ArrayList<>();
		for(BeverageBill i : BeverageBillDAO.getAllBeveBill())
			if(ID == i.getIdBeveBill())
				l.add(i);
		return l;
	}
	
	public BeverageBill Add(BeverageBill BeverageBill)
	{
			return BeverageBillDAO.insertBeverageBill(BeverageBill);
	}
	
	public Object[][]  Sort(int ID, int m)
	{
		List<BeverageBill> l = BeverageBillDAO.getAllBeveBill();
		Collections.sort(l,  new Comparator<BeverageBill>() {
            @Override
            public int compare(BeverageBill o1, BeverageBill o2) {
            	switch(m)
            	{
            		case 1:
            			return o1.getMountBeve() - o2.getMountBeve();
            		case 2:
            			return BeverageDAO.Price(o1.getIdBeve()) 
            					- BeverageDAO.Price(o2.getIdBeve());
            		case 3:
            			return BeverageDAO.Price(o1.getIdBeve())*o1.getMountBeve() 
            					- BeverageDAO.Price(o2.getIdBeve())*o2.getMountBeve();
            		default:
            			return BeverageDAO.Name(o1.getIdBeveBill())
            					.compareTo(BeverageDAO.Name(o2.getIdBeveBill()));
            	}
            }
        });
		return showBeverageBills(ID, l); 
	}
	
	public static void AddList(BeverageBill b, List<BeverageBill> l)
	{
		boolean k = true;
		for(BeverageBill i : l)
			if(i.getIdBeveBill() == b.getIdBeveBill() && i.getIdBeve() == b.getIdBeve())
			{
				i.setMountBeve(i.getMountBeve() + b.getMountBeve());
				k = false;
			}
		if(k) l.add(b);
	}
	
	public static Object[][] showBeverageBills(int ID, List<BeverageBill> l){
		Object[][] result = new Object[l.size()][10];
		int dem = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			if(ID == l.get(i).getIdBeveBill())
			{
				result[dem][0] = dem + 1;
				result[dem][1] = BeverageDAO.Name(l.get(i).getIdBeve());
				result[dem][2] = String.valueOf(l.get(i).getMountBeve());
				result[dem][3] = String.valueOf(BeverageDAO.Price(l.get(i).getIdBeve()));
				result[dem][4] = String.valueOf(BeverageDAO.Price(l.get(i).getIdBeve())
						*l.get(i).getMountBeve());
				dem++;
			}
		}
		return result;
	}
	
	public static int TinhTien(List<BeverageBill> l)
	{
		int tien = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			tien += BeverageDAO.Price(l.get(i).getIdBeve())
					*l.get(i).getMountBeve();
		}
		return tien;
	}
	
	public int ReID(String name)
	{
		for(Beverage i : BeverageDAO.getAllBeverage())
			if(i.getNameBeverage().contains(name))
				return i.getIdBeverage();
		return 0;
	}
	
	public Object[][] showBeverageBills(int ID){
		listBeverageBill = BeverageBillDAO.getAllBeveBill();
		return showBeverageBills(ID, listBeverageBill);
	}
	public BeverageBill checkID(int Id) {
		listBeverageBill = BeverageBillDAO.getAllBeveBill();
		for(BeverageBill item : listBeverageBill) {	
			if(item.getIdBeveBill() == Id) 	return item;
		}
		return null;
	}

}
