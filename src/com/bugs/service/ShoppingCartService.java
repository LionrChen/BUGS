package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.CartDao;
import com.bugs.domain.Address;
import com.bugs.domain.Book;
import com.bugs.domain.Customer;
import com.bugs.domain.ShoppingCart;

public class ShoppingCartService {
	CartDao cartDao = new CartDao();
	public  boolean InsertShoppingCartItem(Customer customer,Book book, int num) throws SQLException{
		int rowCount = cartDao.InserCart(customer, book, num);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean DeleteShoppingCartItem(ShoppingCart shoppingCart) throws SQLException{
		int rowCount = cartDao.DeleteCart(shoppingCart.id);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean UpdateShoppingCartItemNumById(ShoppingCart shoppingCart) throws SQLException{
		int rowCount = cartDao.UpdateShoppingCart(shoppingCart.id, shoppingCart.num);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	public List<ShoppingCart> QueryShoppingCartItem() throws SQLException {
		return cartDao.queryShoppingCarts();
	}
	public List<ShoppingCart> QueryAllShoppingCartItemByCustomerId(int id) throws SQLException {
		return cartDao.queryShoppingCartsById(id);
	}
}
