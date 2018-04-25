package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bugs.domain.Customer;
import com.bugs.domain.User;
import com.bugs.util.DBUtils;

public class UserDao {
	//����Աע��
	public boolean insertUserToDB(User user) throws SQLException {
		// ���ݿ�������
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into user (email,username,password,gender,telephone,"
				+ "introduce,activeCode) values (?,?,?,?,?,?,?)";
		int rowCount = queryRunner.update(sql, user.getEmail(), user.getUsername(), user.getPassword(),
				user.getGender(), user.getTelephone(), user.getIntroduce(), user.getActiveCode());

		if (rowCount > 0) {
			return true;
		}
		return false;
	}
	//�û�ע��
	public boolean insertCustomerToDB(Customer user) throws SQLException {
		// ���ݿ�������
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into customer (email,username,password,gender,telephone,"
				+ "introduce,activeCode) values (?,?,?,?,?,?,?)";
		int rowCount = queryRunner.update(sql, user.getEmail(), user.getUsername(), user.getPassword(),
				user.getGender(), user.getTelephone(), user.getIntroduce(), user.getActiveCode());

		if (rowCount > 0) {
			return true;
		}
		return false;
	}
	//���¹���Աע��״̬
	public boolean updateActiveCodeState(String code) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="update user set state = ?  where activeCode = ?";
		int rowCount = queryRunner.update(sql,1,code);
		if (rowCount > 0) {
			return true;
		}
		return false;
	}
	//�����û�ע��״̬
	public boolean updateCustomerActiveCodeState(String code) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="update customer set state = ?  where activeCode = ?";
		int rowCount = queryRunner.update(sql,1,code);
		if (rowCount > 0) {
			return true;
		}
		return false;
	}
	//������Ա�Ƿ�ע��
	public User  queryUserByEmail(String email) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from user where email = ?";
		//����2��rsh��ResultSetHandler(�ӿ�)�����������   ������   
		//new BeanHandler<User>(User.class):��ʾ�Ѳ�ѯ���Ľ��ӳ�䵽ʵ�����Ӧ�ı�����(����)��
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class),email);
		 return user;	
	}
	//����û��Ƿ�ע��
	public User  queryCustomerByEmail(String email) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from customer where email = ?";
		//����2��rsh��ResultSetHandler(�ӿ�)�����������   ������   
		//new BeanHandler<User>(User.class):��ʾ�Ѳ�ѯ���Ľ��ӳ�䵽ʵ�����Ӧ�ı�����(����)��
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class),email);
		 return user;	
	}
	
	public  User  queryUserByUserNameAndPwd(User user) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from user where username =? and password = ?";
		System.out.println("===user===="+user.getUsername()+"===userpassword"+user.getPassword());
		User user2 =queryRunner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		return user2;
	}
	
	public  String  queryPwdbyUserName(String username)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select password from user where username = ? ";
		Object object = queryRunner.query(sql, new ScalarHandler(),username);
		return object.toString();
	}

	public int updatePwd(User user)throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="update user set  password=  ?  where username = ? ";
		int rowCount  = queryRunner.update(sql, user.getPassword(),user.getUsername());
		return rowCount;
	}
	
	public List<User> queryUserList()throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from user";
		return queryRunner.query(sql, new BeanListHandler<User>(User.class));
		
	}
	
	public int  deleteUserById(String id)throws SQLException  {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="delete  from user  where id = ?";
		int rowCount = queryRunner.update(sql,id);
		return rowCount;
	}
	
	
	public List<User> queryUserByKey(String keyString)throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from user where username like ?";
		return	queryRunner.query(sql, new BeanListHandler<User>(User.class),"%"+keyString+"%");
	}
}
