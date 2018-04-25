package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bugs.domain.Customer;
import com.bugs.domain.Wallet;
import com.bugs.util.DBUtils;

public class WalletDao {
	public int InsertWallet(Customer customer,Wallet wallet) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into wallet(userid,balance) values(?,?)";
		int rowCount = queryRunner.update(sql, customer.id,wallet.balance);

		return rowCount;
	}
	public int DeleteWallet(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "delete from wallet where id =?";
		int rowCount = queryRunner.update(sql,id);

		return rowCount;
	}
	
	public int UpdateWallet(int id, double balance) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "update wallet set balance=? where id=?";
		int rowCount = queryRunner.update(sql,balance,id);
		return rowCount;
	}
	
	public List<Wallet> queryWallet() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from wallet";
		return queryRunner.query(sql, new BeanListHandler<Wallet>(Wallet.class));
	}
	public Wallet queryWalletById(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from wallet where userid=?";
		return queryRunner.query(sql, new BeanHandler<Wallet>(Wallet.class),id);
	}
}
