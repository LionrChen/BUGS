package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.CustomerDao;
import com.bugs.domain.Customer;
import com.bugs.domain.User;
import com.bugs.exception.BookException;
import com.bugs.exception.UserException;

public class CustomerService {
	
	CustomerDao custdao=new CustomerDao();
	public List<Customer> queryCustomersList(int type) throws SQLException {
		return custdao.queryCustomersByType(type);
	}
	public Customer queryCustomer(int id) throws SQLException {
		return custdao.queryCustomerById(id);
	}
	public boolean updateCustomer(Customer customer) throws SQLException {
		if(custdao.updateCustomerById(customer)>0){
			return true;
		}else {
			return false;
		}
	}
	public void  DelBybatch(String[] ids)throws SQLException, BookException{
		 int[] rowCount = custdao.DeleteBybatch(ids);
		 if (rowCount.length>0) {
			
		}else{
			throw new BookException("删除失败");
		}
	}
	
	public  Customer   LoginUser(Customer customer) throws SQLException, UserException{
    	Customer user2 = custdao.queryCustomerByUserNamePWD(customer);
    	
    	if(user2!=null){
    		//判断该用户是否被激活
    		if(user2.getState()==0){
    			//表示该用户未激活
    			throw new UserException("用户没有激活，请激活后再登录！");
    		}
    	}else{
    		throw new UserException("用户不存在！");
    	}
    	
    	return user2;
    }
}
