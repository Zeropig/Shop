package Shop.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Shop.domain.Category;
import Shop.domain.Order;
import Shop.domain.Product;
import Shop.web.domain.AdminDao;

public class AdminService {

	public List<Category> findAllCatagory() {
		AdminDao dao = new AdminDao();
		List<Category> listcategory = null;
		try {
			listcategory = dao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listcategory;
	}

	public void saveProduct(Product product) {
		AdminDao dao = new AdminDao();
		try {
			dao.saveProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Order> findAllOrders() {
		AdminDao dao = new AdminDao();
		List<Order> listorder = null;
		try {
			listorder = dao.findAllOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listorder;
	}

	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		AdminDao  dao  = new AdminDao();
		List<Map<String, Object>> mapList =null;
		try {
			mapList = dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapList;
	}
            
}
