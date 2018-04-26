package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bugs.domain.Address;
import com.bugs.domain.Book;
import com.bugs.domain.Customer;
import com.bugs.domain.ShoppingCart;
import com.bugs.util.DBUtils;

public class CartDao {
	public int InserCart(Customer customer,Book book, int num) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into shoppingcart(userid,bookid,num) values(?,?,?)";
		int rowCount = queryRunner.update(sql, customer.id,book.id,num);

		return rowCount;
	}
	public int DeleteCart(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "delete from shoppingcart where id =?";
		int rowCount = queryRunner.update(sql,id);

		return rowCount;
	}
	
	public int UpdateShoppingCart(int id, int num) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "update shoppingcart set num=? where id=?";
		int rowCount = queryRunner.update(sql,num,id);
		return rowCount;
	}
	
	public ShoppingCart queryShoppingCartById(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from shoppingcart where id=?";
		return queryRunner.query(sql, new BeanHandler<ShoppingCart>(ShoppingCart.class),id);
	}
	
	public List<ShoppingCart> queryShoppingCarts() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from shoppingcart";
		return queryRunner.query(sql, new BeanListHandler<ShoppingCart>(ShoppingCart.class));
	}
	public List<ShoppingCart> queryShoppingCartsByCustomerId(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from shoppingcart where userid=?";
		return queryRunner.query(sql, new BeanListHandler<ShoppingCart>(ShoppingCart.class),id);
	}
	public int[] DeleteBybatch(String[] ids) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		// ÅúÁ¿²Ù×÷
		String sql = "delete from shoppingcart where  id = ?";
		Object[][] params = new Object[ids.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { ids[i] };
		}
		int[] rowCount = queryRunner.batch(sql, params);
		return rowCount;

	}
}
