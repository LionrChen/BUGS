package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.UserDao;
import com.bugs.domain.Customer;
import com.bugs.domain.User;
import com.bugs.exception.UserException;
import com.bugs.util.SendJMail;

public class UserService {
	UserDao userDao =new UserDao();
	//����Աע��
    public  boolean    registerUser(User user) throws SQLException{
       //���·������������ݵ�ͬʱ�����䷢�ͼ����룺
    	boolean isFlag = userDao.insertUserToDB(user);
    	//�����䷢�ͼ��������û�ȥ����   ����(��ʵ�����޸ķ��������û�״̬state)
    	System.out.println("==registerUser=="+user.getActiveCode());
    	SendJMail.sendMail(user.getEmail(), "��ӭע�᱾��վ������ת���������伤���ʺ���ȥ��¼<a "
    			+ "href='http://localhost:8080/BUGS/activeCodeServlet?activeCode="+user.getActiveCode()+"'>��������</a>");
    	return  isFlag;
    }
    
    //�û�ע��
    public  boolean    registerCustomer(Customer user) throws SQLException{
       //���·������������ݵ�ͬʱ�����䷢�ͼ����룺
    	boolean isFlag = userDao.insertCustomerToDB(user);
    	//�����䷢�ͼ��������û�ȥ����   ����(��ʵ�����޸ķ��������û�״̬state)
    	System.out.println("==registerUser=="+user.getActiveCode());
    	SendJMail.sendMail(user.getEmail(), "��ӭע�᱾��վ������ת���������伤���ʺ���ȥ��¼<a "
    			+ "href='http://localhost:8080/BUGS/activeCodeServlet?activeCode="+user.getActiveCode()+"'>��������</a>");
    	return  isFlag;
    }
    //����������֤��
    public boolean activeCode(String code) throws SQLException{
    	 return   userDao.updateActiveCodeState(code);
    }
    
    //Ajax��֤����
    public String checkEmail(String email) throws SQLException{
    	//����ָ����������û�������û����ڣ�˵�������Ѿ���ע����ˣ�
    	User user = userDao.queryUserByEmail(email);
         if(user!=null){
        	 return  "�����Ѿ���ע����";
         }
         return "�����������ʹ��";
         
    }
    
    
    
    
	//��¼
    public  User   LoginUser(User user) throws SQLException, UserException{
    	User user2 = userDao.queryUserByUserNameAndPwd(user);
    	
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
    public  String  queryPwdByUserName(String name)throws SQLException{
    	return  userDao.queryPwdbyUserName(name);
    }

    //�����û���Ϣ(�û���)�޸�����
	public void  updatePwd(User user)throws SQLException,UserException {
		// TODO Auto-generated method stub
		int rowCount=   userDao.updatePwd(user);
		if(rowCount>0){
			
		}else{
			throw new UserException("�޸�����ʧ�ܣ�");
		}
		
	}
	
	public List<User> queryUserList() throws SQLException{
		// TODO Auto-generated method stub
		return userDao.queryUserList();
	}
	
	public void deleteUserById(String id) throws SQLException, UserException {
		// TODO Auto-generated method stub
		int rowCount = userDao.deleteUserById(id);
		if(rowCount>0){
			
		}else{
			throw new UserException("ɾ���û�ʧ��");
		}
	}
	public List<User> queryUserByKey(String keyString) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.queryUserByKey(keyString);
	}
}
