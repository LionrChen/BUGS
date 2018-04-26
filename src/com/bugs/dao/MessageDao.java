package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bugs.domain.Message;
import com.bugs.util.DBUtils;

public class MessageDao {
	public int InserMessage(Message message) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into message(userid,parentid,title,content) values(?,?,?,?)";
		int rowCount = queryRunner.update(sql, message.getUserid(),message.getParentid(),message.getTitle(),message.getContent());

		return rowCount;
	}
	public int DeleteMessage(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "delete from message where id =?";
		int rowCount = queryRunner.update(sql,id);

		return rowCount;
	}
	
	public int UpdateMessage(Message message) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "update message set title=?,content=? where id=?";
		int rowCount = queryRunner.update(sql,message.getTitle(),message.getContent(),message.getId());
		return rowCount;
	}
	
	public Message queryMessageById(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from message where id=?";
		return queryRunner.query(sql, new BeanHandler<Message>(Message.class),id);
	}
	
	public List<Message> queryMessages() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from message";
		return queryRunner.query(sql, new BeanListHandler<Message>(Message.class));
	}
	public List<Message> queryMessagesByCustomerId(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from message where userid=?";
		return queryRunner.query(sql, new BeanListHandler<Message>(Message.class),id);
	}
	public int[] DeleteBybatch(String[] ids) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		// ÅúÁ¿²Ù×÷
		String sql = "delete from message where  id = ?";
		Object[][] params = new Object[ids.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { ids[i] };
		}
		int[] rowCount = queryRunner.batch(sql, params);
		return rowCount;

	}
}
