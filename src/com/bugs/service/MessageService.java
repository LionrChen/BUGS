package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.MessageDao;
import com.bugs.domain.Customer;
import com.bugs.domain.Message;

public class MessageService {
	MessageDao messageDao = new MessageDao();
	public  boolean InsertMessage(Message message) throws SQLException{
		int rowCount = messageDao.InserMessage(message);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean DeleteMessage(Message message) throws SQLException{
		int rowCount = messageDao.DeleteMessage(message.id);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}

	public  boolean DeleteByBatch(String[] ids)throws  SQLException {
		int[] rowCount = messageDao.DeleteBybatch(ids);
		if(rowCount.length == ids.length) {
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean UpdateMessage(Message message) throws SQLException{
		int rowCount = messageDao.UpdateMessage(message);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public List<Message> queryAllMessages() throws SQLException {
		return messageDao.queryMessages();
	}
	
	public List<Message> QueryMessagesByCustomer(Customer customer) throws SQLException {
		return messageDao.queryMessagesByCustomerId(customer.getId());
	}
	
	public Message QueryMessageById(int id) throws SQLException {
		return messageDao.queryMessageById(id);
	}
}
