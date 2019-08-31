package Shop.web.domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import Shop.domain.Category;
import Shop.domain.Order;
import Shop.domain.Product;
import Shop.utils.DataSourceUtils;


public class AdminDao {

	public List<Category> findAllCategory() throws SQLException {
		QueryRunner runner = new  QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from category ";
		List<Category> list = runner.query(sql, new BeanListHandler<Category>(Category.class));
		return list;
	}

	public void saveProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),product.getPdate(),
				product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid());
	}

	public List<Order> findAllOrder() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from Orders";
		return runner.query(sql,new BeanListHandler<Order>(Order.class));
	}


	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select p.pimage,p.pname,p.shop_price,i.count,i.subtotal "+
				" from orderitem i,product p "+
				" where i.pid=p.pid and i.oid=? ";
		return runner.query(sql, new MapListHandler(),oid);
	}

}
