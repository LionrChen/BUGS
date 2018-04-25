package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bugs.domain.Address;
import com.bugs.domain.Customer;
import com.bugs.util.DBUtils;

public class AddressDao {
	public int InsertAddress(Address address,Customer customer) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into address(userid,province,city,area,detail,zipcode,phonenumber,contact) values(?,?,?,?,?,?,?,?)";
		int rowCount = queryRunner.update(sql, customer.id,address.getProvince(),address.getCity(),
				address.getArea(),address.getDetail(),address.getZipcode(),address.getPhonenumber(),address.getContact());

		return rowCount;
	}
	public int DeleteAddress(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "delete from address where id =?";
		int rowCount = queryRunner.update(sql,id);

		return rowCount;
	}
	
	public int UpdateAddress(Address address) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "update address set province=?,city=?,area=?,detail=?,zipcode=?,phonenumber=?,contact=? where id=?";
		int rowCount = queryRunner.update(sql,address.getProvince(),address.getCity(),address.getArea(),address.getDetail(),
				address.getZipcode(),address.getPhonenumber(),address.getContact(),address.getId());
		return rowCount;
	}
	
	public List<Address> queryAddress() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from address";
		return queryRunner.query(sql, new BeanListHandler<Address>(Address.class));
	}
	
	public List<Address> queryAddressByCustomerId(Customer customer) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from address where userid=?";
		return queryRunner.query(sql, new BeanListHandler<Address>(Address.class),customer.id);
	}
	
	public Address queryAddressById(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * address wallet where userid=?";
		return queryRunner.query(sql, new BeanHandler<Address>(Address.class),id);
	}
}
