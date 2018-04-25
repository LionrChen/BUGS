package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bugs.domain.Customer;
import com.bugs.util.DBUtils;

public class CustomerDao {
	
	public List<Customer> queryCustomersByType(int type) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from customer where type=?";
		return	queryRunner.query(sql, new BeanListHandler<Customer>(Customer.class),type);
	}
	public Customer queryCustomerById(int id) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from customer where id=?";
		return queryRunner.query(sql, new BeanHandler<Customer>(Customer.class),id);
	}
	public Customer queryCustomerByUserNamePWD(Customer customer) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from customer where username=? and password=?";
		return queryRunner.query(sql, new BeanHandler<Customer>(Customer.class),customer.username,customer.password);
	}
	public int updateCustomerById(Customer customer) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="update customer set email=?,username=?,password=?,gender=?,telephone=?,introduce=? where id=?";
		int rowCount = queryRunner.update(sql,customer.email,customer.username,customer.password,customer.gender,
				customer.telephone,customer.introduce,customer.id);
		return rowCount;
	}
	public int[] DeleteBybatch(String[] ids) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		// ��������
		String sql = "delete from customer where  id = ?";
		// ����2��Object[][] params
		// ��һά����ʾɾ��������������������ĳ��� ,ִ��ɾ���Ĵ���
		// �ڶ�ά��ɾ���ľ����������ֵ
		Object[][] params = new Object[ids.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { ids[i] };
		}
		int[] rowCount = queryRunner.batch(sql, params);
		return rowCount;

	}
}
