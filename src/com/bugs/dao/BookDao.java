package com.bugs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bugs.domain.Book;
import com.bugs.util.DBUtils;

public class BookDao {
	public int InsertBookToDB(Book book) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into book values(?,?,?,?,?,?,?)";
		int rowCount = queryRunner.update(sql, book.getId(), book.getName(), book.getPrice(), book.getCategory(),
				book.getPnum(), book.getImgurl(), book.getDescription());

		return rowCount;
	}

	public List<Book> queryBookList() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from book";
		return queryRunner.query(sql, new BeanListHandler<Book>(Book.class));
	}
	
	public List<Book> queryBookListByRecommendBookID() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from book where id=any(select t.bookid from (select bookid from recommendbook limit 5) as t)";
		return queryRunner.query(sql, new BeanListHandler<Book>(Book.class));
	}
	// 批处理,删除多条数据
	public int[] DeleteBybatch(String[] ids) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		// 批量操作
		String sql = "delete from book where  id = ?";
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
	public List<Book> queryBookByManyCondition(String id, String category, String name, String minprice,
			String maxprice) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		
		String sql = "select * from book where 1=1";
		// 拼凑sql
		if ((null == id) | ("".equals(id))) {

		} else {
			sql = sql + " and id ="+id;
		}

		if ((null == category) | ("".equals(category))) {

		} else {
			sql = sql + " and category ="+"'"+category+"'";
		}
		
		if ((null == name) | ("".equals(name))) {

		} else {
			sql = sql + " and name like "+"'%"+name+"%'";
		}

		if ((null == minprice) | ("".equals(minprice))) {

		} else {
			sql = sql + " and price > "+Integer.parseInt(minprice);
		}
		if ((null == maxprice) | ("".equals(maxprice))) {

		} else {
			sql = sql + " and price < "+Integer.parseInt(maxprice);
		}
		System.out.println("===sql="+sql);
		return queryRunner.query(sql, new BeanListHandler<Book>(Book.class));
	}

	public Book queryBookById(String id) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="select * from book where id = ?";
		return queryRunner.query(sql, new BeanHandler<Book>(Book.class),id);
	}

	public int  updateBookById(Book book)throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql="update book set name= ?, price =?, pnum=?,category=?,imgurl=?, description=?  where id=  ?";
		int rowCount = queryRunner.update(sql,book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),
				book.getImgurl(),book.getDescription(),book.getId());
	  return rowCount;
	}
}
