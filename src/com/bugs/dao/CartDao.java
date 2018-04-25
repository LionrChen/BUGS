package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
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
	
	public List<ShoppingCart> queryShoppingCarts() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from shoppingcart";
		return queryRunner.query(sql, new BeanListHandler<ShoppingCart>(ShoppingCart.class));
	}
	public List<ShoppingCart> queryShoppingCartsById(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from shoppingcart where userid=?";
		return queryRunner.query(sql, new BeanListHandler<ShoppingCart>(ShoppingCart.class),id);
	}
}
