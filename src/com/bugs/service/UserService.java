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
	//管理员注册
    public  boolean    registerUser(User user) throws SQLException{
       //最新分析：插入数据的同时给邮箱发送激活码：
    	boolean isFlag = userDao.insertUserToDB(user);
    	//给邮箱发送激活码让用户去激活   激活(其实就是修改服务器的用户状态state)
    	System.out.println("==registerUser=="+user.getActiveCode());
    	SendJMail.sendMail(user.getEmail(), "欢迎注册本网站，请跳转到您的邮箱激活帐号再去登录<a "
    			+ "href='http://localhost:8080/BUGS/activeCodeServlet?activeCode="+user.getActiveCode()+"'>激活邮箱</a>");
    	return  isFlag;
    }
    
    //用户注册
    public  boolean    registerCustomer(Customer user) throws SQLException{
       //最新分析：插入数据的同时给邮箱发送激活码：
    	boolean isFlag = userDao.insertCustomerToDB(user);
    	//给邮箱发送激活码让用户去激活   激活(其实就是修改服务器的用户状态state)
    	System.out.println("==registerUser=="+user.getActiveCode());
    	SendJMail.sendMail(user.getEmail(), "欢迎注册本网站，请跳转到您的邮箱激活帐号再去登录<a "
    			+ "href='http://localhost:8080/BUGS/activeCodeServlet?activeCode="+user.getActiveCode()+"'>激活邮箱</a>");
    	return  isFlag;
    }
    //激活邮箱验证码
    public boolean activeCode(String code) throws SQLException{
    	 return   userDao.updateActiveCodeState(code);
    }
    
    //Ajax验证邮箱
    public String checkEmail(String email) throws SQLException{
    	//根据指定邮箱查找用户，如果用户存在，说明邮箱已经被注册过了，
    	User user = userDao.queryUserByEmail(email);
         if(user!=null){
        	 return  "邮箱已经被注册了";
         }
         return "邮箱可以正常使用";
         
    }
    
    
    
    
	//登录
    public  User   LoginUser(User user) throws SQLException, UserException{
    	User user2 = userDao.queryUserByUserNameAndPwd(user);
    	
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
    public  String  queryPwdByUserName(String name)throws SQLException{
    	return  userDao.queryPwdbyUserName(name);
    }

    //根据用户信息(用户名)修改密码
	public void  updatePwd(User user)throws SQLException,UserException {
		// TODO Auto-generated method stub
		int rowCount=   userDao.updatePwd(user);
		if(rowCount>0){
			
		}else{
			throw new UserException("修改密码失败！");
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
			throw new UserException("删除用户失败");
		}
	}
	public List<User> queryUserByKey(String keyString) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.queryUserByKey(keyString);
	}
}
