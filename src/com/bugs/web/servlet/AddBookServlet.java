package com.bugs.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bugs.domain.Book;
import com.bugs.exception.BookException;
import com.bugs.service.BookService;
import com.bugs.util.UploadUtils;

public class AddBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddBookServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> fileMap = UploadUtils.HandleFormByUpload(request, getServletContext());
		Book book=new Book();
		try {
			BeanUtils.populate(book, map);
			BeanUtils.populate(book, fileMap);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setId(UUID.randomUUID().toString());
		System.out.println("======"+book.toString());
		//2.处理业务逻辑
		 BookService bookService =new BookService();
		 try {
			bookService.AddBook(book);
			//表示图书添加成功
			//待定
			request.getRequestDispatcher("queryBookServlet").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//表示图书添加失败
			request.setAttribute("error_book_msg", e.getMessage());
			request.setAttribute("book", book);
			request.getRequestDispatcher("product/add.jsp").forward(request, response);
		} catch (BookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//表示图书添加失败
			request.setAttribute("error_book_msg", e.getMessage());
			request.setAttribute("book", book);
			request.getRequestDispatcher("product/add.jsp").forward(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
