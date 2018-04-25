package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.AddressDao;
import com.bugs.domain.Address;
import com.bugs.domain.Customer;

public class AddressService {
	AddressDao addressDao = new AddressDao();
	public  boolean InsertAddressItem(Address address,Customer customer) throws SQLException{
		int rowCount = addressDao.InsertAddress(address, customer);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean DeleteAddressItem(Address address) throws SQLException{
		int rowCount = addressDao.DeleteAddress(address.id);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean UpdateAddressItemNumById(Address address) throws SQLException{
		int rowCount = addressDao.UpdateAddress(address);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	public List<Address> QueryAddressItem() throws SQLException {
		return addressDao.queryAddress();
	}
	public List<Address> QueryAddressItemByCustomerId(Customer customer) throws SQLException {
		return addressDao.queryAddressByCustomerId(customer);
	}
}
