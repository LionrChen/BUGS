package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.BookDao;
import com.bugs.domain.Book;
import com.bugs.exception.BookException;

public class BookService {
	BookDao bookDao =new BookDao();
	public void AddBook(Book book) throws SQLException, BookException{
		
		int rowCount = bookDao.InsertBookToDB(book);
		if(rowCount>0){
			
		}else{
			throw new BookException("ÃÌº”Õº È ß∞‹");
		}
	}
	public List<Book> queryBookList() throws SQLException{
		// TODO Auto-generated method stub
		
		return bookDao.queryBookList();
	}
	public List<Book> queryBookListByRecommendBookID() throws SQLException{
		// TODO Auto-generated method stub
		
		return bookDao.queryBookListByRecommendBookID();
	}
	public void  DelBybatch(String[] ids)throws SQLException, BookException{
		 int[] rowCount = bookDao.DeleteBybatch(ids);
		 if (rowCount.length>0) {
			
		}else{
			throw new BookException("…æ≥˝ ß∞‹");
		}
	}
	public List<Book> queryBookByManyCondition(String id, String category, String name, String minprice,
			String maxprice)throws SQLException {
		// TODO Auto-generated method stub
		return bookDao.queryBookByManyCondition( id,  category,  name,  minprice, maxprice);
	}
	public Book queryBookById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return bookDao.queryBookById(id);
	}
	public void updateBookById(Book book) throws SQLException, BookException{
		// TODO Auto-generated method stub
		 int rowCount = bookDao.updateBookById(book);
		 if(rowCount>0){
			 
		 }else {
			throw new BookException("–ﬁ∏ƒ ß∞‹");
		}
	}
}
