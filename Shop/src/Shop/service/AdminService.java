package Shop.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Shop.dao.AdminDao;
import Shop.domain.Category;
import Shop.domain.Order;
import Shop.domain.Product;

public interface AdminService {

	public List<Category> findAllCategory();

	public void saveProduct(Product product) throws SQLException;

	public List<Order> findAllOrders();

	public List<Map<String, Object>> findOrderInfoByOid(String oid);

}
