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
			throw new BookException("ɾ��ʧ��");
		}
	}
	
	public  Customer   LoginUser(Customer customer) throws SQLException, UserException{
    	Customer user2 = custdao.queryCustomerByUserNamePWD(customer);
    	
    	if(user2!=null){
    		//�жϸ��û��Ƿ񱻼���
    		if(user2.getState()==0){
    			//��ʾ���û�δ����
    			throw new UserException("�û�û�м���뼤����ٵ�¼��");
    		}
    	}else{
    		throw new UserException("�û������ڣ�");
    	}
    	
    	return user2;
    }
}
