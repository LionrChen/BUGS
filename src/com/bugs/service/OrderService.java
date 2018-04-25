package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.OrderDao;
import com.bugs.domain.Customer;
import com.bugs.domain.order;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	public  boolean InsertOrderItem(order order) throws SQLException{
		int rowCount = orderDao.InsertOrder(order);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean DeleteOrderItem(order order) throws SQLException{
		int rowCount = orderDao.DeleteOrder(order.id);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}

	public  boolean DeleteByBatchItem(String[] ids)throws  SQLException {
		int[] rowCount = orderDao.DeleteBybatch(ids);
		if(rowCount.length == ids.length) {
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean UpdateOrderItemNumById(order order) throws SQLException{
		int rowCount = orderDao.updateOrderById(order);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean UpdateOrderItemById(order order) throws SQLException{
		int rowCount = orderDao.updateOrderById(order);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public order QueryOrderItemById(order order) throws SQLException {
		return orderDao.queryOrderById(order.getId());
	}
	
	public order QueryOrderItemByOrderNumber(String ordernumber) throws SQLException {
		return orderDao.queryOrderByOrdernumber(ordernumber);
	}
	
	public List<order> QueryOrdersItem() throws SQLException {
		return orderDao.queryOrders();
	}
	public List<order> QueryOrdersItemByCustomerId(Customer customer) throws SQLException {
		return orderDao.queryOrdersByCustomerId(customer.getId());
	}
//	public List<order> QueryOrdersItemByCustomerIdAndPaymentstate(Customer customer,String paymentstate) throws SQLException {
//		return orderDao.queryOrdersByCustomerId(customer.getId(),paymentstate);
//	}
}
