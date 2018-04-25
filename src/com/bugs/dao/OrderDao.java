package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bugs.domain.order;
import com.bugs.util.DBUtils;

public class OrderDao {
	public int InsertOrder(order order) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into neworder(ordernumber,userid,bookid,num,payment,paymentstate,telephone,position) values(?,?,?,?,?,?,?,?)";
		int rowCount = queryRunner.update(sql,order.getOrdernumber(),order.getUserid(),order.getBookid(),order.getNum(),order.getPayment(),
				order.getPaymentState(),order.getTelephone(),order.getPosition());

		return rowCount;
	}
	
	public int DeleteOrder(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "delete from neworder where id =?";
		int rowCount = queryRunner.update(sql,id);

		return rowCount;
	}
	
	public List<order> queryOrders() throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from neworder";
		return	queryRunner.query(sql, new BeanListHandler<order>(order.class));
	}
	
	public List<order> queryOrdersByType(int type) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from neworder where type=?";
		return	queryRunner.query(sql, new BeanListHandler<order>(order.class),type);
	}
	
	public List<order> queryOrdersByCustomerId(int id) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from neworder where userid=?";
		return	queryRunner.query(sql, new BeanListHandler<order>(order.class),id);
	}
	
	
	public order queryOrderById(int id) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from neworder where id=?";
		return queryRunner.query(sql, new BeanHandler<order>(order.class),id);
	}
	
	public order queryOrderByOrdernumber(String ordernumber) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from neworder where ordernumber=?";
		return queryRunner.query(sql, new BeanHandler<order>(order.class),ordernumber);
	}
	
	public int updateOrderById(order order) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="update neworder set ordernumber=?,userid=?,bookid=?,num=?,payment=?,paymentstate=?,telephone=?,position=? where id=?";
		int rowCount = queryRunner.update(sql,order.getOrdernumber(),order.getUserid(),order.getBookid(),order.getNum(),order.getPayment(),
				order.getPaymentState(),order.getTelephone(),order.getPosition(),order.getId());
		return rowCount;
	}
	public int[] DeleteBybatch(String[] ids) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		// 批量操作
		String sql = "delete from neworder where  id = ?";
		// 参数2：Object[][] params
		// 第一维：表示删除的数量，个数，数组的长度 ,执行删除的次数
		// 第二维：删除的具体的条件的值
		Object[][] params = new Object[ids.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { ids[i] };
		}
		int[] rowCount = queryRunner.batch(sql, params);
		return rowCount;

	}
}
